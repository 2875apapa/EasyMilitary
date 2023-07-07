package com.apapa.interfaces;

import com.apapa.classes.Mezzo;

import java.util.List;

public interface MezzoDAO
{
    void save (Mezzo mezzo);
    void update (String targa, boolean stato);
    void delete (String targa);
    List<Mezzo> getAll();
}
