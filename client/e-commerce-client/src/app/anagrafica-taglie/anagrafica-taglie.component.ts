import { TagliaUpdateDto } from './../classi/taglia-update-dto';
import { TagliaCreateDto } from './../classi/taglia-create-dto';
import { AreaComuneService } from './../area-comune.service';
import { TagliaSearchDto } from './../classi/taglia-search-dto';
import { HttpClient } from '@angular/common/http';
import { TagliaSearchResultsDto } from './../classi/taglia-search-results-dto';
import { Taglia } from './../classi/taglia';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

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
  inputDisabled: boolean;
  visPrecedente: string;
  id = 0;

  constructor(
    private http: HttpClient,
    private sessione: AreaComuneService,
    private root: ActivatedRoute,
    private router: Router
  ) {
    this.initVis();
    // FIXME : stub
    this.sessione.token = '123';
  }

  ngOnInit() { }

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

  visCercaConRisultato() {
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

  visCercaSenzaRisultato() {
    this.visCercaConRisultato();
    this.risultatoEnabled = false;
  }

  visAttesaConferma() {
    this.panelEnabled = true;
    this.inputDisabled = false;
    this.confermaEnabled = true;
    this.annullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = false;
    this.risultatoEnabled = false;
    this.aggiungiEnabled = false;
  }

  visView() {
    this.panelEnabled = true;
    this.inputDisabled = true;
    this.confermaEnabled = false;
    this.annullaEnabled = false;
    this.creaEnabled = true;
    this.modificaEnabled = true;
    this.rimuoviEnabled = true;
    this.cercaEnabled = true;
    this.risultatoEnabled = true;
    this.aggiungiEnabled = true;
  }

  visAnnulla() {
    switch (this.visPrecedente) {
      case 'aggiungi':
        this.cerca();
        break;
      case 'crea':
        this.cerca();
        break;
      case 'edit':
        this.visView();
        break;
      case 'delete':
        this.panelEnabled = true;
        this.confermaEnabled = false;
        this.creaEnabled = true;
        this.modificaEnabled = true;
        this.rimuoviEnabled = true;
        this.cercaEnabled = true;
        this.risultatoEnabled = true;
        this.aggiungiEnabled = true;
        this.inputDisabled = true;
        break;
    }
  }

  cerca() {
    // prepara la chiamata al server
    const dto: TagliaSearchDto = new TagliaSearchDto();
    dto.token = this.sessione.token;
    dto.searchKey = this.searchKey;
    const obs: Observable<TagliaSearchResultsDto> =
      this.http.post<TagliaSearchResultsDto>('http://localhost:8080/search-taglia', dto);

    // invia la richiesta al server
    obs.subscribe(risposta => {
      this.taglie = risposta.result;
      if (this.taglie && this.taglie.length > 0) {
        // se trova qualcosa mostra il risultato
        this.visCercaConRisultato();
      } else {
        // altrimenti nasconde la tabella dei risultati
        this.visCercaSenzaRisultato();
      }
      console.log(this.visPrecedente);
      if (this.visPrecedente === 'edit') {
        this.view(this.id);
      }
      this.visPrecedente = 'cerca';
    });
  }

  aggiungi() {
    // imposta la visibilità su visAggiungi
    this.visAttesaConferma();
    this.visPrecedente = 'aggiungi';
  }

  conferma() {
    switch (this.visPrecedente) {
      case 'aggiungi':
        this.confermaAggiungi();
        break;
      case 'crea':
        break;
      case 'modifica':
        break;
      case 'edit':
        this.confermaEdit();
        break;
      case 'rimuovi':
        break;
      case 'delete':
        break;
    }
    this.codice = '';
    this.descrizione = '';
  }

  annulla() {
    switch (this.visPrecedente) {
      case 'aggiungi':
        this.visAnnulla();
        this.codice = '';
        this.descrizione = '';
        this.id = 0;
        break;
      case 'crea':
        break;
      case 'modifica':
        break;
      case 'edit':
        this.view(this.id);
        break;
      case 'rimuovi':
        break;
      case 'delete':
        break;
    }
  }

  confermaAggiungi() {
    if (this.codice && this.descrizione) {
      // prepara la chiamata al server
      const dto: TagliaCreateDto = new TagliaCreateDto();
      dto.token = this.sessione.token;
      dto.dati = new Taglia();
      dto.dati.codice = this.codice;
      dto.dati.descrizione = this.descrizione;
      const obs: Observable<any> = this.http.post('http://localhost:8080/create-taglia', dto);
      // invia la richiesta al server
      obs.subscribe(risposta => {
        // ripete ultima ricerca
        this.cerca();
      });
    }
  }

  crea() { }

  modifica() { }

  rimuovi() { }

  view(id: number) {
    this.visView();
    this.getDettagli(id);
  }

  edit(id: number) {
    this.visAttesaConferma();
    this.getDettagli(id);
    this.visPrecedente = 'edit';
  }

  delete(id: number) {
  }

  getDettagli(id: number) {
    this.id = id;
    this.taglie.forEach(element => {
      if (element.id === id) {
        this.codice = element.codice;
        this.descrizione = element.descrizione;
      }
    });
  }

  confermaEdit() {
    // creo un oggetto da passare al server
    const dto: TagliaUpdateDto = new TagliaUpdateDto();
    dto.token = this.sessione.token;
    dto.dati = new Taglia();
    dto.dati.id = this.id;
    dto.dati.codice = this.codice;
    dto.dati.descrizione = this.descrizione;
    // preparo la richiesta al server
    const obs: Observable<any> = this.http.post('http://localhost:8080/update-taglia', dto);
    // invia la richiesta al server
    obs.subscribe(response => {
      this.cerca();
    });
  }

}
