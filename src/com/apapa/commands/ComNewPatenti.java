package com.apapa.commands;

import com.apapa.classes.EasyMilitary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ComNewPatenti implements Comando
{

    public static final String cod = "1";
    public static final String descr = "Nuove Patenti";

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
        System.out.println("- - - EASY MILITARY - AGGIUNGI NUOVE PATENTI - - -");
        int modello = 0;
        while(modello < 1 || modello > 4)
        {
            System.out.println("Selezionare tipologia di patente: \n1) Modello1\n2) Modello2\n3) Modello3\n4) Modello4");
            modello  = Integer.parseInt(scannerIO.nextLine());
        }
        Date conseguimento = null;
        Date scadenza = null;
        try
        {
            System.out.println("Inserisci data di conseguimento: [dd/MM/yyyy]");
            String data = scannerIO.nextLine();
            conseguimento = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            System.out.println("Inserisci data di scadenza: [dd/MM/yyyy]");
            data = scannerIO.nextLine();
            scadenza = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            System.out.println("Errore nell'inserimento delle date");
        }
        System.out.println("Inserisci il numero di militari che hanno acquisito questa abilitazione");
        int num  = Integer.parseInt(scannerIO.nextLine());
        int i;
        List<String> matricole = new ArrayList<String>();
        for(i=0; i<num; i++)
        {
            String matricola = null;
            do {
                int y = i+1;
                System.out.println("Inserisci il numero di matricola del " + y + "-iessimo militare");
                matricola = (scannerIO.nextLine());
            }while(!EasyMilitary.getInstance().checkMilitare(matricola));
            matricole.add(matricola);
        }
        int scelta;
        do {
            System.out.println("Inserire l'abilitazione al modello " + modello + " con conseguimento in data " + conseguimento.toString() + " e scadenza in data " + scadenza.toString());
            System.out.println("Al seguente personale: ");
            System.out.println(matricole.toString());
            System.out.println("1)Conferma Dati\n2)Inserisci un altro militare\n3)Annulla");
            scelta = Integer.parseInt(scannerIO.nextLine());
            if(scelta == 1)
            {
                EasyMilitary.getInstance().addPatenti(modello, conseguimento, scadenza, matricole);
                System.out.println("Fatto!!");
            }
            if(scelta == 2)
            {
                String matricola = null;
                do {
                    System.out.println("Inserisci il numero di matricola del militare a cui assegnare l'abilitazione");
                    matricola = (scannerIO.nextLine());
                }while(!EasyMilitary.getInstance().checkMilitare(matricola));
                matricole.add(matricola);
            }
            if(scelta == 3)
            {
                System.out.println("Annulla!!");
            }
        }while(scelta!=1 && scelta!=3);
    }
}