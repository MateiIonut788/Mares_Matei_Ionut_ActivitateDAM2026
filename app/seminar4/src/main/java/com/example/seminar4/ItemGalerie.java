package com.example.seminar4;

import android.graphics.Bitmap;

public class ItemGalerie {
    private String urlImagine;
    private String descriere;
    private String urlPagina;
    private Bitmap imagine;

    public ItemGalerie(String urlImagine, String descriere, String urlPagina) {
        this.urlImagine = urlImagine;
        this.descriere = descriere;
        this.urlPagina = urlPagina;
    }

    public String getUrlImagine() {
        return urlImagine;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getUrlPagina() {
        return urlPagina;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }
}