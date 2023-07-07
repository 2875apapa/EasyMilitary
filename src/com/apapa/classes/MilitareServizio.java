package com.apapa.classes;

import com.apapa.interfaces.ModelBuilderMilitare;

public class MilitareServizio extends ModelBuilderMilitare
{

    private int idRuolo;
    private String ruolo;

    public MilitareServizio build()
    {
        MilitareServizio militare = new MilitareServizio();
        militare.matricola = this.matricola;
        militare.idRuolo = this.idRuolo;
        militare.ruolo = this.ruolo;
        return militare;
    }

    public int getIdRuolo() {
        return idRuolo;
    }

    public MilitareServizio setIdRuolo(int idRuolo) {
        this.idRuolo = idRuolo;
        return this;
    }

    public String getRuolo() {
        return ruolo;
    }

    public MilitareServizio setRuolo(String ruolo) {
        this.ruolo = ruolo;
        return this;
    }

    public MilitareServizio setMatricola(String matricola) {
        this.matricola = matricola;
        return this;
    }

}
