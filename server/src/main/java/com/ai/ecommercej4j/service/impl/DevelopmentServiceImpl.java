package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Colori;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.Taglia;
import com.ai.ecommercej4j.repository.*;
import com.ai.ecommercej4j.service.DevelopmentService;
import com.ai.ecommercej4j.service.StartupDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevelopmentServiceImpl implements DevelopmentService {

    @Autowired
    ProdottoRepository pr;
    @Autowired
    UtenteRepository ur;
    @Autowired
    SpedizioneRepository sr;
    @Autowired
    TagliaRepository tr;
    @Autowired
    ColoriRepository cr;
    @Autowired
    StartupDataService sds;

    @Override
    public void generateTestData() {

        // elimina tutti i dati presenti sul db
        dropDataBase();

        // inserimento dati di startup
        sds.createAllStartupData();

        // inserimento prodotti
        generateProdotti();
        
        // inserimento taglie
        generateTaglie();
        
        // inserimento colori
        generateColori();
    }

    @Override
    public void dropDataBase() {
        // elimina tutti dati dal db
        pr.deleteAllInBatch();
        ur.deleteAllInBatch();
        sr.deleteAllInBatch();
        tr.deleteAllInBatch();
        cr.deleteAllInBatch();
    }

    @Override
    public void generateProdotti() {

        Prodotto p = new Prodotto();
        
        p.setPrezzo(12.50);
        p.setCodice("32");
        p.setDescrizione("calzini");
        p.setOfferta(true);
        pr.save(p);

        p.setPrezzo(25.90);
        p.setCodice("15");
        p.setDescrizione("maglia");
        p.setOfferta(false);
        pr.save(p);

        p.setPrezzo(6.50);
        p.setCodice("88");
        p.setDescrizione("felpa");
        p.setOfferta(true);
        pr.save(p);

        p.setPrezzo(39.64);
        p.setCodice("51");
        p.setDescrizione("jeans");
        p.setOfferta(true);
        pr.save(p);

        p.setPrezzo(9.00);
        p.setCodice("34");
        p.setDescrizione("calzini");
        p.setOfferta(true);
        pr.save(p);

        p.setPrezzo(29.99);
        p.setCodice("19");
        p.setDescrizione("cappello");
        p.setOfferta(false);
        pr.save(p);

        p.setPrezzo(54.01);
        p.setCodice("60");
        p.setDescrizione("jeans slim");
        p.setOfferta(true);
        pr.save(p);

        p.setPrezzo(9.89);
        p.setCodice("77");
        p.setDescrizione("sciarpa");
        p.setOfferta(true);
        pr.save(p);

        p.setPrezzo(4.02);
        p.setCodice("42");
        p.setDescrizione("maglioncino");
        p.setOfferta(false);
        pr.save(p);

        p.setPrezzo(269.99);
        p.setCodice("27");
        p.setDescrizione("felpa con cappuccio");
        p.setOfferta(false);
        pr.save(p);

        p.setPrezzo(152.00);
        p.setCodice("43");
        p.setDescrizione("scarpe sportive");
        p.setOfferta(true);
        pr.save(p);

        p.setPrezzo(44.64);
        p.setCodice("84");
        p.setDescrizione("scarpe di pelle");
        p.setOfferta(false);
        pr.save(p);

        p.setPrezzo(85.50);
        p.setCodice("21");
        p.setDescrizione("camicia");
        p.setOfferta(false);
        pr.save(p);

        p.setPrezzo(10.00);
        p.setCodice("30");
        p.setDescrizione("cravatta");
        p.setOfferta(false);
        pr.save(p);
    }

    @Override
    public void generateTaglie() {
        
        Taglia t = new Taglia();
        
        t.setCodice("20");
        t.setDescrizione("XL");
        tr.save(t);

        t.setCodice("37");
        t.setDescrizione("S");
        tr.save(t);

        t.setCodice("11");
        t.setDescrizione("M");
        tr.save(t);

        t.setCodice("46");
        t.setDescrizione("XXS");
        tr.save(t);

        t.setCodice("89");
        t.setDescrizione("L");
        tr.save(t);

        t.setCodice("68");
        t.setDescrizione("XXL");
        tr.save(t);

        t.setCodice("13");
        t.setDescrizione("XS");
        tr.save(t);

        t.setCodice("77");
        t.setDescrizione("XXXL");
        tr.save(t);

        t.setCodice("47");
        t.setDescrizione("XXXS");
        tr.save(t);
    }

    @Override
    public void generateColori() {
        
        Colori c = new Colori();
        
        c.setCodice("32");
        c.setDescrizione("blu");
        cr.save(c);
        
        c.setCodice("35");
        c.setDescrizione("bianco");
        cr.save(c);
        
        c.setCodice("11");
        c.setDescrizione("rosso");
        cr.save(c);
        
        c.setCodice("78");
        c.setDescrizione("nero");
        cr.save(c);
        
        c.setCodice("56");
        c.setDescrizione("giallo");
        cr.save(c);
        
        c.setCodice("77");
        c.setDescrizione("blu");
        cr.save(c);
        
        c.setCodice("47");
        c.setDescrizione("verde");
        cr.save(c);
        
        c.setCodice("32");
        c.setDescrizione("arancione");
        cr.save(c);
        
        c.setCodice("51");
        c.setDescrizione("viola");
        cr.save(c);
        
        c.setCodice("90");
        c.setDescrizione("lilla");
        cr.save(c);
    }
}
