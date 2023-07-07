package com.apapa.interfaces;

import com.apapa.classes.Servizio;

import java.util.List;

public interface ServizioDAO
{
    int add(Servizio s);
    void addMezzo(int id, String targa);
    void addMilitare(int id, String matricola, String ruolo);
    List<Servizio> getAll();
    boolean addCritMili(int id, String matricola);
    boolean addCritMezzo(int id, String targa);
    boolean dropCritickMz(int id, String targa);
    void changeMilitare(int id, String old, String nuova);
    void changeMezzo(int id, String old, String nuova);
}
