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
import java.util.Optional;
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
            // ... se risuta positivo recupera l'ordine dell'utente nello stato carrello
            Optional<Ordine> optional = utente.getOrdini().stream()
                    .filter(o -> o.getStato().equals("carrello"))
                    .findFirst();
            Ordine ordine;
            // Se l'utente non ha ordini nello stato carrello...
            if (optional.isEmpty()) {
                // ...ne viene creato uno
                ordine = new Ordine(utente);
                ordineRepository.save(ordine);
                // Viene associato l'ordine all'utente
                utente.getOrdini().add(ordine);
                utenteRepository.save(utente);
            } else {
                // ...altrimenti viene recuperato l'ordine già esistente
                ordine = optional.get();
            }
            RigaOrdine rigaOrdine = new RigaOrdine(1, ordine, prod);
            rigaOrdineRepository.save(rigaOrdine);
            ordine.getRighe().add(rigaOrdine);
            ordineRepository.save(ordine);
            prod.getRighe().add(rigaOrdine);
            prodottoRepository.save(prod);
        }
        // ...altrimenti non fa nulla
    }

    @Override
    public void viewCarrello(OrdineCreateDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
