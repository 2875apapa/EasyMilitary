package com.apapa.persistence;

import com.apapa.classes.Mezzo;
import com.apapa.classes.Militare;
import com.apapa.interfaces.MezzoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMezzo implements MezzoDAO
{
    private static DAOMezzo singleton;

    public static DAOMezzo getInstance()
    {
        if (singleton == null)
            singleton = new DAOMezzo();
        return singleton;
    }

    private DAOMezzo() {}


    @Override
    public void save(Mezzo mezzo)
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
            String query = "INSERT INTO mezzo (targa, marca, modello, tipo, patente, stato, inuse) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setString(1, mezzo.getTarga());
            s.setString(2,mezzo.getMarca());
            s.setString(3, mezzo.getModello());
            s.setBoolean(4,mezzo.isTipo());
            s.setInt(5,mezzo.getPatente());
            s.setBoolean(6,mezzo.isStato());
            s.setBoolean(7, true);
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String targa, boolean stato)
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
                String query = "UPDATE mezzo SET stato = ? WHERE targa = ?";
                // Creazione della query di update
                PreparedStatement s = c.prepareStatement(query);
                // Impostazione dei parametri della query
                s.setBoolean(1, stato);
                s.setString(2, targa);
                // Esecuzione della query
                s.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String targa)
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
            String query = "UPDATE mezzo SET inuse = false  WHERE targa = ?";
            // Creazione della query
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setString(1, targa);
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Mezzo> getAll()
    {
        {
            List<Mezzo> ml = new ArrayList<Mezzo>();
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
                String query = "SELECT targa, marca, modello, tipo, patente, stato FROM mezzo WHERE inuse = true";
                PreparedStatement s = c.prepareStatement(query);
                // Esecuzione della query
                ResultSet resultSet = s.executeQuery();
                // Recupero dei dati dal ResultSet
                while (resultSet.next())
                {
                    Mezzo m = new Mezzo();
                    m.setTarga(resultSet.getString("targa"));
                    m.setMarca(resultSet.getString("marca"));
                    m.setModello(resultSet.getString("modello"));
                    m.setTipo(resultSet.getBoolean("tipo"));
                    m.setPatente(resultSet.getInt("patente"));
                    m.setStato(resultSet.getBoolean("stato"));
                    ml.add(m);
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            return ml;
        }
    }
}
