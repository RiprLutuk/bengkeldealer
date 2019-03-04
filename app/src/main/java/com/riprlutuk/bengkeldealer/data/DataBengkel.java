package com.riprlutuk.bengkeldealer.data;

import java.io.Serializable;

public class DataBengkel implements Serializable {

    private String nmbengkel, jmlmontir, haribuka, jambuka, alamat, kelurahan, kecamatan, kdpos, kabupaten, telepon, lati, longi, jarak, foto;


    public DataBengkel() {
    }

    public DataBengkel(String nmbengkel, String jmlmontir, String haribuka, String jambuka, String alamat, String kelurahan, String kecamatan, String kdpos, String kabupaten, String telepon, String lati, String longi, String jarak, String foto) {
        this.nmbengkel = nmbengkel;
        this.jmlmontir = jmlmontir;
        this.haribuka = haribuka;
        this.jambuka = jambuka;
        this.alamat = alamat;
        this.kelurahan = kelurahan;
        this.kecamatan = kecamatan;
        this.kdpos = kdpos;
        this.kabupaten = kabupaten;
        this.telepon = telepon;
        this.lati = lati;
        this.longi = longi;
        this.jarak = jarak;
        this.foto = foto;
    }

    public String getNmbengkel() {
        return nmbengkel;
    }
    public void setNmbengkel(String nmbengkel){
        this.nmbengkel = nmbengkel;
    }

    public String getJmlMontir() {
        return jmlmontir;
    }
    public void setJmlmontir(String jmlmontir){
        this.jmlmontir = jmlmontir;
    }

    public String getHariBuka() {
        return haribuka;
    }
    public void setHaribuka(String haribuka){
        this.haribuka = haribuka;
    }

    public String getJambuka() {
        return jambuka;
    }
    public void setJambuka(String jambuka){
        this.jambuka = jambuka;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getKelurahan() {
        return kelurahan;
    }
    public void setKelurahan(String kelurahan){
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }
    public void setKecamatan(String kecamatan){
        this.kecamatan = kecamatan;
    }

    public String getKdpos() {
        return kdpos;
    }
    public void setKdpos(String kdpos){
        this.kdpos = kdpos;
    }

    public String getKabupaten() {
        return kabupaten;
    }
    public void setKabupaten(String kabupaten){
        this.kabupaten = kabupaten;
    }

    public String getTelepon() {
        return telepon;
    }
    public void setTelepon(String telepon){
        this.telepon = telepon;
    }

    public String getLati(){
        return lati;
    }
    public  void setLati(String lati){
        this.lati = lati;
    }

    public  String getLongi(){
        return longi;
    }
    public void setLongi(String longi){
        this.longi = longi;
    }

    public String getJarak() {
        return jarak;
    }
    public void setJarak(String jarak){
        this.jarak = jarak;
    }

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto){
        this.foto = foto;
    }
}
