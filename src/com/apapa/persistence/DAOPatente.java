package com.apapa.persistence;

import com.apapa.classes.Militare;
import com.apapa.classes.Patente;
import com.apapa.interfaces.PatenteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPatente implements PatenteDAO
{

    private static DAOPatente singleton;

    public static DAOPatente getInstance()
    {
        if (singleton == null)
            singleton = new DAOPatente();
        return singleton;
    }

    private DAOPatente()
    {
    }

    @Override
    public void save(Patente patente)
    {
        Connessione con = null;
        try
        {
            con = Connessione.getInstance();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Errore nel recupero della connessione col DB");
        }
        Connection c = con.getConnessione();
        try {
            // Creazione della query di inserimento
            String query = "INSERT INTO patenti (militare, tipo_patente, data_conseguimento, data_scadenza) VALUES (?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setString(1, patente.getMatricola());
            s.setInt(2, patente.getTipo_patente());
            s.setDate(3, new java.sql.Date(patente.getDataConseguimento().getTime()));
            s.setDate(4, new java.sql.Date(patente.getDataScadenza().getTime()));
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifica(String matricola, int modello)
    {
        Connessione con = null;
        try
        {
            con = Connessione.getInstance();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Errore nel recupero della connessione col DB");
        }
        Connection c = con.getConnessione();
        try {
            // Creazione della query di inserimento
            String query = "SELECT * FROM patenti WHERE militare = ? AND tipo_patente = ?";
            PreparedStatement s = c.prepareStatement(query);
            s.setString(1, matricola);
            s.setInt(2, modello);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            if(resultSet.next())
                return true;
            else
                return false;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
