package com.apapa.commands;

import com.apapa.classes.EasyMilitary;
import com.apapa.classes.Giorno;
import com.apapa.classes.Ruolo;
import com.apapa.classes.TipoServizio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.*;

public class ComNewTipoServizio implements Comando
{

    public static final String cod = "8";
    public static final String descr = "Crea un nuovo tipo di servizio";

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
        List<Giorno> lgiorni = new ArrayList<Giorno>();
        List<Ruolo> lruolo = new ArrayList<Ruolo>();
        //esegui
        System.out.println("- - - EASY MILITARY - CREA UN NUOVO TIPO DI SERVIZIO - - -");
        System.out.println("Inserire il nome da dare al tipo di servizio");
        String nome = scannerIO.nextLine();
        System.out.print("Il servizio e' di tipo armato [S / N]");
        String arm =  scannerIO.nextLine();
        Boolean armato = false;
        if(arm.equals("s")||arm.equals("S"))
            armato = true;
        int n= 0;
        int r= 0;
        while(n<1)
        {
            System.out.print("Indicare il numero di giorni della durate del servizio (compresi eventuali giorni di riposo)");
            n =  Integer.parseInt(scannerIO.nextLine());
        }
        if(n > 1)
        {
            System.out.print("Indicare il numero di giorni di riposo all'interno della durata del servizio");
            r =  Integer.parseInt(scannerIO.nextLine());
        }
        for(int i=0; i<n-r; i++)
        {
            System.out.print("Inserire l'orario di inizio servizio per "+ (i+1) + "-iesimo giorno [hh:mm:ss]");
            String timeInput = scannerIO.nextLine();
            Time inizio = Time.valueOf(timeInput);
            System.out.print("Inserire l'orario di fine servizio per "+ (i+1) + "-iesimo giorno [hh:mm:ss]");
            timeInput = scannerIO.nextLine();
            Time fine = Time.valueOf(timeInput);
            lgiorni.add(new Giorno(i+1,inizio,fine));
        }
        int m=0;
        while(m<1)
        {
            System.out.print("Indicare il numero di militari richiesto per ottemperare al servizio");
            m =  Integer.parseInt(scannerIO.nextLine());
        }
        for(int i=0; i<m; i++)
        {
            System.out.print("Inserire il nuome del ruolo del "+ (i+1) + "-iesimo militare");
            String nome_ruolo = scannerIO.nextLine();
            System.out.println("Seleziona il grado minimo per il ruolo: ");
            int grado;
            int l=Menu.getCom(Menu.GRADI).length;
            do{
                System.out.println(Menu.elencoCom(Menu.GRADI));
                grado  = Integer.parseInt(scannerIO.nextLine());
            }while(grado < 0 || grado > l-1);
            System.out.print("Il servizio ruolo richiede abilitazione di guida [S / N]");
            String gui =  scannerIO.nextLine();
            Boolean guida = false;
            if(gui.equals("s")||gui.equals("S"))
                guida = true;
            lruolo.add(new Ruolo(nome_ruolo, grado, guida));
        }
        System.out.print("Indicare il numero di mezzi richiesto per questo tipo di servizio");
        int z =  Integer.parseInt(scannerIO.nextLine());

        //TipoServizio ts = new TipoServizio(nome,armato,n,r,m,z,lruolo,lgiorni);
        TipoServizio ts = EasyMilitary.getInstance().newTipoServizio(nome,armato,n,r,m,z,lruolo,lgiorni);

        System.out.println("Vuoi confermare l'inserimento del seguente tipo di servizio: \n"+ts.toString());
        System.out.println("Con ruoli:");
        for(int i=0; i<lruolo.size(); i++)
        {
            System.out.println(lruolo.get(i).toString());
        }
        System.out.println("Con orari:");
        for(int i=0; i<lgiorni.size(); i++)
        {
            System.out.println(lgiorni.get(i).toString());
        }
        System.out.println("[S / N]");
        String conf =  scannerIO.nextLine();
        if(conf.equals("s")||conf.equals("S"))
        {
            System.out.println("Confermato!!");
            EasyMilitary em = EasyMilitary.getInstance();
            if(em.addTipoServizio(ts))
            {
                ts = null;
                System.gc();
            }
        }
        else
        {
            System.out.println("Non Confermato!!");
            ts = null;
            System.gc();
        }
    }
}
