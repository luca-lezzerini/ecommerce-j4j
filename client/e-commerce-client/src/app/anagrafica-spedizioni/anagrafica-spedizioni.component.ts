import { Component, OnInit } from '@angular/core';
import { Spedizione } from '../classi/spedizione';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { SpedizioneSearchDto } from '../classi/spedizione-search-dto';
import { Observable } from 'rxjs';
import { SpedizioneSearchResultsDto } from '../classi/spedizione-search-results-dto';
import { SpedizioneDeleteDto } from '../classi/spedizione-delete-dto';
import { SpedizioneCreateDto } from '../classi/spedizione-create-dto';
import { ValueConverter } from '@angular/compiler/src/render3/view/template';
import { SpedizioneUpdateDto } from '../classi/spedizione-update-dto';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-anagrafica-spedizioni',
  templateUrl: './anagrafica-spedizioni.component.html',
  styleUrls: ['./anagrafica-spedizioni.component.css']
})
export class AnagraficaSpedizioniComponent implements OnInit {
  codice: string;
  descrizione: string;
  prezzo: number;
  id: number;
  spedizioni: Spedizione[] = [];
  spedizioneDaRimuovere = new Spedizione();
  spedizioneSelezionata = new Spedizione();

  searchKey: string;
  searchKeyPrecedente: string;
  idToDelete: number;

  panelEnabled: boolean;
  panelInputDisabled: boolean;
  confermaEnabled: boolean;
  annullaEnabled: boolean;
  creaEnabled: boolean;
  modificaEnabled: boolean;
  rimuoviEnabled: boolean;

  searchPanelEnabled: boolean;
  cercaInputDisabled: boolean;
  cercaEnabled: boolean;

  aggiungiEnabled: boolean;

  resultsEnabled: boolean;
  viewEnabled: boolean;
  editEnabled: boolean;
  deleteEnabled: boolean;

  trovatoQualcosa = false;
  vieneDaSearch = false;
  statoPrecedente: string;



