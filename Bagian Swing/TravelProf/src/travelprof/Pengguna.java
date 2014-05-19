/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelprof;


import java.util.ArrayList;
import java.util.List;


public class Pengguna {
    private final String Username;
    private String Password;
    private int Level;
    private long Exp;
    private long nextLevelExp;
    private List<Lokasi> listLokasiTerkunjungi;
    
    public void setInit(int lv, long exp, String pass){
        Level = lv;
        Exp = exp;
        Password = pass;
    }
    
    public String getUsername(){
        return Username;
    }
    
    public String getPassword(){
        return Password;
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
    
    public Lokasi getLokasi(int i){
        if (i >= listLokasiTerkunjungi.size()){
            return null;
        }else{
            return listLokasiTerkunjungi.get(i);
        }
    }
    
    public boolean isLokValid(String lok){
        int i;
        
        for(i=0; i < listLokasiTerkunjungi.size(); i++){
            if (listLokasiTerkunjungi.get(i).getLocationName().equalsIgnoreCase(lok)){
                return true;
            }
        }
        
        return false;
    }
    
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
        if(isLvlUp(10)) {
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
    
    public long getRemainingExp(){
        return (nextLevelExp - Exp);
    }
    
    public boolean isLvlUp (long addExp){
        long temp = Exp + addExp;
        if (temp >= nextLevelExp){
            return true;
        }
        else{
            return false;
        }
    }
}
