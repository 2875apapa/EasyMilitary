package com.apapa.classes;

import com.apapa.commands.Menu;

public class Ruolo
{
    private int id;
    private String nome;
    private int grado;
    private boolean guida;

    public Ruolo(int id, String nome, int grado, boolean guida)
    {
        this.id = id;
        this.nome = nome;
        this.grado = grado;
        this.guida = guida;
    }

    public Ruolo(String nome, int grado, boolean guida)
    {
        this.nome = nome;
        this.grado = grado;
        this.guida = guida;
    }

    public Ruolo()
    {
    }

    public String getNome() {
        return nome;
    }

    public int getGrado() {
        return grado;
    }

    public boolean isGuida() {
        return guida;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public void setGuida(boolean guida) {
        this.guida = guida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ruolo{" +
                "nome='" + nome + '\'' +
                ", grado minimo=" + Menu.descr(grado,Menu.GRADI) + '\'' +
                ", guida=" + guida +
                '}';
    }
}
