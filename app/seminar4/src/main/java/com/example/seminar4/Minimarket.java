package com.example.seminar4;

import java.io.Serializable;

public class Minimarket implements Serializable {
    private String nume;
    private boolean esteDeschis;
    private String marime;
    private String categorie;
    private float rating;
    private boolean areLivrare;

    public Minimarket(String nume, boolean esteDeschis, String marime, String categorie, float rating, boolean areLivrare) {
        this.nume = nume;
        this.esteDeschis = esteDeschis;
        this.marime = marime;
        this.categorie = categorie;
        this.rating = rating;
        this.areLivrare = areLivrare;
    }

    @Override
    public String toString() {
        return "Minimarket: " + nume + "\n" +
                "Categorie: " + categorie + "\n" +
                "Mărime: " + marime + "\n" +
                "Deschis: " + (esteDeschis ? "Da" : "Nu") + "\n" +
                "Livrare: " + (areLivrare ? "Da" : "Nu") + "\n" +
                "Rating: " + rating + " stele";
    }
}