package com.apapa.interfaces;

import com.apapa.classes.Comandata;

import java.util.List;

public interface ComandataDAO
{
    int add(Comandata c);
    void addMilitare(int id, String matricola);
    List<Comandata> getAll();
    boolean addCritMili(int id, String matricola);
    boolean addCritMezzo(int id, String targa);
    boolean dropCritickMz(int id, String targa);
    void changeMilitare(int id, String old, String nuova);
    void changeMezzo(int id, String old, String nuova);
}
