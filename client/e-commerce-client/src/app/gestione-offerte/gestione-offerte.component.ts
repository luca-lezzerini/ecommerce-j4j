import { ProdottoUpdateDto } from './../classi/prodotto-update-dto';
import { ProdottoSearchResultsDto } from './../classi/prodotto-search-results';
import { Observable } from 'rxjs';
import { AreaComuneService } from './../area-comune.service';
import { HttpClient } from '@angular/common/http';
import { ProdottoSearchDto } from './../classi/prodotto-search-dto';
import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gestione-offerte',
  templateUrl: './gestione-offerte.component.html',
  styleUrls: ['./gestione-offerte.component.css']
})
export class GestioneOfferteComponent implements OnInit {

  newPrice: number;
  inOfferta: boolean;
  searchCode = '';
  selezionaEnabled = false;
  prodotti: Prodotto[] = [];
  prodottoSelezionato = new Prodotto();

  constructor(
    private http: HttpClient,
    private sessione: AreaComuneService
  ) { }

  ngOnInit() { }

  /**
   * Manda una richiesta al server con un oggetto contenente il token per poter
   * verificare l'accesso e i nuovi dati immessi dal cliente. Dopodiché,
   * ripete la ricerca e mostra la visuale della selezione dei prodotti
   */
  conferma() {
    const dto: ProdottoUpdateDto = new ProdottoUpdateDto();
    dto.token = this.sessione.token;
    dto.dati = this.prodottoSelezionato;
    dto.dati.prezzo = this.newPrice;
    dto.dati.offerta = this.inOfferta;
    const obs: Observable<any> = this.http.post<any>('http://localhost:8080/update-prodotto', dto);
    obs.subscribe(risp => {
      this.cercaCodice();
      this.selezionaEnabled = false;
    });
  }

  /**
   * Mostra la visuale della selezione dei prodotti e pulisce
   * il campo di ricerda del prodotto
   */
  annulla() {
    this.searchCode = '';
    this.selezionaEnabled = false;
  }

  /**
   * Manda una richiesta al server un oggetto contenente il token per poter
   * verificare l'accesso e la stringa contenente il parametro di ricerca. Se
   * il server ritorna una lista di prodotti non nulla, mostro il suo contenuto
   */
  cercaCodice() {
    // prepara richiesta http
    const dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.searchKey = this.searchCode;
    dto.token = this.sessione.token;
    // preparo richiesta al server
    const obs: Observable<ProdottoSearchResultsDto> =
      this.http.post<ProdottoSearchResultsDto>('http://localhost:8080/search-prodotto', dto);
    // invio richiesta al server
    obs.subscribe(response => {
      if (response) {
        this.prodotti = response.result;
      }
    });
  }

  /**
   * Nasconte la visuale precedente e mostra il prodotto selezionato
   * in maniera da poterlo gestire dal lato delle offerte
   * @param p Viene passato il prodotto selezionato
   */
  seleziona(p: Prodotto) {
    this.selezionaEnabled = true;
    this.prodottoSelezionato = p;
    this.newPrice = this.prodottoSelezionato.prezzo;
    this.inOfferta = this.prodottoSelezionato.offerta;
  }
}
