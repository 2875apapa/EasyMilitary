package com.apapa.interfaces;

import com.apapa.classes.Giorno;

import java.util.List;

public interface GiornoDAO {
    void add (Giorno g, int id);
    List<Giorno> getlg (int id);
}
