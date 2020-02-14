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
  result: Colori[] = [];
  state: string;
  olderState: string;
  idTest: number = 0;

  showPanel: boolean;
  inputNotEditable: boolean;
  showConferma: boolean;
  showAnnulla: boolean;
  showCrea: boolean;
  showModifica: boolean;
  showRimuovi: boolean;
  showCerca: boolean;
  showRisultati: boolean;
  showAggiungi: boolean;
  showVisualizza: boolean;

  constructor(private http: HttpClient) {
    this.showPanel = false;
    this.showCerca = true;
    this.showRisultati = false;
    this.showAggiungi = true;
    this.state = 'Cerca';
  }

  ngOnInit() {}

  confermaCheckState(stato: string) {
    if (stato === 'Aggiungi') {
      this.olderState = this.state;
      this.state = 'Cerca';
      this.createColori();
      this.cercaState();
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
      this.CUDState();
      this.inputNotEditable = false;
    }
  }

  cancellaCheckState(stato: string) {
    if (stato === ('Cerca' || 'Visualizza')) {
      this.olderState = this.state;
      this.state = 'Cancella';
      this.CUDState();
      this.inputNotEditable = true;
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

    this.showPanel = true;
    this.inputNotEditable = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = true;
    this.showVisualizza = true;
    this.showModifica = true;
    this.showRimuovi = true;
    this.showCerca = true;
    this.showRisultati = true;
    this.showAggiungi = true;
  }
// create, update, delete
  CUDState() {
    this.showPanel = true;
    this.showConferma = true;
    this.showAnnulla = true;
    this.showCrea = false;
    this.showVisualizza = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showCerca = false;
    this.showRisultati = false;
    this.showAggiungi = false;
  }
  cercaState() {
    this.showPanel = false;
    this.inputNotEditable = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = true;
    this.showVisualizza = true;
    this.showModifica = true;
    this.showRimuovi = true;
    this.showCerca = true;
    // this.showRisultati = true;
    this.showAggiungi = true;
  }
  aggiungiState() {
    this.showPanel = true;
    this.inputNotEditable = false;
    this.showConferma = true;
    this.showAnnulla = true;
    this.showCrea = true;
    this.showVisualizza = true;
    this.showModifica = true;
    this.showRimuovi = true;
    this.showCerca = true;
    this.showRisultati = true;
    this.showAggiungi = true;
    this.state = 'Aggiungi';
  }

  createColori(): void {
    const coloreTest: Colori = {id: this.idTest, codice: this.codice, descrizione: this.descrizione};
    this.result.push(coloreTest);
    this.idTest++;
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
