package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.UtenteAutosezione;

public class ComAutosezione implements Comando
{

    public static final String cod = "3";
    public static final String descr = "Utente Autosezione";

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
        System.out.println("Login by Ufficio Autosezione");
        EasyMilitary em = EasyMilitary.getInstance();
        em.setTipoUtente(new UtenteAutosezione());
    }
}