  constructor(private http: HttpClient, private acService: AreaComuneService, private router: Router) {

    this.initView();
  }
  /*Abbiamo inserito che venga effettuato il Login
    *prima di procedere con le pagine di anagrafica
  */
  ngOnInit() {
    if (!this.acService.token) {
      this.router.navigateByUrl('/login');
    }
  }
  initView() {

    this.panelEnabled = false;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;

    this.resultsEnabled = false;

    this.searchKey = '';
  }
  // Transizione in uscita da stato search tramite bottone cerca
  daSearchCerca() {

    this.panelEnabled = false;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;

    if (this.trovatoQualcosa) {
      this.resultsEnabled = true;
      this.viewEnabled = true;
      this.editEnabled = true;
      this.deleteEnabled = true;
    } else {
      this.resultsEnabled = false;
    }
    this.statoPrecedente = 'search';
  }
  // Transizione in uscita da stato search tramite bottone delete
  daSearchDelete() {
    this.panelEnabled = true;
    this.panelInputDisabled = true;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;

    this.searchPanelEnabled = false;
    this.aggiungiEnabled = false;

    this.resultsEnabled = false;

    this.statoPrecedente = 'search';
    this.vieneDaSearch = true;
  }
  // Transizione in uscita da stato search tramite bottone edit
  daSearchEdit() {
    this.panelEnabled = true;
    this.panelInputDisabled = false;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;

    this.searchPanelEnabled = false;
    this.aggiungiEnabled = false;

    this.resultsEnabled = false;

    this.statoPrecedente = 'search';
  }
  // Transizione in uscita da stato search tramite bottone view
  daSearchView() {
    this.panelEnabled = true;
    this.panelInputDisabled = true;
    this.confermaEnabled = false;
    this.annullaEnabled = false;
    this.creaEnabled = true;
    this.modificaEnabled = true;
    this.rimuoviEnabled = true;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;

    this.aggiungiEnabled = true;

    this.resultsEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;

    this.statoPrecedente = 'search';

    console.log('stato precedente');
  }
  // Transizione in uscita da stato search tramite bottone Aggiungi
  daSearchAggiungi() {
    this.panelEnabled = true;
    this.panelInputDisabled = false;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;

    this.searchPanelEnabled = false;
    this.aggiungiEnabled = false;

    this.resultsEnabled = false;

    this.statoPrecedente = 'search';

  }
  // Transizione in uscita da stato Edit tramite bottone view
  daEditView() {

    this.panelEnabled = true;
    this.panelInputDisabled = true;
    this.confermaEnabled = false;
    this.annullaEnabled = false;
    this.creaEnabled = true;
    this.modificaEnabled = true;
    this.rimuoviEnabled = true;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;

    this.aggiungiEnabled = true;

    this.resultsEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;

    this.statoPrecedente = 'edit';

  }
  // Transizione in uscita da stato create tramite bottone Annulla
  daCreateAnnulla() {
    this.panelEnabled = false;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;

    this.aggiungiEnabled = true;

    this.resultsEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;

    this.statoPrecedente = 'create';
  }
  // Transizione in uscita da stato create tramite bottone Conferma
  daCreateConferma() {
    this.panelEnabled = false;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;

    this.resultsEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;

    this.statoPrecedente = 'create';
  }
  // Transizione in uscita da stato view tramite bottone Crea
  daViewCreate() {
    this.panelEnabled = true;
    this.panelInputDisabled = false;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;

    this.searchPanelEnabled = false;
    this.aggiungiEnabled = false;

    this.resultsEnabled = false;
    this.statoPrecedente = 'view';

  }
  // Transizione in uscita da stato view tramite bottone Edit
  daViewEdit() {
    this.panelEnabled = true;
    this.panelInputDisabled = false;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;

    this.resultsEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;

    this.statoPrecedente = 'view';
  }
  // Transizione in uscita da stato view tramite bottone Delete
  daViewDelete() {
    this.panelEnabled = true;
    this.panelInputDisabled = true;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;

    this.searchPanelEnabled = false;
    this.aggiungiEnabled = false;

    this.resultsEnabled = false;

    this.statoPrecedente = 'view';
  }
  // Transizione in uscita da stato delet tramite bottone Conferma
  daDeleteConferma() {
    this.panelEnabled = false;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;

    this.resultsEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;

    this.statoPrecedente = 'delete';
  }
  // Transizione  da stato delete a stato view tramite bottone Annulla
  daDeleteAnnullaView() {
    this.panelEnabled = true;
    this.panelInputDisabled = true;
    this.confermaEnabled = false;
    this.annullaEnabled = false;
    this.creaEnabled = true;
    this.modificaEnabled = true;
    this.rimuoviEnabled = true;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;

    this.resultsEnabled = true;
    this.resultsEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;

    this.statoPrecedente = 'delete';
  }
  // Transizione  da stato delete a stato search tramite bottone Annulla
  daDeleteAnnullaSearch() {
    this.panelEnabled = false;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;
    this.resultsEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;

    this.statoPrecedente = 'delete';
  }
  // Metodo invocato dal bottone conferma
  conferma() {
    // da quale stato viene chiamata?
    console.log('sono in stato' + this.statoPrecedente);
    switch (this.statoPrecedente) {
      case 'create':
        this.daCreateConferma(); // vissualiza cambiamenti su UI
        this.creaSpedizione();   // crea una nuova spedizione e la manda a DB
        this.annullaEdit();
        break;
      case 'delete':
        this.daDeleteConferma();  // vissualiza cambiamenti su UI
        this.idToDelete = this.spedizioneDaRimuovere.id;
        this.deleteSpedizione();  // cancella i dati dal db
        this.annullaEdit();
        break;
      case 'edit':
        this.daEditView();              // vissualiza cambiamenti su UI
        this.modificaSpedizione();      // manda i dati dal panel (codice, descrizione e prezzo) a DB
        this.annullaEdit();
        break;
      case 'view':
        this.creaSpedizione();   // crea una nuova spedizione e la manda a DB
        this.annullaEdit();
        break;
      default:
        console.log('Stato sbagliato: ' + this.statoPrecedente);
        break;
    }
  }
  // Metodo invocato dal bottone annulla
  annulla() {
    // da quale stato viene invocato?
    switch (this.statoPrecedente) {
      case 'create':
        this.daCreateAnnulla();
        this.annullaEdit();
        break;
      case 'delete':
        if (this.vieneDaSearch) {
          this.vieneDaSearch = false;
          this.daDeleteAnnullaSearch();
        } else {    // viene da view
          this.daDeleteAnnullaView();
        }
        break;
      case 'edit':
        this.daEditView();
        this.annullaEdit();
        break;
      default:
        console.log('Stato sbagliato: ' + this.statoPrecedente);
        this.annullaEdit();
        break;
    }
  }
  // Metodo invocato dal bottone crea
  crea() {
    this.daViewCreate();
    this.creaSpedizione();
  }
  // Metodo invocato dal bottone edit : in questo metodo abbiamo come parametro la riga selezionata
  edit(selezionata: Spedizione) {
    this.id = selezionata.id;
    this.codice = selezionata.codice;
    this.descrizione = selezionata.descrizione;
    this.prezzo = selezionata.prezzo;
    this.daSearchEdit();

    this.statoPrecedente = 'edit';
  }
  // Metodo invocato dal bottone modifica
  modifica() {
    this.panelInputDisabled = false;

    this.modificaSpedizione();
    this.statoPrecedente = 'edit';
  }
  // Metodo invocato dal bottone delete
  delete(selezionata: Spedizione) {

    this.idToDelete = selezionata.id;
    // da quale stato viene invocato?
    switch (this.statoPrecedente) {
      case 'search':
        this.daSearchDelete();
        break;
      case 'view':
        this.daViewDelete();
        this.statoPrecedente = 'delete';
        break;
      default:
        console.log('Stato sbagliato: ' + this.statoPrecedente);
        break;
    }
    this.deleteSpedizione();

  }
  /**
   * Metodo invocato dal bottone rimuovi
   */
  rimuovi() {
    // se i campi di input del panel sono vuoti non esegue
    if (this.codice && this.descrizione && this.prezzo !== null) {
      // copia in SpedizioneDaRimuovere i dati dei campi di input
      if (this.id) {
        this.spedizioneDaRimuovere.id = this.id;
      }
      this.spedizioneDaRimuovere.codice = this.codice;
      this.spedizioneDaRimuovere.descrizione = this.descrizione;
      this.spedizioneDaRimuovere.prezzo = +this.prezzo;
      this.daViewDelete();
      this.statoPrecedente = 'delete';
    }
  }
  // Metodo invocato dal bottone view
  view(selezionata: Spedizione) {
    this.id = selezionata.id;
    this.codice = selezionata.codice;
    this.descrizione = selezionata.descrizione;
    this.prezzo = selezionata.prezzo;
    this.daSearchView();
    this.statoPrecedente = 'search';
  }
  // Metodo invocato dal bottone aggiungi
  aggiungi() {
    this.annullaEdit();
    this.daSearchAggiungi();
    this.statoPrecedente = 'create';
  }
  // Metodo invocato dal bottone cerca
  cerca() {
    // da quale stato viene invocato?
    this.annullaEdit();
    switch (this.statoPrecedente) {
      case 'search':
        this.daSearchCerca();
        break;
      case 'view':
        this.daViewCreate();
        break;
      default:
        console.log('Stato sbagliato!');
        break;
    }
    this.statoPrecedente = 'search';
    this.cercaSpedizione(this.searchKey);   // cerca spedizione nel db e vissualizza se trova qualcosa
    console.log('dopo cerca nel db sono in stato ' + this.statoPrecedente);
    this.daSearchView();

  }

