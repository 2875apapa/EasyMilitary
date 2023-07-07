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

public class ComAssenze implements Comando
{

    public static final String cod = "2";
    public static final String descr = "Inserisci assenze del personale";

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
        System.out.println("- - - EASY MILITARY - GESTISCI ASSENZE DEL PERSONALE - - -");
        Militare m = ricerca();
        if(m == null)
        {
            System.out.println("Nessun militare presente in memoria....");
        }
        else
        {
            String matricola = m.getMatricola();
            int s;
            do
            {
                System.out.println("1) Nuova Licenza\n2) Periodo di Malattia\n3) Assenza giustificata ");
                s = Integer.parseInt(scannerIO.nextLine());
            }while (s<1 || s>3);
            Date dinizio = null;
            Date dfine = null;
            try
            {
                System.out.println("Inserisci data d'inizio del periodo di assenza: [dd/MM/yyyy]");
                String data = scannerIO.nextLine();
                dinizio = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                System.out.println("Inserisci data di fine del periodo di assenza: [dd/MM/yyyy]");
                data = scannerIO.nextLine();
                dfine = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            }
            catch (ParseException e)
            {
                System.out.println("Errore - Inserimento date non riuscito");
                e.printStackTrace();
            }

            boolean a = EasyMilitary.getInstance().checkAssenze(matricola, dinizio, dfine, s);
            //System.out.println(a);

        }
    }

    private Militare ricerca()
    {
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        Scanner scannerIO = new Scanner(System.in);
        int s;
        do{
            System.out.println("RICERCARE IL MILITARE A CUI AGGIUNGERE L'ASSENZA\n1) Ricerca per Matricola\n2) Ricerca per Cognome ");
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
