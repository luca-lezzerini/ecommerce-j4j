package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Ordine;
import com.ai.ecommercej4j.model.OrdineCreateDto;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.RigaOrdine;
import com.ai.ecommercej4j.model.Utente;
import com.ai.ecommercej4j.repository.OrdineRepository;
import com.ai.ecommercej4j.repository.ProdottoRepository;
import com.ai.ecommercej4j.repository.RigaOrdineRepository;
import com.ai.ecommercej4j.repository.UtenteRepository;
import com.ai.ecommercej4j.service.OrdineService;
import com.ai.ecommercej4j.service.SecurityService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private RigaOrdineRepository rigaOrdineRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Override
    public void addCarrello(OrdineCreateDto dto) {
        String tok = dto.getToken();
        Prodotto prod = dto.getDati();
        Utente utente = utenteRepository.findByToken(tok);
        // Verifica se il token è di un utente anonimo o registsto...
        if (securityService.checkToken(tok) || securityService.checkAnonimo(tok)) {
            // ... se risuta positivo crea l'ordine e aggiunge una riga
            // TODO controllare se l'orfdine è gia esistente
            Ordine ordine = new Ordine();
            ordine.setData(LocalDate.now());
            ordine.setStato("carrello");
            ordine.setNumero((int) (Math.random() * 10000 + 1));
            ordine.setUtente(utente);
            ordineRepository.save(ordine);
            utente.getOrdini().add(ordine);
            utenteRepository.save(utente);
            RigaOrdine rigaOrdine = new RigaOrdine(1, ordine, prod);
            rigaOrdineRepository.save(rigaOrdine);
            ordine.getRighe().add(rigaOrdine);
            ordineRepository.save(ordine);
            prod.getRighe().add(rigaOrdine);
            prodottoRepository.save(prod);
        }
        // ...altrimenti non fa nulla
    }
}
