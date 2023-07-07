package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Militare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ComEditPersonale implements Comando
{

    public static final String cod = "3";
    public static final String descr = "Modifica Personale";

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
        System.out.println("- - - EASY MILITARY - AGGIORNA I DATI DI UN MILITARE - - -");
        Militare m = ricerca();
        if(m == null)
        {
            System.out.println("Nessun militare presente in memoria....");
        }
        else
        {
            int s;
            do
            {
                System.out.println("1) Modifica dati anagrafici\n2) Modifica grado\n3) Modifica abilitazione alle armi ");
                s = Integer.parseInt(scannerIO.nextLine());
            }while (s<1 || s>3);
            if(s == 1)
            {
                System.out.println("Nome: " + m.getNome());
                System.out.println("Inserisci nome aggiornato: ");
                String nome = scannerIO.nextLine();
                m.setNome(nome);
                System.out.println("Cognome: " + m.getCognome());
                System.out.println("Inserisci cognome aggiornato: ");
                String cognome = scannerIO.nextLine();
                m.setCognome(cognome);
                System.out.println("Nato a " + m.getLuogo_nascita() + " il " + m.getData_nascita());
                System.out.println("Inserisci luogo di nascita aggiornato: ");
                String luogo = scannerIO.nextLine();
                try
                {
                    System.out.println("Inserisci data di nascita aggiornata: [dd/MM/yyyy]");
                    String data = scannerIO.nextLine();
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                    m.setNascita(date,luogo);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                    System.out.println("Inserimento della data non riuscito");
                }
                System.out.println("Vuoi confermare l'aggiornamento del seguente elemento: \n"+m.toString());
                System.out.println("[S / N]");
                String conf =  scannerIO.nextLine();
                if(conf.equals("s")||conf.equals("S"))
                {
                    System.out.println("Confermato!!");
                    EasyMilitary em = EasyMilitary.getInstance();
                    if(em.updMilitare(m, 1))  //1 per update anagrafico
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
            if (s == 2)
            {
                System.out.println("Grado attuale: " + Menu.descr(m.getGrado(),Menu.GRADI));
                System.out.println("Seleziona il grado: ");
                int i, grado;
                int l=Menu.getCom(Menu.GRADI).length;
                do{
                    System.out.println(Menu.elencoCom(Menu.GRADI));
                    grado  = Integer.parseInt(scannerIO.nextLine());
                }while(grado < 0 || grado > l-1);
                m.setGrado(grado);
                System.out.println("Vuoi confermare l'aggiornamento del seguente elemento: \n"+m.toString());
                System.out.println("[S / N]");
                String conf =  scannerIO.nextLine();
                if(conf.equals("s")||conf.equals("S"))
                {
                    System.out.println("Confermato!!");
                    EasyMilitary em = EasyMilitary.getInstance();
                    if(em.updMilitare(m, 2))  //1 per update grado
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
            if (s==3)
            {
                System.out.println("Attualmente abilitato alle armi: " + m.isAb_armi());
                m.setArmi(!m.isAb_armi());
                System.out.println("Vuoi confermare l'aggiornamento del seguente elemento: \n"+m.toString());
                System.out.println("[S / N]");
                String conf =  scannerIO.nextLine();
                if(conf.equals("s")||conf.equals("S"))
                {
                    System.out.println("Confermato!!");
                    EasyMilitary em = EasyMilitary.getInstance();
                    if(em.updMilitare(m, 3))  //3 per update abil. armi
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

    }

    private Militare ricerca()
    {
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        Scanner scannerIO = new Scanner(System.in);
        int s;
        do{
            System.out.println("RICERCARE IL MILITARE DA AGGIORNARE\n1) Ricerca per Matricola\n2) Ricerca per Cognome ");
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