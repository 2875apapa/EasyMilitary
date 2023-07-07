package com.apapa.persistence;

import com.apapa.classes.*;
import com.apapa.interfaces.ServizioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOServizio implements ServizioDAO
{
    private static DAOServizio singleton;

    public static DAOServizio getInstance()
    {
        if (singleton == null)
            singleton = new DAOServizio();
        return singleton;
    }

    private DAOServizio() {}

    @Override
    public int add(Servizio servizio)
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
            String query = "INSERT INTO servizio (tipo_servizio, data_inizio, data_fine) VALUES (?, ?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setInt(1, servizio.getCod_tipo_servizio());
            s.setDate(2, new java.sql.Date(servizio.getData_inizio().getTime()));
            s.setDate(3, new java.sql.Date(servizio.getData_fine().getTime()));
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
    public void addMezzo(int id, String targa)
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
            String query = "INSERT INTO ass_mezzo (servizio, mezzo, critik) VALUES (?, ?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setInt(1, id);
            s.setString(2, targa);
            s.setBoolean(3, false);
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addMilitare(int id, String matricola, String ruolo)
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
            String query = "INSERT INTO ass_mil (servizio, militare, ruolo, critik) VALUES (?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            // Impostazione dei parametri della query
            s.setInt(1, id);
            s.setString(2, matricola);
            s.setString(3,ruolo);
            s.setBoolean(4, false);
            // Esecuzione della query
            s.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Servizio> getAll()
    {
        List<Servizio> sl = new ArrayList<Servizio>();
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
            String query = "SELECT servizio.id, servizio.tipo_servizio, servizio.data_inizio, " +
                    "servizio.data_fine, tiposervizio.nome_servizio, tiposervizio.armato, " +
                    "tiposervizio.durata, tiposervizio.riposo, tiposervizio.num_militari, " +
                    "tiposervizio.num_mezzi " +
                    "FROM servizio " +
                    "INNER JOIN tiposervizio ON servizio.tipo_servizio = tiposervizio.id";
            PreparedStatement s = c.prepareStatement(query);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                Servizio servizio = new Servizio();
                servizio.setId(resultSet.getInt(1));
                servizio.setCod_tipo_servizio(resultSet.getInt(2));
                servizio.setData_inizio(resultSet.getDate(3));
                servizio.setData_fine(resultSet.getDate(4));
                servizio.setNome_tipo_servizio(resultSet.getString(5));
                servizio.setArmato(resultSet.getBoolean(6));
                servizio.setDurata(resultSet.getInt(7));
                servizio.setRiposo(resultSet.getInt(8));
                servizio.setNum_militari(resultSet.getInt(9));
                servizio.setNum_mezzi(resultSet.getInt(10));
                if(servizio.getNum_militari()>0)
                {
                    String query2 = "SELECT ass_mil.id, ass_mil.servizio, ass_mil.militare, " +
                            "tiposervizio.id AS cod_tipo_servizio, tiposervizio.nome_servizio " +
                            "FROM (ass_mil INNER JOIN servizio ON ass_mil.servizio = servizio.id) " +
                            "LEFT JOIN tiposervizio ON servizio.tipo_servizio = tiposervizio.id " +
                            "WHERE ass_mil.servizio = ?";
                    s = c.prepareStatement(query2);
                    // Impostazione del parametro della query
                    s.setInt(1, servizio.getId());
                    // Esecuzione della query
                    ResultSet resultSet2 = s.executeQuery();
                    // Recupero dei dati dal ResultSet
                    List <MilitareServizio> mlt = new ArrayList<MilitareServizio>();
                    while(resultSet2.next())
                    {
                        MilitareServizio mt = new MilitareServizio();
                        mt.setMatricola(resultSet2.getString(3));
                        mt.setIdRuolo(resultSet2.getInt(4));
                        mt.setRuolo(resultSet2.getString(5));
                        mlt.add(mt);
                    }
                    servizio.setElencoMilitari(mlt);
                }
                if(servizio.getNum_mezzi()>0)
                {
                    String query3 = "SELECT ass_mezzo.servizio, mezzo.targa, mezzo.marca, mezzo.modello, mezzo.tipo, mezzo.patente, mezzo.stato " +
                            "FROM ass_mezzo " +
                            "INNER JOIN mezzo ON ass_mezzo.mezzo = mezzo.targa " +
                            "WHERE ass_mezzo.servizio = ?";
                    s = c.prepareStatement(query3);
                    // Impostazione del parametro della query
                    s.setInt(1, servizio.getId());
                    // Esecuzione della query
                    ResultSet resultSet3 = s.executeQuery();
                    // Recupero dei dati dal ResultSet
                    List <Mezzo> mlt = new ArrayList<Mezzo>();
                    while(resultSet3.next())
                    {
                        Mezzo mt = new Mezzo();
                        mt.setTarga(resultSet3.getString(2));
                        mt.setModello(resultSet3.getString(3));
                        mt.setMarca(resultSet3.getString(4));
                        mt.setTipo(resultSet3.getBoolean(5));
                        mt.setPatente(resultSet3.getInt(6));
                        mt.setStato(resultSet3.getBoolean(7));
                        mlt.add(mt);
                    }
                    servizio.setElencoMezzi(mlt);
                }
                if(servizio.getDurata()>0)
                {
                    String query4 = "SELECT numero, ora_inizio, ora_fine " +
                            "FROM giorni " +
                            "RIGHT JOIN servizio ON giorni.tipo_servizio = servizio.tipo_servizio " +
                            "WHERE giorni.tipo_servizio = ?";
                    s = c.prepareStatement(query4);
                    // Impostazione del parametro della query
                    s.setInt(1, servizio.getCod_tipo_servizio());
                    // Esecuzione della query
                    ResultSet resultSet4 = s.executeQuery();
                    // Recupero dei dati dal ResultSet
                    List <Giorno> mlt = new ArrayList<Giorno>();
                    while(resultSet4.next())
                    {
                        Giorno mt = new Giorno();
                        mt.setNumero(resultSet4.getInt(1));
                        mt.setOra_inizio(resultSet4.getTime(2));
                        mt.setOra_fine(resultSet4.getTime(3));
                        mlt.add(mt);
                    }
                    servizio.setElencoGiorni(mlt);
                }
                sl.add(servizio);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return sl;
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
            String query = "UPDATE ass_mil SET critik = true WHERE servizio = ? AND militare = ?";
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
            String query = "UPDATE ass_mezzo SET critik = true WHERE servizio = ? AND mezzo = ?";
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
            String query = "UPDATE ass_mezzo SET critik = false WHERE servizio = ? AND mezzo = ?";
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
            String query = "SELECT id FROM ass_mil WHERE servizio = ? AND militare = ?";
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
                String query = "UPDATE ass_mil SET militare = ? , critik = false WHERE id = ?";
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
        int idr = -1;
        try {
            String query = "SELECT id FROM ass_mezzo WHERE servizio = ? AND mezzo = ?";
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
                String query = "UPDATE ass_mezzo SET mezzo = ?, critik = false WHERE id = ?";
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
}
