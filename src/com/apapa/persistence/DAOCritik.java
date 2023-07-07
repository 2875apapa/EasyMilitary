package com.apapa.persistence;

import com.apapa.classes.Critik;
import com.apapa.classes.Servizio;
import com.apapa.interfaces.CritikDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCritik implements CritikDAO
{

    private static DAOCritik singleton;

    public static DAOCritik getInstance()
    {
        if (singleton == null)
            singleton = new DAOCritik();
        return singleton;
    }

    private DAOCritik() {}


    @Override
    public List<Critik> getMS()
    {
        List <Critik> lc = new ArrayList<Critik>();
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
            String query = "SELECT servizio, militare, ruolo, tiposervizio.nome_servizio, servizio.data_inizio, servizio.data_fine " +
                    "FROM ass_mil " +
                    "LEFT JOIN (servizio LEFT JOIN tiposervizio ON servizio.tipo_servizio = tiposervizio.id) " +
                    "ON ass_mil.servizio = servizio.id " +
                    "WHERE ass_mil.critik=true";
            PreparedStatement s = c.prepareStatement(query);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                Critik cri = new Critik();
                String servizio = resultSet.getString(4) + " - " + resultSet.getString(3);
                cri.setServizio(servizio);
                cri.setIdsc(resultSet.getInt(1));
                cri.setType(1);
                cri.setInizio(resultSet.getDate(5));
                cri.setFine(resultSet.getDate(6));
                cri.setMatrcola(resultSet.getString(2));
                cri.setTarga(null);
                lc.add(cri);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return lc;
    }

    @Override
    public List<Critik> getMzS()
    {
        List <Critik> lc = new ArrayList<Critik>();
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
            String query = "SELECT servizio, mezzo, tiposervizio.nome_servizio, servizio.data_inizio, servizio.data_fine " +
                    "FROM ass_mezzo " +
                    "LEFT JOIN (servizio LEFT JOIN tiposervizio ON servizio.tipo_servizio = tiposervizio.id) " +
                    "ON ass_mezzo.servizio = servizio.id " +
                    "WHERE ass_mezzo.critik=true";
            PreparedStatement s = c.prepareStatement(query);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                Critik cri = new Critik();
                cri.setServizio(resultSet.getString(3));
                cri.setIdsc(resultSet.getInt(1));
                cri.setType(2);
                cri.setInizio(resultSet.getDate(4));
                cri.setFine(resultSet.getDate(5));
                cri.setMatrcola(null);
                cri.setTarga(resultSet.getString(2));
                lc.add(cri);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return lc;
    }

    @Override
    public List<Critik> getMC() {
        List <Critik> lc = new ArrayList<Critik>();
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
            String query = "SELECT comandata, militare, comandata.dinizio, comandata.dfine " +
                    "FROM ass_com " +
                    "LEFT JOIN comandata ON ass_com.comandata = comandata.cod " +
                    "WHERE ass_com.critik=true";
            PreparedStatement s = c.prepareStatement(query);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                Critik cri = new Critik();
                String servizio = "comandata";
                cri.setServizio(servizio);
                cri.setIdsc(resultSet.getInt(1));
                cri.setType(1);
                cri.setInizio(resultSet.getDate(3));
                cri.setFine(resultSet.getDate(4));
                cri.setMatrcola(resultSet.getString(2));
                cri.setTarga(null);
                lc.add(cri);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return lc;
    }

    @Override
    public List<Critik> getMzC() {
        List <Critik> lc = new ArrayList<Critik>();
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
            String query = "SELECT cod, mezzo, dinizio, dfine FROM comandata WHERE critik_mezzo=true";
            PreparedStatement s = c.prepareStatement(query);
            // Esecuzione della query
            ResultSet resultSet = s.executeQuery();
            // Recupero dei dati dal ResultSet
            while (resultSet.next())
            {
                Critik cri = new Critik();
                String servizio = "comandata";
                cri.setServizio(servizio);
                cri.setIdsc(resultSet.getInt(1));
                cri.setType(1);
                cri.setInizio(resultSet.getDate(3));
                cri.setFine(resultSet.getDate(4));
                cri.setMatrcola(resultSet.getString(2));
                cri.setTarga(null);
                lc.add(cri);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return lc;
    }
}
