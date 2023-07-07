package com.apapa.persistence;

import com.apapa.classes.Assenza;
import com.apapa.interfaces.AssenzaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOAssenza implements AssenzaDAO
{
    private static DAOAssenza singleton;

    public static DAOAssenza getInstance()
    {
        if (singleton == null)
            singleton = new DAOAssenza();
        return singleton;
    }

    private DAOAssenza() {}


    @Override
    public int save(Assenza a)
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
            String query = "INSERT INTO assenze (tipo, data_inizio, data_fine, militare) VALUES (?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setString(1, a.getTipo());
            s.setDate(2, new java.sql.Date(a.getDinizio().getTime()));
            s.setDate(3, new java.sql.Date(a.getDfine().getTime()));
            s.setString(4, a.getMilitare());
            // Esecuzione della query
            s.executeUpdate();
        }
        catch (SQLException e)
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
    public List<Assenza> getAll()
    {
        List<Assenza> al = new ArrayList<Assenza>();
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
            // Creazione della query
            String query = "SELECT * FROM assenze";
            PreparedStatement s = c.prepareStatement(query);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                Assenza a = new Assenza();
                a.setId(resultSet.getInt(1));
                a.setTipo(resultSet.getString(2));
                a.setDinizio(resultSet.getDate(3));
                a.setDfine(resultSet.getDate(4));
                a.setMilitare(resultSet.getString(5));
                al.add(a);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return al;
    }
}
