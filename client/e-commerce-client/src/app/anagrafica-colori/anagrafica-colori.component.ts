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
  id: number;
  colore: Colori = new Colori();
  ricerca: string;
  result: Colori[] = [];
  state: string;
  olderState: string;

  // variabili per mostrare o nascondere componenti UI
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

  // vari stati in cui si può trovare
  statoCerca = 'Cerca';
  statoVisualizza = 'Visualizza';
  statoModifica = 'Modifica';
  statoCancella = 'Cancella';
  statoAggiungi = 'Aggiungi';

  // inizializzo le variabili UI
  constructor(private http: HttpClient) {
    this.showPanel = false;
    this.showCerca = true;
    this.showRisultati = false;
    this.showAggiungi = true;
    this.state = this.statoCerca;
  }

  ngOnInit() {
  }

  // cambia lo stato quando si clicca conferma, invoca diversi metodi in base allo stato precedente
  confermaCheckState(stato: string) {
    // Salva i dati del colore selezionato
    this.colore = {
      id: this.id,
      codice: this.codice,
      descrizione: this.descrizione,
    };
    if (stato === this.statoAggiungi) {
      this.olderState = this.state;
      this.state = this.statoCerca;
      this.createColori();
      this.cercaState();
    } else if (stato === this.statoModifica) {
      this.olderState = this.state;
      this.state = this.statoVisualizza;
      this.updateColori(this.colore);
      this.visualizzaState(this.colore);
    } else if (stato === this.statoCancella) {
      this.olderState = this.state;
      this.state = this.statoCerca;
      this.deleteColori(this.colore);
      this.cercaState();
    }
  }

  // cambia stato quando si preme annulla, esegue controllo sullo stato precedente
  annullaCheckState(stato: string) {
    if (stato === this.statoAggiungi) {
      this.olderState = this.state;
      this.state = this.statoCerca;
      this.cercaState();
    } else if (stato === this.statoModifica) {
      this.olderState = this.state;
      this.state = this.statoVisualizza;
      this.visualizzaState(this.colore);
    } else if (stato === this.statoCancella) {
      if (this.olderState === this.statoCerca) {
        this.olderState = this.state;
        this.state = this.statoCerca;
        this.cercaState();
      } else if (this.olderState === this.statoVisualizza) {
        this.olderState = this.state;
        this.state = this.statoVisualizza;
        this.visualizzaState(this.colore);
      }
    }
  }
