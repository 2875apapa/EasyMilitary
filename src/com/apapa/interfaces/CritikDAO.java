package com.apapa.interfaces;

import com.apapa.classes.Critik;

import java.util.List;

public interface CritikDAO
{
    List<Critik> getMS();
    List<Critik> getMzS();
    List<Critik> getMC();
    List<Critik> getMzC();
}
