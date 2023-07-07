package com.apapa.commands;

import com.apapa.classes.EasyMilitary;

import java.util.Scanner;

public class EMConsole
{
    Scanner scannerIO = new Scanner(System.in);
    public void start()
    {
        EasyMilitary em = EasyMilitary.getInstance();
        messIniziale();
        int scelta = scannerIO.nextInt();
        Comando com = ParserCom.getInstance().getComando(Menu.INIZIALE,scelta);
        while (!com.getCodice().equals("0"))
        {
            com.esegui();
            System.out.println();
            if(!com.getCodice().equals("-1"))
            {
                int type = em.getTipoUtente().getT();
                com = printMenu(type);
            }
            else
            {
                messIniziale();
                scelta = scannerIO.nextInt();
                com = ParserCom.getInstance().getComando(Menu.INIZIALE,scelta);
            }
        }
        com.esegui(); // Comando esci
        System.out.println(" Arrivederci...");
    }

    private void messIniziale()
    {
        System.out.println("- - - EASY MILITARY - - -");
        System.out.println(Menu.elencoCom(Menu.INIZIALE));
        System.out.println("Seleziona: ");
    }

    private void messFureria()
    {
        System.out.println("- - - EASY MILITARY - FURERIA - - -");
        System.out.println(Menu.elencoCom(Menu.FURERIA));
        System.out.println("Seleziona: ");
    }

    private void messPersonale()
    {
        System.out.println("- - - EASY MILITARY - PERSONALE - - -");
        System.out.println(Menu.elencoCom(Menu.PERSONALE));
        System.out.println("Seleziona: ");
    }

    private void messAuto()
    {
        System.out.println("- - - EASY MILITARY - AUTOSEZIONE - - -");
        System.out.println(Menu.elencoCom(Menu.AUTOSEZIONE));
        System.out.println("Seleziona: ");

    }

    private Comando printMenu(int scelta)
    {
        int nscelta;
        Comando com2 = null;
        do{
            switch (scelta)
            {
                case 1:
                    messFureria();
                    nscelta = scannerIO.nextInt();
                    com2 = ParserCom.getInstance().getComando(Menu.FURERIA,nscelta);
                    com2.esegui();
                    break;
                case 2:
                    messPersonale();
                    nscelta = scannerIO.nextInt();
                    com2 = ParserCom.getInstance().getComando(Menu.PERSONALE,nscelta);
                    com2.esegui();
                    break;
                case 3:
                    messAuto();
                    nscelta = scannerIO.nextInt();
                    com2 = ParserCom.getInstance().getComando(Menu.AUTOSEZIONE,nscelta);
                    com2.esegui();
                    break;
            };
        }while(!com2.getCodice().equals("0"));
        return ParserCom.getInstance().getComando(Menu.INIZIALE,0);
    }
}