// cambia lo stato quando si clicca crea: se sto visualizzando passa alla creazione di un nuovo elemento
  creaCheckState(stato: string) {
    if (stato === this.statoAggiungi) {
      this.olderState = this.state;
      this.state = this.statoCerca;
      this.createColori();
    } else if (stato === this.statoVisualizza) {
      this.olderState = this.state;
      this.state = this.statoAggiungi;
      this.aggiungiState();
    }
  }

  // cambia stato quando si preme modifica nel panel
  modificaCheckState(stato: string) {
    if (stato === this.statoCerca || stato === this.statoVisualizza) {
      this.olderState = this.state;
      this.state = this.statoModifica;
      this.CUDState();
      this.inputNotEditable = false;
    }
  }
  // cambia stato quando si preme modifica nella tabella
  modificaInTableCheckState(stato: string, c: Colori) {
    if (stato === this.statoCerca || stato === this.statoVisualizza) {
      // salva le proprietà dell'elemento selezionato nelle proprietà dichiarate localmente
      this.id = c.id;
      this.descrizione = c.descrizione;
      this.codice = c.codice;
      this.olderState = this.state;
      this.state = this.statoModifica;
      this.CUDState();
      this.inputNotEditable = false;
    }
  }

  // cambia stato quando si preme cancella nel panel
  cancellaCheckState(stato: string) {
    if (stato === this.statoCerca || stato === this.statoVisualizza) {
      this.olderState = this.state;
      this.state = this.statoCancella;
      this.CUDState();
      this.inputNotEditable = true;
    }
  }
  // cambia stato quando si preme cancella nella tabella
  cancellaInTableCheckState(stato: string, c: Colori) {
    if (stato === this.statoCerca || stato === this.statoVisualizza) {
      // salva le proprietà dell'elemento selezionato nelle proprietà dichiarate localmente
      this.id = c.id;
      this.descrizione = c.descrizione;
      this.codice = c.codice;
      this.olderState = this.state;
      this.state = this.statoCancella;
      this.CUDState();
      this.inputNotEditable = true;
    }
  }

  // cambia stato quando si preme cerca e esegue la ricerca
  cercaCheckState(stato: string) {
    if (stato === this.statoCerca || stato === this.statoVisualizza) {
      this.olderState = this.state;
      this.state = this.statoCerca;
      this.searchColori();
      this.cercaState();
    }
  }

  visualizzaState(colore: Colori) {
    this.olderState = this.state;
    this.state = this.statoVisualizza;

    // salva i dati del colore selezionato
    this.id = colore.id;
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
    this.showRisultati = true;
    this.showAggiungi = true;
  }
  aggiungiState() {
    this.showPanel = true;
    this.inputNotEditable = false;
    this.showConferma = true;
    this.showAnnulla = true;
    this.showCrea = false;
    this.showVisualizza = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showCerca = false;
    this.showRisultati = false;
    this.showAggiungi = true;
    this.state = this.statoAggiungi;
    this.codice = '';
    this.descrizione = '';
  }

  createColori(): void {
    // Fare check dei campi se sono vuoti o meno
    if (this.codice.trim() !== '' && this.descrizione.trim() !== '') {
      this.colore = {
        id: this.id,
        codice: this.codice,
        descrizione: this.descrizione
      };

      // preparo i dati da inviare al server
      const dto: ColoriCreateDto = new ColoriCreateDto();
      this.colore.codice = this.codice;
      this.colore.descrizione = this.descrizione;
      dto.dati = this.colore;
      // preparo la richiesta http
      const obs: Observable<void> = this.http.post<void>(
        'http://localhost:8080/create-colori',
        dto
      );
      obs.subscribe(data => {
        // dopo la risposta del server visualizza gli elementi salvati nel database
        this.searchColori();
      });
    }
  }
  searchColori(): void {
    // se il campo è vuoto o sono stati inseriti solo spazi imposta ricerca ad una stringa vuota
    if (this.ricerca === undefined) {
      this.ricerca = '';
    }
    if (this.ricerca.trim() === '') {
      this.ricerca = '';
    }
    // preparo i dati da inviare al server
    const dto: ColoriSearchDto = new ColoriSearchDto();
    dto.searchKey = this.ricerca;
    // preparo la richiesta http
    const obs: Observable<ColoriSearchResultsDto> = this.http.post<
      ColoriSearchResultsDto
    >('http://localhost:8080/search-colori', dto);
    obs.subscribe(data => {
      // salva la lista di risultati ottenuti dal server nell' array locale che viene visualizzato
      this.result = data.result;
    });
  }
  deleteColori(c: Colori): void {
    // preparo i dati da inviare al server
    const dto: ColoriDeleteDto = new ColoriDeleteDto();
    dto.idToDelete = c.id;
    // preparo la richiesta http
    const obs: Observable<void> = this.http.post<void>(
      'http://localhost:8080/delete-colori',
      dto
    );
    obs.subscribe(data => {
      // dopo la risposta del server visualizza gli elementi salvati nel database
      this.searchColori();
    });
  }
  updateColori(c: Colori): void {
    // preparo i dati da inviare al server
    const dto: ColoriUpdateDto = new ColoriUpdateDto();
    dto.dati = c;
    // preparo la richiesta http
    const obs: Observable<void> = this.http.post<void>(
      'http://localhost:8080/update-colori',
      dto
    );
    obs.subscribe(data => {
      // dopo la risposta del server visualizza gli elementi salvati nel database
      this.searchColori();
    });
  }
}
