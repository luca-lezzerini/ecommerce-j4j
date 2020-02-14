import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ColoriCreateDto } from '../classi/colori-create-dto';
import { ColoriUpdateDto } from '../classi/colori-update-dto';
import { ColoriSearchDto } from '../classi/colori-search-dto';
import { ColoriSearchResultsDto } from '../classi/colori-search-results-dto';
import { ColoriDeleteDto } from '../classi/colori-delete-dto';
import { Colori } from '../classi/colori';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-anagrafica-colori',
  templateUrl: './anagrafica-colori.component.html',
  styleUrls: ['./anagrafica-colori.component.css']
})
export class AnagraficaColoriComponent implements OnInit {
  codice: string;
  descrizione: string;
  colore: Colori;
  ricerca: string;
  result: Colori[];
  state: string;
  olderState: string;

  panelHidden: boolean;
  inputEditable: boolean;
  confermaHidden: boolean;
  annullaHidden: boolean;
  creaHidden: boolean;
  modificaHidden: boolean;
  rimuoviHidden: boolean;
  cercaHidden: boolean;
  risultatiHidden: boolean;
  aggiungiHidden: boolean;
  visualizzaHidden: boolean;

  constructor(private http: HttpClient) {
    this.panelHidden = true;
    this.cercaHidden = false;
    this.risultatiHidden = true;
    this.aggiungiHidden = false;
  }

  ngOnInit() {}

  confermaCheckState(stato: string) {
    if (stato === 'Aggiungi') {
      this.olderState = this.state;
      this.state = 'Cerca';
      this.createColori();
      this.creaState();
    } else if (stato === 'Modifica') {
      this.olderState = this.state;
      this.state = 'Visualizza';
      this.updateColori();
      this.visualizzaState(null);
    } else if (stato === 'Cancella') {
      this.olderState = this.state;
      this.state = 'Cerca';
      this.deleteColori();
      this.cercaState();
    }
  }

  annullaCheckState(stato: string) {
    if (stato === 'Aggiungi') {
      this.olderState = this.state;
      this.state = 'Cerca';
      this.cercaState();
    } else if (stato === 'Modifica') {
      this.olderState = this.state;
      this.state = 'Visualizza';
      this.visualizzaState(null);
    } else if (stato === 'Cancella') {
      if (this.olderState === 'Cerca') {
        this.olderState = this.state;
        this.state = 'Cerca';
        this.cercaState();
      } else if (this.olderState === 'Visualizza') {
        this.olderState = this.state;
        this.state = 'Visualizza';
        this.visualizzaState(null);
      }
    }
  }

  modificaCheckState(stato: string) {
    if (stato === ('Cerca' || 'Visualizza')) {
      this.olderState = this.state;
      this.state = 'Modifica';
      this.modificaState();
    }
  }

  cancellaCheckState(stato: string) {
    if (stato === ('Cerca' || 'Visualizza')) {
      this.olderState = this.state;
      this.state = 'Cancella';
      this.cancellaState();
    }
  }

  cercaCheckState(stato: string) {
    if (stato === ('Cerca' || 'Visualizza')) {
      this.olderState = this.state;
      this.state = 'Cerca';
      this.searchColori();
      this.cercaState();
    }
  }

  visualizzaState(colore: Colori) {
    this.olderState = this.state;
    this.state = 'Visualizza';
    // salva i dati del colore selezionato
    this.codice = colore.codice;
    this.descrizione = colore.descrizione;

    this.panelHidden = false;
    this.inputEditable = false;
    this.confermaHidden = true;
    this.annullaHidden = true;
    this.creaHidden = false;
    this.modificaHidden = false;
    this.rimuoviHidden = false;
    this.cercaHidden = false;
    this.risultatiHidden = false;
    this.aggiungiHidden = false;
  }

  modificaState() {
    this.panelHidden = false;
    this.inputEditable = true;
    this.confermaHidden = false;
    this.annullaHidden = false;
    this.creaHidden = true;
    this.modificaHidden = true;
    this.rimuoviHidden = true;
    this.cercaHidden = true;
    this.risultatiHidden = true;
    this.aggiungiHidden = true;
  }
  creaState() {
    this.panelHidden = false;
    this.inputEditable = true;
    this.confermaHidden = false;
    this.annullaHidden = false;
    this.creaHidden = true;
    this.modificaHidden = true;
    this.rimuoviHidden = true;
    this.cercaHidden = true;
    this.risultatiHidden = true;
    this.aggiungiHidden = true;
  }
  cancellaState() {
    this.panelHidden = false;
    this.inputEditable = false;
    this.confermaHidden = true;
    this.annullaHidden = true;
    this.creaHidden = true;
    this.modificaHidden = true;
    this.rimuoviHidden = true;
    this.cercaHidden = true;
    this.risultatiHidden = true;
    this.aggiungiHidden = true;
  }
  cercaState() {
    this.panelHidden = true;
    this.inputEditable = false;
    this.confermaHidden = true;
    this.annullaHidden = true;
    this.creaHidden = true;
    this.modificaHidden = true;
    this.rimuoviHidden = false;
    this.cercaHidden = true;
    // this.risultatiHidden = true;
    this.aggiungiHidden = true;
  }
  aggiungiState() {
    this.panelHidden = false;
    this.inputEditable = true;
    this.confermaHidden = false;
    this.annullaHidden = false;
    this.creaHidden = true;
    this.modificaHidden = true;
    this.rimuoviHidden = true;
    this.cercaHidden = true;
    this.risultatiHidden = true;
    this.aggiungiHidden = true;
  }

  createColori(): void {
    let coloreTest: Colori = {id: 1, codice: 'ciao', descrizione: 'ciao'};
    // // TODO Fare check dei campi se sono vuoti o meno

    // // preparo i dati da inviare al server
    // const dto: ColoriCreateDto = new ColoriCreateDto();
    // this.colore.codice = this.codice;
    // this.colore.descrizione = this.descrizione;
    // dto.dati = this.colore;
    // // preparo la richiesta http
    // const obs: Observable<void> = this.http.post<void>(
    //   'http://localhost:8080/create-colori',
    //   dto
    // );
    // obs.subscribe(data => {});
  }
  searchColori(): void {
    // // preparo i dati da inviare al server
    // const dto: ColoriSearchDto = new ColoriSearchDto();
    // dto.searchKey = this.ricerca;
    // // preparo la richiesta http
    // const obs: Observable<ColoriSearchResultsDto> = this.http.post<
    //   ColoriSearchResultsDto
    // >('http://localhost:8080/search-colori', dto);
    // obs.subscribe(data => {
    //   this.result = data.result;
    // });
  }
  deleteColori(): void {
    // // preparo i dati da inviare al server
    // const dto: ColoriDeleteDto = new ColoriDeleteDto();
    // dto.idToDelete = this.colore.id;
    // // preparo la richiesta http
    // const obs: Observable<void> = this.http.post<void>(
    //   'http://localhost:8080/delete-colori',
    //   dto
    // );
    // obs.subscribe(data => {});
  }
  updateColori(): void {
    // // preparo i dati da inviare al server
    // const dto: ColoriUpdateDto = new ColoriUpdateDto();
    // dto.dati = this.colore;
    // // preparo la richiesta http
    // const obs: Observable<void> = this.http.post<void>(
    //   'http://localhost:8080/update-colori',
    //   dto
    // );
    // obs.subscribe(data => {});
  }
}
