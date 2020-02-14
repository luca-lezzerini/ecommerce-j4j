import { AreaComuneService } from './../area-comune.service';
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
  inputEditable: boolean;

  constructor(private http: HttpClient, private singleton: AreaComuneService) {
    this.initVis();
  }

  ngOnInit() {
  }

  initVis() {
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

  visCercaSiRisultato() {
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

  visCercaNoRisultato() {
    this.visCercaSiRisultato();
    this.risultatoEnabled = false;
  }

  visAggiungi() {
    this.panelEnabled = true;
    this.inputEditable = true;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = false;
    this.risultatoEnabled = false;
    this.aggiungiEnabled = false;
  }

  cerca() {
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
  }

  aggiungi() {
    //imposta la visibilità su visAggiungi
    this.visAggiungi();
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

}
