package com.apapa;

import com.apapa.classes.EasyMilitary;
import com.apapa.commands.EMConsole;

public class Main
{

    public static void main(String[] args)
    {
        //inizializza singleton
        EasyMilitary em = EasyMilitary.getInstance();

        EMConsole emc = new EMConsole();
        emc.start();
    }
}
