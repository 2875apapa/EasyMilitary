package com.apapa.classes;

import com.apapa.interfaces.TipoUtente;

public class UtentePersonale implements TipoUtente
{
    int t = 2;

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
