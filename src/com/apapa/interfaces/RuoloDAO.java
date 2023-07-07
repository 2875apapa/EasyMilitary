package com.apapa.interfaces;

import com.apapa.classes.Ruolo;

import java.util.List;

public interface RuoloDAO {
    void add(Ruolo r, int id);
    List<Ruolo> getlr(int id);
}
