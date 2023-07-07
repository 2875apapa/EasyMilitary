package com.apapa.persistence;

import com.apapa.classes.Militare;
import com.apapa.interfaces.MilitareDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOMilitare implements MilitareDAO
{

    private static DAOMilitare singleton;

    public static DAOMilitare getInstance()
    {
        if (singleton == null)
            singleton = new DAOMilitare();
        return singleton;
    }

    private DAOMilitare()
    {
    }

    @Override
    public void save(Militare militare)
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
            String query = "INSERT INTO militare (matricola, cognome, nome, data_nascita, luogo_nascita, grado, ab_armi, inuse) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setString(1, militare.getMatricola());
            s.setString(2, militare.getCognome());
            s.setString(3, militare.getNome());
            s.setDate(4, new java.sql.Date(militare.getData_nascita().getTime()));
            s.setString(5, militare.getLuogo_nascita());
            s.setInt(6, militare.getGrado());
            s.setBoolean(7, militare.isAb_armi());
            s.setBoolean(8, true);
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Militare militare, int type)
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
            if(type==1)
            {
                String query = "UPDATE militare SET cognome = ?, nome = ?, data_nascita = ?, luogo_nascita = ? WHERE matricola = ?";
                // Creazione della query di inserimento
                PreparedStatement s = c.prepareStatement(query);
                // Impostazione dei parametri della query
                s.setString(1, militare.getCognome());
                s.setString(2, militare.getNome());
                s.setDate(3, new java.sql.Date(militare.getData_nascita().getTime()));
                s.setString(4, militare.getLuogo_nascita());
                s.setString(5, militare.getMatricola());
                // Esecuzione della query
                s.executeUpdate();
                //System.out.println("Esecuzione query: " + s.toString());
            }
            if(type == 2)
            {
                String query = "UPDATE militare SET grado = ? WHERE matricola = ?";
                // Creazione della query di inserimento
                PreparedStatement s = c.prepareStatement(query);
                // Impostazione dei parametri della query
                s.setInt(1, militare.getGrado());
                s.setString(2, militare.getMatricola());
                // Esecuzione della query
                s.executeUpdate();
                //System.out.println("Esecuzione query: " + s.toString());
            }
            if(type == 3)
            {
                String query = "UPDATE militare SET ab_armi = ? WHERE matricola = ?";
                // Creazione della query di inserimento
                PreparedStatement s = c.prepareStatement(query);
                // Impostazione dei parametri della query
                s.setBoolean(1, militare.isAb_armi());
                s.setString(2, militare.getMatricola());
                // Esecuzione della query
                s.executeUpdate();
                //System.out.println("Esecuzione query: " + s.toString());
            }


        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String matricola)
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
            String query = "UPDATE militare SET inuse = false  WHERE matricola = ?";
            // Creazione della query di inserimento
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setString(1, matricola);
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Militare getByMatricola(String matricola)
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
            // Creazione della query di selezione
            String query = "SELECT * FROM militare WHERE matricola = ?";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione del parametro della query
            s.setString(1, matricola);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            if (resultSet.next())
            {
                Militare m= new Militare();
                m.setMatricola(resultSet.getString("matricola"));
                m.setCognome(resultSet.getString("cognome"));
                m.setNome(resultSet.getString("nome"));
                m.setNascita(resultSet.getDate("data_nascita"),resultSet.getString("luogo_nascita"));
                m.setGrado(resultSet.getInt("grado"));
                m.setArmi(resultSet.getBoolean("ab_armi"));
                return m;
            }
            else
            {
                return null;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Militare> getAll()
    {
        List<Militare> ml = new ArrayList<Militare>();
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
            String query = "SELECT matricola, cognome, nome, data_nascita FROM militare WHERE inuse = true";
            PreparedStatement s = c.prepareStatement(query);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                Militare m = new Militare();
                m.setMatricola(resultSet.getString("matricola"));
                m.setCognome(resultSet.getString("cognome"));
                m.setNome(resultSet.getString("nome"));
                m.setDataNascita(resultSet.getDate("data_nascita"));
                ml.add(m);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ml;
    }

    @Override
    public Militare gag(String matricola, boolean guida)
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
            String query = "";
            if(guida)
            {
                // Creazione della query di selezione
                query = "SELECT militare.matricola, militare.grado, militare.ab_armi, patenti.cod_patente " +
                        "FROM militare " +
                        "INNER JOIN patenti ON militare.matricola = patenti.militare " +
                        "WHERE militare.matricola = ? " +
                        "group by matricola";
            }
            else
            {
                query = "SELECT matricola, grado, ab_armi FROM militare WHERE matricola = ?";
            }
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione del parametro della query
            s.setString(1, matricola);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            if (resultSet.next())
            {
                Militare m= new Militare();
                m.setMatricola(resultSet.getString("matricola"));
                m.setGrado(resultSet.getInt("grado"));
                m.setArmi(resultSet.getBoolean("ab_armi"));
                return m;
            }
            else
            {
                return null;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
