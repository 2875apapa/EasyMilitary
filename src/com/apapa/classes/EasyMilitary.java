package com.apapa.classes;

import com.apapa.interfaces.TipoUtente;
import com.apapa.persistence.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyMilitary
{
    //instanza Singleton
    private static EasyMilitary singleton;

    private TipoUtente tipoUtente;
    private List<Militare> militari = null;
    private List<Mezzo> mezzi = null;
    private List<Servizio> servizi = null;
    private List<Comandata> comandate = null;
    private List<Assenza> assenze = null;


    //DAO
    DAOMilitare dao_m = DAOMilitare.getInstance();
    DAOPatente dao_p = DAOPatente.getInstance();
    DAOTipoServizio dao_ts = DAOTipoServizio.getInstance();
    DAORuolo dao_r = DAORuolo.getInstance();
    DAOGiorno dao_g = DAOGiorno.getInstance();
    DAOMezzo dao_mz = DAOMezzo.getInstance();
    DAOServizio dao_s = DAOServizio.getInstance();
    DAOComandata dao_c = DAOComandata.getInstance();
    DAOAssenza dao_a = DAOAssenza.getInstance();
    DAOCritik dao_crt = DAOCritik.getInstance();

    //Restituisce il riferimento all'istanza singleton
    public static EasyMilitary getInstance()
    {
        if ( singleton == null)
        {
            singleton = new EasyMilitary();
        }
        return singleton;
    }

    private EasyMilitary()
    {
        /* carica dei dati iniziali */
        militari = dao_m.getAll();
        mezzi = dao_mz.getAll();
        servizi = dao_s.getAll();
        comandate = dao_c.getAll();
        assenze = dao_a.getAll();

        System.out.println("Caricamento dei dati in memoria dal DB eseguito....");
    }

    public TipoUtente getTipoUtente()
    {
        return tipoUtente;
    }

    public void setTipoUtente(TipoUtente tipoUtente)
    {
        this.tipoUtente = tipoUtente;
    }


    /*      CASE D'USO UC1: Gestisci Personale      */

    public Militare nuovoMilitare(String matricola, String cognome, String nome, Date dn, String ln, int grado, boolean armi)
    {
        Militare m = new Militare();
        m.setMatricola(matricola).setCognome(cognome).setNome(nome).setNascita(dn, ln).setGrado(grado).setArmi(armi).build();
        return m;
    }

    public boolean addMilitare(Militare m, List<Patente> ps)
    {
        militari.add(m);
        System.out.println("Militare aggiunto alla memoria locale");
        dao_m.save(m);
        System.out.println("Militare aggiunto al DB");
        int j;
        for(j=0; j<ps.size(); j++)
        {
            dao_p.save(ps.get(j));
            System.out.println("Patente aggiunta al database");
        }

        return true;
    }

    public Patente newPatente(String matricola, int modello, Date conseguimento, Date scadenza)
    {
        Patente p = new Patente(matricola,modello);
        p.setDataConseguimento(conseguimento);
        p.setDataScadenza(scadenza);
        return p;
    }

    public boolean updMilitare(Militare m, int type)
    {
        for (Militare mm : militari)
        {
            if(mm.getMatricola().equals(m.getMatricola()))
            {
                mm = m;
                System.out.println(mm.toString());
                System.out.println("Militare aggiornato in memoria locale");
                dao_m.update(m,type);
                System.out.println("Militare aggiornato all'interno del DB");
                return true;
            }
        }
        return false;
    }

    public Militare findMilitare(String matricola)
    {
        for (Militare mm : militari)
        {
            if(mm.getMatricola().equals(matricola))
            {
                Militare m = dao_m.getByMatricola(matricola);
                mm = m;
                return mm;
            }
        }
        System.out.println("Il numero di matricola inserito non corrisponde a nessun elemento del sistema");
        return null;
    }

    public List<Militare> findMilitareS(String cognome)
    {
        List<Militare> ml = new ArrayList<Militare>();
        for (Militare mm : militari)
        {
            if(mm.getCognome().equals(cognome))
                ml.add(mm);
        }
        if(ml.size()>0)
            return ml;
        else
        {
            System.out.println("Il cognome inserito non corrisponde a nessun elemento presente nel sistema");
            return null;
        }
    }

    public boolean deleteMilitare(String matricola)
    {
        for (Militare mm : militari)
        {
            if(mm.getMatricola().equals(matricola))
            {
                militari.remove(mm);
                System.out.println("Militare eliminato dalla memoria locale");
                dao_m.delete(matricola);
                System.out.println("Militare eliminato all'interno del DB");
                return true;
            }
        }
        return false;
    }

    public boolean checkMilitare(String matricola)
    {
        for (Militare mm : militari)
        {
            if(mm.getMatricola().equals(matricola))
                return true;
        }
        System.out.println("Il numero di matricola inserito non corrisponde a nessun elemento del sistema");
        return false;
    }





    /*      CASE D'USO UC2: Crea Tipo Servizio      */

    public TipoServizio newTipoServizio(String nome, Boolean armato, int n, int r, int m, int z, List<Ruolo> lruolo, List<Giorno> lgiorni)
    {
        TipoServizio ts = new TipoServizio(nome, armato, n, r, m, z,lruolo,lgiorni);
        return ts;
    }

    public boolean addTipoServizio(TipoServizio ts)
    {
        List<Ruolo> lruoli = ts.getLruoli();
        List<Giorno> lgiorni = ts.getLgiorni();
        int idts = dao_ts.add(ts);
        System.out.println("Tipo Servizio aggiunto al DB");
        int i;
        for(i=0; i<lruoli.size(); i++)
        {
            dao_r.add(lruoli.get(i), idts);
            System.out.println("Ruolo aggiunto al database");
        }
        for(i=0; i<lgiorni.size(); i++)
        {
            dao_g.add(lgiorni.get(i), idts);
            System.out.println("Giorno aggiunto al database");
        }
        return true;
    }








    /*      CASE D'USO UC3: Gestisci Mezzi      */

    public Mezzo nuovoMezzo (String targa, String marca, String modello, boolean tipo, int patente, boolean stato)
    {
        Mezzo m = new Mezzo(targa,marca, modello, tipo, patente, stato);
        return m;
    }

    public boolean addMezzo(Mezzo m)
    {
        mezzi.add(m);
        System.out.println("Mezzo aggiunto alla memoria locale");
        dao_mz.save(m);
        System.out.println("Mezzo aggiunto al DB");
        return true;
    }

    public Mezzo findMezzo(String targa)
    {
        for (Mezzo mm : mezzi)
        {
            if(mm.getTarga().equals(targa))
            {
                return mm;
            }
        }
        return null;
    }

    public boolean updMezzo(String targa, boolean stato)
    {
        for (Mezzo mm : mezzi)
        {
            if(mm.getTarga().equals(targa))
            {
                mm.setStato(stato);
                System.out.println(mm.toString());
                System.out.println("Stato del Mezzo aggiornato in memoria locale");
                dao_mz.update(targa,stato);
                System.out.println("Stato del Mezzo aggiornato all'interno del DB");
                if(!stato)
                    ctl_mezzo_ck(targa);
                else
                    ctl_mezzo_ckn(targa);
                return true;
            }
        }
        return false;
    }

    public void ctl_mezzo_ck(String targa)
    {
        for(Servizio ss : servizi)
        {
            if(ss.getNum_mezzi()>0)
            {
                List <Mezzo> lms = ss.getElencoMezzi();
                for(Mezzo mz : lms)
                {
                    if(targa.equals(mz.getTarga()))
                    {
                        boolean cdate = isEnter(ss.getData_fine());
                        if(cdate)
                        {
                            addCriticitaMezzo(targa, "servizio", ss.getId());
                            System.out.println("Nuova criticita' per assenza mezzo!");
                        }
                    }
                }
            }
        }

        for(Comandata cc : comandate)
        {
            if(targa.equals(cc.getTarga()))
            {
                boolean cdate = isEnter(cc.getDfine());
                if(cdate)
                {
                    addCriticitaMezzo(targa, "comandata", cc.getId());
                    System.out.println("Nuova criticita' per assenza mezzo!");
                }

            }
        }
    }

    public void ctl_mezzo_ckn(String targa)
    {
        List <Critik> crtMz = getAllCritikMezzi();
        for(Critik cc : crtMz)
        {
            if(targa.equals(cc.getTarga()))
            {
                int idsc = cc.getIdsc();
                if(cc.getServizio().equals("comandata"))
                {
                    if(dao_c.dropCritickMz(idsc, targa))
                        System.out.println("Criticita' dovuta ad assenza del mezzo risolta!");
                }
                else
                {
                    if(dao_s.dropCritickMz(idsc, targa))
                        System.out.println("Criticita' dovuta ad assenza del mezzo risolta!");
                }
            }
        }
    }

    public boolean isEnter(Date end1)
    {
        Date today = new Date();
        return (end1.after(today) || end1.equals(today));
    }

    public boolean addCriticitaMezzo(String targa, String t, int id)
    {
        boolean bb = false;
        if(t.equals("servizio"))
        {
            bb = dao_s.addCritMezzo(id,targa);
        }
        else
        {
            bb = dao_c.addCritMezzo(id,targa);
        }
        return bb;
    }

    public List<Mezzo> findMezzoT(Boolean tipo)
    {
        List<Mezzo> ml = new ArrayList<Mezzo>();
        for (Mezzo mm : mezzi)
        {
            if(mm.isTipo() == tipo)
                ml.add(mm);
        }
        if(ml.size()>0)
            return ml;
        else
        {
            System.out.println("Non esistono mezzi per il tipo selezionato");
            return null;
        }
    }

    public List<Mezzo> findMezzoMM(String marca, String modello)
    {
        List<Mezzo> ml = new ArrayList<Mezzo>();
        for (Mezzo mm : mezzi)
        {
            if(mm.getMarca().equals(marca) && mm.getModello().equals(modello))
                ml.add(mm);
        }
        if(ml.size()>0)
            return ml;
        else
        {
            System.out.println("Non esistono mezzi per marca e modello selezionati");
            return null;
        }
    }

    public boolean deleteMezzo(String targa)
    {
        for (Mezzo mm : mezzi)
        {
            if(mm.getTarga().equals(targa))
            {
                mezzi.remove(mm);
                System.out.println("Mezzo eliminato dalla memoria locale");
                dao_mz.delete(targa);
                System.out.println("Mezzo eliminato all'interno del DB");
                return true;
            }
        }
        return false;
    }

    public boolean checkMezzo(String targa)
    {
        for (Mezzo mm : mezzi)
        {
            if(mm.getTarga().equals(targa))
                return true;
        }
        return false;
    }




    /*      CASE D'USO UC4: Gestisci Patenti      */


    public void addPatenti(int modello, Date conseguimento, Date scadenza, List<String> matricole)
    {
        int j;
        for(j=0; j<matricole.size(); j++)
        {
            Patente p = new Patente(matricole.get(j),modello,conseguimento,scadenza);
            dao_p.save(p);
            System.out.println("Patente aggiunta al database");
        }
    }





    /*      CASE D'USO UC6: Crea Servizio      */

    public Servizio newServizio(int id, String nome_servizio, int durata, int riposo, int num_militari, int num_mezzi, boolean armato)
    {
        Servizio s = new Servizio(id, nome_servizio, durata, riposo, num_militari, num_mezzi, armato);
        return s;
    }

    public boolean addServizio(Servizio s)
    {
        servizi.add(s);
        System.out.println("Servizio aggiunto alla memoria locale");
        int ids = dao_s.add(s);
        for(MilitareServizio m : s.getElencoMilitari())
        {
            dao_s.addMilitare(ids,m.getMatricola(),m.getRuolo());
        }
        for(Mezzo m : s.getElencoMezzi())
        {
            dao_s.addMezzo(ids,m.getTarga());
        }
        System.out.println("Servizio aggiunto al DB");
        return true;
    }

    public boolean checkMilServ(String matricola, Date data_inizio, Date data_fine, int grado, boolean armato, boolean guida)
    {
        for (Militare mm : militari)
        {
            if(mm.getMatricola().equals(matricola))
            {
                Militare tm = dao_m.gag(matricola, guida);
                if(tm == null && guida == true)
                {
                    System.out.println("Il militare non ha le abilitazioni di guida richieste");
                    return false;
                }
                if(armato == true && tm.isAb_armi() == false)
                {
                    System.out.println("Il militare non e' abilitato ai servizi armati come richiesto dal servizio");
                    return false;
                }
                if(tm.getGrado() < grado)
                {
                    System.out.println("Il militare non possiede il grado minimo per questo ruolo");
                    return false;
                }
                for(Servizio ss : servizi)
                {
                    if(isOverlap(data_inizio,data_fine,ss.getData_inizio(),ss.getData_fine()))
                    {
                        if(ss.getNum_militari()>0)
                        {
                            List <MilitareServizio> lms = ss.getElencoMilitari();
                            for(MilitareServizio mslms : lms)
                            {
                                if(mslms.getMatricola().equals(matricola))
                                {
                                    System.out.println("Il militare selezionato e' gia' di servizio per: " + ss.getNome_tipo_servizio() + " - " + mslms.getRuolo());
                                    return false;
                                }
                            }
                        }
                    }
                }
                for(Comandata cc : comandate)
                {
                    if(isOverlap(data_inizio,data_fine,cc.getDinizio(),cc.getDfine()))
                    {
                        if(cc.getNum_militari()>0)
                        {
                            List <String> lmc = cc.getMilitari();
                            for(String matc : lmc)
                            {
                                if(matc.equals(matricola))
                                {
                                    System.out.println("Il militare selezionato e' gia' impiegato in una comandata");
                                    return false;
                                }
                            }
                        }
                    }
                }
                for(Assenza aa : assenze)
                {
                    if(aa.getMilitare().equals(matricola))
                    {
                        if(isOverlap(data_inizio, data_fine, aa.getDinizio(), aa.getDfine()))
                        {
                            System.out.println("Il militare selezionato e' assente per: " + aa.getTipo());
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        System.out.println("Il numero di matricola inserito non corrisponde a nessun elemento del sistema");
        return false;
    }

    public boolean checkMezServ(String targa, Date data_inizio, Date data_fine)
    {
        for (Mezzo mm : mezzi)
        {
            if(mm.getTarga().equals(targa))
            {
                if(!mm.isStato())
                {
                    System.out.println("Il mezzo non e' al momento funzionante");
                    return false;
                }
                else
                {
                    for(Servizio ss : servizi)
                    {
                        if(isOverlap(data_inizio,data_fine,ss.getData_inizio(),ss.getData_fine()))
                        {
                            if(ss.getNum_mezzi()>0)
                            {
                                List <Mezzo> lms = ss.getElencoMezzi();
                                for(Mezzo mzlms : lms)
                                {
                                    if(mzlms.getTarga().equals(targa))
                                    {
                                        System.out.println("Il mezzo e' gia' occupato per il servizio: " + ss.getNome_tipo_servizio());
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                    for(Comandata cc : comandate)
                    {
                        if(isOverlap(data_inizio,data_fine,cc.getDinizio(), cc.getDfine()))
                        {
                            if (cc.getTarga().equals(targa)) {
                                System.out.println("Il mezzo e' gia' occupato per una comandata");
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        }
        System.out.println("Il numero di targa inserito non corrisponde a nessun mezzo presente nel sistema");
        return false;
    }

    public List<TipoServizio> lts()
    {
        List <TipoServizio> lts = dao_ts.getAll();
        for(TipoServizio ts : lts)
        {
            ts.setLruoli(dao_r.getlr(ts.getId()));
            ts.setLgiorni(dao_g.getlg(ts.getId()));
        }
        return lts;
    }


    public Servizio addGiorniServizio(Servizio s, List<Giorno> lgiorni)
    {
        s.setElencoGiorni(lgiorni);
        return s;
    }

    public Servizio setDateServizio(Servizio s, Date inizio)
    {
        s.setData_inizio(inizio);
        Date fine = new Date(inizio.getTime() + (s.getDurata()*1000 * 60 * 60 * 24));
        s.setData_fine(fine);
        return s;

    }






    /*      CASE D'USO UC7: Crea Comandata      */

    public Comandata nuovaComandata(boolean tipo, Date dinizio, Date dfine, Time tinizio, Time tfine, String destinazione, String targa, int num_militari)
    {
        Comandata c = new Comandata(tipo,dinizio,dfine,tinizio,tfine,destinazione,targa,num_militari);
        return c;
    }

    public boolean checkMilServ(String matricola, Date dinizio, Date dfine, int modello)
    {
        for (Militare mm : militari)
        {
            if(mm.getMatricola().equals(matricola))
            {
                if(dao_p.verifica(matricola, modello))
                {
                    for(Servizio ss : servizi)
                    {
                        if(isOverlap(dinizio,dfine,ss.getData_inizio(),ss.getData_fine()))
                        {
                            if(ss.getNum_militari()>0)
                            {
                                List <MilitareServizio> lms = ss.getElencoMilitari();
                                for(MilitareServizio mslms : lms)
                                {
                                    if(mslms.getMatricola().equals(matricola))
                                    {
                                        System.out.println("Il militare selezionato e' gia' di servizio per: " + ss.getNome_tipo_servizio() + " - " + mslms.getRuolo());
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                    for(Comandata cc : comandate)
                    {
                        if(isOverlap(dinizio,dfine,cc.getDinizio(),cc.getDfine()))
                        {
                            if(cc.getNum_militari()>0)
                            {
                                List <String> lmc = cc.getMilitari();
                                for(String matc : lmc)
                                {
                                    if(matc.equals(matricola))
                                    {
                                        System.out.println("Il militare selezionato e' gia' impiegato in una comandata");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                    for(Assenza aa : assenze)
                    {
                        if(aa.getMilitare().equals(matricola))
                        {
                            if(isOverlap(dinizio, dfine, aa.getDinizio(), aa.getDfine()))
                            {
                                System.out.println("Il militare selezionato e' assente per: " + aa.getTipo());
                                return false;
                            }
                        }
                    }
                    return true;
                }
                else
                {
                    System.out.println("Il militare non ha l'abilitazione di guida richiesta per questo tipo di mezzo");
                    return false;
                }
            }
        }
        System.out.println("Il numero di matricola inserito non corrisponde a nessun elemento del sistema");
        return false;
    }

    public boolean addComadata(Comandata c, List<String> matricole)
    {
        for(String m : matricole)
        {
            c.addMili(m);
        }
        comandate.add(c);
        System.out.println("Comandata aggiunta alla memoria locale");
        int idc = dao_c.add(c);
        for(String m : matricole)
        {
            dao_c.addMilitare(idc,m);
        }
        System.out.println("Comandata aggiunta al DB");
        return true;
    }









    /*      CASE D'USO UC5: Gestisci Assenze      */

    public static boolean isOverlap(Date start1, Date end1, Date start2, Date end2)
    {
        return (start1.before(end2) || start1.equals(end2)) && (end1.after(start2) || end1.equals(start2));
    }

    public boolean checkAssenze(String matricola, Date dinizio, Date dfine, int tipo)
    {
        boolean overlap = false;
        String tc = null;
        int idc = -1;
        if(dinizio != null && dfine != null)
        {

            for (Servizio ss : servizi)
            {
                List <MilitareServizio> lm = ss.getElencoMilitari();
                for(MilitareServizio mm : lm)
                {
                    if(mm.getMatricola().equals(matricola))
                    {
                        overlap = isOverlap(dinizio, dfine, ss.getData_inizio(), ss.getData_fine());
                        if(overlap)
                        {
                            tc = "servizio";
                            idc = ss.getId();
                            break;
                        }

                    }
                }
                if(overlap)
                    break;
            }
            if(!overlap)
            {
                for (Comandata cc : comandate)
                {
                    List <String> lm = cc.getMilitari();
                    for(String mm : lm)
                    {
                        if(mm.equals(matricola))
                        {
                            overlap = isOverlap(dinizio, dfine, cc.getDinizio(), cc.getDfine());
                            if(overlap)
                            {
                                tc = "comandata";
                                idc = cc.getId();
                                break;
                            }

                        }
                    }
                    if(overlap)
                        break;
                }
            }
        }
        else
        {
            System.out.println("Impossibile effettuare verifiche");
            return false;
        }
        switch (tipo)
        {
            case 1:
            {
                if(overlap)
                {
                    System.out.println("Il militare e' in servizio nel periodo selezionato");
                    System.out.println("Non e' possibile assegnare un periodo di licenza");
                    return false;
                }
                else
                {
                    System.out.println("Il militare e' libero da servizi nel periodo selezionato");
                    Assenza a = new Assenza("Licenza",dinizio, dfine, matricola);
                    int ida = addAssenza(a);
                    System.out.println("Assenza aggiunta al sistema con id: " + ida);
                    System.out.println(a.toString());
                    return true;
                }
            }
            case 2:
            {
                if(overlap)
                {
                    System.out.println("Il militare e' in servizio nel periodo selezionato");
                    if(addCriticita(matricola,tc,idc))
                    {
                        System.out.println("Aggiunta criticita'");
                        Assenza a = new Assenza("Malattia",dinizio, dfine, matricola);
                        int ida = addAssenza(a);
                        System.out.println("Assenza aggiunta al sistema con id: " + ida);
                        System.out.println(a.toString());
                        return true;
                    }
                    else
                    {
                        System.out.println("Errore durante la gestione della criticita'");
                        return false;
                    }
                }
                else
                {
                    System.out.println("Il militare e' libero da servizi nel periodo selezionato");
                    Assenza a = new Assenza("Malattia",dinizio, dfine, matricola);
                    int ida = addAssenza(a);
                    System.out.println("Assenza aggiunta al sistema con id: " + ida);
                    System.out.println(a.toString());
                    return true;
                }
            }
            case 3:
            {
                if(overlap)
                {
                    System.out.println("Il militare e' in servizio nel periodo selezionato");
                    if(addCriticita(matricola,tc,idc))
                    {
                        System.out.println("Aggiunta criticita'");
                        Assenza a = new Assenza("Giustificata",dinizio, dfine, matricola);
                        int ida = addAssenza(a);
                        System.out.println("Assenza aggiunta al sistema con id: " + ida);
                        System.out.println(a.toString());
                        return true;
                    }
                    else
                    {
                        System.out.println("Errore durante la gestione della criticita'");
                        return false;
                    }
                }
                else
                {
                    System.out.println("Il militare e' libero da servizi nel periodo selezionato");
                    Assenza a = new Assenza("Giustificata",dinizio, dfine, matricola);
                    int ida = addAssenza(a);
                    System.out.println("Assenza aggiunta al sistema con id: " + ida);
                    System.out.println(a.toString());
                    return true;
                }
            }
            default:
            {
                System.out.println("Errore di tipo");
                return false;
            }
        }
    }

    public int addAssenza(Assenza a)
    {
        int ida = dao_a.save(a);
        a.setId(ida);
        System.out.println("Assenza aggiunta al DB");
        assenze.add(a);
        System.out.println("Assenza aggiunta alla memoria locale");
        return ida;
    }

    public boolean addCriticita(String m, String t, int id)
    {
        boolean bb = false;
        if(t.equals("servizio"))
        {
            bb = dao_s.addCritMili(id,m);
        }
        else
        {
            bb = dao_c.addCritMili(id,m);
        }
        return bb;
    }










    /*      CASE D'USO UC8: Visualizza Servizi      */

    public List <Servizio> cercaservizi (Date dinizio, Date dfine)
    {
        List <Servizio> ls = new ArrayList<Servizio>();
        for (Servizio ss : servizi)
        {
            boolean overlap = isOverlap(dinizio, dfine, ss.getData_inizio(), ss.getData_fine());
            if(overlap)
            {
                ls.add(ss);
            }
        }
        return ls;
    }

    public List <Comandata> cercacomandate (Date dinizio, Date dfine)
    {
        List <Comandata> lc = new ArrayList<Comandata>();
        for (Comandata cc : comandate)
        {
            boolean overlap = isOverlap(dinizio, dfine, cc.getDinizio(), cc.getDfine());
            if(overlap)
            {
                lc.add(cc);
            }
        }
        return lc;
    }


    /*      CASE D'USO UC11: Visualizza Criticità      */

    public List <Critik> getAllCritikMilitari ()
    {
        List<Critik> cri = new ArrayList<Critik>();
        cri.addAll(dao_crt.getMS());
        cri.addAll(dao_crt.getMC());
        return cri;
    }

    public List <Critik> getAllCritikMezzi ()
    {
        List<Critik> cri = new ArrayList<Critik>();
        cri.addAll(dao_crt.getMzS());
        cri.addAll(dao_crt.getMzC());
        return cri;
    }








    /*      CASE D'USO UC9: Cambio Turno Servizi      */


    public List <Servizio> elencoservizi ()
    {
        List <Servizio> ls = new ArrayList<Servizio>();
        for (Servizio ss : servizi)
        {
            boolean overlap = isEnter(ss.getData_fine());
            if(overlap)
            {
                ls.add(ss);
            }
        }
        return ls;
    }

    public List <Comandata> elencocomandate ()
    {
        List <Comandata> lc = new ArrayList<Comandata>();
        for (Comandata cc : comandate)
        {
            boolean overlap = isEnter(cc.getDfine());
            if(overlap)
            {
                lc.add(cc);
            }
        }
        return lc;
    }

    public boolean changeMilitaryS(int id, String old, MilitareServizio nuovo)
    {
        for(Servizio ss : servizi)
        {
            if(ss.getId() == id)
            {
                for(MilitareServizio ms : ss.getElencoMilitari())
                {
                    if(ms.getMatricola().equals(old))
                    {
                        dao_s.changeMilitare(id,old,nuovo.getMatricola());
                        System.out.println("Servizio aggiornato nel DB");
                        servizi = dao_s.getAll();
                        System.out.println("Servizio aggiornato nella memoria locale");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean changeMilitaryC(int id, String old, String nuovo)
    {
        for(Comandata cc : comandate)
        {
            if(cc.getId() == id)
            {
                for(String ms : cc.getMilitari())
                {
                    if(ms.equals(old))
                    {
                        dao_c.changeMilitare(id,old,nuovo);
                        System.out.println("Servizio aggiornato nel DB");
                        comandate = dao_c.getAll();
                        System.out.println("Servizio aggiornato nella memoria locale");
                        return true;
                    }
                }
            }
        }
        return false;
    }











    /*      CASE D'USO UC10: Sostituzione Mezzo      */

    public boolean changeMezzoS(int id, String old, String nuova)
    {
        for(Servizio ss : servizi)
        {
            if(ss.getId() == id)
            {
                for(Mezzo mz : ss.getElencoMezzi())
                {
                    if(mz.getTarga().equals(old))
                    {
                        dao_s.changeMezzo(id,old,nuova);
                        System.out.println("Servizio aggiornato nel DB");
                        servizi = dao_s.getAll();
                        System.out.println("Servizio aggiornato nella memoria locale");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean changeMezzoC(int id, String old, String nuova)
    {
        for(Comandata cc : comandate)
        {
            if(cc.getId() == id)
            {
                dao_c.changeMezzo(id,old,nuova);
                System.out.println("Servizio aggiornato nel DB");
                comandate = dao_c.getAll();
                System.out.println("Servizio aggiornato nella memoria locale");
                return true;
            }
        }
        return false;
    }







    /*      CASE D'USO UC12: Info Servizi Personale      */

    public List<Servizio> getServMili(String matricola)
    {
        List <Servizio> ls = new ArrayList<Servizio>();
        for(Servizio ss : servizi)
        {
            for(MilitareServizio mm : ss.getElencoMilitari())
            {
                if(mm.getMatricola().equals(matricola))
                {
                    ls.add(ss);
                }
            }
        }
        return ls;
    }

    public List<Comandata> getComMili(String matricola)
    {
        List <Comandata> lc = new ArrayList<Comandata>();
        for(Comandata cc : comandate)
        {
            for(String mm : cc.getMilitari())
            {
                if(mm.equals(matricola))
                {
                    lc.add(cc);
                }
            }
        }
        return lc;
    }
}