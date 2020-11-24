/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

/**
 *
 * @author acer
 */
public class Prestasi {
    private int IDPrestasi;
    private String JenisPrestasi;
    private String TingkatPrestasi;
    private String Keterangan;
    private String Tanggal;
    private int Poin;

    public Prestasi(int IDPrestasi, String JenisPrestasi, String TingkatPrestasi, String Keterangan, String Tanggal, int Poin) {
        this.IDPrestasi = IDPrestasi;
        this.JenisPrestasi = JenisPrestasi;
        this.TingkatPrestasi = TingkatPrestasi;
        this.Keterangan = Keterangan;
        this.Tanggal = Tanggal;
        this.Poin = Poin;
    }

    public int getIDPrestasi() {
        return IDPrestasi;
    }

    public String getJenisPrestasi() {
        return JenisPrestasi;
    }

    public String getTingkatPrestasi() {
        return TingkatPrestasi;
    }

    public String getKeterangan() {
        return Keterangan;
    }
    
    public String getTanggal() {
        return Tanggal;
    }
    
    public int getPoin() {
        return Poin;
    }

    
         
}
