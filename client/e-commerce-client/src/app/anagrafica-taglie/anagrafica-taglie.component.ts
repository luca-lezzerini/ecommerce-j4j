import { TagliaCreateDto } from './../classi/taglia-create-dto';
import { AreaComuneService } from "./../area-comune.service";
import { TagliaSearchDto } from "./../classi/taglia-search-dto";
import { HttpClient } from "@angular/common/http";
import { TagliaSearchResultsDto } from "./../classi/taglia-search-results-dto";
import { Taglia } from "./../classi/taglia";
import { Observable } from "rxjs";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: "app-anagrafica-taglie",
  templateUrl: "./anagrafica-taglie.component.html",
  styleUrls: ["./anagrafica-taglie.component.css"]
})
export class AnagraficaTaglieComponent implements OnInit {
  codice = '';
  descrizione = '';
  searchKey = '';
  panelEnabled2: boolean;
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
  visPrecedente: string;

 
  constructor(private http: HttpClient,
    private singleton: AreaComuneService,
    private root: ActivatedRoute,
private router: Router) {
    this.initVis();
  }

  ngOnInit() { }

  initVis() {
    this.panelEnabled2 = false;
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
    this.panelEnabled2 = false;
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
    this.panelEnabled2 = true;
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
      this.http.post<TagliaSearchResultsDto>('http://localhost:8080/search-taglia', dto);

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
    this.visPrecedente = 'cerca';
  }

  aggiungi() {
    //imposta la visibilità su visAggiungi
    this.visAggiungi();
    this.visPrecedente = 'aggiungi';
  }

  conferma() {
    switch (this.visPrecedente) {
      case 'aggiungi':
        this.confermaCrea();
        break;
      case 'crea':
        this.confermaCrea();
        break;
      case 'modifica':
        this.confermaCrea();
        break;
      case 'edit':
        this.confermaCrea();
        break;
      case 'rimuovi':
        this.confermaCrea();
        break;
      case 'delete':
        this.confermaCrea();
        break;
    }
  }

  confermaCrea() {
    //prepara la chiamata al server
    let dto: TagliaCreateDto = new TagliaCreateDto();
    dto.token = this.singleton.token;
    dto.dati = new Taglia();
    dto.dati.codice = this.codice;
    dto.dati.descrizione = this.descrizione;
    let obs: Observable<any> = this.http.post('http://localhost:8080/create-taglia', dto);
    //prepara la callback
    obs.subscribe(risposta => {
      //ripete ultima ricerca
      this.cerca();
    });
  }

  annulla() { }

  crea() { }

  modifica() { }

  rimuovi() { }

  view() { }

  edit() { }

  delete() { }
}
