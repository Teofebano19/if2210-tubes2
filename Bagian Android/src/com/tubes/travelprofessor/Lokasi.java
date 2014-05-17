package com.tubes.travelprofessor;


public class Lokasi {
    private String NamaLokasi;
    private float Lat;
    private float Lng;
    
    private float Lat2;
    private float Lng2;
    
    public Lokasi(String initName) {
        NamaLokasi = initName;
        Lat = 0;
        Lng = 0;
    }
    
    public void copyLocation(Lokasi K) {
        NamaLokasi = K.NamaLokasi;
        Lat = K.Lat;
        Lng = K.Lng;
    }
    
    public String getLocationName() {
        return NamaLokasi;
    }
    
    public void setKoordinat(String Koordinat) {
        Lat = Float.parseFloat(Koordinat.split("[,]+")[0]);
        Lng = Float.parseFloat(Koordinat.split("[,]+")[1]);
    }

    public String getKoordinat() {
        return ("("+Lat+","+Lng+")");
    }
    
    public void showLocation() {
        System.out.println(NamaLokasi);
    }
}
