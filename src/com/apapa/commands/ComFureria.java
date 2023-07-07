package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.UtenteAutosezione;
import com.apapa.classes.UtenteFureria;

public class ComFureria implements Comando
{

    public static final String cod = "1";
    public static final String descr = "Utente Fureria";

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
        System.out.println("Login by Ufficio Fureria");
        EasyMilitary em = EasyMilitary.getInstance();
        em.setTipoUtente(new UtenteFureria());
    }
}