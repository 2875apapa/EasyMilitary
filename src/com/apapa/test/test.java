package com.apapa.test;

import static org.junit.Assert.assertEquals;

import com.apapa.classes.*;
import org.junit.Test;


import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;



public class test
{

    public static String generateMatricola(int length)
    {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++)
        {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }
        return randomString.toString();
    }

    @Test
    public void testAddMilitare()
    {
        //Test inserimento nuovo militare
        Militare m = new Militare();
        m.setMatricola(generateMatricola(8));
        m.setCognome("Rossi");
        m.setNome("Scuro");
        m.setNascita(new Date(),"Catania");
        m.setGrado(1);
        m.setArmi(true);
        Patente p1 = new Patente(m.getMatricola(), 2, new Date(), new Date());
        Patente p2 = new Patente(m.getMatricola(), 3, new Date(), new Date());
        List <Patente> lp = new ArrayList<Patente>();
        lp.add(p1);
        lp.add(p2);
        boolean result = EasyMilitary.getInstance().addMilitare(m,lp);
        System.out.println(m.getMatricola());
        assertEquals(true, result);
    }

    @Test
    public void testDeleteMilitare()
    {
        boolean result = EasyMilitary.getInstance().deleteMilitare("kCuvrt9Z");
        assertEquals(true, result);
    }

    @Test
    public void testAddTipoServizio()
    {
        List <Ruolo> lruolo = new ArrayList<Ruolo>();
        List<Giorno> lgiorni = new ArrayList<Giorno>();
        lruolo.add(new Ruolo("Prov1", 0, false));
        lgiorni.add(new Giorno(1, Time.valueOf("08:00:00"),Time.valueOf("10:00:00")));
        TipoServizio ts = EasyMilitary.getInstance().newTipoServizio("ProvaTest",false,1,0,1,1,lruolo,lgiorni);
        boolean result = EasyMilitary.getInstance().addTipoServizio(ts);
        assertEquals(true,result);
    }

    @Test
    public void testAddServizio()
    {
        List<Giorno> lgiorni = new ArrayList<Giorno>();
        lgiorni.add(new Giorno(1, Time.valueOf("08:00:00"),Time.valueOf("10:00:00")));
        List<MilitareServizio> militari = new ArrayList<MilitareServizio>();
        MilitareServizio ms = new MilitareServizio();
        ms.setMatricola("dvsjWhac");
        ms.setIdRuolo(19);
        ms.setRuolo("Prov1");
        militari.add(ms);
        List<Mezzo> mezzi = new ArrayList<Mezzo>();
        Mezzo m = EasyMilitary.getInstance().findMezzo("EI123MM");
        mezzi.add(m);
        Servizio s = new Servizio(7, "Prov1", 1, 0, 1, 1, false);
        s = EasyMilitary.getInstance().addGiorniServizio(s, lgiorni);
        s = EasyMilitary.getInstance().setDateServizio(s, new Date());
        s.setElencoMilitari(militari);
        s.setElencoMezzi(mezzi);
        boolean result = EasyMilitary.getInstance().addServizio(s);
    }


}
