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
    
    private void setNextExpGenerator() {
        nextLevelExp += Level*1000;
    }
    
    private void levelUp() {
        Level++;
        setNextExpGenerator();
    }
    
    private void upExp10() {
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
    
    public void setInit(int newLevel,long newExp,String newPassword) {
        Level = newLevel;
        Exp = newExp;
        Password = newPassword;
    }
    
    public String getUsername(){
        return Username;
    }
    
    public String getPassword(){
        return Password;
    }
    
    public Lokasi getLokasi(int i){
        if (i >= listLokasiTerkunjungi.size()){
            return null;
        }else{
            return listLokasiTerkunjungi.get(i);
        }
    }
    
    public boolean isListLocationMember(String Location){
        int i;
        for(i=0; i < listLokasiTerkunjungi.size(); i++){
            if (listLokasiTerkunjungi.get(i).getLocationName().equalsIgnoreCase(Location)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isListLocationsMember(Lokasi Location) {
        int i;
        for(i=0; i < listLokasiTerkunjungi.size(); i++){
            if (listLokasiTerkunjungi.get(i).getLocationName().equalsIgnoreCase(Location.getLocationName())){
                return true;
            }
        }
        return false;
    }
    
    public Pengguna(String initUsername,String initPassword) {
        Username = initUsername;
        Password = initPassword;
        Level = 1;
        Exp = 0;
        nextLevelExp = 1000;
        listLokasiTerkunjungi = new ArrayList<Lokasi>();
        
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
        upExp10();
    }
    
    public void addVisitedLocationData(Lokasi Location) {
        listLokasiTerkunjungi.add(Location);
    }
    
    public void userInfo() {
        System.out.println("----------------------INFO PENGGUNA----------------------");
        System.out.println("Username : "+Username);
        System.out.println("LEVEL "+Level);
        System.out.println("Exp : "+Exp);
        System.out.println("Next Level Experience : "+nextLevelExp);
        System.out.println(" : Lokasi yang sudah dikunjungi : ");
        if(listLokasiTerkunjungi.size() > 0) {
            for(Lokasi temp : listLokasiTerkunjungi) {
                System.out.println("        "+temp.getLocationName());
            }
        }
        else {
            System.out.println("  Belum ada Lokasi yang dikunjungi");
        }
        System.out.println("---------------------------------------------------------");
    }
}