  creaSpedizione() {     // CRUD create
    // prepara la chiamata al server
    const dto: SpedizioneCreateDto = new SpedizioneCreateDto();
    dto.dati = new Spedizione();

    dto.token = this.acService.token;
    dto.dati.codice = this.codice;
    dto.dati.descrizione = this.descrizione;
    dto.dati.prezzo = +this.prezzo;  // qui + converte string in number

    const obs: Observable<any> =
      this.http.post<any>('http://localhost:8080/create-spedizione', dto);
    // invia la richiesta al server
    obs.subscribe(risposta => {
      // ripete ultima ricerca
      // una volta eseguito l'inserimento, stampoeseguo di nuovo l'ultima ricerca effettuata, oppure tutta la tabella
      this.searchKeyPrecedente = '';
      this.cercaSpedizione(this.searchKeyPrecedente);
    });
    this.showResults();
  }
  cercaSpedizione(search: string) {    // CRUD read
    // prepara la chiamata al server
    const dto: SpedizioneSearchDto = new SpedizioneSearchDto();
    dto.searchKey = search;
    dto.token = this.acService.token;
    const obs: Observable<SpedizioneSearchResultsDto> =
      this.http.post<SpedizioneSearchResultsDto>('http://localhost:8080/search-spedizione', dto);
    console.log('mandato post ');
    // invia la richiesta al server
    obs.subscribe(risposta => {
      this.spedizioni = risposta.result;
      if (risposta.result.length > 0) {
        // se trova qualcosa lo fa vedere
        this.showResults();
      } else {
        this.resultsEnabled = false;
      }
    });

    // salvo la chiave di ricerca
    this.searchKeyPrecedente = this.searchKey;
    // pulisco il campo ricerca
    this.searchKey = '';
  }

  modificaSpedizione() {    // CRUD update
    // prepara la chiamata al server
    const dto: SpedizioneUpdateDto = new SpedizioneUpdateDto();
    dto.dati = new Spedizione();
    dto.token = this.acService.token;
    dto.dati.id = this.id;
    dto.dati.descrizione = this.descrizione;
    dto.dati.codice = this.codice;
    dto.dati.prezzo = +this.prezzo;  // qui + converte string in number
    const obs: Observable<any> =
      this.http.post<any>('http://localhost:8080/update-spedizione', dto);
    // invia la richiesta al server
    obs.subscribe(risposta => {
      this.searchKeyPrecedente = '';
      this.cercaSpedizione(this.searchKeyPrecedente);
    });
    this.showResults();
  }

  deleteSpedizione() {    // CRUD delete
    // prepara la chiamata al server
    const dto: SpedizioneDeleteDto = new SpedizioneDeleteDto();
    dto.token = this.acService.token;
    dto.idToDelete = this.idToDelete;
    const obs: Observable<any> =
      this.http.post<any>('http://localhost:8080/delete-spedizione', dto);
    // invia la richiesta al server
    obs.subscribe(risposta => {
      // ripete ultima ricerca
      this.cercaSpedizione(this.searchKeyPrecedente);

    });
  }

  showResults() {
    this.resultsEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;
  }

  annullaEdit() {
    // reset input fields nel panel
    this.codice = '';
    this.descrizione = '';
    this.prezzo = +'';
    this.id = 0;
  }


}

