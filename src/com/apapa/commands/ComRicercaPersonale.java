package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Militare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class ComRicercaPersonale implements Comando
{
    public static final String cod = "5";
    public static final String descr = "Ricerca Personale";

    @Override
    public String getCodice() {
        return cod;
    }

    @Override
    public String getDescrizione() {
        return descr;
    }

    @Override
    public void esegui() {
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        Scanner scannerIO = new Scanner(System.in);
        //esegui
        System.out.println("- - - EASY MILITARY - RICERCA DI UN MILITARE - - -");
        int s;
        do{
            System.out.println("1) Ricerca per Matricola\n2) Ricerca per Cognome ");
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
            }
            //List<Militare> pp = EasyMilitary.getInstance().getMilitari();
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
                    }
                }
            }
        }
    }
}
