package com.apapa.classes;

import java.util.Date;

public class Assenza
{
    private int id = -1;
    private String tipo;
    private Date dinizio;
    private Date dfine;
    private String militare;

    public Assenza()
    {
    }

    public Assenza(int id, String tipo, Date dinizio, Date dfine, String militare)
    {
        this.id = id;
        this.tipo = tipo;
        this.dinizio = dinizio;
        this.dfine = dfine;
        this.militare = militare;
    }

    public Assenza(String tipo, Date dinizio, Date dfine, String militare)
    {
        this.tipo = tipo;
        this.dinizio = dinizio;
        this.dfine = dfine;
        this.militare = militare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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

    public String getMilitare() {
        return militare;
    }

    public void setMilitare(String militare) {
        this.militare = militare;
    }

    @Override
    public String toString() {
        return "Assenza{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", dinizio=" + dinizio +
                ", dfine=" + dfine +
                ", militare='" + militare + '\'' +
                '}';
    }
}
