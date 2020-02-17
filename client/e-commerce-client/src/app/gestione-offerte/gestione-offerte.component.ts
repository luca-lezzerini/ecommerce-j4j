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

  ngOnInit() {

  }

  conferma() {
    const dto: ProdottoUpdateDto = new ProdottoUpdateDto();
    dto.token = this.sessione.token;
    dto.dati = this.prodottoSelezionato;
    dto.dati.prezzo = this.newPrice;
    dto.dati.offerta = this.inOfferta;
    console.log(dto);
    const obs: Observable<any> = this.http.post<any>('http://localhost:8080/update-prodotto', dto);
    obs.subscribe(risp => {
      this.cercaCodice();
      this.selezionaEnabled = false;
    });
  }

  annulla() {
    this.selezionaEnabled = false;
  }

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

  seleziona(id: number) {
    this.selezionaEnabled = true;
    this.getDettagli(id);
    this.newPrice = this.prodottoSelezionato.prezzo;
    this.inOfferta = this.prodottoSelezionato.offerta;
  }

  getDettagli(id: number) {
    this.prodotti.forEach(p => {
      if (p.id === id) {
        this.prodottoSelezionato.id = p.id;
        this.prodottoSelezionato.codice = p.codice;
        this.prodottoSelezionato.descrizione = p.descrizione;
        this.prodottoSelezionato.prezzo = p.prezzo;
        this.prodottoSelezionato.offerta = p.offerta;
      }
    });
  }
}
