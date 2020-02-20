package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.LoginResponseDto;
import com.ai.ecommercej4j.model.Ordine;
import com.ai.ecommercej4j.model.OrdineCreateDto;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.RigaOrdine;
import com.ai.ecommercej4j.model.Utente;
import com.ai.ecommercej4j.model.ViewCarrelloResponseDto;
import com.ai.ecommercej4j.repository.OrdineRepository;
import com.ai.ecommercej4j.repository.ProdottoRepository;
import com.ai.ecommercej4j.repository.RigaOrdineRepository;
import com.ai.ecommercej4j.repository.UtenteRepository;
import com.ai.ecommercej4j.service.OrdineService;
import com.ai.ecommercej4j.service.SecurityService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
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
        String token = dto.getToken();
        Prodotto prodotto = dto.getDati();
        Utente utente = utenteRepository.findByToken(token);
        // Verifica se il token è di un utente anonimo o registsto...
        if (securityService.checkToken(token) || securityService.checkAnonimo(token)) {
            // ... se risuta positivo recupera l'ordine dell'utente nello stato carrello
            Optional<Ordine> optionalOrdine = utente.getOrdini().stream()
                    .filter(o -> o.getStato().equals("carrello"))
                    .findFirst();
            Ordine ordine;
            // Se l'utente non ha ordini nello stato carrello...
            if (optionalOrdine.isEmpty()) {
                // ...ne viene creato uno
                ordine = new Ordine(utente, generateNumeroOrdine());
                ordineRepository.save(ordine);
                // Viene associato l'ordine all'utente
                utente.getOrdini().add(ordine);
                utenteRepository.save(utente);
            } else {
                // ...altrimenti viene recuperato l'ordine già esistente
                ordine = optionalOrdine.get();
            }
            // Recupera la riga del prodotto da aggiungere
            Optional<RigaOrdine> optionalRiga = ordine.getRighe().stream()
                    .filter(r -> r.getProdotto().getId().equals(prodotto.getId()))
                    .findFirst();
            RigaOrdine rigaOrdine;
            // Se la riga non esiste...
            if (optionalRiga.isEmpty()) {
                // ... ne crea una nuova con quantità uguale ad 1
                rigaOrdine = new RigaOrdine(1, ordine, prodotto);
                rigaOrdineRepository.save(rigaOrdine);
                // Associa la nuova riga all'ordine
                ordine.getRighe().add(rigaOrdine);
                ordineRepository.save(ordine);
                // Associa la riga al prodotto
                prodotto.getRighe().add(rigaOrdine);
                prodottoRepository.save(prodotto);
            } else {
                // ...altrimenti ne incrementa di 1 la quantità
                rigaOrdine = optionalRiga.get();
                rigaOrdine.setQta(rigaOrdine.getQta() + 1);
                rigaOrdineRepository.save(rigaOrdine);
            }
        }
    }

    public int generateNumeroOrdine() {
//        List<Ordine> ordini = ordineRepository.findAll();
//        Optional<Ordine> optionalOrdine = ordini.parallelStream()
//                .max((o1, o2)
//                        -> Integer.valueOf(o1.getNumero())
//                        .compareTo(Integer.valueOf(o2.getNumero())));
//        int numero;
//        if (optionalOrdine.isEmpty()) {
//            numero = 1;
//        } else {
//            numero = optionalOrdine.get().getNumero() + 1;
//        }
//        return numero;
        Optional<Integer> i = ordineRepository.selectMaxNumero();
        if (i.isEmpty()) {
            return 1;
        } else {
            return i.get() + 1;
        }
    }

    @Override
    public ViewCarrelloResponseDto viewCarrello(LoginResponseDto dto) {
        ViewCarrelloResponseDto rdto = new ViewCarrelloResponseDto();
        String tok = dto.getToken();
        Ordine carrello = new Ordine();
        double totale=0;
        Utente utente = utenteRepository.findByToken(tok);
        System.out.println(utente);
        List<RigaOrdine> listaRigheOrdine;
        if (securityService.checkToken(tok) || securityService.checkAnonimo(tok)) {
            carrello = utente.getOrdini()
                    .parallelStream()
                    .filter(o -> o.getStato().equals("carrello"))
                    .findFirst()
                    .get();
            listaRigheOrdine = carrello.getRighe();
            for(RigaOrdine r: listaRigheOrdine){
                totale += r.getProdotto().getPrezzo();
            }
        } else {
            System.out.println("sto nell'else");
            listaRigheOrdine = Collections.emptyList();
        }
        rdto.setCarrello(listaRigheOrdine);
        rdto.setOrdine(carrello);
        rdto.setTotale(totale);
        return rdto;
    }
}
