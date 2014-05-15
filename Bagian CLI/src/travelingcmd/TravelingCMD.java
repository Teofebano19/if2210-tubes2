/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelingcmd;

import com.sun.org.apache.bcel.internal.generic.GOTO;
import java.util.*;

/**
 *
 * @author Home Basic
 */
public class TravelingCMD {
    private static Pengguna P;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Quest Q;
        Q = new Quest("Jakarta");
        Q.setArea(Q.getArea().toUpperCase());
        Q.addLocations("Ancol,Monas,Museum Geje,Kebun Binatang,Kampung Deret,Pulau Seribu");
        Q.editDescription("Ibu Kota Negara Indonesia");
        
        Scanner cin = new Scanner(System.in);
        String Input;
        
        System.out.println("---------------------MENU AWAL--------------------------");
        System.out.println("Register/Login? "); Input = cin.nextLine();
        System.out.println("-------------------------------------------------------");
        
        while(!Input.equalsIgnoreCase("Login")) {
            if(Input.equalsIgnoreCase("Register")) {
                Register();
            }
            System.out.println("---------------------MENU AWAL--------------------------");
            System.out.println("Register/Login? "); Input = cin.nextLine();
            System.out.println("-------------------------------------------------------");
        }
        
        Login();
        
        Q.showQuest();
        
        P.userInfo();
        
        String Loc;
        
        System.out.println("---------------------------------------------------------");
        System.out.println("Masukan Input Lokasi yang ingin dikunjungi di Area "+Q.getArea());
        System.out.println("Akhiri Input dengan \"END\" ");
        Loc = cin.nextLine();
        
        while(!Loc.equalsIgnoreCase("END")) {
            if(Q.isLocationsMember(Loc)) {
                if(P.isListLocationMember(Loc)) {
                    System.out.println("Lokasi sudah ada di Visited List anda");
                }
                else {
                    P.addVisitedLocation(new Lokasi(Loc));
                    System.out.println("--------------------------------------");
                    System.out.println("|Lokasi berhasil ditambahkan,EXP NAIK|");
                    System.out.println("--------------------------------------");
                    P.userInfo();
                }
            }
            else {
                System.out.println("Lokasi tidak ada di Area "+Q.getArea());
            }
            Loc = cin.nextLine();
        }
        
        P.userInfo();
        UserData.writeUser(P);
        System.out.println("---------------------------------------------------------");
        System.out.println("PROGRAM SELESAI");
    }
    
    public static void Register() {
        Scanner cin = new Scanner(System.in);
        String Username,Password;
        System.out.println("-------------REGISTER---------------");
        System.out.print("UserName : "); Username = cin.nextLine();
        System.out.print("Password : "); Password = cin.nextLine();
        System.out.println("------------------------------------");
        if(UserData.bacaFile(new Pengguna(Username,Password))) {
            System.out.println("Username "+Username+" Sudah digunakan");
        }
        else {
            UserData.writeUser(new Pengguna(Username,Password));
            System.out.println("Register Berhasil");
        }
    }
    
    public static void Login() {
        String Username,Password;
        Scanner cin = new Scanner(System.in);
        System.out.println("----------------Login FORM------------------");
        System.out.print("Username : "); Username = cin.nextLine();
        System.out.print("Password : "); Password = cin.nextLine();
        System.out.println("-------------------------------------------");
        P = new Pengguna(Username,Password);
        
        while (!UserData.bacaFile(P)) {
            System.out.println("------------Username/Password Salah------------");
            System.out.println("Login FORM");
            System.out.print("Username : "); Username = cin.nextLine();
            System.out.print("Password : "); Password = cin.nextLine();
            System.out.println("-----------------------------------------------");
            P = new Pengguna(Username,Password);
        }
        
        System.out.println("----------------");
        System.out.println("|Berhasil Masuk|");
        System.out.println("----------------");
    }
}
