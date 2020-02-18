import { Router } from '@angular/router';
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
  offerta = false;
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
  searchKeyPrecedente = '';
  statoPrecedente = '';
  prodottoSelezionato = new Prodotto();

  constructor(private http: HttpClient,
    private acService: AreaComuneService,
    private router: Router) {
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
    if(!this.acService.token){
      this.router.navigateByUrl('/login');
    }
  }

  conferma() {
    // se i campi di input del panel sono vuoti non esegue
    if (this.codice && this.descrizione && this.prezzo !== null) {

      // copia in prodottoSelezionato i dati dei campi di input
      if (this.id) {
        this.prodottoSelezionato.id = this.id;
      }
      if (this.offerta) {
        this.prodottoSelezionato.offerta = this.offerta;
      }
      this.prodottoSelezionato.codice = this.codice;
      this.prodottoSelezionato.descrizione = this.descrizione;
      this.prodottoSelezionato.prezzo = this.prezzo;

      // imposta visibilità degli elementi dell'interfaccia
      this.showPanel = this.statoPrecedente == 'modifica';
      this.inputDisabled = true;
      this.showConferma = false;
      this.showAnnulla = false;
      this.showCrea = this.statoPrecedente == 'modifica';
      this.showModifica = this.statoPrecedente == 'modifica';
      this.showRimuovi = this.statoPrecedente == 'modifica';
      this.showSearchPanel = true;
      this.showAggiungi = true;

      // eseguo operazione confermata in base allo stato precedente
      switch (this.statoPrecedente) {
        case 'crea': this.confermaCrea();
          break;
        case 'modifica': this.confermaModifica();
          break;
        case 'rimuovicerca': this.confermaRimuovi();
          break;
        case 'rimuoviview': this.confermaRimuovi();
          break;
      }
    }
  }

  // esegue l'inserimento di un nuovo prodotto

  private confermaCrea() {
    // prepara i dati da inviare al server
    let dto: ProdottoCreateDto = new ProdottoCreateDto();
    dto.dati = new Prodotto();
    dto.dati.codice = this.codice;
    dto.dati.descrizione = this.descrizione;
    dto.dati.prezzo = this.prezzo;
    dto.token = this.acService.token;

    // prepara la richiesta HTTP
    let oss: Observable<any> =
      this.http.post<any>('http://localhost:8080/create-prodotto', dto);

    // invio la richiesta
    oss.subscribe(risposta => {

      // una volta eseguito l'inserimento, eseguo di nuovo l'ultima ricerca effettuata
      this.eseguiRicerca(this.searchKeyPrecedente);
    });
  }

  // esegue la modifica di un prodotto

  private confermaModifica() {
    // prepara i dati da inviare al server
    let dto: ProdottoUpdateDto = new ProdottoUpdateDto();
    dto.dati = new Prodotto();
    dto.dati.id = this.id;
    dto.dati.codice = this.codice;
    dto.dati.descrizione = this.descrizione;
    dto.dati.prezzo = this.prezzo;
    dto.dati.offerta = this.offerta;
    dto.token = this.acService.token;

    // prepara la richiesta HTTP
    let oss: Observable<any> =
      this.http.post<any>('http://localhost:8080/update-prodotto', dto);

    // invio la richiesta
    oss.subscribe(risposta => {

      // una volta eseguita la modifica, eseguo di nuovo l'ultima ricerca effettuata
      this.eseguiRicerca(this.searchKeyPrecedente);
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

      // una volta eseguita la rimozione, eseguo di nuovo l'ultima ricerca effettuata
      this.eseguiRicerca(this.searchKeyPrecedente);
    });
  }

  annulla() {
    // copia i valori  del prodotto selezionato precedentemente nei campi del panel
    this.popolaCampiPanel(null);

    // imposta visibilità degli elementi dell'interfaccia
    let mostraPanel = this.statoPrecedente == 'modifica' || this.statoPrecedente == 'rimuoviview';
    this.showPanel = mostraPanel;
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = mostraPanel;
    this.showModifica = mostraPanel;
    this.showRimuovi = mostraPanel;
    this.showSearchPanel = true;
    this.showResults = true;
    this.showAggiungi = true;
  }

  crea() {
    // pulisci campi di input nel panel

    this.annullaCampiPanel();

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
    this.showSearchPanel = false;
    this.showResults = false;
    this.showAggiungi = false;

    // aggiorno lo stato
    if (this.statoPrecedente == 'view') {
      this.statoPrecedente = 'rimuoviview';
    } else {
      this.statoPrecedente = 'rimuovicerca';
    }
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
    this.showAggiungi = true;

    // aggiorno lo stato
    this.statoPrecedente = 'cerca';

    // eseguo la ricerca con la searchKey attuale
    this.eseguiRicerca(this.searchKey);
  }

  private eseguiRicerca(search: string) {
    // prepara i dati da inviare al server
    let dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.searchKey = search;
    dto.token = this.acService.token;

    // prepara la richiesta HTTP
    let oss: Observable<ProdottoSearchResultsDto> =
      this.http.post<ProdottoSearchResultsDto>('http://localhost:8080/search-prodotto', dto);

    // invio la richiesta
    oss.subscribe(risposta => {

      // aggiorno lista prodotti
      this.prodotti = risposta.result;

      // se ci sono risultati li visualizzo
      this.showResults = risposta.result.length > 0;

      // salvo la chiave di ricerca
      this.searchKeyPrecedente = this.searchKey;

      // pulisco il campo ricerca
      this.searchKey = '';
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

  // copia i valori del prodotto selezionato nei campi di testo del panel
  private popolaCampiPanel(p: Prodotto) {

    // se provengo dalla tabella, p è avvalorato...
    if (p) {
      this.id = p.id;
      this.codice = p.codice;
      this.descrizione = p.descrizione;
      this.prezzo = p.prezzo;
      this.offerta = p.offerta;

      // salva il prodotto selezionato dalla tabella
      this.prodottoSelezionato = p;
    } else {

      // ... altrimenti usa i dati del prodotto selezionato precedentemente
      this.id = this.prodottoSelezionato.id;
      this.codice = this.prodottoSelezionato.codice;
      this.descrizione = this.prodottoSelezionato.descrizione;
      this.prezzo = this.prodottoSelezionato.prezzo;
      this.offerta = this.prodottoSelezionato.offerta;
    }
  }

  // pulisco i valori dei campi di input nel panel
  private annullaCampiPanel() {
    this.codice = '';
    this.descrizione = '';
    this.prezzo = null;
  }
}
