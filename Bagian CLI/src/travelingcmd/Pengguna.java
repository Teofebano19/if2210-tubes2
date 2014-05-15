/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelingcmd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home Basic
 */
public class Pengguna {
    private final String Username;
    private String Password;
    private int Level;
    private long Exp;
    private long nextLevelExp;
    private List<Lokasi> listLokasiTerkunjungi;
    
    private static int JumlahPengguna = 0;
    
    private void setNextExpGenerator() {
        nextLevelExp += Level*1000;
    }
    
    private void levelUp() {
        Level++;
        setNextExpGenerator();
    }
    
    public Pengguna(String initUsername,String initPassword) {
        Username = initUsername;
        Password = initPassword;
        Level = 1;
        Exp = 0;
        nextLevelExp = 1000;
        listLokasiTerkunjungi = new ArrayList<Lokasi>();
        
        JumlahPengguna++;
    }
    
    public void changePassword(String newPassword) {
        Password = newPassword;
    }
    
    public int getLevel() {
        return Level;
    }
    
    public long getExp() {
        return Exp;
    }
    
    public long getNextLevelExp() {
        return nextLevelExp;
    }
    
    public void upExp10() {
        long temp = Exp+10;
        if(temp > nextLevelExp) {
            long nextLevelExptemp = nextLevelExp;
            levelUp();
            Exp = 0;
            upExp10();
        }
        else {
            Exp = temp;
        }
    }
    
    /*
    public void upExp(long Experience) {
        long temp = Exp+Experience;
        if(temp > nextLevelExp) {
            long nextLevelExptemp = nextLevelExp;
            levelUp();
            Exp = 0;
            upExp(temp-nextLevelExptemp);
        }
        else {
            Exp = temp;
        }
    } */
    
    public void addVisitedLocation(Lokasi Location) {
        listLokasiTerkunjungi.add(Location);
    }
    
    public void userInfo() {
        System.out.println("Username : "+Username);
        System.out.println("LEVEL "+Level);
        System.out.println("Exp : "+Exp);
        System.out.println("Next Level Experience : "+nextLevelExp);
    }
}
