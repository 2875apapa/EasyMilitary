package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Militare;
import com.apapa.classes.Patente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ComAddPersonale implements Comando
{

    public static final String cod = "1";
    public static final String descr = "Aggiungi Personale";

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
        System.out.println("- - - EASY MILITARY - AGGIUNGI UN NUOVO MILITARE - - -");
        //Militare m = new Militare().build();
        List<Patente> ps = new ArrayList<Patente>();
        System.out.println("Inserisci il numero di matricola: ");
        String matricola = scannerIO.nextLine();
        //m.setMatricola(matricola);
        System.out.println("Inserisci nome: ");
        String nome = scannerIO.nextLine();
        //m.setNome(nome);
        System.out.println("Inserisci cognome: ");
        String cognome = scannerIO.nextLine();
        //m.setCognome(cognome);
        System.out.println("Inserisci luogo di nascita: ");
        String luogo = scannerIO.nextLine();
        Date date = null;
        try
        {
            System.out.println("Inserisci data di nascita: [dd/MM/yyyy]");
            String data = scannerIO.nextLine();
            date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            //m.setNascita(date,luogo);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            System.out.println("Inserimento della data non riuscito");
        }
        System.out.println("Seleziona il grado: ");
        int i, grado;
        int l=Menu.getCom(Menu.GRADI).length;
        do{
            System.out.println(Menu.elencoCom(Menu.GRADI));
            grado  = Integer.parseInt(scannerIO.nextLine());
        }while(grado < 0 || grado > l-1);
        //m.setGrado(grado);
        int armi = 0;
        do{
            System.out.println("Militare abilitato all'uso delle armi: \n1)Si\n2)No ");
            armi  = Integer.parseInt(scannerIO.nextLine());
        }while(armi != 1 && armi != 2);
        boolean abarmi = false;
        if(armi == 1)
            abarmi = true;
        //m.setArmi(abarmi);
        Militare m = EasyMilitary.getInstance().nuovoMilitare(matricola,cognome,nome,date,luogo,grado,abarmi);
        int pate = 0;
        do {
            System.out.println("Vuoi inserire una nuova patente al militare: \n1)Si\n2)No ");
            pate  = Integer.parseInt(scannerIO.nextLine());
            if(pate == 1)
            {
                int modello = 0;
                Date conseguimento = null;
                Date scadenza = null;
                while(modello < 1 || modello > 4)
                {
                    System.out.println("Selezionare tipologia di patente: \n1) Modello1\n2) Modello2\n3) Modello3\n4) Modello4");
                    modello  = Integer.parseInt(scannerIO.nextLine());
                }
                //Patente p = new Patente(matricola, modello);
                try
                {
                    System.out.println("Inserisci data di conseguimento: [dd/MM/yyyy]");
                    String data = scannerIO.nextLine();
                    conseguimento = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                    System.out.println("Inserisci data di scadenza: [dd/MM/yyyy]");
                    data = scannerIO.nextLine();
                    scadenza = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                    //p.setDataConseguimento(conseguimento);
                    //p.setDataScadenza(scadenza);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                    System.out.println("Errore nell'inserimento delle date");
                }
                Patente p = EasyMilitary.getInstance().newPatente(matricola, modello, conseguimento, scadenza);
                ps.add(p);
            }
        }while(pate != 2);
        System.out.println("Vuoi confermare l'inserimento del seguente elemento: \n"+m.toString());
        for(i=0; i<ps.size(); i++)
        {
            System.out.println(ps.get(i).toString());
        }
        System.out.println("[S / N]");
        String conf =  scannerIO.nextLine();
        if(conf.equals("s")||conf.equals("S"))
        {
            System.out.println("Confermato!!");
            EasyMilitary em = EasyMilitary.getInstance();
            if(em.addMilitare(m, ps))
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
