package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Mezzo;
import com.apapa.classes.Militare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ComEliminaMezzo implements Comando
{

    public static final String cod = "4";
    public static final String descr = "Elimina Mezzo dal parco auto";

    public String getCodice()
    {
        return cod;
    }

    public String getDescrizione()
    {
        return descr;
    }

    public void esegui()
    {
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        Scanner scannerIO = new Scanner(System.in);
        //esegui
        System.out.println("- - - EASY MILITARY - ELIMINA UN MEZZO - - -");
        System.out.println("Inserisci numero di targa da ricercare: ");
        String targa = scannerIO.nextLine();
        if (!EasyMilitary.getInstance().checkMezzo(targa))
            System.out.println("Il numero di targa inserito non e' presente sul sistema");
        else {
            Mezzo m = EasyMilitary.getInstance().findMezzo(targa);
            if (m != null) {
                System.out.println(m.toString());
                System.out.println("Vuoi confermare l'ELIMINAZIONE dell'elemento");
                System.out.println("[S / N]");
                String conf = scannerIO.nextLine();
                if (conf.equals("s") || conf.equals("S")) {
                    System.out.println("Confermato!!");
                    if (EasyMilitary.getInstance().deleteMezzo(targa)) {
                        m = null;
                        System.gc();
                    }
                } else {
                    System.out.println("Non Confermato!!");
                    m = null;
                    System.gc();
                }
            }
        }
    }
}