package com.apapa.commands;

import com.apapa.classes.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ComCambioTurno implements Comando
{

    public static final String cod = "5";
    public static final String descr = "Cambio Turno";

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
        System.out.println("- - - EASY MILITARY - CAMBIA TURNO - - -");


        List <Servizio> ls = EasyMilitary.getInstance().elencoservizi();
        List <Comandata> lc = EasyMilitary.getInstance().elencocomandate();

        if(ls.size()>0 || lc.size()>0)
        {

            System.out.println("\nServizi: ");
            int i = 0;
            for(Servizio ss : ls)
            {
                System.out.print("\n" + (i+1) + ") ");
                ss.reportFinale();
                i++;
            }

            System.out.println("\nComandate: ");
            for(Comandata cc : lc)
            {
                System.out.print("\n" + (i+1) + ") ");
                cc.reportFinale();
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
                int j=0;
                for(MilitareServizio ms : mod.getElencoMilitari())
                {
                    System.out.println((j+1) + ") " + ms.getMatricola() + " - " + ms.getRuolo());
                    j++;
                }
                int h= -1;
                do {
                    System.out.println("\nSelezionare il militare da sostituire: ");
                    h = Integer.parseInt(scannerIO.nextLine());
                }while(h<1 || h >j);

                System.out.println("\n....recupero dal DB i tipi di servizio esistenti....\n");
                List<TipoServizio> lts = EasyMilitary.getInstance().lts();
                if(lts== null)
                {
                    System.out.println("Errore nel recupero dati........");
                }
                else
                {
                    TipoServizio tstemp = null;
                    for(TipoServizio tt : lts)
                    {
                        if(tt.getId() == mod.getCod_tipo_servizio())
                        {
                            tstemp = tt;
                            break;
                        }
                    }
                    boolean a = false;
                    String matricola = "";
                    do {
                        System.out.println(tstemp.getLruoli().get(h-1).toString());
                        System.out.println("Inserisci il numero di matricola del militare da assegnare: ");
                        matricola = scannerIO.nextLine();
                        a = EasyMilitary.getInstance().checkMilServ(matricola, mod.getData_inizio(), mod.getData_fine(),tstemp.getLruoli().get(h-1).getGrado(),mod.isArmato(),tstemp.getLruoli().get(h-1).isGuida());
                    }while(!a);
                    System.out.println("Vuoi confermare la sostituzione di " + mod.getElencoMilitari().get(h-1).getMatricola() +" con " + matricola +" :");
                    System.out.println("[S / N]");
                    String conf =  scannerIO.nextLine();
                    if(conf.equals("s")||conf.equals("S"))
                    {
                        MilitareServizio ms = new MilitareServizio();
                        ms.setMatricola(matricola).setRuolo(tstemp.getLruoli().get(h-1).getNome()).setIdRuolo(tstemp.getLruoli().get(h-1).getId()).build();
                        System.out.println("Confermato!!");
                        if(EasyMilitary.getInstance().changeMilitaryS(mod.getId(), mod.getElencoMilitari().get(h-1).getMatricola(), ms))
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
                for(String ms : mod.getMilitari())
                {
                    System.out.println((j+1) + ") " + ms);
                    j++;
                }
                int h= -1;
                do {
                    System.out.println("\nSelezionare il militare da sostituire: ");
                    h = Integer.parseInt(scannerIO.nextLine());
                }while(h<1 || h >j);
                Mezzo mezzo = EasyMilitary.getInstance().findMezzo(mod.getTarga());
                boolean a = false;
                String matricola = "";
                do {
                    System.out.println("Inserisci il numero di matricola del militare da assegnare: ");
                    matricola = scannerIO.nextLine();
                    a = EasyMilitary.getInstance().checkMilServ(matricola, mod.getDinizio(), mod.getDfine(), mezzo.getPatente());
                }while(!a);
                System.out.println("Vuoi confermare la sostituzione di " + mod.getMilitari().get(h-1)+" con " + matricola +" :");
                System.out.println("[S / N]");
                String conf =  scannerIO.nextLine();
                if(conf.equals("s")||conf.equals("S"))
                {
                        System.out.println("Confermato!!");
                        if(EasyMilitary.getInstance().changeMilitaryC(mod.getId(), mod.getMilitari().get(h-1), matricola))
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