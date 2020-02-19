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
  statoAttuale: string;
  searchKeyPrecedente: string;

  constructor(private http: HttpClient, private acService: AreaComuneService, private root: ActivatedRoute) {

    this.initView();
    //DEBUG only
    this.acService.token = '1234';
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

    this.statoAttuale = 'search';
  }

  daSearchCerca() {
    console.log( 'daSearcCerca ' );
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
    this.statoAttuale = 'search';
  }

  daSearchDelete() {
    console.log( 'daSearchDelete ' );

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

    console.log( 'daSearcEdit' );

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
    console.log( 'daSearcView ' );

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

    // DEBUG only
    if( this.resultsEnabled ) {
       console.log('devo disegnare la tabela' );
    }

    this.statoPrecedente = 'search';
    console.log ('stato precedente');
  }

  daSearchAggiungi(){

    console.log( 'daSearcAggiungi ' );

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
    console.log( 'daEditView' );

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
  daCreateAnnulla() {
   console.log( 'daCreateAnnulla ' );

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

   // ??? da vedere
   this.statoAttuale = 'create';
  }

  // da CreateState a SearchState freccia "conferma" - parte Filippo -
  daCreateConferma(){

    console.log( 'daCreateConferma ' );

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

  // da ViewState a CreateState freccia "create" - parte Filippo -
  daViewCreate(){

    console.log( 'daViewCreate ' );

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

  // da ViewState a EditState freccia "edit" - parte Filippo -
  daViewEdit(){
    console.log( 'daViewEdit ' );
    this.panelEnabled = false;

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

  // da ViewState a DeleteState freccia "delete" - parte Filippo -
  daViewDelete(){
    console.log( 'daViewDelete ' );

    this.panelEnabled = true;
    this.panelInputDisabled = true;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;

    this.searchPanelEnabled = false;
    this.aggiungiEnabled = true;

    this.resultsEnabled = false;

    this.statoPrecedente = 'view';
  }

  // da DeleteState a SearchState freccia "conferma" - parte Filippo -
  daDeleteConferma(){
    console.log( 'daDeleteConferma ' );
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

  // da DeleteState a ViewState freccia "annulla[venivo da view]" - parte Filippo -
  daDeleteAnnullaView(){

    console.log( 'daDeleteAnnullaView' );

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

  // da DeleteState a ViewState freccia "annulla[venivo da search]" - parte Filippo -
  daDeleteAnnullaSearch(){

    console.log( 'daDeleteAnnullaSearch' );

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

    // DEBUG only
    console.log('sono in conferma, vengo da ' + this.statoPrecedente);

    // da quale stato viene chiamata?
    switch (this.statoPrecedente) {

      case 'create':
        this.daCreateConferma(); // vissualiza cambiamenti su UI

        this.creaSpedizione();   // crea una nuova spedizione e la manda a DB
        this.statoAttuale = 'create';
        break;
      case 'delete':
        this.daDeleteConferma();  // vissualiza cambiamenti su UI
        this.idToDelete = this.spedizioneDaRimuovere.id;
        this.deleteSpedizione();  // cancella i dati dal db
        //this.deleteRiga(this.spedizioneDaRimuovere);
        this.statoAttuale = 'search';
        break;
      case 'edit':
        this.daEditView();              // vissualiza cambiamenti su UI
        this.modificaSpedizione();      // manda i dati dal panel (codice, descrizione e prezzo) a DB
        this.statoAttuale = 'view';
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
        this.statoAttuale = 'create';
        break;
      case 'delete':
        if (this.vieneDaSearch) {

          this.vieneDaSearch = false;
          this.daDeleteAnnullaSearch();
          this.statoAttuale = 'search';

        } else {    // viene da view
          this.daDeleteAnnullaView();
          this.statoAttuale = 'view';
        }

        break;
      case 'edit':
        this.daEditView();
        this.annullaEdit();
        this.statoAttuale = 'edit';
        break;
      default:
        console.log('Stato sbagliato: ' + this.statoPrecedente);
        break;
    }

  }

  // Metodo invocato dal bottone crea
  crea() {
    this.statoAttuale = 'create';
    // DEBUG  only
    console.log('sono in crea, vengo da ' + this.statoPrecedente);

    // da quale stato viene invocato?
    switch (this.statoPrecedente) {

      case 'view':
        this.daViewCreate();
        // this.statoPrecedente = 'view';
        break;
      default:
        console.log('Stato sbagliato: '  + this.statoPrecedente);
        break;
    }

  }

  // Metodo invocato dal bottone edit
  edit( selezionata: Spedizione) {
      this.statoAttuale = 'edit';
      // DEBUG only
      console.log('sono in edit, vengo da ' + this.statoPrecedente);
      //this.codice = selezionata.codice;

      // da quale stato viene invocato?
      switch (this.statoPrecedente) {

        case 'search':
          this.daSearchEdit();
          //this.statoPrecedente = '';
          break;
        case 'view':
          this.daViewEdit();
          //this.statoAttuale = 'edit';
          break;
        default:
          console.log('Stato sbagliato:  + this.statoPrecedente');
          break;
      }
  }

  // Metodo invocato dal bottone modifica
  modifica() {

     // DEBUG only
     console.log('sono in modifica, vengo da ' + this.statoPrecedente);
     this.panelInputDisabled = false;
     //this.edit(selezionata: Spedizione);
     this.statoAttuale = 'edit';
  }

  // Metodo invocato dal bottone delete
  delete( selezionata: Spedizione ) {

      // DEBUG only
      console.log('sono in delete, vengo da ' + this.statoPrecedente);

      // da quale stato viene invocato?
      switch (this.statoPrecedente) {

        case 'search':
          this.daSearchDelete();
          this.statoAttuale = 'delete';
          this.idToDelete = selezionata.id;
          console.log('id to delete (search) = ' + this.idToDelete);
          this.deleteSpedizione();
          break;
        case 'view':
          this.daViewDelete();
          this.statoPrecedente = 'delete';
          this.idToDelete = selezionata.id;
          console.log('id to delete (view) = ' + this.idToDelete);
          this.deleteSpedizione();
          break;
        case 'delete':
            this.idToDelete = selezionata.id;
            console.log('id to delete (delete) = ' + this.idToDelete);
            // non fa delete, aspetta conferma
            break;
        default:
          console.log('Stato sbagliato: ' + this.statoPrecedente );
          break;
      }
  }

  // Metodo invocato dal bottone rimuovi
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
   // DEBUG only
      console.log('sono in rimuovi, vengo da ' + this.statoPrecedente);
      this.daViewDelete();
      this.annullaEdit();
      this.statoPrecedente = 'delete';
    }
  }

  // Metodo invocato dal bottone view
    view(selezionata: Spedizione) {

    // DEBUG only
    console.log('sono in view, vengo da ' + this.statoPrecedente);
    this.id = selezionata.id;
    this.codice = selezionata.codice;
    this.descrizione = selezionata.descrizione;
    this.prezzo =  selezionata.prezzo;
    this.daSearchView();
    this.statoPrecedente = 'search';
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

    this.statoAttuale = 'search';
    // DEBUG only
    console.log('sono in cerca, vengo da ' + this.statoPrecedente);

    // da quale stato viene invocato?
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
    this.cercaSpedizione();   // cerca spedizione nel db e vissualizza se trova qualcosa
    console.log('dopo cerca nel db sono in stato ' + this.statoPrecedente );
    //this.view();
    this.daSearchView();
    //DEBUG only
    //his.statoPrecedente =
    //console.log('dopo cerca ne db sono in stato ' + this.statoPrecedente);
  }

  creaSpedizione(){     // CRUD create

    // DEBUG only
    console.log('Sono in creaSpedizione');
    // Fare check dei campi se sono vuoti o meno
    //if (this.codice.trim() !== '' && this.descrizione.trim() !== '') {
    //
    //}


    // prepara la chiamata al server
    const dto: SpedizioneCreateDto = new SpedizioneCreateDto();
    dto.token = this.acService.token;
    dto.dati = new Spedizione();
    dto.dati.codice = this.codice;
    dto.dati.descrizione = this.descrizione;
    dto.dati.prezzo = +this.prezzo;  // qui + converte string in number
    // DEBUG only
    console.log('siamo prima di observable, ho token ' + dto.token);

    const obs: Observable<any> =
          this.http.post<any>('http://localhost:8080/create-spedizione', dto);

    console.log('siamo dopo di observable, ho token ' + dto.token);

    // invia la richiesta al server
    obs.subscribe(risposta => {
      // ripete ultima ricerca
      // una volta eseguito l'inserimento, eseguo di nuovo l'ultima ricerca effettuata
      // this.searchKeyPrecedente
      // this.cercaSpedizione();

      // console.log(risposta);
    });

  }

    cercaSpedizione(){    // CRUD read

    // DEBUG only
    console.log('Sono in cercaSpedizione');

    /*  codice da testare */
    // prepara la chiamata al server
    const dto: SpedizioneSearchDto = new SpedizioneSearchDto();
    dto.token = this.acService.token;
    dto.searchKey = this.searchKey;
    const obs: Observable<any> =
      this.http.post<any>('http://localhost:8080/search-spedizione', dto);
    console.log('mandato post ');
    // invia la richiesta al server
    obs.subscribe(risposta => {
      this.spedizioni = risposta.result;

      //console.log('SpedCodice ' + this.spedizioni[0].codice);

      // se ci sono risultati li visualizzo
      //this.showResults = risposta.results.length > 0;
      //if (this.spedizioni && this.spedizioni.length > 0) {

      if (risposta.result.length > 0) {
       // se trova qualcosa lo fa vedere
       console.log('trovato!!!');
       this.showResults();
      } else {
        this.resultsEnabled = false;
        console.log('non ho trovato!!!');
      }
    });

    // salvo la chiave di ricerca
    this.searchKeyPrecedente = this.searchKey;

    // pulisco il campo ricerca
    this.searchKey = '';


  }

    modificaSpedizione() {    // CRUD update

    // DEBUG only
    console.log('Sono in modificaSpedizione');

    /*  codice da testare  */
    // prepara la chiamata al server
    const dto: SpedizioneUpdateDto = new SpedizioneUpdateDto();
    dto.dati = new Spedizione();
    dto.dati.id = this.id;
    dto.dati.descrizione = this.descrizione;

    dto.token = this.acService.token;

    dto.dati.codice = this.codice;
    dto.dati.prezzo = +this.prezzo;  // qui + converte string in number
    const obs: Observable<any> =
          this.http.post<any>('http://localhost:8080/update-spedizione', dto);

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

    // prepara la chiamata al server
    const dto: SpedizioneDeleteDto = new SpedizioneDeleteDto();
    dto.token = this.acService.token;
    dto.idToDelete = this.idToDelete;
    const obs: Observable<any> =
       this.http.post<any>('http://localhost:8080/delete-spedizione', dto);
    console.log('fatto post delete');
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
    this.prezzo = +'';
  }

    deleteRiga(selezionata: Spedizione){


  }


}

