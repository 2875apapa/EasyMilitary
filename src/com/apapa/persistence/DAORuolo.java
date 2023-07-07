package com.apapa.persistence;

import com.apapa.classes.Ruolo;
import com.apapa.classes.TipoServizio;
import com.apapa.interfaces.RuoloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAORuolo implements RuoloDAO
{
    private static DAORuolo singleton;

    public static DAORuolo getInstance()
    {
        if (singleton == null)
            singleton = new DAORuolo();
        return singleton;
    }

    private DAORuolo() {}

    @Override
    public void add(Ruolo r, int id)
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
            String query = "INSERT INTO ruoli (nome, grado, tipo_servizio, guida) VALUES (?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setString(1, r.getNome());
            s.setInt(2, r.getGrado());
            s.setInt(3,id);
            s.setBoolean(4,r.isGuida());
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ruolo> getlr(int id)
    {
        List<Ruolo> rl = new ArrayList<Ruolo>();
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
            String query = "SELECT id, nome, grado, guida FROM ruoli WHERE tipo_servizio = ? ";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setInt(1, id);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                Ruolo tr = new Ruolo();
                tr.setId(resultSet.getInt("id"));
                tr.setNome(resultSet.getString("nome"));
                tr.setGrado(resultSet.getInt("grado"));
                tr.setGuida(resultSet.getBoolean("guida"));
                rl.add(tr);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rl;
    }
}
