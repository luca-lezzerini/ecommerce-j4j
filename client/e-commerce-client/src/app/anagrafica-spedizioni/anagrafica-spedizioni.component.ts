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
  codice: '';
  descrizione: '';
  prezzo: '';

  spedizioni: Spedizione[] = [];

  searchKey: '';
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

  constructor(private http: HttpClient, private singleton: AreaComuneService, private root: ActivatedRoute) {
    this.initView();
    //DEBUG only
    this.singleton.token = '1234';
  }


  ngOnInit() {
  }

  initView() {

    this.panelEnabled = false;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;

    this.resultsEnabled = false;

    this.statoPrecedente = 'search';
  }

  daSearchCerca() {

    this.panelEnabled = false;

    this.searchPanelEnabled = true;
    this.cercaInputDisabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;

    if ( this.trovatoQualcosa ) {

      this.resultsEnabled = true;
      this.viewEnabled = true;
      this.editEnabled = true;
      this.deleteEnabled = true;

    } else {

      this.resultsEnabled = false;

    }
    this.statoPrecedente = 'search';
  }

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

  daSearchView()  {

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

  }

  daSearchAggiungi(){

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

  daEditView(){

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


  // da CreateState a SearchState freccia "annulla" - parte Filippo -
  daCreateAnnulla(){
  this.panelEnabled = false;
  this.cercaEnabled = true;
  this.resultsEnabled = true;
  this.aggiungiEnabled = true;

  this.statoPrecedente = 'create';
  }

  // da CreateState a SearchState freccia "conferma" - parte Filippo -
  daCreateConferma(){
    this.panelEnabled = false;
    this.cercaEnabled = true;
    this.resultsEnabled = true;
    this.aggiungiEnabled = true;

    this.statoPrecedente = 'create';

  }

  // da ViewState a CreateState freccia "create" - parte Filippo -
  daViewCreate(){
    this.panelEnabled = true;
    this.panelInputDisabled = false;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = false;
    this.resultsEnabled = false;
    this.aggiungiEnabled = false;

    this.statoPrecedente = 'view';

  }

  // da ViewState a EditState freccia "edit" - parte Filippo -
  daViewEdit(){
    this.panelEnabled = false;
    this.cercaEnabled = true;
    this.resultsEnabled = true;
    this.aggiungiEnabled = true;

    this.statoPrecedente = 'view';

  }

  // da ViewState a DeleteState freccia "delete" - parte Filippo -
  daViewDelete(){
    this.panelEnabled = false;
    this.panelInputDisabled = true;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = false;
    this.resultsEnabled = false;
    this.aggiungiEnabled = false;

    this.statoPrecedente = 'view';
  }

  // da DeleteState a ViewState freccia "conferma" - parte Filippo -
  daDeleteConferma(){
    this.panelEnabled = false;
    this.cercaEnabled = true;
    this.resultsEnabled = true;
    this.aggiungiEnabled = true;

    this.statoPrecedente = 'delete';
  }

  // da DeleteState a ViewState freccia "annulla[venivo da view]" - parte Filippo -
  daDeleteAnnullaView(){
    this.panelEnabled = true;
    this.panelInputDisabled = true;
    this.confermaEnabled = false;
    this.annullaEnabled = false;
    this.creaEnabled = true;
    this.modificaEnabled = true;
    this.rimuoviEnabled = true;
    this.cercaEnabled = true;
    this.resultsEnabled = true;
    this.aggiungiEnabled = true;

    this.statoPrecedente = 'delete';
  }

  // da DeleteState a ViewState freccia "annulla[venivo da search]" - parte Filippo -
  daDeleteAnnullaSearch(){
    this.panelEnabled = false;
    this.cercaEnabled = true;
    this.resultsEnabled = true;
    this.aggiungiEnabled = true;

    this.statoPrecedente = 'delete';
  }


  // Metodo invocato dal bottone conferma
  conferma() {

    // DEBUG only
    console.log('sono in conferma, vengo da ' + this.statoPrecedente);

    // da quale stato viene chiamata?
    switch (this.statoPrecedente) {

      case 'create':
        this.daCreateConferma(); // vissualiza cambiamenti su UI
        this.creaSpedizione();   // crea una nuova spedizione e la manda a DB
        this.statoPrecedente = 'create';
        break;
      case 'delete':
        this.daDeleteConferma();  // vissualiza cambiamenti su UI
        this.deleteSpedizione();  // cancella dati da DB
        this.statoPrecedente = 'delete';
        break;
      case 'edit':
        this.daEditView();              // vissualiza cambiamenti su UI
        this.modificaSpedizione();      // manda i dati dal panel (codice, descrizione e prezzo) a DB
        this.statoPrecedente = 'edit';
        break;
      default:
        console.log('Stato sbagliato: ' + this.statoPrecedente);
    }

  }


  // Metodo invocato dal bottone annulla
  annulla() {

    // DEBUG only
    console.log('sono in annulla, vengo da ' + this.statoPrecedente);

    // da quale stato viene invocato?
    switch (this.statoPrecedente) {

      case 'create':
        this.daCreateAnnulla();
        this.annullaEdit();
        this.statoPrecedente = 'create';
        break;
      case 'delete':
        if (this.vieneDaSearch) {

          this.vieneDaSearch = false;
          this.daDeleteAnnullaSearch();
          this.statoPrecedente = 'search';

        } else {    // viene da view
          this.daDeleteAnnullaView();
          this.statoPrecedente = 'view';
        }

        break;
      case 'edit':
        this.daEditView();
        this.annullaEdit();
        this.statoPrecedente = 'edit';
        break;
      default:
        console.log('Stato sbagliato: ' + this.statoPrecedente);
        break;
    }

  }

  // Metodo invocato dal bottone crea
  crea() {

    // DEBUG only
    console.log('sono in crea, vengo da ' + this.statoPrecedente);

    // da quale stato viene invocato?
    switch (this.statoPrecedente) {

      case 'view':
        this.daViewCreate();
        this.statoPrecedente = 'view';
        break;
      default:
        console.log('Stato sbagliato: '  + this.statoPrecedente);
        break;
    }

  }

  // Metodo invocato dal bottone edit
  edit() {

      // DEBUG only
      console.log('sono in edit, vengo da ' + this.statoPrecedente);

      // da quale stato viene invocato?
      switch (this.statoPrecedente) {

        case 'search':
          this.daSearchEdit();
          this.statoPrecedente = 'search';
          break;
        case 'view':
          this.daViewEdit();
          this.statoPrecedente = 'view';
          break;
        default:
          console.log('Stato sbagliato:  + this.statoPrecedente');
          break;
      }
  }

  // Metodo invocato dal bottone modifica
  modifica() {

     // DEBUG only
     console.log('sono in modificaa, vengo da ' + this.statoPrecedente);

     this.edit();
     this.statoPrecedente = 'edit';
  }

  // Metodo invocato dal bottone delete
  delete() {

      // DEBUG only
      console.log('sono in delete, vengo da ' + this.statoPrecedente);

      // da quale stato viene invocato?
      switch (this.statoPrecedente) {

        case 'search':
          this.daSearchDelete();
          this.statoPrecedente = 'search';
          break;
        case 'view':
          this.daViewDelete();
          this.statoPrecedente = 'view';
          break;
        default:
          console.log('Stato sbagliato: ' + this.statoPrecedente );
          break;
      }
  }

  // Metodo invocato dal bottone rimuovi
  rimuovi() {

    // DEBUG only
    console.log('sono in rimuovi, vengo da ' + this.statoPrecedente);

    this.delete();
    this.statoPrecedente = 'delete';
  }

  // Metodo invocato dal bottone view
  view() {

    // DEBUG only
    console.log('sono in view, vengo da ' + this.statoPrecedente);

    this.daSearchView();
    this.statoPrecedente = 'view';
  }

  // Metodo invocato dal bottone aggiungi
  aggiungi() {

    // DEBUG only
    console.log('sono in aggiungi, vengo da ' + this.statoPrecedente);

    this.daSearchAggiungi();
    this.statoPrecedente = 'create';
  }


  // Metodo invocato dal bottone cerca
  cerca() {

    // DEBUG only
    console.log('sono in cerca, vengo da ' + this.statoPrecedente);

    // da quale stato viene invocato?
    switch (this.statoPrecedente) {

      case 'search':
        this.daSearchCerca();
        this.statoPrecedente = 'search';
        break;
      case 'view':
        this.daViewCreate();
        this.statoPrecedente = 'view';
        break;
      default:
        console.log('Stato sbagliato!');
        break;
    }
    this.cercaSpedizione();   // cerca spedizione nel db e vissualizza se trova qualcosa
  }

  creaSpedizione(){     // CRUD create

    // DEBUG only
    console.log('Sono in creaSpedizione');

    /*  codice da testare */

    // prepara la chiamata al server
    const dto: SpedizioneCreateDto = new SpedizioneCreateDto();
    dto.token = this.singleton.token;
    dto.dati = new Spedizione();
    dto.dati.codice = this.codice;
    dto.dati.descrizione = this.descrizione;
    dto.dati.prezzo = +this.prezzo;  // qui + converte string in number
    // DEBUG only
    console.log('siamo prima di observable, ho token ' + dto.token);

    const obs: Observable<SpedizioneCreateDto> =
          this.http.post<SpedizioneCreateDto>('http://localhost:8080/create-spedizione', dto);

    console.log('siamo dopo di observable, ho token ' + dto.token);

    // invia la richiesta al server
    obs.subscribe(risposta => {
      // ripete ultima ricerca
      // this.cercaSpedizione();

      console.log(risposta);
    });

  }

  cercaSpedizione(){    // CRUD read

    // DEBUG only
    console.log('Sono in cercaSpedizione');

    /*  codice da testare */
    // prepara la chiamata al server
    const dto: SpedizioneSearchDto = new SpedizioneSearchDto();
    dto.token = this.singleton.token;
    dto.searchKey = this.searchKey;
    const obs: Observable<SpedizioneSearchResultsDto> =
      this.http.post<SpedizioneSearchResultsDto>('http://localhost:8080/search-spedizione', dto);

    // invia la richiesta al server
    obs.subscribe(risposta => {
      this.spedizioni = risposta.result;
      if (this.spedizioni && this.spedizioni.length > 0) {
       // se trova qualcosa lo fa vedere
       this.showResults();
      } else {
        this.resultsEnabled = false;
      }
    });
  }

  modificaSpedizione() {    // CRUD update

    // DEBUG only
    console.log('Sono in modificaSpedizione');

    /*  codice da testare  */
    // prepara la chiamata al server
    const dto: SpedizioneUpdateDto = new SpedizioneUpdateDto();
    dto.token = this.singleton.token;

    dto.dati.codice = this.codice;
    dto.dati.descrizione = this.descrizione;
    dto.dati.prezzo = +this.prezzo;  // qui + converte string in number
    const obs: Observable<SpedizioneUpdateDto> =
          this.http.post<SpedizioneUpdateDto>('http://localhost:8080/update-spedizione', dto);

    // invia la richiesta al server
    obs.subscribe(risposta => {
      // ripete ultima ricerca
      // this.cercaSpedizione();

      console.log(risposta);
    });
  }


  deleteSpedizione() {    // CRUD delete

    // DEBUG only
    console.log('Sono in deleteSpedizione');

    /*  codice da testare */
    // prepara la chiamata al server
    const dto: SpedizioneDeleteDto = new SpedizioneDeleteDto();
    dto.token = this.singleton.token;
    dto.idToDelete = this.idToDelete;
    const obs: Observable<SpedizioneDeleteDto> =
      this.http.post<SpedizioneDeleteDto>('http://localhost:8080/search-spedizione', dto);

    // invia la richiesta al server
    obs.subscribe(risposta => {
        // ripete ultima ricerca
        // this.cercaSpedizione();

         console.log(risposta);
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
    this.prezzo = '';
  }

}

