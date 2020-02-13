import { TagliaSearchDto } from './../classi/taglia-search-dto';
import { HttpClient } from '@angular/common/http';
import { TagliaSearchResultsDto } from './../classi/taglia-search-results-dto';
import { Taglia } from './../classi/taglia';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-anagrafica-taglie',
  templateUrl: './anagrafica-taglie.component.html',
  styleUrls: ['./anagrafica-taglie.component.css']
})
export class AnagraficaTaglieComponent implements OnInit {

  codice = '';
  descrizione = '';
  searchKey = '';
  panelEnabled: boolean;
  confermaEnabled: boolean;
  annullaEnabled: boolean;
  creaEnabled: boolean;
  modificaEnabled: boolean;
  rimuoviEnabled: boolean;
  cercaEnabled: boolean;
  aggiungiEnabled: boolean;
  risultatoEnabled: boolean;
  taglie: Taglia[] = [];

  constructor(private http: HttpClient) {
    this.initNgIf();
  }

  ngOnInit() {
  }

  initNgIf() {
    this.panelEnabled = false;
    this.confermaEnabled = false;
    this.annullaEnabled = false;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;
    this.risultatoEnabled = false;
  }

  cercaSiRisultato() {
    this.panelEnabled = false;
    this.confermaEnabled = false;
    this.annullaEnabled = false;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;
    this.risultatoEnabled = true;
  }

  cercaNoRisultato() {
    this.cercaSiRisultato();
    this.risultatoEnabled = false;
  }

  cerca() {
    //prepara la chiamata al server
    let dto: TagliaSearchDto = new TagliaSearchDto();
    dto.token = null; // TODO da inserire il token della sessione corrente
    dto.searchKey = this.searchKey;

      let obs: Observable < TagliaSearchResultsDto > =
    this.http.post<TagliaSearchResultsDto>('http://localhost:8080/search-taglie' , dto);
    //prepara la callback
    //se trova qualcosa imposta le visibilità su cercaSiRisultato
    //altrimenti imposta le visibilità su cercaNoRisultato
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

  aggiungi() {

  }

}
