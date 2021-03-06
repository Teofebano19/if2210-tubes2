package travelingcmd;

import java.util.ArrayList;
import java.util.List;

public class Quest {
    private String Area;
    private String Deskripsi;
    private List<Lokasi> listLokasi;

    public Quest(String initArea) {
        Area = initArea;
        Deskripsi = "Tidak ada deskripsi";
        listLokasi = new ArrayList<Lokasi>();
    }

    public void copyQuest(Quest Q) {
        Area = Q.Area;
        Deskripsi = Q.Deskripsi;
        for(Lokasi temp : Q.listLokasi)
            listLokasi.add(temp);
    }
    
    public void setArea(String newArea) {
        Area = newArea;
    }

    public String getArea() {
        return Area;
    }

    public String getDescription() {
        return Deskripsi;
    }

    public void showDescription() {
        System.out.println(Deskripsi);
    }
    public void editDescription(String newDescription) {
        Deskripsi = newDescription;
    }

    public List<Lokasi> getLocation() {
        return listLokasi;
    }
    
    public void showLocations() {
        for(Lokasi tempLokasi : listLokasi) {
            tempLokasi.showLocation();
        }
    }

    public void addLocations(String newLocations) {
        String[] Locations;
        Locations = new String[newLocations.split(",").length];
        Locations = newLocations.split("[,]+");
        for(int i=1; i<=Locations.length; i++) {
            listLokasi.add(new Lokasi(Locations[i-1]));
        }
    }
    
    public boolean isLocationsMember(String Location) {
        int i;
        for(i=0; i < listLokasi.size(); i++){
            if (listLokasi.get(i).getLocationName().equalsIgnoreCase(Location)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isLocationsMember(Lokasi Location) {
        int i;
        for(i=0; i < listLokasi.size(); i++){
            if (listLokasi.get(i).getLocationName().equalsIgnoreCase(Location.getLocationName())){
                return true;
            }
        }
        return false;
    }
            
    public void showQuest() {
        System.out.println("----------------------INFO QUEST----------------------");
        System.out.println("            "+"QUEST AREA "+Area);
        System.out.println("Deskripsi : ");
        System.out.println("            "+Deskripsi);
        System.out.println("Lokasi    : ");
        for(Lokasi tempLokasi : listLokasi) {
            System.out.print("            ");
            tempLokasi.showLocation();
        }
        System.out.println("------------------------------------------------------");
    }
   
    /*
    public void showDescriptions() {
        int longestLength;
        int x;
        
        longestLength = 0;
        String[] Descriptions = new String[Deskripsi.split("[.]+").length];
        
        Descriptions = Deskripsi.split("[.]+");
        
        for(int i=1; i<=Descriptions.length; i++) {
            if(Descriptions[i-1].length() > longestLength) {
                longestLength = Descriptions[i-1].length();
            }
        }
        
        x = (longestLength/10) + ((longestLength%10 > 0)? 1 : 0);
        
        System.out.printf("%c",201);
        for(int i=1; i<=x*10; i++) {
            System.out.printf("%c",205);
        }
        System.out.printf("%c",187);
        
        for(int i=1; i<=Descriptions.length; i++) {
            System.out.printf("%c",186);
            System.out.print(Descriptions[i-1]);
            for(int j=1; j<=x*10-Descriptions[i-1].length(); j++)
            System.out.print(" ");
            System.out.printf("%c",186);
            System.out.println();
        }
        
        System.out.printf("%c",200);
        for(int i=1; i<=x*10; i++) {
            System.out.printf("%c",205);
        }
        System.out.printf("%c",188);
    } */
}
