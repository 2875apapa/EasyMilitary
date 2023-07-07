package com.apapa.classes;

import com.apapa.commands.Menu;
import com.apapa.interfaces.ModelBuilderMilitare;

public class Militare extends ModelBuilderMilitare
{


    public Militare build()
    {
        Militare militare = new Militare();
        militare.matricola = this.matricola;
        militare.cognome = this.cognome;
        militare.nome = this.nome;
        militare.data_nascita = this.data_nascita;
        militare.luogo_nascita = this.luogo_nascita;
        militare.grado = this.grado;
        militare.ab_armi = this.ab_armi;
        return militare;
    }

    @Override
    public String toString()
    {
        return "Militare {" +
                "matricola='" + matricola + '\'' +
                ", cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", data_nascita=" + data_nascita +
                ", luogo_nascita='" + luogo_nascita + '\'' +
                ", grado=" + Menu.descr(grado,Menu.GRADI) + '\'' +
                ", ab_armi=" + ab_armi +
                '}';
    }

    public String toStringRidu()
    {
        return "Militare {" +
                "matricola='" + matricola + '\'' +
                ", cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", data_nascita=" + data_nascita +
                '}';
    }

}
