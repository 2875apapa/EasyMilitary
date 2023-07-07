package com.apapa.commands;

import com.apapa.classes.Comandata;
import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Militare;
import com.apapa.classes.Servizio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ComViewServizi implements Comando
{

    public static final String cod = "3";
    public static final String descr = "Visualizza tutti i servizi";

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
        System.out.println("- - - EASY MILITARY - VISUALIZZA SERVIZI - - -");
        Date dinizio = null;
        Date dfine = null;
        try
        {
            System.out.println("Inserisci data d'inizio ricerca: [dd/MM/yyyy]");
            String data = scannerIO.nextLine();
            dinizio = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            System.out.println("Inserisci data di fine ricerca: [dd/MM/yyyy]");
            data = scannerIO.nextLine();
            dfine = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        }
        catch (ParseException e)
        {
            System.out.println("Errore - Inserimento date non riuscito");
            e.printStackTrace();
        }

        List <Servizio> ls = EasyMilitary.getInstance().cercaservizi(dinizio, dfine);
        List <Comandata> lc = EasyMilitary.getInstance().cercacomandate(dinizio, dfine);

        System.out.println("\nServizi: ");
        for(Servizio ss : ls)
        {
            System.out.println("");
            ss.reportFinale();
        }


        System.out.println("\nComandate: ");
        for(Comandata cc : lc)
        {
            System.out.println("");
            cc.reportFinale();
        }
    }
}
