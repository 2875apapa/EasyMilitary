package com.apapa.commands;

public class ParserCom
{
    private Menu comandi;
    private static ParserCom singleton;

    public ParserCom()
    {
        comandi = new Menu();
    }

    public static ParserCom getInstance()
    {
        if (singleton == null)
        {
            singleton = new ParserCom();
        }
        return singleton;
    }

    public Comando getComando(int console, int scelta)
    {
        String p = Integer.toString(scelta);
        Comando comando = null;

        if(comandi.comValido(p,console))
        {
            /* CONSOLE PRINCIPALE */
            if (console == Menu.INIZIALE)
            {
                if (p.equals("1"))
                    comando = new ComFureria();
                if (p.equals("2"))
                    comando = new ComPersonale();
                if (p.equals("3"))
                    comando = new ComAutosezione();
            }

            /* CONSOLE FURERIA */
            if (console == Menu.FURERIA)
            {
                if (p.equals("1"))
                    comando = new ComNuovoServizio();
                if (p.equals("2"))
                    comando = new ComNuovaComandata();
                if (p.equals("3"))
                    comando = new ComViewServizi();
                if (p.equals("4"))
                    comando = new ComViewCriticita();
                if (p.equals("5"))
                    comando = new ComCambioTurno();
                if (p.equals("6"))
                    comando = new ComCambioMezzo();
                if (p.equals("7"))
                    comando = new ComInfo();
                if (p.equals("8"))
                    comando = new ComNewTipoServizio();
            }

            /* CONSOLE PERSONALE */
            if (console == Menu.PERSONALE)
            {
                if (p.equals("1"))
                    comando = new ComAddPersonale();
                if (p.equals("2"))
                    comando = new ComAssenze();
                if (p.equals("3"))
                    comando = new ComEditPersonale();
                if (p.equals("4"))
                    comando = new ComEliminaPersonale();
                if (p.equals("5"))
                    comando = new ComRicercaPersonale();
            }

            /* CONSOLE AUTOSEZIONE */
            if (console == Menu.AUTOSEZIONE)
            {
                if (p.equals("1"))
                    comando = new ComNewPatenti();
                if (p.equals("2"))
                    comando = new ComAddMezzo();
                if (p.equals("3"))
                    comando = new ComEditMezzo();
                if (p.equals("4"))
                    comando = new ComEliminaMezzo();
                if (p.equals("5"))
                    comando = new ComRicercaMezzo();
            }

            /* TORNA AL MENU' PRECEDENTE O ESCI */
            if (p.equals("0"))
                comando = new ComEsci();
        }
        else
            comando = new ComNonValido();

        return comando;
    }

}
