package com.apapa.persistence;

import com.apapa.classes.*;
import com.apapa.interfaces.ComandataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOComandata implements ComandataDAO
{

    private static DAOComandata singleton;

    public static DAOComandata getInstance()
    {
        if (singleton == null)
            singleton = new DAOComandata();
        return singleton;
    }

    private DAOComandata() {}

    @Override
    public int add(Comandata com)
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
            String query = "INSERT INTO comandata (tipo, dinizio, tinizio, dfine, tfine, destinazione, mezzo, critik_mezzo, num_militari) VALUES (?, ?, ?, ?, ?, ?, ?, false, ? )";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setBoolean(1, com.isTipo());
            s.setDate(2, new java.sql.Date(com.getDinizio().getTime()));
            s.setTime(3,com.getTinizio());
            s.setDate(4, new java.sql.Date(com.getDfine().getTime()));
            s.setTime(5,com.getTfine());
            s.setString(6, com.getDestinazione());
            s.setString(7, com.getTarga());
            s.setInt(8, com.getNum_militari());
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
    public void addMilitare(int id, String matricola)
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
            String query = "INSERT INTO ass_com (comandata, militare, critik) VALUES (?, ?, false)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setInt(1, id);
            s.setString(2, matricola);
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comandata> getAll()
    {
        List<Comandata> cl = new ArrayList<Comandata>();
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
            String query = "SELECT cod, tipo, dinizio, tinizio, dfine, tfine, destinazione, mezzo, num_militari FROM comandata";
            PreparedStatement s = c.prepareStatement(query);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                Comandata com = new Comandata();
                com.setId(resultSet.getInt(1));
                com.setTipo(resultSet.getBoolean(2));
                com.setDinizio(resultSet.getDate(3));
                com.setTinizio(resultSet.getTime(4));
                com.setDfine(resultSet.getDate(5));
                com.setTfine(resultSet.getTime(6));
                com.setDestinazione(resultSet.getString(7));
                com.setTarga(resultSet.getString(8));
                com.setNum_militari(resultSet.getInt(9));
                if(com.getNum_militari()>0)
                {
                    String query2 = "SELECT militare FROM ass_com WHERE comandata = ?";
                    s = c.prepareStatement(query2);
                    // Impostazione del parametro della query
                    s.setInt(1, com.getId());
                    // Esecuzione della query
                    ResultSet resultSet2 = s.executeQuery();
                    // Recupero dei dati dal ResultSet
                    while(resultSet2.next())
                    {
                        String mat = resultSet2.getString(1);
                        com.addMili(mat);
                    }
                }
                cl.add(com);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return cl;
    }

    @Override
    public boolean addCritMili(int id, String matricola)
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
            String query = "UPDATE ass_com SET critik = true WHERE comandata = ? AND militare = ?";
            // Creazione della query di inserimento
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setInt(1, id);
            s.setString(2, matricola);
            s.executeUpdate();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addCritMezzo(int id, String targa)
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
            String query = "UPDATE comandata SET critik_mezzo = true WHERE cod = ? AND mezzo = ?";
            // Creazione della query di inserimento
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setInt(1, id);
            s.setString(2, targa);
            s.executeUpdate();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean dropCritickMz(int id, String targa)
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
            String query = "UPDATE comandata SET critik_mezzo = false WHERE cod = ? AND mezzo = ?";
            // Creazione della query di inserimento
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setInt(1, id);
            s.setString(2, targa);
            s.executeUpdate();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void changeMilitare(int id, String old, String nuova)
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
        int idr = -1;
        try {
            String query = "SELECT id FROM ass_com WHERE comandata = ? AND militare = ?";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query;
            s.setInt(1, id);
            s.setString(2, old);
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            if (resultSet.next())
                idr=resultSet.getInt(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        if(idr!=-1)
        {
            try {
                String query = "UPDATE ass_com SET militare = ? , critik = false WHERE id = ?";
                PreparedStatement s = c.prepareStatement(query);
                // Impostazione dei parametri della query
                s.setString(1, nuova);
                s.setInt(2, idr);
                s.executeUpdate();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void changeMezzo(int id, String old, String nuova)
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
            String query = "UPDATE comandata SET mezzo = ?, critik_mezzo = false WHERE cod = ?";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query;
            s.setString(1, nuova);
            s.setInt(2, id);
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
