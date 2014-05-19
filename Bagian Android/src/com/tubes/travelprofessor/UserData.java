package com.tubes.travelprofessor;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;

public class UserData {
    
    public static void bacaFile(Context context, Pengguna pengguna, boolean l) throws LoginFailedException{
         try {
 //, filename
            File fXmlFile = new File(context.getFilesDir(),  pengguna.getUsername() +  "_data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("User");
            Node nNode = nList.item(0);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                pengguna.setInit(Integer.parseInt(eElement.getElementsByTagName("level").item(0).getTextContent()), Long.parseLong(eElement.getElementsByTagName("exp").item(0).getTextContent()), eElement.getElementsByTagName("password").item(0).getTextContent());
            }

            if (!l) {        
	            NodeList nLokasi = doc.getElementsByTagName("lokasi");
	
	            for (int temp = 0; temp < nLokasi.getLength(); temp++) {
	
	                    Node lok = nLokasi.item(temp);
	
	                    if (lok.getNodeType() == Node.ELEMENT_NODE) {
	
	                            Element lokas = (Element) lok;
	                            pengguna.addVisitedLocation(new Lokasi(lokas.getTextContent()));
	
	                    }
	            }
            }
        } catch (Exception e) {
        	throw new LoginFailedException();
        	//e.printStackTrace();
        }
    }
    
    public static void writeUser(Context context, Pengguna pengguna){
    
      try {
        
          int i = 0;
          Lokasi lok;
          Element lokE;
                  
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("USERDATA");
        doc.appendChild(rootElement);

        Element usrE = doc.createElement("User");
        rootElement.appendChild(usrE);

        
        Attr attr = doc.createAttribute("username");
        attr.setValue(pengguna.getUsername());
        usrE.setAttributeNode(attr);

        Element level = doc.createElement("level");
        level.appendChild(doc.createTextNode(pengguna.getLevel() + ""));
        usrE.appendChild(level);

        Element exp = doc.createElement("exp");
        exp.appendChild(doc.createTextNode(pengguna.getExp() + ""));
        usrE.appendChild(exp);

        
        Element passwd = doc.createElement("password");
        passwd.appendChild(doc.createTextNode(pengguna.getPassword()));
        usrE.appendChild(passwd);
        
        lok = pengguna.getLokasi(i++);
        while (lok != null){
            lokE = doc.createElement("lokasi");
            lokE.appendChild(doc.createTextNode(lok.getLocationName()));
            rootElement.appendChild(lokE);
            
            lok = pengguna.getLokasi(i++);
        }
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(context.getFilesDir(),  pengguna.getUsername() +  "_data.xml"));

        // Output to console for testing
        // StreamResult result = new StreamResult(System.out);

        transformer.transform(source, result);

        System.out.println("File saved!");

      }catch (Exception x) {
          x.printStackTrace();
      } 
    }
}
