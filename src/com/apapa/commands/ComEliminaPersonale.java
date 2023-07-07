package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Militare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class ComEliminaPersonale implements Comando
{

    public static final String cod = "4";
    public static final String descr = "Elimina personale della caserma";

    public String getCodice()
    {
        return cod;
    }

    public String getDescrizione()
    {
        return descr;
    }

    public void esegui() {
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        Scanner scannerIO = new Scanner(System.in);
        //esegui
        System.out.println("- - - EASY MILITARY - ELIMINA UN MILITARE - - -");
        Militare m = ricerca();
        if(m == null)
        {
            System.out.println("Nessun militare presente in memoria....");
        }
        else
        {
            System.out.println("Vuoi confermare l'ELIMINAZIONE dell'elemento");
            System.out.println("[S / N]");
            String conf =  scannerIO.nextLine();
            if(conf.equals("s")||conf.equals("S"))
            {
                System.out.println("Confermato!!");
                if(EasyMilitary.getInstance().deleteMilitare(m.getMatricola()))
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

    private Militare ricerca()
    {
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        Scanner scannerIO = new Scanner(System.in);
        int s;
        do{
            System.out.println("RICERCARE IL MILITARE DA ELIMINARE\n1) Ricerca per Matricola\n2) Ricerca per Cognome ");
            s  = Integer.parseInt(scannerIO.nextLine());
        }while(s<1 || s>2);
        if(s == 1)
        {
            System.out.println("Inserisci matricola da ricercare: ");
            String mat = scannerIO.nextLine();
            Militare m = EasyMilitary.getInstance().findMilitare(mat);
            if(m != null)
            {
                System.out.println(m.toString());
                return m;
            }
        }
        else
        {
            System.out.println("Inserisci cognome da ricercare: ");
            String cogn = scannerIO.nextLine();
            List<Militare> ml = EasyMilitary.getInstance().findMilitareS(cogn);
            if(ml != null)
            {
                if(ml.size()==1)
                {
                    Militare m = EasyMilitary.getInstance().findMilitare(ml.get(0).getMatricola());
                    if(m != null)
                    {
                        System.out.println(m.toString());
                        return m;
                    }
                }
                else
                {
                    int k =1;
                    for (Militare mm : ml)
                    {
                        System.out.println(k + ") "+ mm.toStringRidu());
                        k++;
                    }
                    do {
                        System.out.println("Selezionare il militare desiderato: ");
                        k = Integer.parseInt(scannerIO.nextLine());
                    }while(k<1 || k >ml.size());
                    Militare m = EasyMilitary.getInstance().findMilitare(ml.get(k-1).getMatricola());
                    if(m != null)
                    {
                        System.out.println(m.toString());
                        return m;
                    }
                }
            }
        }
        return null;
    }
}