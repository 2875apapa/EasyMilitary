package com.apapa.classes;

import java.util.List;

public class TipoServizio
{
    private  int id;
    private String nome_servizio;
    private boolean armato;
    private int durata;
    private int riposo;
    private int num_militari;
    private int num_mezzi;
    private List<Ruolo> lruoli;
    private List<Giorno> lgiorni;

    public TipoServizio(String nome_servizio, boolean armato, int durata, int riposo, int num_militari, int num_mezzi, List<Ruolo> lruoli, List<Giorno> lgiorni) {
        this.nome_servizio = nome_servizio;
        this.armato = armato;
        this.durata = durata;
        this.riposo = riposo;
        this.num_militari = num_militari;
        this.num_mezzi = num_mezzi;
        this.lruoli = lruoli;
        this.lgiorni = lgiorni;
    }

    public TipoServizio()
    {
    }

    public String getNome_servizio() {
        return nome_servizio;
    }

    public void setNome_servizio(String nome_servizio) {
        this.nome_servizio = nome_servizio;
    }

    public boolean isArmato() {
        return armato;
    }

    public void setArmato(boolean armato) {
        this.armato = armato;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public int getRiposo() {
        return riposo;
    }

    public void setRiposo(int riposo) {
        this.riposo = riposo;
    }

    public int getNum_militari() {
        return num_militari;
    }

    public void setNum_militari(int num_militari) {
        this.num_militari = num_militari;
    }

    public int getNum_mezzi() {
        return num_mezzi;
    }

    public void setNum_mezzi(int num_mezzi) {
        this.num_mezzi = num_mezzi;
    }

    public List<Ruolo> getLruoli() {
        return lruoli;
    }

    public List<Giorno> getLgiorni() {
        return lgiorni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLruoli(List<Ruolo> lruoli) {
        this.lruoli = lruoli;
    }

    public void setLgiorni(List<Giorno> lgiorni) {
        this.lgiorni = lgiorni;
    }

    @Override
    public String toString() {
        return "TipoServizio{" +
                "nome_servizio='" + nome_servizio + '\'' +
                ", armato=" + armato +
                ", durata=" + durata +
                ", riposo=" + riposo +
                ", num_militari=" + num_militari +
                ", num_mezzi=" + num_mezzi +
                '}';
    }
}
