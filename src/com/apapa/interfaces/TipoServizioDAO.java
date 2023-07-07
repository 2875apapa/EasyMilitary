package com.apapa.interfaces;

import com.apapa.classes.TipoServizio;

import java.util.List;

public interface TipoServizioDAO
{
    int add (TipoServizio tiposervizio);
    List<TipoServizio> getAll();
}
