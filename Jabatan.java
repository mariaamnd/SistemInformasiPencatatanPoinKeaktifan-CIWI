package com.mycompany.sistemcatatpoinkeaktifan;


public class Jabatan {
    private int IDJabatan;
    private String Organisasi;
    private String Tingkat;
    private String JenisJabatan;
    private String Keterangan;
    private String MasaJabatan;
    private int Poin;
    
    
    public Jabatan(int IDJabatan, String Organisasi, String Tingkat, String JenisJabatan, String Keterangan, String MasaJabatan, int Poin){
        this.IDJabatan = IDJabatan;
        this.Organisasi = Organisasi;
        this.Tingkat = Tingkat;
        this.JenisJabatan = JenisJabatan;
        this.Keterangan = Keterangan;
        this.MasaJabatan = MasaJabatan;
        this.Poin = Poin;
    }

     public int getIDJabatan() {
        return IDJabatan;
    }

    public String getOrganisasi() {
        return Organisasi;
        
    }
    
    public String getTingkat() {
        return Tingkat;
        
    }
    
    public String getJenisJabatan() {
        return JenisJabatan;
    }   

    public String getKeterangan() {
        return Keterangan;
    }

    public String getMasaJabatan() {
        return MasaJabatan;
    }

    public int getPoin() {
        return Poin;
    }
    
    
}
