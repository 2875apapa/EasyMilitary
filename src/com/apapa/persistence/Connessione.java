package com.apapa.persistence;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione
{
    private static Connessione singleton;

    private final static String CONFIG_FILE =
            System.getProperty("user.dir") +
                    System.getProperty("file.separator") +
                    "db.txt";

    private static Connection c = null;
    // driver mariadb
    //private String driver = "org.mariadb.jdbc.Driver";
    private String driver = "";
    // URL della base di dati
    //private String url = "jdbc:mariadb://localhost:3306/database_ing_sw";
    private String url = "";
    // username
    //private String username = "ing_sw";
    private String username = "";
    // password
    //private String password = "auto1234";
    private String password = "";

    public static Connessione getInstance() throws SQLException {
        if (singleton == null)
            singleton = new Connessione();
        return singleton;
    }

    private Connessione() throws SQLException
    {
        ReadFile();
        // Inizializzazione della connessione al database
        try
        {
            Class.forName(driver);
            c = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Errore nella connessione al DB");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("Errore nella connessione al DB");
        }
    }

    private void ReadFile()
    {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(CONFIG_FILE));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null && count < 4)
            {
                switch (count)
                {
                    case 0:
                        this.driver = line;
                        break;
                    case 1:
                        this.url = line;
                        break;
                    case 2:
                        this.username = line;
                        break;
                    case 3:
                        this.password = line;
                        break;
                }
                //System.out.println(line);
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Errore durante la lettura del file: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore durante la lettura del file: " + e.getMessage());
        }


    }

    public Connection getConnessione()
    {
        return c;
    }
}
