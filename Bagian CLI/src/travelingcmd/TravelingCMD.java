/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelingcmd;

/**
 *
 * @author Home Basic
 */
public class TravelingCMD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Quest Q;
        Q = new Quest("Jakarta");
        Q.setArea(Q.getArea().toUpperCase());
        Q.addLocations("Ancol,Monas,Museum Geje");
        Q.editDescription("Ibu Kota Negara Indonesia");
        Q.showQuest();
        System.out.println("---------------------");
        
        Pengguna P;
        P = new Pengguna("Konda","CapeDah");
        P.userInfo();
        System.out.println("---------------------");
        P.addVisitedLocation(new Lokasi("Ancol"));
        P.upExp10();
        P.userInfo();
        System.out.println("---------------------");
    }  
}
