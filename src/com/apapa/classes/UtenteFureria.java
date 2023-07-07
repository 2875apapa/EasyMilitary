package com.apapa.classes;

import com.apapa.interfaces.TipoUtente;

public class UtenteFureria implements TipoUtente
{
    int t = 1;

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
