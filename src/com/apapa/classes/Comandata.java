package com.apapa.classes;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comandata
{
    private int id = -1;
    private boolean tipo;
    private Date dinizio;
    private Date dfine;
    private Time tinizio;
    private Time tfine;
    private String destinazione;
    private String targa;
    private int num_militari;
    private List<String> militari;

    public Comandata(boolean tipo, Date dinizio, Date dfine, Time tinizio, Time tfine, String destinazione, String targa, int num_militari) {
        this.tipo = tipo;
        this.dinizio = dinizio;
        this.dfine = dfine;
        this.tinizio = tinizio;
        this.tfine = tfine;
        this.destinazione = destinazione;
        this.targa = targa;
        this.num_militari = num_militari;
        this.militari = new ArrayList<String>();
    }

    public Comandata() {
        this.militari = new ArrayList<String>();
    }

    public void setId(int id)
    {
        this.id = id;
    }


    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Date getDinizio() {
        return dinizio;
    }

    public void setDinizio(Date dinizio) {
        this.dinizio = dinizio;
    }

    public Date getDfine() {
        return dfine;
    }

    public void setDfine(Date dfine) {
        this.dfine = dfine;
    }

    public Time getTinizio() {
        return tinizio;
    }

    public void setTinizio(Time tinizio) {
        this.tinizio = tinizio;
    }

    public Time getTfine() {
        return tfine;
    }

    public void setTfine(Time tfine) {
        this.tfine = tfine;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public int getNum_militari() {
        return num_militari;
    }

    public void setNum_militari(int num_militari) {
        this.num_militari = num_militari;
    }

    @Override
    public String toString()
    {
        return "Comandata{" +
                "tipo=" + getTipoComa(tipo) +
                ", destinazione='" + destinazione + '\'' +
                ", mezzo='" + targa + '\'' +
                ", dinizio=" + dinizio +
                ", tinizio=" + tinizio +
                ", dfine=" + dfine +
                ", tfine=" + tfine +
                ", num_militari=" + num_militari +
                '}';
    }

    public void addMili (String m)
    {
        this.militari.add(m);
    }

    public List<String> getMilitari()
    {
        return this.militari;
    }

    public int getId() {
        return id;
    }

    public String getTipoComa(boolean tipo)
    {
        if(false)
            return "Trasporto materiale";
        else
            return "Trasporto personale";
    }

    public void reportFinale()
    {
        System.out.println("Comandata{" + "tipo=" + getTipoComa(tipo) +", destinazione='" + destinazione + '\'' +", mezzo='" + targa + '\'' +", dinizio=" + dinizio +", tinizio=" + tinizio +", dfine=" + dfine +", tfine=" + tfine +", num_militari=" + num_militari +'}');
        int i;
        System.out.println("Militari Assegnati : ");
        for(i=0; i<this.num_militari; i++)
        {
            System.out.println(militari.get(i));
        }
    }

    public void reportMezziFinale()
    {
        System.out.println("Comandata{" + "tipo=" + getTipoComa(tipo) +", destinazione='" + destinazione + '\'' +", mezzo='" + targa + '\'' +", dinizio=" + dinizio +", tinizio=" + tinizio +", dfine=" + dfine +", tfine=" + tfine +", num_militari=" + num_militari +'}');
    }

    public void minireportFinale()
    {
        System.out.println("Comandata{" + "tipo=" + getTipoComa(tipo) +", destinazione='" + destinazione + '\'' +", mezzo='" + targa + '\'' +", dinizio=" + dinizio +", tinizio=" + tinizio +", dfine=" + dfine +", tfine=" + tfine +", num_militari=" + num_militari +'}');
    }
}


