package com.apapa.classes;

import java.util.Date;

public class Critik
{
    private String servizio;
    private int idsc;
    private int type;
    private Date inizio;
    private Date fine;
    private String matrcola;
    private String targa;

    //type 1 = criticità militare  -  type 2 = criticità mezzo

    public Critik() {
    }

    public Critik(String servizio, int idsc, int type, Date inizio, Date fine) {
        this.servizio = servizio;
        this.idsc = idsc;
        this.type = type;
        this.inizio = inizio;
        this.fine = fine;
        this.matrcola = matrcola;
    }

    public Critik(String servizio, int idsc, int type, Date inizio, Date fine, int ps, String x)
    {
        this.servizio = servizio;
        this.idsc = idsc;
        this.type = type;
        this.inizio = inizio;
        this.fine = fine;
        if(ps == 1)
            this.matrcola = x;
        else
            this.targa = x;
    }



    public String getServizio()
    {
        return servizio;
    }

    public void setServizio(String servizio)
    {
        this.servizio = servizio;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public void setInizio(Date inizio)
    {
        this.inizio = inizio;
    }

    public void setFine(Date fine)
    {
        this.fine = fine;
    }

    public void setMatrcola(String matrcola)
    {
        this.matrcola = matrcola;
    }

    public String getTarga()
    {
        return targa;
    }

    public void setTarga(String targa)
    {
        this.targa = targa;
    }

    public int getIdsc() {
        return idsc;
    }

    public void setIdsc(int idsc) {
        this.idsc = idsc;
    }

    public String Cmilitare() {
        return "Servizio='" + servizio + '\'' +
                ", matrcola='" + matrcola + '\'' +
                ", inizio servizio=" + inizio +
                ", fine servizio=" + fine;
    }

    public String Cmezzo() {
        return "Servizio='" + servizio + '\'' +
                ", targa='" + targa + '\'' +
                ", inizio servizio=" + inizio +
                ", fine servizio=" + fine;
    }
}


