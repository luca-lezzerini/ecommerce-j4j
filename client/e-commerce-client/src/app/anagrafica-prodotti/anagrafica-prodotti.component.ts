import { Observable } from 'rxjs';
import { ProdottoSearchDto } from './../classi/prodotto-search-dto';
import { Component, OnInit } from '@angular/core';
import { Prodotto } from '../classi/prodotto';
import { ProdottoSearchResultsDto } from '../classi/prodotto-search-results';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { ProdottoCreateDto } from '../classi/prodotto-create-dto';
import { ProdottoUpdateDto } from '../classi/prodotto-update-dto';
import { ProdottoDeleteDto } from '../classi/prodotto-delete-dto';

@Component({
  selector: 'app-anagrafica-prodotti',
  templateUrl: './anagrafica-prodotti.component.html',
  styleUrls: ['./anagrafica-prodotti.component.css']
})

export class AnagraficaProdottiComponent implements OnInit {

  id: number;
  codice = '';
  descrizione = '';
  prezzo: number;
  prodotti: Prodotto[] = [];
  showPanel: boolean;
  inputDisabled: boolean;
  showConferma: boolean;
  showAnnulla: boolean;
  showCrea: boolean;
  showModifica: boolean;
  showRimuovi: boolean;
  showSearchPanel: boolean;
  showResults: boolean;
  showAggiungi: boolean;
  trovatoQualcosa: boolean;
  searchKey = '';
  statoPrecedente = '';

  constructor(private http: HttpClient,
    private acService: AreaComuneService, ) {
    // imposta visibilità iniziale degli elementi dell'interfaccia
    this.showPanel = false;
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = false;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = true;
    this.showResults = false;
    this.showAggiungi = true;
  }

  ngOnInit() {

  }

  conferma() {
    // imposta visibilità degli elementi dell'interfaccia
    this.showPanel = this.statoPrecedente == 'modifica';
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = true;
    this.showResults = this.trovatoQualcosa;
    this.showAggiungi = true;

    // aggiorno lo stato
    this.statoPrecedente = 'conferma';

    // TODO: se i campi sono vuoti non esegue

    // eseguo operazione confermata in base allo stato precedente
    switch (this.statoPrecedente) {
      case 'crea': this.confermaCrea();
        break;
      case 'modifica': this.confermaModifica();
        break;
      case 'rimuovi': this.confermaRimuovi();
        break;
    }
  }

  // esegue l'inserimento di un nuovo prodotto

  private confermaCrea() {
    // prepara i dati da inviare al server
    let dto: ProdottoCreateDto = new ProdottoCreateDto();
    dto.dati.codice = this.codice;
    dto.dati.descrizione = this.descrizione;
    dto.dati.prezzo = this.prezzo;
    dto.token = this.acService.token;

    // prepara la richiesta HTTP
    let oss: Observable<any> =
      this.http.post<any>('http://localhost:8080/create-prodotto', dto);

    // invio la richiesta
    oss.subscribe(risposta => {
      // TODO eseguo una nuova ricerca per aggiornare la lista dei prodotti
    });
  }

  // esegue la modifica di un prodotto

  private confermaModifica() {
    // prepara i dati da inviare al server
    let dto: ProdottoUpdateDto = new ProdottoUpdateDto();
    dto.dati.codice = this.codice;
    dto.dati.descrizione = this.descrizione;
    dto.dati.prezzo = this.prezzo;
    dto.token = this.acService.token;

    // prepara la richiesta HTTP
    let oss: Observable<any> =
      this.http.post<any>('http://localhost:8080/update-prodotto', dto);

    // invio la richiesta
    oss.subscribe(risposta => {
      // TODO eseguo una nuova ricerca per aggiornare la lista dei prodotti
    });
  }

  // esegue la rimozione di un prodotto

  private confermaRimuovi() {
    // prepara i dati da inviare al server
    let dto: ProdottoDeleteDto = new ProdottoDeleteDto();
    dto.idToDelete = this.id;
    dto.token = this.acService.token;

    // prepara la richiesta HTTP
    let oss: Observable<any> =
      this.http.post<any>('http://localhost:8080/delete-prodotto', dto);

    // invio la richiesta
    oss.subscribe(risposta => {
      // TODO eseguo una nuova ricerca per aggiornare la lista dei prodotti
    });
  }

  annulla() {
    // imposta visibilità degli elementi dell'interfaccia
    this.showPanel = this.statoPrecedente == 'view';
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = true;
    this.showResults = true;
    this.showAggiungi = true;

    // aggiorno lo stato
    this.statoPrecedente = 'annulla';
  }

  crea() {
    // imposta visibilità degli elementi dell'interfaccia
    this.showPanel = true;
    this.inputDisabled = false;
    this.showConferma = true;
    this.showAnnulla = true;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = false;
    this.showResults = false;
    this.showAggiungi = false;

    // aggiorno lo stato
    this.statoPrecedente = 'crea';
  }

  modifica(p: Prodotto) {

    // copia i valori  del prodotto selezionato nei campi del panel
    this.popolaCampiPanel(p);

    // imposta visibilità degli elementi dell'interfaccia
    this.showPanel = true;
    this.inputDisabled = false;
    this.showConferma = true;
    this.showAnnulla = true;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = false;
    this.showResults = false;
    this.showAggiungi = false;

    // aggiorno lo stato
    this.statoPrecedente = 'modifica';
  }

  rimuovi(p: Prodotto) {

    // copia i valori  del prodotto selezionato nei campi del panel
    this.popolaCampiPanel(p);

    // imposta visibilità degli elementi dell'interfaccia
    this.showPanel = true;
    this.inputDisabled = true;
    this.showConferma = true;
    this.showAnnulla = true;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = true;
    this.showResults = false;
    this.showAggiungi = true;

    // aggiorno lo stato
    this.statoPrecedente = 'rimuovi';
  }

  cerca() {
    // imposta visibilità degli elementi dell'interfaccia
    this.showPanel = false;
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = true;
    this.showResults = this.trovatoQualcosa;
    this.showAggiungi = true;

    // aggiorno lo stato
    this.statoPrecedente = 'cerca';

    // prepara i dati da inviare al server
    let dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.searchKey = this.searchKey;
    dto.token = this.acService.token;

    // prepara la richiesta HTTP
    let oss: Observable<ProdottoSearchResultsDto> =
      this.http.post<ProdottoSearchResultsDto>('http://localhost:8080/search-prodotto', dto);

    // invio la richiesta
    oss.subscribe(risposta => {
      this.prodotti = risposta.results;
    });
  }

  view(p: Prodotto) {

    // copia i valori  del prodotto selezionato nei campi del panel
    this.popolaCampiPanel(p);

    // imposta visibilità degli elementi dell'interfaccia
    this.showPanel = true;
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = true;
    this.showModifica = true;
    this.showRimuovi = true;
    this.showSearchPanel = true;
    this.showResults = true;
    this.showAggiungi = true;

    // aggiorno lo stato
    this.statoPrecedente = 'view';
  }

  private popolaCampiPanel(p: Prodotto) {
    this.id = p.id;
    this.codice = p.codice;
    this.descrizione = p.descrizione;
    this.prezzo = p.prezzo;
  }
}
