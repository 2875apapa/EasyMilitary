package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Mezzo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ComAddMezzo implements Comando
{

    public static final String cod = "2";
    public static final String descr = "Aggiungi Mezzo";

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
        System.out.println("- - - EASY MILITARY - AGGIUNGI UN NUOVO MEZZO - - -");
        boolean ct = true;
        String targa = "";
        while(ct)
        {
            System.out.println("Inserisci il numero di targa del mezzo: ");
            targa = scannerIO.nextLine();
            ct =EasyMilitary.getInstance().checkMezzo(targa);
            if(ct)
                System.out.println("Errore: Il numero di targa inserito esiste gia' nel sistema");
        }
        System.out.println("Inserisci marca del mezzo: ");
        String marca = scannerIO.nextLine();
        System.out.println("Inserisci modello del mezzo: ");
        String modello = scannerIO.nextLine();
        int trasp = 0;
        do{
            System.out.println("Tipologia del mezzo: \n1)Trasporto persone\n2)Trasporto merci ");
            trasp  = Integer.parseInt(scannerIO.nextLine());
        }while(trasp != 1 && trasp != 2);
        boolean tipo = false;
        if(trasp == 2)
            tipo = true;
        int patente = 0;
        while(patente < 1 || patente > 4)
        {
            System.out.println("Selezionare tipologia di abilitazione di guida richiesta: \n1) Modello1\n2) Modello2\n3) Modello3\n4) Modello4");
            patente  = Integer.parseInt(scannerIO.nextLine());
        }
        int ok = 0;
        do{
            System.out.println("Stato del mezzo: \n1)Mezzo Marciante\n2)Mezzo Guasto");
            ok  = Integer.parseInt(scannerIO.nextLine());
        }while(ok != 1 && ok != 2);
        boolean stato = false;
        if(ok == 1)
            stato = true;
        Mezzo m = EasyMilitary.getInstance().nuovoMezzo(targa, marca, modello, tipo, patente, stato);
        System.out.println("Vuoi confermare l'inserimento del seguente mezzo: \n"+m.toString());
        System.out.println("[S / N]");
        String conf =  scannerIO.nextLine();
        if(conf.equals("s")||conf.equals("S"))
        {
            System.out.println("Confermato!!");
            if(EasyMilitary.getInstance().addMezzo(m))
            {
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
}