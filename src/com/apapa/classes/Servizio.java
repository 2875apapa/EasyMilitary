package com.apapa.classes;

import java.util.Date;
import java.util.List;

public class Servizio
{
    private int id = -1;
    private int cod_tipo_servizio;
    private String nome_tipo_servizio;
    private Date data_inizio;
    private Date data_fine;
    private int durata;
    private int riposo;
    private int num_militari;
    private int num_mezzi;
    private boolean armato;
    private List<MilitareServizio> elencoMilitari;
    private List<Mezzo> elencoMezzi;
    private List<Giorno> elencoGiorni;

    public Servizio(int id, int cod_tipo_servizio, String nome_tipo_servizio, Date data_inizio, Date data_fine, int durata, int riposo, int num_militari, int num_mezzi, boolean armato, List<MilitareServizio> elencoMilitari, List<Mezzo> elencoMezzi, List<Giorno> elencoGiorni)
    {
        this.id = id;
        this.cod_tipo_servizio = cod_tipo_servizio;
        this.nome_tipo_servizio = nome_tipo_servizio;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.durata = durata;
        this.riposo = riposo;
        this.num_militari = num_militari;
        this.num_mezzi = num_mezzi;
        this.armato = armato;
        this.elencoMilitari = elencoMilitari;
        this.elencoMezzi = elencoMezzi;
        this.elencoGiorni = elencoGiorni;
    }

    public Servizio(int cod_tipo_servizio, String nome_tipo_servizio, Date data_inizio, Date data_fine, int durata, int riposo, int num_militari, int num_mezzi, boolean armato)
    {
        this.cod_tipo_servizio = cod_tipo_servizio;
        this.nome_tipo_servizio = nome_tipo_servizio;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.durata = durata;
        this.riposo = riposo;
        this.num_militari = num_militari;
        this.num_mezzi = num_mezzi;
        this.armato = armato;
    }

    public Servizio(int cod_tipo_servizio, String nome_tipo_servizio, int durata, int riposo, int num_militari, int num_mezzi, boolean armato)
    {
        this.cod_tipo_servizio = cod_tipo_servizio;
        this.nome_tipo_servizio = nome_tipo_servizio;
        this.durata = durata;
        this.riposo = riposo;
        this.num_militari = num_militari;
        this.num_mezzi = num_mezzi;
        this.armato = armato;
    }

    public Servizio() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod_tipo_servizio() {
        return cod_tipo_servizio;
    }

    public void setCod_tipo_servizio(int cod_tipo_servizio) {
        this.cod_tipo_servizio = cod_tipo_servizio;
    }

    public String getNome_tipo_servizio() {
        return nome_tipo_servizio;
    }

    public void setNome_tipo_servizio(String nome_tipo_servizio) {
        this.nome_tipo_servizio = nome_tipo_servizio;
    }

    public Date getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(Date data_inizio) {
        this.data_inizio = data_inizio;
    }

    public Date getData_fine() {
        return data_fine;
    }

    public void setData_fine(Date data_fine) {
        this.data_fine = data_fine;
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

    public List<MilitareServizio> getElencoMilitari() {
        return elencoMilitari;
    }

    public void setElencoMilitari(List<MilitareServizio> elencoMilitari) {
        this.elencoMilitari = elencoMilitari;
    }

    public List<Mezzo> getElencoMezzi() {
        return elencoMezzi;
    }

    public void setElencoMezzi(List<Mezzo> elencoMezzi) {
        this.elencoMezzi = elencoMezzi;
    }

    public List<Giorno> getElencoGiorni() {
        return elencoGiorni;
    }

    public void setElencoGiorni(List<Giorno> elencoGiorni) {
        this.elencoGiorni = elencoGiorni;
    }

    public boolean isArmato() {
        return armato;
    }

    public void setArmato(boolean armato) {
        this.armato = armato;
    }

    public String info_base() {
        return "Servizio{" +
                "nome_tipo_servizio='" + nome_tipo_servizio + '\'' +
                ", data_inizio=" + data_inizio +
                ", data_fine=" + data_fine +
                ", durata=" + durata +
                ", riposo=" + riposo +
                ", armato=" + armato +
                ", num_militari=" + num_militari +
                ", num_mezzi=" + num_mezzi +
                '}';
    }

    public void reportFinale()
    {
        System.out.println("Servizio{ Nome ='" + nome_tipo_servizio + ", data_inizio=" + data_inizio + ", data_fine=" + data_fine + ", giorni di riposo=" + riposo + ", servizio armato=" + armato + "}");
        int i;
        System.out.println("Militari Assegnati : ");
        for(i=0; i<num_militari; i++)
        {
            System.out.println(elencoMilitari.get(i).getRuolo() + " = " + elencoMilitari.get(i).getMatricola());
        }
        if(num_mezzi>0)
        {
            System.out.println("Mezzi Assegnati : ");
            for(i=0; i<num_mezzi; i++)
            {
                System.out.println(elencoMezzi.get(i).getTarga() + " - " + elencoMezzi.get(i).getMarca() + " " + elencoMezzi.get(i).getModello());
            }
        }

    }

    public void reportMezziFinale()
    {
        System.out.println("Servizio{ Nome ='" + nome_tipo_servizio + ", data_inizio=" + data_inizio + ", data_fine=" + data_fine + ", giorni di riposo=" + riposo + ", servizio armato=" + armato + "}");
        int i;
        if(num_mezzi>0)
        {
            System.out.println("Mezzi Assegnati : ");
            for(i=0; i<num_mezzi; i++)
            {
                System.out.println(elencoMezzi.get(i).getTarga() + " - " + elencoMezzi.get(i).getMarca() + " " + elencoMezzi.get(i).getModello());
            }
        }
        else
        {
            System.out.println("Servizio non prevede utilizzo di mezzi");
        }

    }


    public void minireportFinale()
    {
        System.out.println("Servizio{ Nome ='" + nome_tipo_servizio + ", data_inizio=" + data_inizio + ", data_fine=" + data_fine + ", giorni di riposo=" + riposo + ", servizio armato=" + armato + "}");
    }
}
