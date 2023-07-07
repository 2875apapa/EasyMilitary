package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Mezzo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class ComRicercaMezzo implements Comando {
    public static final String cod = "5";
    public static final String descr = "Ricerca Mezzo";

    @Override
    public String getCodice() {
        return cod;
    }

    @Override
    public String getDescrizione() {
        return descr;
    }

    @Override
    public void esegui()
    {
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        Scanner scannerIO = new Scanner(System.in);
        //esegui
        System.out.println("- - - EASY MILITARY - RICERCA DI UN MEZZO - - -");
        int s;
        do{
            System.out.println("1) Ricerca per numero di Targa\n2) Ricerca per tipo \n3) Ricerca per marca e modello ");
            s  = Integer.parseInt(scannerIO.nextLine());
        }while(s<1 || s>3);
        if(s == 1)
        {
            System.out.println("Inserisci numero di targa da ricercare: ");
            String targa = scannerIO.nextLine();
            if(!EasyMilitary.getInstance().checkMezzo(targa))
                System.out.println("Il numero di targa inserito non e' presente sul sistema");
            else
            {
                Mezzo m = EasyMilitary.getInstance().findMezzo(targa);
                if(m!=null)
                    System.out.println(m.toString());
            }
        }
        if(s == 2)
        {
            int trasp = 0;
            do{
                System.out.println("Tipologia del mezzo: \n1)Trasporto persone\n2)Trasporto merci ");
                trasp  = Integer.parseInt(scannerIO.nextLine());
            }while(trasp != 1 && trasp != 2);
            boolean tipo = false;
            if(trasp == 2)
                tipo = true;
            List<Mezzo> ml = EasyMilitary.getInstance().findMezzoT(tipo);
            if(ml != null)
            {
                if(ml.size()==1)
                {
                    Mezzo m = ml.get(0);
                    System.out.println(m.toString());
                }
                else
                {
                    int k =1;
                    for (Mezzo mm : ml)
                    {
                        System.out.println(k + ") "+ mm.toStringRidu());
                        k++;
                    }
                    do {
                        System.out.println("Selezionare il mezzo desiderato: ");
                        k = Integer.parseInt(scannerIO.nextLine());
                    }while(k<1 || k >ml.size());
                    Mezzo m = EasyMilitary.getInstance().findMezzo(ml.get(k-1).getTarga());
                    if(m != null)
                    {
                        System.out.println(m.toString());
                    }
                }
            }
        }
        if(s == 3)
        {
            System.out.println("Inserisci marca del mezzo da ricercare: ");
            String marca = scannerIO.nextLine();
            System.out.println("Inserisci modello del mezzo da ricercare: ");
            String modello = scannerIO.nextLine();
            List<Mezzo> ml = EasyMilitary.getInstance().findMezzoMM(marca, modello);
            if(ml != null)
            {
                if(ml.size()==1)
                {
                    Mezzo m = ml.get(0);
                    System.out.println(m.toString());
                }
                else
                {
                    int k =1;
                    for (Mezzo mm : ml)
                    {
                        System.out.println(k + ") "+ mm.toStringRidu());
                        k++;
                    }
                    do {
                        System.out.println("Selezionare il mezzo desiderato: ");
                        k = Integer.parseInt(scannerIO.nextLine());
                    }while(k<1 || k >ml.size());
                    Mezzo m = EasyMilitary.getInstance().findMezzo(ml.get(k-1).getTarga());
                    if(m != null)
                    {
                        System.out.println(m.toString());
                    }
                }
            }
        }

    }
}
