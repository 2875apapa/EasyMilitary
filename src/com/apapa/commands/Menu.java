package com.apapa.commands;

public class Menu
{
    public static final int INIZIALE = 0;
    public static final int FURERIA = 1;
    public static final int PERSONALE = 2;
    public static final int AUTOSEZIONE = 3;
    public static final int GRADI = 4;

    // MENU' INIZIALE
    private static final String comandiIniziale[][] = {
            {ComFureria.cod,ComFureria.descr},
            {ComPersonale.cod,ComPersonale.descr},
            {ComAutosezione.cod, ComAutosezione.descr},
            {ComEsci.cod, ComEsci.descr}
    };

    // MENU' FURERIA
    private static final String comandiFureria[][] = {
            {ComNuovoServizio.cod,ComNuovoServizio.descr},
            {ComNuovaComandata.cod,ComNuovaComandata.descr},
            {ComViewServizi.cod, ComViewServizi.descr},
            {ComViewCriticita.cod, ComViewCriticita.descr},
            {ComCambioTurno.cod, ComCambioTurno.descr},
            {ComCambioMezzo.cod, ComCambioMezzo.descr},
            {ComInfo.cod, ComInfo.descr},
            {ComNewTipoServizio.cod, ComNewTipoServizio.descr},
            {ComEsci.cod, ComEsci.descr}
    };

    // MENU' PERSONALE
    private static final String comandiPersonale[][] = {
            {ComAddPersonale.cod,ComAddPersonale.descr},
            {ComAssenze.cod,ComAssenze.descr},
            {ComEditPersonale.cod, ComEditPersonale.descr},
            {ComEliminaPersonale.cod, ComEliminaPersonale.descr},
            {ComRicercaPersonale.cod, ComRicercaPersonale.descr},
            {ComEsci.cod, ComEsci.descr}
    };



    // MENU' AUTOSEZIONE
    private static final String comandiAuto[][] = {
            {ComNewPatenti.cod,ComNewPatenti.descr},
            {ComAddMezzo.cod,ComAddMezzo.descr},
            {ComEditMezzo.cod, ComEditMezzo.descr},
            {ComEliminaMezzo.cod, ComEliminaMezzo.descr},
            {ComRicercaMezzo.cod, ComRicercaMezzo.descr},
            {ComEsci.cod, ComEsci.descr}
    };


    // GRADI MILITARE
    private static final String gradi[][] = {
            {"0","Soldato"},
            {"1","Caporale"},
            {"2","Caporal Maggiore"},
            {"3","1° Caporal Maggiore"},
            {"4","Caporal Maggiore Scelto"},
            {"5","Caporal Maggiore Capo"},
            {"6","Caporal Maggiore Capo Scelto"},
            {"7","Sergente"},
            {"8","Sergente Maggiore"},
            {"9","Sergente Maggiore Capo"},
            {"10","Maresciallo"},
            {"11","Maresciallo Ordinario"},
            {"12","Maresciallo Capo"},
            {"13","Primo Maresciallo"},
            {"14","Luogotenente"},
            {"15","Sottotenente"},
            {"16","Tenente"},
            {"17","Capitano"},
            {"18","Maggiore"},
            {"19","Tenente Colonello"},
            {"20","Colonello"}
    };


    public static String[][] getCom(int console)
    {
        String com[][]=null;
        switch (console)
        {
            case INIZIALE: com = comandiIniziale; break;
            case FURERIA: com = comandiFureria; break;
            case PERSONALE: com = comandiPersonale; break;
            case AUTOSEZIONE: com = comandiAuto; break;
            case GRADI: com = gradi; break;
        };
        return com;
    }

    private static String comando(int i, int console)
    {
        String comandi[][]= getCom(console);
        return " " + comandi[i][0] + ")" + comandi[i][1];
    }


    public static String elencoCom(int console)
    {
        int i=0;
        StringBuffer elenco = new StringBuffer();
        String comandi[][]=getCom(console);
        int k = comandi.length;
        for (i=0; i<k-1; i++)
        {
            elenco.append(comando(i,console));
            elenco.append("\n");
        }
        elenco.append(comando(i,console));
        return elenco.toString();
    }


    public static String descr(int i, int console)
    {
        String comandi[][]= getCom(console);
        return comandi[i][1];
    }



    public boolean comValido(String stringa, int console)
    {
        String comandi[][]= getCom(console);
        int k = comandi.length;
        for(int i = 0; i < k; i++)
        {
            if(comandi[i][0].equals(stringa))
                return true;
        }
        return false;
    }
}
