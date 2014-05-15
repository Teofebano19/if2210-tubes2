/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tempLate file, choose Tools | TempLates
 * and open the tempLate in the editor.
 */

package travelingcmd;

/**
 *
 * @author Home Basic
 */
public class Lokasi {
    private String NamaLokasi;
    private float Lat;
    private float Lng;
    private boolean Terkunjungi;
    
    public Lokasi(String initName) {
        NamaLokasi = initName;
        Lat = 0;
        Lng = 0;
        Terkunjungi = false;
    }
    
    public void copyLocation(Lokasi K) {
        NamaLokasi = K.NamaLokasi;
        Lat = K.Lat;
        Lng = K.Lng;
        Terkunjungi = K.Terkunjungi;
    }
    
    public void setKoordinat(String Koordinat) {
        Lat = Float.parseFloat(Koordinat.split("[,]+")[0]);
        Lng = Float.parseFloat(Koordinat.split("[,]+")[1]);
    }

    public String getKoordinat() {
        return ("("+Lat+","+Lng+")");
    }

    public boolean getStatus() {
        return Terkunjungi;
    }

    public void setStatus(boolean Status) {
        Terkunjungi = Status;
    }
    
    public void showLocation() {
        System.out.println(NamaLokasi);
    }
}
