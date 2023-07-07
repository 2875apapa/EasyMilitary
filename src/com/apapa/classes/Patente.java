package com.apapa.classes;


import java.util.Date;

public class Patente
{
    private String matricola;
    private int tipo_patente;
    private Date dataConseguimento;
    private Date dataScadenza;

    public Patente(String matricola, int tipo_patente, Date dataConseguimento, Date dataScadenza)
    {
        this.matricola = matricola;
        this.tipo_patente = tipo_patente;
        this.dataConseguimento = dataConseguimento;
        this.dataScadenza = dataScadenza;
    }

    public Patente(String matricola, int tipo_patente)
    {
        this.matricola = matricola;
        this.tipo_patente = tipo_patente;
    }

    public String getMatricola()
    {
        return matricola;
    }

    public void setMatricola(String matricola)
    {
        this.matricola = matricola;
    }

    public int getTipo_patente() {
        return tipo_patente;
    }

    public void setTipo_patente(int tipo_patente) {
        this.tipo_patente = tipo_patente;
    }

    public Date getDataConseguimento() {
        return dataConseguimento;
    }

    public void setDataConseguimento(Date dataConseguimento) {
        this.dataConseguimento = dataConseguimento;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    @Override
    public String toString() {
        return "Patente{" +
                "tipo_patente=" + tipo_patente +
                ", dataConseguimento=" + dataConseguimento +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
