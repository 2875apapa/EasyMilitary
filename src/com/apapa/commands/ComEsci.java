package com.apapa.commands;

public class ComEsci implements Comando
{

    public static final String cod = "0";
    public static final String descr = "Esci";

    public String getCodice()
    {
        return cod;
    }

    public String getDescrizione()
    {
        return descr;
    }

    public void esegui() {
        System.out.println("Esci.....");
    }
}