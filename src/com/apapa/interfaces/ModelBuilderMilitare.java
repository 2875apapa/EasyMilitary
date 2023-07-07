package com.apapa.interfaces;

import com.apapa.classes.Militare;

import java.util.Date;

public abstract class ModelBuilderMilitare
{
        protected String matricola;
        protected String cognome;
        protected String nome;
        protected Date data_nascita;
        protected String luogo_nascita;
        protected int grado;
        protected boolean ab_armi;

        public ModelBuilderMilitare()
        {
            this.matricola = "";
            this.cognome = "";
            this.nome = "";
            this.data_nascita = null;
            this.luogo_nascita = "";
            this.grado = 0;
            this.ab_armi = false;
        }

        public ModelBuilderMilitare setMatricola (String matricola)
        {
            this.matricola = matricola;
            return this;
        }

        public ModelBuilderMilitare setCognome (String cognome)
        {
            this.cognome = cognome;
            return this;
        }

        public ModelBuilderMilitare setNome (String nome)
        {
            this.nome = nome;
            return this;
        }

        public ModelBuilderMilitare setNascita (Date data, String luogo)
        {
            this.data_nascita = data;
            this.luogo_nascita = luogo;
            return this;
        }

    public ModelBuilderMilitare setDataNascita (Date data)
    {
        this.data_nascita = data;
        return this;
    }

        public ModelBuilderMilitare setGrado (int grado)
        {
            this.grado = grado;
            return this;
        }

        public ModelBuilderMilitare setArmi (boolean ab_armi)
        {
            this.ab_armi = ab_armi;
            return this;
        }


    public String getMatricola() {
        return matricola;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public Date getData_nascita() {
        return data_nascita;
    }

    public String getLuogo_nascita() {
        return luogo_nascita;
    }

    public int getGrado() {
        return grado;
    }

    public boolean isAb_armi() {
        return ab_armi;
    }

    public abstract Object build();

}
