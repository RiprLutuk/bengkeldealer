package com.riprlutuk.bengkeldealer.data;

import java.io.Serializable;

/**
 * Created by Ripr Lutuk on 11/6/2017.
 */

public class DataDealer implements Serializable {
    private String nmdealer, merk, jmlkaryawan, haribukadlr, jambukadlr, alamatdlr, kelurahandlr, kecamatandlr, kdposdlr,
            kabupatendlr, telepondlr, pelayanandlr, fasilitasdlr, latitudedlr, longitudedlr, foto;

    public DataDealer(String nmdealer, String merk, String jmlkaryawan, String haribuka, String jambuka,
                      String alamat,String kelurahan, String kecamatan, String kdpos, String kabupaten, String telepon,
                      String pelayanan, String fasilitas, String latitude, String longitude, String foto) {

        this.nmdealer = nmdealer;
        this.merk = merk;
        this.jmlkaryawan = jmlkaryawan;
        this.haribukadlr = haribuka;
        this.jambukadlr = jambuka;
        this.alamatdlr = alamat;
        this.kelurahandlr = kelurahan;
        this.kecamatandlr = kecamatan;
        this.kdposdlr = kdpos;
        this.kabupatendlr = kabupaten;
        this.telepondlr = telepon;
        this.pelayanandlr = pelayanan;
        this.fasilitasdlr = fasilitas;
        this.latitudedlr = latitude;
        this.longitudedlr = longitude;
        this.foto = foto;
    }

    public String getNmdealer() {
        return nmdealer;
    }

    public String getMerk() {
        return merk;
    }

    public String getJmlkaryawan() {
        return jmlkaryawan;
    }

    public String getHaribukadlr() {
        return haribukadlr;
    }

    public String getJambukadlr() {
        return jambukadlr;
    }

    public String getAlamatdlr() {
        return alamatdlr;
    }

    public String getKelurahandlr() {
        return kelurahandlr;
    }

    public String getKecamatandlr() {
        return kecamatandlr;
    }

    public String getKdposdlr() {
        return kdposdlr;
    }

    public String getKabupatendlr() {
        return kabupatendlr;
    }

    public String getTelepondlr() {
        return telepondlr;
    }

    public String getPelayanandlr() {
        return pelayanandlr;
    }

    public String getFasilitasdlr() {
        return fasilitasdlr;
    }

    public String getLatitudedlr() {
        return latitudedlr;
    }

    public String getLongitudedlr() {
        return longitudedlr;
    }

    public String getFoto() {
        return foto;
    }

}