package com.apapa.classes;

import java.sql.Time;

public class Giorno
{
    private int numero;
    private Time ora_inizio;
    private Time ora_fine;

    public Giorno(int numero, Time ora_inizio, Time ora_fine)
    {
        this.numero = numero;
        this.ora_inizio = ora_inizio;
        this.ora_fine = ora_fine;
    }

    public Giorno()
    {
    }
    @Override
    public String toString()
    {
        return "{" +
                "Giorno numero=" + numero +
                ", ora_inizio=" + ora_inizio +
                ", ora_fine=" + ora_fine +
                '}';
    }

    public int getNumero() {
        return numero;
    }

    public Time getOra_inizio() {
        return ora_inizio;
    }

    public Time getOra_fine() {
        return ora_fine;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setOra_inizio(Time ora_inizio) {
        this.ora_inizio = ora_inizio;
    }

    public void setOra_fine(Time ora_fine) {
        this.ora_fine = ora_fine;
    }
}


