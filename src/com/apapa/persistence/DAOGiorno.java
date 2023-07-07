package com.apapa.persistence;

import com.apapa.classes.Giorno;
import com.apapa.classes.Ruolo;
import com.apapa.interfaces.GiornoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOGiorno implements GiornoDAO
{
    private static DAOGiorno singleton;

    public static DAOGiorno getInstance()
    {
        if (singleton == null)
            singleton = new DAOGiorno();
        return singleton;
    }

    private DAOGiorno() {}


    @Override
    public void add(Giorno g, int id)
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
            String query = "INSERT INTO giorni (numero, ora_inizio, ora_fine, tipo_servizio) VALUES (?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setInt(1, g.getNumero());
            s.setTime(2, g.getOra_inizio());
            s.setTime(3, g.getOra_fine());
            s.setInt(4,id);
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Giorno> getlg(int id)
    {
        List<Giorno> gl = new ArrayList<Giorno>();
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
            String query = "SELECT numero, ora_inizio, ora_fine FROM giorni WHERE tipo_servizio = ? ";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setInt(1, id);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                Giorno tg = new Giorno();
                tg.setNumero(resultSet.getInt("numero"));
                tg.setOra_inizio(resultSet.getTime("ora_inizio"));
                tg.setOra_fine(resultSet.getTime("ora_fine"));
                gl.add(tg);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return gl;
    }
}
