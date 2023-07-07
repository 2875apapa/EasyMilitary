package com.apapa.interfaces;

import com.apapa.classes.Assenza;

import java.util.List;

public interface AssenzaDAO
{
    int save (Assenza a);
    List<Assenza> getAll();
}
