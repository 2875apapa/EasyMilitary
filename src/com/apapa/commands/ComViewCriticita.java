package com.apapa.commands;

import com.apapa.classes.Critik;
import com.apapa.classes.EasyMilitary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ComViewCriticita implements Comando
{

    public static final String cod = "4";
    public static final String descr = "Visualizza le criticita'";

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
        System.out.println("- - - EASY MILITARY - VISUALIZZA CRITICITA' - - -");
        System.out.println("Criticita' nei turni assegnati al personale: ");
        List <Critik> crtMili = EasyMilitary.getInstance().getAllCritikMilitari();
        if(crtMili.size()<1)
        {
            System.out.println("Nessuna criticita'");
        }
        else
        {
            for(Critik cc : crtMili)
                System.out.println(cc.Cmilitare());
        }
        System.out.println("Criticita' nei mezzi assegnati : ");
        List <Critik> crtMz = EasyMilitary.getInstance().getAllCritikMezzi();
        if(crtMz.size()<1)
        {
            System.out.println("Nessuna criticita'");
        }
        else
        {
            for(Critik cc : crtMz)
                System.out.println(cc.Cmezzo());
        }
    }
}