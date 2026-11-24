package com.example.seminar4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Minimarket implements Parcelable {
    private String nume;
    private boolean esteDeschis;
    private String marime;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public boolean isEsteDeschis() {
        return esteDeschis;
    }

    public void setEsteDeschis(boolean esteDeschis) {
        this.esteDeschis = esteDeschis;
    }

    public String getMarime() {
        return marime;
    }

    public void setMarime(String marime) {
        this.marime = marime;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isAreLivrare() {
        return areLivrare;
    }

    public void setAreLivrare(boolean areLivrare) {
        this.areLivrare = areLivrare;
    }

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

    protected Minimarket(Parcel in) {
        nume = in.readString();
        esteDeschis = in.readByte() != 0;
        marime = in.readString();
        categorie = in.readString();
        rating = in.readFloat();
        areLivrare = in.readByte() != 0;
    }

    public static final Creator<Minimarket> CREATOR = new Creator<Minimarket>() {
        @Override
        public Minimarket createFromParcel(Parcel in) {
            return new Minimarket(in);
        }

        @Override
        public Minimarket[] newArray(int size) {
            return new Minimarket[size];
        }
    };

    @Override
    public String toString() {
        return "Minimarket: " + nume + "\n" +
                "Categorie: " + categorie + "\n" +
                "Marime: " + marime + "\n" +
                "Deschis: " + (esteDeschis ? "Da" : "Nu") + "\n" +
                "Livrare: " + (areLivrare ? "Da" : "Nu") + "\n" +
                "Rating: " + rating + " stele";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeByte((byte) (esteDeschis ? 1 : 0));
        dest.writeString(marime);
        dest.writeString(categorie);
        dest.writeFloat(rating);
        dest.writeByte((byte) (areLivrare ? 1 : 0));
    }
}