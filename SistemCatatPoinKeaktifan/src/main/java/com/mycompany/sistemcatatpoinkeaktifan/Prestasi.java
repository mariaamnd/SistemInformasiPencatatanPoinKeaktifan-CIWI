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
    private int PoinIntern;
    private int PoinDIY;
    private int PoinNasional;
    private int PoinInternasional;

    public Prestasi(int IDPrestasi, String JenisPrestasi, int PoinIntern, int PoinDIY, int PoinNasional, int PoinInternasional) {
        this.IDPrestasi = IDPrestasi;
        this.JenisPrestasi = JenisPrestasi;
        this.PoinIntern = PoinIntern;
        this.PoinDIY = PoinDIY;
        this.PoinNasional = PoinNasional;
        this.PoinInternasional = PoinInternasional;
    }

    public int getIDPrestasi() {
        return IDPrestasi;
    }

    public String getJenisPrestasi() {
        return JenisPrestasi;
    }

    public int getPoinIntern() {
        return PoinIntern;
    }

    public int getPoinDIY() {
        return PoinDIY;
    }
    
    public int getPoinNasional() {
        return PoinNasional;
    }
    
    public int getPoinInternasional() {
        return PoinInternasional;
    }

    
         
}
