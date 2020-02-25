package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.AssociazioneTagliaColori;
import com.ai.ecommercej4j.model.Colori;
import com.ai.ecommercej4j.model.Ordine;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.ProdottoTaglia;
import com.ai.ecommercej4j.model.RigaOrdine;
import com.ai.ecommercej4j.model.Spedizione;
import com.ai.ecommercej4j.model.Taglia;
import com.ai.ecommercej4j.model.Utente;
import com.ai.ecommercej4j.repository.*;
import com.ai.ecommercej4j.service.DevelopmentService;
import com.ai.ecommercej4j.service.StartupDataService;
import java.time.LocalDate;
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
    @Autowired
    private ProdottoTagliaRepository prodottoTagliaRepository;
    @Autowired
    private TagliaColoriRepository associazioneTagliaColoriRepository;

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

        generateAssociazioneTagliaColori();
    }

    private void dropDataBase() {
        // elimina tutti dati dal db
        prodottoTagliaRepository.deleteAllInBatch();
        rigaOrdineRepository.deleteAllInBatch();
        ordineRepository.deleteAllInBatch();
        prodottoRepository.deleteAllInBatch();
        utenteRepository.deleteAllInBatch();
        spedizioneRespository.deleteAllInBatch();
        associazioneTagliaColoriRepository.deleteAllInBatch();
        tagliaRepository.deleteAllInBatch();
        coloriRepository.deleteAllInBatch();
    }

    private void generateProdotti() {

        Prodotto p0 = new Prodotto();
        p0.setDescrizione("prova");
        prodottoRepository.save(p0);

        Taglia t0 = new Taglia();
        t0.setDescrizione("prova");
        tagliaRepository.save(t0);

        ProdottoTaglia pt = new ProdottoTaglia();
        pt.setProdotto(p0);
        pt.setTaglia(t0);
        prodottoTagliaRepository.save(pt);

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

        tagliaRepository.save(new Taglia("43", "M"));
        tagliaRepository.save(new Taglia("68", "XXL"));
        tagliaRepository.save(new Taglia("23", "L"));
        tagliaRepository.save(new Taglia("48", "XXS"));
        tagliaRepository.save(new Taglia("65", "XS"));
        tagliaRepository.save(new Taglia("36", "XXXL"));
        tagliaRepository.save(new Taglia("20", "S"));
        tagliaRepository.save(new Taglia("34", "XL"));
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
        o1.setStato("Carrello");
        ordineRepository.save(o1);
        Ordine o2 = new Ordine();
        o2.setData(LocalDate.now());
        o2.setNumero(2);
        o2.setStato("Carrello");
        ordineRepository.save(o2);
        Ordine o3 = new Ordine();
        o3.setData(LocalDate.now());
        o3.setNumero(3);
        o3.setStato("carrello");
        ordineRepository.save(o3);
        Ordine o4 = new Ordine();
        o4.setData(LocalDate.now());
        o4.setNumero(4);
        o4.setStato("carrello");
        ordineRepository.save(o4);
        Ordine o5 = new Ordine();
        o5.setData(LocalDate.now());
        o5.setNumero(5);
        o5.setStato("carrello");
        ordineRepository.save(o5);
        Ordine o6 = new Ordine();
        o6.setData(LocalDate.now());
        o6.setNumero(6);
        o6.setStato("carrello");
        ordineRepository.save(o6);
        Ordine o7 = new Ordine();
        o7.setData(LocalDate.now());
        o7.setNumero(7);
        o7.setStato("In checkout");
        ordineRepository.save(o7);
        Ordine o8 = new Ordine();
        o8.setData(LocalDate.now());
        o8.setNumero(8);
        o8.setStato("Pagato");
        ordineRepository.save(o8);
        Ordine o9 = new Ordine();
        o9.setData(LocalDate.now());
        o9.setNumero(9);
        o9.setStato("Pagato");
        ordineRepository.save(o9);
        Ordine o10 = new Ordine();
        o10.setData(LocalDate.now());
        o10.setNumero(10);
        o10.setStato("Spedito");
        ordineRepository.save(o10);

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
        RigaOrdine r21 = new RigaOrdine();
        r13.setQta(2);
        rigaOrdineRepository.save(r21);
        RigaOrdine r31 = new RigaOrdine();
        r13.setQta(3);
        rigaOrdineRepository.save(r31);
        RigaOrdine r41 = new RigaOrdine();
        r13.setQta(4);
        rigaOrdineRepository.save(r41);
        RigaOrdine r51 = new RigaOrdine();
        r13.setQta(5);
        rigaOrdineRepository.save(r51);
        RigaOrdine r61 = new RigaOrdine();
        r13.setQta(6);
        rigaOrdineRepository.save(r61);
        RigaOrdine r71 = new RigaOrdine();
        r13.setQta(7);
        rigaOrdineRepository.save(r71);
        RigaOrdine r81 = new RigaOrdine();
        r13.setQta(8);
        rigaOrdineRepository.save(r81);
        RigaOrdine r91 = new RigaOrdine();
        r13.setQta(9);
        rigaOrdineRepository.save(r91);
        RigaOrdine r101 = new RigaOrdine();
        r13.setQta(10);
        rigaOrdineRepository.save(r101);

        // ... associo le righe all'ordine
        o1.getRighe().add(r11);
        o1.getRighe().add(r12);
        o1.getRighe().add(r13);
        o2.getRighe().add(r21);
        o3.getRighe().add(r31);
        o4.getRighe().add(r41);
        o5.getRighe().add(r51);
        o6.getRighe().add(r61);
        o7.getRighe().add(r71);
        o8.getRighe().add(r81);
        o9.getRighe().add(r91);
        o10.getRighe().add(r101);
        ordineRepository.save(o1);
        ordineRepository.save(o2);
        ordineRepository.save(o3);
        ordineRepository.save(o4);
        ordineRepository.save(o5);
        ordineRepository.save(o6);
        ordineRepository.save(o7);
        ordineRepository.save(o8);
        ordineRepository.save(o9);
        ordineRepository.save(o10);
        r11.setOrdine(o1);
        rigaOrdineRepository.save(r11);
        r12.setOrdine(o1);
        rigaOrdineRepository.save(r12);
        r13.setOrdine(o1);
        rigaOrdineRepository.save(r13);
        r21.setOrdine(o2);
        rigaOrdineRepository.save(r21);
        r31.setOrdine(o3);
        rigaOrdineRepository.save(r31);
        r41.setOrdine(o4);
        rigaOrdineRepository.save(r41);
        r51.setOrdine(o5);
        rigaOrdineRepository.save(r51);
        r61.setOrdine(o6);
        rigaOrdineRepository.save(r61);
        r71.setOrdine(o7);
        rigaOrdineRepository.save(r71);
        r81.setOrdine(o8);
        rigaOrdineRepository.save(r81);
        r91.setOrdine(o9);
        rigaOrdineRepository.save(r91);
        r101.setOrdine(o10);
        rigaOrdineRepository.save(r101);

        // ... associo gli ordini all'utente
        u1.getOrdini().add(o1);
        u1.getOrdini().add(o2);
        u1.getOrdini().add(o3);
        u1.getOrdini().add(o4);
        u1.getOrdini().add(o5);
        u1.getOrdini().add(o6);
        u1.getOrdini().add(o7);
        u1.getOrdini().add(o8);
        u1.getOrdini().add(o9);
        u1.getOrdini().add(o10);
        utenteRepository.save(u1);
        o1.setUtente(u1);
        o2.setUtente(u1);
        o3.setUtente(u1);
        o4.setUtente(u1);
        o5.setUtente(u1);
        o6.setUtente(u1);
        o7.setUtente(u1);
        o8.setUtente(u1);
        o9.setUtente(u1);
        o10.setUtente(u1);
        ordineRepository.save(o1);
        ordineRepository.save(o2);
        ordineRepository.save(o3);
        ordineRepository.save(o4);
        ordineRepository.save(o5);
        ordineRepository.save(o6);
        ordineRepository.save(o7);
        ordineRepository.save(o8);
        ordineRepository.save(o9);
        ordineRepository.save(o10);

        // ... associo le righe ai prodotti
        p1.getRighe().add(r11);
        p1.getRighe().add(r12);
        p1.getRighe().add(r13);
        p1.getRighe().add(r21);
        p1.getRighe().add(r31);
        p1.getRighe().add(r41);
        p1.getRighe().add(r51);
        p1.getRighe().add(r61);
        p1.getRighe().add(r71);
        p1.getRighe().add(r81);
        p1.getRighe().add(r91);
        p1.getRighe().add(r101);
        prodottoRepository.save(p1);
        r11.setProdotto(p1);
        r12.setProdotto(p1);
        r13.setProdotto(p1);
        r21.setProdotto(p1);
        r31.setProdotto(p1);
        r41.setProdotto(p1);
        r51.setProdotto(p1);
        r61.setProdotto(p1);
        r71.setProdotto(p1);
        r81.setProdotto(p1);
        r91.setProdotto(p1);
        r101.setProdotto(p1);
        rigaOrdineRepository.save(r11);
        rigaOrdineRepository.save(r12);
        rigaOrdineRepository.save(r13);
        rigaOrdineRepository.save(r21);
        rigaOrdineRepository.save(r31);
        rigaOrdineRepository.save(r41);
        rigaOrdineRepository.save(r51);
        rigaOrdineRepository.save(r61);
        rigaOrdineRepository.save(r71);
        rigaOrdineRepository.save(r81);
        rigaOrdineRepository.save(r91);
        rigaOrdineRepository.save(r101);
    }

    private void generateAssociazioneTagliaColori() {
        AssociazioneTagliaColori tc = new AssociazioneTagliaColori();
        Taglia t1 = new Taglia("82", "XXXS");
        t1.getTagliaColori().add(tc);
        Colori c1 = new Colori();
        c1.setCodice("99");
        c1.setDescrizione("rosso pompeiano");
        c1.getTagliaColori().add(tc);
        coloriRepository.save(c1);
        tagliaRepository.save(t1);
        tc.setTaglia(t1);
        tc.setColore(c1);
        associazioneTagliaColoriRepository.save(tc);
    }
}
