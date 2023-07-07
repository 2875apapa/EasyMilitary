package com.apapa.interfaces;

import com.apapa.classes.Militare;
import com.apapa.classes.Patente;

public interface PatenteDAO
{
    void save(Patente patente);
    boolean verifica(String matrico, int modello);
}
