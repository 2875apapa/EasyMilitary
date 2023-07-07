package com.apapa.commands;

import com.apapa.classes.Comandata;
import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Mezzo;
import com.apapa.classes.MilitareServizio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ComNuovaComandata implements Comando
{

    public static final String cod = "2";
    public static final String descr = "Crea una nuova comandata";

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
        System.out.println("- - - EASY MILITARY - CREA UNA UNA COMANDATA - - -");
        int tipoc;
        boolean tipo = false;
        do {
            System.out.println("Tipo comandata: \n1.Trasporto personale\n2.Trasporto materiale");
            tipoc = Integer.parseInt(scannerIO.nextLine());
        }while(tipoc<1 || tipoc>2);
        if(tipoc == 1)
            tipo = true;
        Date dinizio = null;
        Time tinizio = null;
        Date dfine = null;
        Time tfine= null;
        try
        {
            System.out.println("Inserisci data di inizio: [dd/MM/yyyy]");
            String data = scannerIO.nextLine();
            dinizio = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            System.out.print("Inserire l'orario di inizio [hh:mm:ss]");
            String timeInput = scannerIO.nextLine();
            tinizio = Time.valueOf(timeInput);
            System.out.println("Inserisci data di fine: [dd/MM/yyyy]");
            data = scannerIO.nextLine();
            dfine = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            System.out.print("Inserire l'orario di fine [hh:mm:ss]");
            timeInput = scannerIO.nextLine();
            tfine = Time.valueOf(timeInput);
        } catch (ParseException e)
        {
            e.printStackTrace();
            System.out.println("Inserimento data/time non funzionante");
        }
        System.out.println("Inserisci luogo di destinazione");
        String luogo = scannerIO.nextLine();
        boolean a = false;
        String targa = "";
        do {
            System.out.println("Inserisci il numero di targa del mezzo da utilizzare: ");
            targa = scannerIO.nextLine();
            a = EasyMilitary.getInstance().checkMezServ(targa, dinizio, dfine);
        }while(!a);
        System.out.println("Mezzo "+ targa +" disponibile!");
        Mezzo mezzo = EasyMilitary.getInstance().findMezzo(targa);
        int num;
        do {
            System.out.println("Inserire il numero di militari impiegati per svolgere la comandata: [max 4]");
            num = Integer.parseInt(scannerIO.nextLine());
        }while(num<1 || num>4);
        List<String> matricole = new ArrayList<String>();
        for( int i=0; i<num; i++)
        {
            String matricola = "";
            do {
                System.out.println("Militare num. " + (i+1) + "assegnato alla comandata");
                System.out.println("Inserisci il numero di matricola del militare da assegnare: ");
                matricola = scannerIO.nextLine();
                a = EasyMilitary.getInstance().checkMilServ(matricola, dinizio, dfine, mezzo.getPatente());
            }while(!a);
            matricole.add(matricola);
        }

        Comandata c = EasyMilitary.getInstance().nuovaComandata(tipo,dinizio,dfine,tinizio,tfine,luogo,targa,num);
        System.out.println("Vuoi confermare la calendarizzazione della seguente comandata:");
        System.out.println(c.toString());
        System.out.println("Con il seguente personale: ");
        for(String ss : matricole)
            System.out.println(ss);
        System.out.println("[S / N]");
        String conf =  scannerIO.nextLine();
        if(conf.equals("s")||conf.equals("S"))
        {
            System.out.println("Confermata!!");
            if(EasyMilitary.getInstance().addComadata(c, matricole))
            {
                c = null;
                System.gc();
            }
        }
        else
        {
            System.out.println("Non Confermata!!");
            c = null;
            System.gc();
        }


    }
}
