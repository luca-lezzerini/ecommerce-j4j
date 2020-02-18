import { TagliaDeleteDto } from './../classi/taglia-delete-dto';
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
  confermaAndAnnullaEnabled: boolean;
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
    private router: Router
  ) {
    this.initVis();
  }

  ngOnInit() {
    console.log(this.sessione);
    if (! this.sessione.token) {
      this.router.navigateByUrl('login');
    }
  }

  initVis() {
    this.panelEnabled = false;
    this.confermaAndAnnullaEnabled = false;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = true;
    this.aggiungiEnabled = true;
    this.risultatoEnabled = false;
  }

  visCercaConRisultato() {
    this.panelEnabled = false;
    this.confermaAndAnnullaEnabled = false;
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
    this.confermaAndAnnullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = false;
    this.risultatoEnabled = false;
    this.aggiungiEnabled = false;
  }

  visAttesaConfermaDelete() {
    this.visAttesaConferma();
    this.inputDisabled = true;
  }

  visView() {
    this.panelEnabled = true;
    this.inputDisabled = true;
    this.confermaAndAnnullaEnabled = false;
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
    }
  }

  visModifica() {
    this.panelEnabled = true;
    this.inputDisabled = true;
    this.confermaAndAnnullaEnabled = true;
    this.creaEnabled = false;
    this.modificaEnabled = false;
    this.rimuoviEnabled = false;
    this.cercaEnabled = false;
    this.risultatoEnabled = false;
    this.aggiungiEnabled = false;
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
      if (this.visPrecedente === 'edit') {
        this.view(this.id);
      }
      this.visPrecedente = 'cerca';
    });
  }

  conferma() {
    switch (this.visPrecedente) {
      case 'aggiungi':
        this.confermaAggiungi();
        break;
      case 'crea':
        this.confermaAggiungi();
        break;
      case 'modifica':
        this.confermaEdit();
        break;
      case 'edit':
        this.confermaEdit();
        break;
      case 'rimuovi':
        this.confermaDelete();
        break;
      case 'delete':
        this.confermaDelete();
        break;
    }
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
        this.visAnnulla();
        this.codice = '';
        this.descrizione = '';
        this.id = 0;
        break;
      case 'modifica':
        this.view(this.id);
        break;
      case 'edit':
        this.view(this.id);
        break;
      case 'rimuovi':
        this.view(this.id);
        break;
      case 'delete':
        this.cerca();
        break;
    }
  }

  aggiungi() {
    // imposta la visibilità su visAggiungi
    this.visAttesaConferma();
    this.visPrecedente = 'aggiungi';
    this.pulisciCampi();
  }

  crea() {
    // imposta la visibilità su visAggiungi
    this.visAttesaConferma();
    this.visPrecedente = 'crea';
    this.pulisciCampi();
  }

  modifica(id: number) {
    // imposta la visibilità su VisAggiungi
    this.visAttesaConferma();
    this.visPrecedente = 'modifica';
  }

  rimuovi() {
    this.visAttesaConfermaDelete();
    this.visPrecedente = 'rimuovi';
  }

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
    this.visAttesaConfermaDelete();
    this.getDettagli(id);
    this.visPrecedente = 'delete';
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

  confermaAggiungi() {
    if (this.checkCampi()) {
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
      this.pulisciCampi();
    }
  }

  confermaEdit() {
    if (this.checkCampi()) {
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
      this.pulisciCampi();
    }
  }

  confermaDelete() {
    const dto: TagliaDeleteDto = new TagliaDeleteDto();
    dto.token = this.sessione.token;
    dto.idToDelete = this.id;
    const obs: Observable<any> = this.http.post<any>('http://localhost:8080/delete-taglia', dto);
    obs.subscribe(reponse => {
      this.cerca();
    });
    this.pulisciCampi();
  }

  checkCampi(): boolean {
    if (this.codice && this.descrizione) {
      return true;
    }
    return false;
  }

  pulisciCampi() {
    this.codice = '';
    this.descrizione = '';
  }
}
