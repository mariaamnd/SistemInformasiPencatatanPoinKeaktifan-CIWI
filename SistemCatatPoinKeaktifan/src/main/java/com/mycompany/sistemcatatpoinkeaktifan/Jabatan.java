package com.mycompany.sistemcatatpoinkeaktifan;


public class Jabatan {
    private int IDJabatan;
    private String JenisJabatan;
    private int PoinIntern;
    private int PoinDIY;
    private int PoinNasional;
    private int PoinInternasional;
    
    public Jabatan(int IDJabatan, String JenisJabatan, int PoinIntern, int PoinDIY, int PoinNasional, int PoinInternasional){
        this.IDJabatan = IDJabatan;
        this.JenisJabatan = JenisJabatan;
        this.PoinIntern = PoinIntern;
        this.PoinDIY = PoinDIY;
        this.PoinNasional = PoinNasional;
        this.PoinInternasional = PoinInternasional;
    }

    public int getIDJabatan() {
        return IDJabatan;
    }

    public String getJenisJabatan() {
        return JenisJabatan;
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
