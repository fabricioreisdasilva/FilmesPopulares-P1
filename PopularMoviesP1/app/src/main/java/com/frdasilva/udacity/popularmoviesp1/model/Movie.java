package com.frdasilva.udacity.popularmoviesp1.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fabricio Reis on 06/01/2017.
 */

public class Movie implements Parcelable {
    private String titulo;
    private String dataLancamento;
    private String sinopse;
    private String cartazFilme;
    private double mediaVotos;

    public Movie(String titulo, String dataLancamento, String sinopse, String cartazFilme, double mediaVotos) {
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.sinopse = sinopse;
        this.cartazFilme = cartazFilme;
        this.mediaVotos = mediaVotos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getCartazFilme() {
        return cartazFilme;
    }

    public void setCartazFilme(String cartazFilme) {
        this.cartazFilme = cartazFilme;
    }

    public double getMediaVotos() {
        return mediaVotos;
    }

    public void setMediaVotos(double mediaVotos) {
        this.mediaVotos = mediaVotos;
    }

    protected Movie(Parcel in) {
        titulo = in.readString();
        dataLancamento = in.readString();
        sinopse = in.readString();
        cartazFilme = in.readString();
        mediaVotos = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(dataLancamento);
        dest.writeString(sinopse);
        dest.writeString(cartazFilme);
        dest.writeDouble(mediaVotos);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
