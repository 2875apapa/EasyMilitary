package com.apapa.classes;

import com.apapa.interfaces.TipoUtente;

public class UtenteAutosezione implements TipoUtente
{
    int t = 3;

    @Override
    public void Login(EasyMilitary em)
    {
        em.setTipoUtente(this);
    }

    public int getT()
    {
        return this.t;
    }
}
