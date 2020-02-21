package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Colori;
import com.ai.ecommercej4j.model.Ordine;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.RigaOrdine;
import com.ai.ecommercej4j.model.Spedizione;
import com.ai.ecommercej4j.model.Taglia;
import com.ai.ecommercej4j.model.Utente;
import com.ai.ecommercej4j.repository.*;
import com.ai.ecommercej4j.service.DevelopmentService;
import com.ai.ecommercej4j.service.StartupDataService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevelopmentServiceImpl implements DevelopmentService {

    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private SpedizioneRepository spedizioneRespository;
    @Autowired
    private TagliaRepository tagliaRepository;
    @Autowired
    private ColoriRepository coloriRepository;
    @Autowired
    private StartupDataService startupDataService;
    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private RigaOrdineRepository rigaOrdineRepository;
    @Autowired
    private OrdineServiceImpl ordineServiceImpl;

    @Override
    public void generateTestData() {

        // elimina tutti i dati presenti sul db
        dropDataBase();

        // inserimento dati di startup
        startupDataService.createAllStartupData();

        // inserimento prodotti
        generateProdotti();

        // inserimento taglie
        generateTaglie();

        // inserimento colori
        generateColori();

        // inserimento spedizioni
        generateSpedizioni();

        // Generazione ordini e righe associate
        generateUtentiProdottiOrdiniRighe();
    }

    private void dropDataBase() {
        // elimina tutti dati dal db
        rigaOrdineRepository.deleteAllInBatch();
        ordineRepository.deleteAllInBatch();
        prodottoRepository.deleteAllInBatch();
        utenteRepository.deleteAllInBatch();
        spedizioneRespository.deleteAllInBatch();
        tagliaRepository.deleteAllInBatch();
        coloriRepository.deleteAllInBatch();
    }

    private void generateProdotti() {

        Prodotto p1 = new Prodotto();
        p1.setPrezzo(12.50);
        p1.setCodice("32");
        p1.setDescrizione("calzini");
        p1.setOfferta(true);
        prodottoRepository.save(p1);

        Prodotto p2 = new Prodotto();
        p2.setPrezzo(25.90);
        p2.setCodice("15");
        p2.setDescrizione("maglia");
        p2.setOfferta(false);
        prodottoRepository.save(p2);

        Prodotto p3 = new Prodotto();
        p3.setPrezzo(6.50);
        p3.setCodice("88");
        p3.setDescrizione("felpa");
        p3.setOfferta(true);
        prodottoRepository.save(p3);

        Prodotto p4 = new Prodotto();
        p4.setPrezzo(39.64);
        p4.setCodice("51");
        p4.setDescrizione("jeans");
        p4.setOfferta(true);
        prodottoRepository.save(p4);

        Prodotto p5 = new Prodotto();
        p5.setPrezzo(9.00);
        p5.setCodice("34");
        p5.setDescrizione("calzini");
        p5.setOfferta(true);
        prodottoRepository.save(p5);

        Prodotto p6 = new Prodotto();
        p6.setPrezzo(29.99);
        p6.setCodice("19");
        p6.setDescrizione("cappello");
        p6.setOfferta(false);
        prodottoRepository.save(p6);

        Prodotto p7 = new Prodotto();
        p7.setPrezzo(54.01);
        p7.setCodice("60");
        p7.setDescrizione("jeans slim");
        p7.setOfferta(true);
        prodottoRepository.save(p7);

        Prodotto p8 = new Prodotto();
        p8.setPrezzo(9.89);
        p8.setCodice("77");
        p8.setDescrizione("sciarpa");
        p8.setOfferta(true);
        prodottoRepository.save(p8);

        Prodotto p9 = new Prodotto();
        p9.setPrezzo(4.02);
        p9.setCodice("42");
        p9.setDescrizione("maglioncino");
        p9.setOfferta(false);
        prodottoRepository.save(p9);

        Prodotto p10 = new Prodotto();
        p10.setPrezzo(269.99);
        p10.setCodice("27");
        p10.setDescrizione("felpa con cappuccio");
        p10.setOfferta(false);
        prodottoRepository.save(p10);

        Prodotto p11 = new Prodotto();
        p11.setPrezzo(152.00);
        p11.setCodice("43");
        p11.setDescrizione("scarpe sportive");
        p11.setOfferta(true);
        prodottoRepository.save(p11);

        Prodotto p12 = new Prodotto();
        p12.setPrezzo(44.64);
        p12.setCodice("84");
        p12.setDescrizione("scarpe di pelle");
        p12.setOfferta(false);
        prodottoRepository.save(p12);

        Prodotto p13 = new Prodotto();
        p13.setPrezzo(85.50);
        p13.setCodice("21");
        p13.setDescrizione("camicia");
        p13.setOfferta(false);
        prodottoRepository.save(p13);

        Prodotto p14 = new Prodotto();
        p14.setPrezzo(10.00);
        p14.setCodice("30");
        p14.setDescrizione("cravatta");
        p14.setOfferta(false);
        prodottoRepository.save(p14);
    }

    private void generateTaglie() {

        Taglia t1 = new Taglia();
        t1.setCodice("20");
        t1.setDescrizione("XL");
        tagliaRepository.save(t1);

        Taglia t2 = new Taglia();
        t2.setCodice("37");
        t2.setDescrizione("S");
        tagliaRepository.save(t2);

        Taglia t3 = new Taglia();
        t3.setCodice("11");
        t3.setDescrizione("M");
        tagliaRepository.save(t3);

        Taglia t4 = new Taglia();
        t4.setCodice("46");
        t4.setDescrizione("XXS");
        tagliaRepository.save(t4);

        Taglia t5 = new Taglia();
        t5.setCodice("89");
        t5.setDescrizione("L");
        tagliaRepository.save(t5);

        Taglia t6 = new Taglia();
        t6.setCodice("68");
        t6.setDescrizione("XXL");
        tagliaRepository.save(t6);

        Taglia t7 = new Taglia();
        t7.setCodice("13");
        t7.setDescrizione("XS");
        tagliaRepository.save(t7);

        Taglia t8 = new Taglia();
        t8.setCodice("77");
        t8.setDescrizione("XXXL");
        tagliaRepository.save(t8);

        Taglia t9 = new Taglia();
        t9.setCodice("47");
        t9.setDescrizione("XXXS");
        tagliaRepository.save(t9);

        Taglia t10 = new Taglia();
        t10.setCodice("47");
        t10.setDescrizione("XXXXS");
        tagliaRepository.save(t10);
    }

    private void generateColori() {

        Colori c1 = new Colori();
        c1.setCodice("32");
        c1.setDescrizione("blu");
        coloriRepository.save(c1);

        Colori c2 = new Colori();
        c2.setCodice("35");
        c2.setDescrizione("bianco");
        coloriRepository.save(c2);

        Colori c3 = new Colori();
        c3.setCodice("11");
        c3.setDescrizione("rosso");
        coloriRepository.save(c3);

        Colori c4 = new Colori();
        c4.setCodice("78");
        c4.setDescrizione("nero");
        coloriRepository.save(c4);

        Colori c5 = new Colori();
        c5.setCodice("56");
        c5.setDescrizione("giallo");
        coloriRepository.save(c5);

        Colori c6 = new Colori();
        c6.setCodice("77");
        c6.setDescrizione("blu");
        coloriRepository.save(c6);

        Colori c7 = new Colori();
        c7.setCodice("47");
        c7.setDescrizione("verde");
        coloriRepository.save(c7);

        Colori c8 = new Colori();
        c8.setCodice("32");
        c8.setDescrizione("arancione");
        coloriRepository.save(c8);

        Colori c9 = new Colori();
        c9.setCodice("51");
        c9.setDescrizione("viola");
        coloriRepository.save(c9);

        Colori c10 = new Colori();
        c10.setCodice("90");
        c10.setDescrizione("lilla");
        coloriRepository.save(c10);
    }

    private void generateSpedizioni() {

        Spedizione s1 = new Spedizione();
        s1.setCodice("10");
        s1.setDescrizione("spedito");
        s1.setPrezzo(3.79);
        spedizioneRespository.save(s1);

        Spedizione s2 = new Spedizione();
        s2.setCodice("10");
        s2.setDescrizione("in lavorazione");
        s2.setPrezzo(7.99);
        spedizioneRespository.save(s2);

        Spedizione s3 = new Spedizione();
        s3.setCodice("10");
        s3.setDescrizione("spedito");
        s3.setPrezzo(10.00);
        spedizioneRespository.save(s3);

        Spedizione s4 = new Spedizione();
        s4.setCodice("10");
        s4.setDescrizione("spedito");
        s4.setPrezzo(1.99);
        spedizioneRespository.save(s4);

        Spedizione s5 = new Spedizione();
        s5.setCodice("10");
        s5.setDescrizione("in lavorazione");
        s5.setPrezzo(5.00);
        spedizioneRespository.save(s5);

        Spedizione s6 = new Spedizione();
        s6.setCodice("10");
        s6.setDescrizione("spedito");
        s6.setPrezzo(2.99);
        spedizioneRespository.save(s6);

        Spedizione s7 = new Spedizione();
        s7.setCodice("10");
        s7.setDescrizione("in lavorazione");
        s7.setPrezzo(15.89);
        spedizioneRespository.save(s7);

        Spedizione s8 = new Spedizione();
        s8.setCodice("10");
        s8.setDescrizione("spedito");
        s8.setPrezzo(21.99);
        spedizioneRespository.save(s8);

        Spedizione s9 = new Spedizione();
        s9.setCodice("10");
        s9.setDescrizione("in lavorazione");
        s9.setPrezzo(3.79);
        spedizioneRespository.save(s9);

        Spedizione s10 = new Spedizione();
        s10.setCodice("10");
        s10.setDescrizione("in lavorazione");
        s10.setPrezzo(10.00);
        spedizioneRespository.save(s10);
    }

    private void generateUtentiProdottiOrdiniRighe() {

        // Creo l'utente...
        Utente u1 = new Utente();
        u1.setUsername("relazione");
        u1.setPassword("relazione");
        utenteRepository.save(u1);

        // Creo il prodotto...
        Prodotto p1 = new Prodotto();
        p1.setDescrizione("Maglione per relazione");
        p1.setCodice("593");
        p1.setPrezzo(19.99);
        p1.setOfferta(false);
        prodottoRepository.save(p1);

        // Creo gli ordini ...
        Ordine o1 = new Ordine();
        o1.setData(LocalDate.now());
        o1.setNumero(1);
        o1.setStato("carrello");
        ordineRepository.save(o1);
        Ordine o2 = new Ordine();
        o2.setData(LocalDate.now());
        o2.setNumero(2);
        o2.setStato("carrello");
        ordineRepository.save(o2);

        // Creo le righe ...
        RigaOrdine r11 = new RigaOrdine();
        r11.setQta(5);
        rigaOrdineRepository.save(r11);
        RigaOrdine r12 = new RigaOrdine();
        r12.setQta(5);
        rigaOrdineRepository.save(r12);
        RigaOrdine r13 = new RigaOrdine();
        r13.setQta(5);
        rigaOrdineRepository.save(r13);

        // ... associo le righe all'ordine
        o1.getRighe().add(r11);
        o1.getRighe().add(r12);
        ordineRepository.save(o1);
        r11.setOrdine(o1);
        rigaOrdineRepository.save(r11);
        r12.setOrdine(o1);
        rigaOrdineRepository.save(r12);
        o2.getRighe().add(r13);
        ordineRepository.save(o2);
        r13.setOrdine(o2);
        rigaOrdineRepository.save(r13);

        // ... associo gli ordini all'utente
        u1.getOrdini().add(o1);
        u1.getOrdini().add(o2);
        utenteRepository.save(u1);
        o1.setUtente(u1);
        o2.setUtente(u1);
        ordineRepository.save(o1);
        ordineRepository.save(o2);

        // ... associo le righe ai prodotti
        p1.getRighe().add(r11);
        p1.getRighe().add(r12);
        p1.getRighe().add(r13);
        prodottoRepository.save(p1);
        r11.setProdotto(p1);
        r12.setProdotto(p1);
        r13.setProdotto(p1);
        rigaOrdineRepository.save(r11);
        rigaOrdineRepository.save(r12);
        rigaOrdineRepository.save(r13);
    }
}
