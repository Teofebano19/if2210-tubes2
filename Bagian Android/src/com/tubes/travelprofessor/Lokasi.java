package com.tubes.travelprofessor;


public class Lokasi {
    private String NamaLokasi;
    private float Lat;
    private float Lng;
    
    private float Lat2;
    private float Lng2;
    
    public float getLat(){
    	return Lat;
    }
    
    public float getLng(){
    	return Lng;
    }
    
    public float getLat2(){
    	return Lat2;
    }
    
    public float getLng2(){
    	return Lng2;
    }
    
    
    
    public Lokasi(String initName) {
        NamaLokasi = initName;
        Lat = 0;
        Lng = 0;
        Lat2 = 0;
        Lng2 = 0;
    }
    
    public Lokasi(String initName, float Lat, float Lng, float Lat2, float Lng2){
    	NamaLokasi = initName;
        this.Lat = Lat;
        this.Lng = Lng;
        this.Lat2 = Lat2;
        this.Lng2 = Lng2;
    }
    
    public void copyLocation(Lokasi K) {
        NamaLokasi = K.NamaLokasi;
        Lat = K.Lat;
        Lng = K.Lng;
        Lat2 = K.Lat2;
        Lng2 = K.Lng2;
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
