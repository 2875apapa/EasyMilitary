package com.apapa.persistence;

import com.apapa.classes.*;
import com.apapa.interfaces.TipoServizioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTipoServizio implements TipoServizioDAO {

    private static DAOTipoServizio singleton;

    public static DAOTipoServizio getInstance()
    {
        if (singleton == null)
            singleton = new DAOTipoServizio();
        return singleton;
    }

    private DAOTipoServizio() {}

    @Override
    public int add(TipoServizio tiposervizio)
    {
        int id = -1;
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
            String query = "INSERT INTO tiposervizio (nome_servizio, armato, durata, riposo, num_militari, num_mezzi) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setString(1, tiposervizio.getNome_servizio());
            s.setBoolean(2, tiposervizio.isArmato());
            s.setInt(3,tiposervizio.getDurata());
            s.setInt(4,tiposervizio.getRiposo());
            s.setInt(5,tiposervizio.getNum_militari());
            s.setInt(6,tiposervizio.getNum_mezzi());
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try {
            // Creazione della query di selezione
            String query = "SELECT LAST_INSERT_ID()";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione del parametro della query
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            if (resultSet.next())
                id=resultSet.getInt(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<TipoServizio> getAll()
    {
        List<TipoServizio> tsl = new ArrayList<TipoServizio>();
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
            // Creazione della query di ricerca
            String query = "SELECT * FROM tiposervizio";
            PreparedStatement s = c.prepareStatement(query);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                TipoServizio ts = new TipoServizio();
                ts.setId(resultSet.getInt("id"));
                ts.setNome_servizio(resultSet.getString("nome_servizio"));
                ts.setArmato(resultSet.getBoolean("armato"));
                ts.setDurata(resultSet.getInt("durata"));
                ts.setRiposo(resultSet.getInt("riposo"));
                ts.setNum_militari(resultSet.getInt("num_militari"));
                ts.setNum_mezzi(resultSet.getInt("num_mezzi"));
                tsl.add(ts);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return tsl;
    }
}
