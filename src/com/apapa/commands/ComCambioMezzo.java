package com.apapa.commands;

import com.apapa.classes.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class ComCambioMezzo implements Comando
{

    public static final String cod = "6";
    public static final String descr = "Sostituisci Mezzo";

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
        System.out.println("- - - EASY MILITARY - SOSTITUISCI MEZZO - - -");


        List<Servizio> ls = EasyMilitary.getInstance().elencoservizi();
        List <Comandata> lc = EasyMilitary.getInstance().elencocomandate();

        if(ls.size()>0 || lc.size()>0)
        {

            System.out.println("\nServizi: ");
            int i = 0;
            for(Servizio ss : ls)
            {
                System.out.print("\n" + (i+1) + ") ");
                ss.reportMezziFinale();
                i++;
            }

            System.out.println("\nComandate: ");
            for(Comandata cc : lc)
            {
                System.out.print("\n" + (i+1) + ") ");
                cc.reportMezziFinale();
                i++;
            }

            int k= -1;
            do {
                System.out.println("\nSelezionare il servizio: ");
                k = Integer.parseInt(scannerIO.nextLine());
            }while(k<1 || k >i);
            if(k<ls.size())
            {
                Servizio mod = ls.get(k-1);
                mod.minireportFinale();
                if(mod.getNum_mezzi()<1)
                {
                    System.out.println("Il servizio selezionato non prevede utilizzo di mezzi");
                }
                else
                {
                    int j=0;
                    for(Mezzo mz : mod.getElencoMezzi())
                    {
                        System.out.println((j+1) + ") " + mz.getTarga());
                        j++;
                    }
                    int h= -1;
                    do {
                        System.out.println("\nSelezionare il mezzo da sostituire: ");
                        h = Integer.parseInt(scannerIO.nextLine());
                    }while(h<1 || h >j);
                    boolean a = false;
                    String targa = "";
                    do {
                        System.out.println("Inserisci il numero di targa del mezzo da utilizzare: ");
                        targa = scannerIO.nextLine();
                        a = EasyMilitary.getInstance().checkMezServ(targa, mod.getData_inizio(), mod.getData_fine());
                    }while(!a);
                    System.out.println("Vuoi confermare la sostituzione del mezzo targato " + mod.getElencoMezzi().get(h-1).getTarga() +" con " + targa +" :");
                    System.out.println("[S / N]");
                    String conf =  scannerIO.nextLine();
                    if(conf.equals("s")||conf.equals("S"))
                    {
                        System.out.println("Confermato!!");
                        if(EasyMilitary.getInstance().changeMezzoS(mod.getId(), mod.getElencoMezzi().get(h-1).getTarga(), targa))
                        {
                            System.out.println("OK");
                            System.gc();
                        }
                    }
                    else
                    {
                        System.out.println("Non Confermato!!");
                        System.gc();
                    }
                }
            }
            else
            {
                k=k-ls.size();
                Comandata mod = lc.get(k-1);
                mod.minireportFinale();
                int j=0;
                System.out.println("Mezzo attualmente assegnato alla comandata: "+mod.getTarga());
                boolean a = false;
                String targa = "";
                do {
                    System.out.println("Inserisci il numero di targa del mezzo da utilizzare: ");
                    targa = scannerIO.nextLine();
                    a = EasyMilitary.getInstance().checkMezServ(targa, mod.getDinizio(), mod.getDfine());
                }while(!a);
                System.out.println("Vuoi confermare la sostituzione del mezzo targato " + mod.getTarga() +" con " + targa +" :");
                System.out.println("[S / N]");
                String conf =  scannerIO.nextLine();
                if(conf.equals("s")||conf.equals("S"))
                {
                    System.out.println("Confermato!!");
                    if(EasyMilitary.getInstance().changeMezzoC(mod.getId(), mod.getTarga(), targa))
                    {
                        System.out.println("OK");
                        System.gc();
                    }
                }
                else
                {
                    System.out.println("Non Confermato!!");
                    System.gc();
                }
            }
        }
        else
        {
            System.out.println("Non sono ancora programmati servizi futuri");
        }

    }
}