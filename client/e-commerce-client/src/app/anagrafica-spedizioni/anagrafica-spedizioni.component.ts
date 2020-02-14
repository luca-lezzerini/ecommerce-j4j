import { Component, OnInit } from '@angular/core';
import { Spedizione } from '../classi/spedizione';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';

@Component({
  selector: 'app-anagrafica-spedizioni',
  templateUrl: './anagrafica-spedizioni.component.html',
  styleUrls: ['./anagrafica-spedizioni.component.css']
})
export class AnagraficaSpedizioniComponent implements OnInit {
  codice: '';
  descrizione: '';
  prezzo: '';
  search: '';

  spedizioni: Spedizione[] = [];

  dettagliEnabled: boolean;
  confermaEnabled: boolean;
  annullaEnabled: boolean;
  creaEnabled: boolean;
  modificaEnabled: boolean;
  rimuoviEnabled: boolean;
  cercaEnabled: boolean;
  aggiungiEnabled: boolean;
  tableEnabled: boolean;
  editEnabled: boolean;
  viewEnabled: boolean;
  deleteEnabled: boolean;
  inputEditable: boolean;

  constructor(private http: HttpClient, private singleton: AreaComuneService) {
    this.initVis();
  }


  ngOnInit() {
  }

  initVis() {
    this.dettagliEnabled = false;
    this.confermaEnabled = false;
    this.annullaEnabled = false;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;
    this.tableEnabled = false;
  }

  visCercaSiRisultato() {
    this.dettagliEnabled = false;
    this.confermaEnabled = false;
    this.annullaEnabled = false;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;
    this.tableEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;
  }

  visCercaNoRisultato() {
    this.visCercaSiRisultato();
    this.tableEnabled = false;
  }



  conferma() {

  }

  annulla() {

  }

  crea() {

  }

  modifica() {

  }

  rimuovi() {

  }

  cerca() {
    /*
    //prepara la chiamata al server
    let dto: TagliaSearchDto = new TagliaSearchDto();
    dto.token = this.singleton.token;
    dto.searchKey = this.searchKey;
    let obs: Observable<TagliaSearchResultsDto> =
    this.http.post<TagliaSearchResultsDto>('http://localhost:8080/search-taglie', dto);

    //prepara la callback
    obs.subscribe(risposta => {
      this.taglie = risposta.result;
      if (this.taglie.length > 0) {
       //se trova qualcosa imposta le visibilità su visCercaSiRisultato
        this.visCercaSiRisultato();
      } else {
        //altrimenti imposta le visibilità su visCercaNoRisultato
        this.visCercaNoRisultato();
      }
    });
    */
  }

  view() {

  }
  aggiungi() {

  }

  edit() {

  }

  delete() {

  }
}

