package com.yunusemre.ogrencikayit;

import java.io.Serializable;

public class Ogrenci implements Serializable {
    private String ogrenciAd;
    private String ogrenciSoyad;
    private int id;


    public Ogrenci(String ogrenciAd, String ogrenciSoyad, int id) {
        this.ogrenciAd = ogrenciAd;
        this.ogrenciSoyad = ogrenciSoyad;
        this.id = id;
    }


    public String getOgrenciAd() {
        return ogrenciAd;
    }

    public void setOgrenciAd(String ogrenciAd) {
        this.ogrenciAd = ogrenciAd;
    }

    public String getOgrenciSoyad() {
        return ogrenciSoyad;
    }

    public void setOgrenciSoyad(String ogrenciSoyad) {
        this.ogrenciSoyad = ogrenciSoyad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
