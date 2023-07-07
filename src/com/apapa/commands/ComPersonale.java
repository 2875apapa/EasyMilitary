package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.UtenteAutosezione;
import com.apapa.classes.UtentePersonale;

public class ComPersonale implements Comando
{

    public static final String cod = "2";
    public static final String descr = "Utente Personale";

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
        System.out.println("Login by Ufficio Personale");
        EasyMilitary em = EasyMilitary.getInstance();
        em.setTipoUtente(new UtentePersonale());
    }
}