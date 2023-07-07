package com.apapa.classes;

public class Mezzo
{
    private String targa;
    private String marca;
    private String modello;
    private boolean tipo;   //true trasporto persone, false trasporto merce
    private int patente;
    private boolean stato;  //true marciante, false guasto

    public Mezzo(String targa, String marca, String modello, boolean tipo, int patente, boolean stato)
    {
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.tipo = tipo;
        this.patente = patente;
        this.stato = stato;
    }

    public Mezzo()
    {
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public int getPatente() {
        return patente;
    }

    public void setPatente(int patente) {
        this.patente = patente;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    @Override
    public String toString()
    {
        String tipoS = "";
        if(tipo)
            tipoS = "Trasporto Persone";
        else
            tipoS = "Trasporto Merci";

        String statoS = "";
        if(stato)
            statoS = "Marciante";
        else
            statoS = "Guasto";
        return "Mezzo{" +
                "targa='" + targa + '\'' +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", tipo=" + tipoS +
                ", patente=" + patente +
                ", stato=" + statoS +
                '}';
    }

    public String toStringRidu()
    {
        return "Mezzo{" +
                "targa='" + targa + '\'' +
                ", marca='" + marca + '\'' +
                ", modello='" + modello +
                '}';
    }
}
