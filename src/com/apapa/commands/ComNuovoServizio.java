package com.apapa.commands;

import com.apapa.classes.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ComNuovoServizio implements Comando
{

    public static final String cod = "1";
    public static final String descr = "Crea un nuovo servizio";

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
        System.out.println("- - - EASY MILITARY - CREA UN NUOVO SERVIZIO - - -");
        System.out.println("....recupero dal DB i tipi di servizio esistenti....");
        List<TipoServizio> lts = EasyMilitary.getInstance().lts();
        if(lts== null)
        {
            System.out.println("Errore nel recupero dati........");
        }
        else if(lts.size()<1)
        {
            System.out.println("Nessun tipo servizio e' stato ancora creato......");
        }
        else
        {
            int k =1;
            for (TipoServizio ts : lts)
            {
                System.out.println(k + ") "+ ts.getNome_servizio());
                k++;
            }
            do {
                System.out.println("Selezionare il tipo di servizio da programmare: ");
                k = Integer.parseInt(scannerIO.nextLine());
            }while(k<1 || k >lts.size());
            //System.out.println("ok");
            TipoServizio tstemp = lts.get(k-1);
            //Servizio s = new Servizio(tstemp.getId(),tstemp.getNome_servizio(),tstemp.getDurata(),tstemp.getRiposo(),tstemp.getNum_militari(),tstemp.getNum_mezzi(), tstemp.isArmato());
            Servizio s = EasyMilitary.getInstance().newServizio(tstemp.getId(),tstemp.getNome_servizio(),tstemp.getDurata(),tstemp.getRiposo(),tstemp.getNum_militari(),tstemp.getNum_mezzi(), tstemp.isArmato());
            s = EasyMilitary.getInstance().addGiorniServizio(s, tstemp.getLgiorni());

            Date inizio = null;

            try
            {
                System.out.println("Inserisci data di inizio: [dd/MM/yyyy]");
                String data = scannerIO.nextLine();
                inizio = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                s = EasyMilitary.getInstance().setDateServizio(s, inizio);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
                System.out.println("Inserimento della data non riuscito");
            }
            System.out.println(s.info_base());
            int i;
            List<MilitareServizio> militari = new ArrayList<MilitareServizio>();
            for(i=0; i<s.getNum_militari();i++)
            {
                boolean a = false;
                String matricola = "";
                do {
                    System.out.println(tstemp.getLruoli().get(i).toString());
                    System.out.println("Inserisci il numero di matricola del militare da assegnare: ");
                    matricola = scannerIO.nextLine();
                    a = EasyMilitary.getInstance().checkMilServ(matricola, s.getData_inizio(), s.getData_fine(),tstemp.getLruoli().get(i).getGrado(),s.isArmato(),tstemp.getLruoli().get(i).isGuida());
                }while(!a);
                MilitareServizio ms = new MilitareServizio();
                ms.setMatricola(matricola).setRuolo(tstemp.getLruoli().get(i).getNome()).setIdRuolo(tstemp.getLruoli().get(i).getId()).build();
                boolean control = true;
                for(MilitareServizio mx : militari)
                {
                    if(mx.getMatricola().equals(ms.getMatricola()))
                    {
                        control = false;
                    }
                }
                if(control)
                {
                    System.out.println("Assegno "+ matricola +" al servizio!");
                    militari.add(ms);
                }
                else
                {
                    System.out.println("Il militare con matricola "+ matricola +" e' stato gia' assegnato al servizio in altro ruolo!");
                    i=i-1;
                }
            }
            s.setElencoMilitari(militari);
            List<Mezzo> mezzi = new ArrayList<Mezzo>();
            for(i=0; i<s.getNum_mezzi();i++)
            {
                boolean a = false;
                String targa = "";
                do {
                    System.out.println("Inserisci il numero di targa del mezzo da utilizzare: " + (i+1) +" di "+s.getNum_mezzi());
                    targa = scannerIO.nextLine();
                    a = EasyMilitary.getInstance().checkMezServ(targa, s.getData_inizio(), s.getData_fine());
                }while(!a);
                boolean control = true;
                for(Mezzo mx : mezzi)
                {
                    if(mx.getTarga().equals(targa))
                    {
                        control = false;
                    }
                }
                if(control)
                {
                    System.out.println("Assegno "+ targa +" al servizio!");
                    Mezzo m = EasyMilitary.getInstance().findMezzo(targa);
                    mezzi.add(m);
                }
                else
                {
                    System.out.println("Il mezzo con targa "+ targa +" e' stato gia' assegnato al servizio in precedenza!");
                    i=i-1;
                }
            }
            s.setElencoMezzi(mezzi);
            System.out.println("Vuoi confermare la calendarizzazione del seguente servizio: \n");
            s.reportFinale();
            System.out.println("[S / N]");
            String conf =  scannerIO.nextLine();
            if(conf.equals("s")||conf.equals("S"))
            {
                System.out.println("Confermato!!");
                if(EasyMilitary.getInstance().addServizio(s))
                {
                    s = null;
                    System.gc();
                }
            }
            else
            {
                System.out.println("Non Confermato!!");
                s = null;
                System.gc();
            }
        }

    }
}
