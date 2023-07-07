package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Mezzo;
import com.apapa.classes.Militare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ComEditMezzo implements Comando {

    public static final String cod = "3";
    public static final String descr = "Modifica Mezzo";

    public String getCodice() {
        return cod;
    }

    public String getDescrizione() {
        return descr;
    }

    public void esegui() {
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        Scanner scannerIO = new Scanner(System.in);
        //esegui
        System.out.println("- - - EASY MILITARY - MODIFICA LO STATO DI UN MEZZO - - -");
        System.out.println("Inserisci numero di targa da ricercare: ");
        String targa = scannerIO.nextLine();
        if (!EasyMilitary.getInstance().checkMezzo(targa))
            System.out.println("Il numero di targa inserito non e' presente sul sistema");
        else {
            Mezzo m = EasyMilitary.getInstance().findMezzo(targa);
            if (m != null)
            {
                System.out.println(m.toString());
                if (m.isStato())
                    System.out.println("Vuoi mutare lo stato del mezzo da marciante a guasto? [S / N]");
                else
                    System.out.println("Vuoi mutare lo stato del mezzo da guasto a marciante? [S / N]");
                String conf = scannerIO.nextLine();
                if (conf.equals("s") || conf.equals("S"))
                {
                    m.setStato(!m.isStato());
                    System.out.println("Confermato!!");
                    if (EasyMilitary.getInstance().updMezzo(targa, m.isStato())) {
                        m = null;
                        System.gc();
                    }
                }
                else
                {
                    System.out.println("Non Confermato!!");
                    m = null;
                    System.gc();
                }
            }
            else
                System.out.println("Errore....");
        }
    }
}
