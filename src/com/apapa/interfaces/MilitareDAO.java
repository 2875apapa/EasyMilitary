package com.apapa.interfaces;

import com.apapa.classes.Militare;

import java.util.List;

public interface MilitareDAO
{
    void save(Militare militare);
    void update(Militare militare, int type);
    void delete(String matricola);
    Militare getByMatricola(String matricola);
    List<Militare> getAll();
    Militare gag(String matricola, boolean guida);
}
