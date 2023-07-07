package com.apapa.commands;

public class ComNonValido implements Comando
{

    public static final String cod = "-1";
    public static final String descr = "Comando non Valido";

    public String getCodice()
    {
        return cod;
    }

    public String getDescrizione()
    {
        return descr;
    }

    public void esegui() {
        //esegui
        System.out.println("Il comando inserito e' INESISTENTE!");
    }
}
