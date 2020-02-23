import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ColoriCreateDto } from '../classi/colori-create-dto';
import { ColoriUpdateDto } from '../classi/colori-update-dto';
import { ColoriSearchDto } from '../classi/colori-search-dto';
import { ColoriSearchResultsDto } from '../classi/colori-search-results-dto';
import { ColoriDeleteDto } from '../classi/colori-delete-dto';
import { Colori } from '../classi/colori';
import { Observable } from 'rxjs';
import { AreaComuneService } from '../area-comune.service';
import { Router } from '@angular/router';

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
  msg: string;

  // variabili per mostrare o nascondere componenti UI
  showPanel: boolean;
  inputNotEditable: boolean;
  showConfermaAnnulla: boolean;
  showCUD: boolean;
  showCerca: boolean;
  showRisultati: boolean;
  showAggiungi: boolean;

  // vari stati in cui si può trovare
  statoCerca = 'Cerca';
  statoVisualizza = 'Visualizza';
  statoModifica = 'Modifica';
  statoCancella = 'Cancella';
  statoAggiungi = 'Aggiungi';

  // inizializzo le variabili UI
  constructor(
    private http: HttpClient,
    private ac: AreaComuneService,
    private router: Router
  ) {
    this.showPanel = false;
    this.showCerca = true;
    this.showRisultati = false;
    this.showAggiungi = true;
    this.state = this.statoCerca;
  }

  ngOnInit() {
    if (!this.ac.token) {
      this.router.navigateByUrl('login');
    }
  }

  /**
   * cambia lo stato quando si clicca conferma, invoca diversi metodi in base allo stato precedente
   * @param stato viene impostato in base allo stato corrente (this.state)
   */

  confermaCheckState(stato: string) {
    // Salva i dati del colore selezionato
    if (this.codice.trim() !== '' && this.descrizione.trim() !== '') {
      this.colore = {
        id: this.id,
        codice: this.codice,
        descrizione: this.descrizione
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
  }

  /**
   * cambia stato quando si preme annulla, esegue controllo sullo stato precedente
   * @param stato viene impostato in base allo stato corrente (this.state)
   */
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

  /**
   * cambia lo stato quando si clicca crea: se sto visualizzando passa alla creazione di un nuovo elemento
   * @param stato viene impostato in base allo stato corrente (this.state)
   */
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

  /**
   * cambia stato quando si preme modifica nel panel
   * @param stato viene impostato in base allo stato corrente (this.state)
   */
  modificaCheckState(stato: string) {
    if (stato === this.statoCerca || stato === this.statoVisualizza) {
      this.olderState = this.state;
      this.state = this.statoModifica;
      this.CUDState();
      this.inputNotEditable = false;
    }
  }

  /**
   * cambia stato quando si preme modifica nella tabella
   * @param stato viene impostato in base allo stato corrente (this.state)
   */
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

  /**
   * cambia stato quando si preme cancella nel panel
   * @param stato viene impostato in base allo stato corrente (this.state)
   */
  cancellaCheckState(stato: string) {
    if (stato === this.statoCerca || stato === this.statoVisualizza) {
      this.olderState = this.state;
      this.state = this.statoCancella;
      this.CUDState();
      this.inputNotEditable = true;
    }
  }

  /**
   * cambia stato quando si preme cancella nella tabella
   * @param stato viene impostato in base allo stato corrente (this.state)
   */
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

  /**
   *  cambia stato quando si preme cerca e esegue la ricerca
   * @param stato viene impostato in base allo stato corrente (this.state)
   */
  cercaCheckState(stato: string) {
    if (stato === this.statoCerca || stato === this.statoVisualizza) {
      this.olderState = this.state;
      this.state = this.statoCerca;
      this.searchColori();
      this.cercaState();
    }
  }

  /**
   * cambia stato in visualizza quando si preme su visualizza
   * @param colore viene impostato in base al colore che viene visualizzato
   */
  visualizzaState(colore: Colori) {
    this.olderState = this.state;
    this.state = this.statoVisualizza;

    // salva i dati del colore selezionato
    this.id = colore.id;
    this.codice = colore.codice;
    this.descrizione = colore.descrizione;

    this.showPanel = true;
    this.inputNotEditable = true;
    this.showConfermaAnnulla = false;
    this.showCUD = true;
    this.showCerca = true;
    this.showRisultati = true;
    this.showAggiungi = true;
  }
  /**
   * Cambia stato in Crea, Modifica e Cancella quando si preme su Crea, Modifica o Cancella
   */
  CUDState() {
    this.msg = null;
    // inputNotEditabile varia in base allo stato in cui si passa
    this.showPanel = true;
    this.showConfermaAnnulla = true;
    this.showCUD = false;
    this.showCerca = false;
    this.showRisultati = false;
    this.showAggiungi = false;
  }

  /**
   * cambia stato in cerca quando si preme su cerca
   */
  cercaState() {
    this.showPanel = false;
    this.inputNotEditable = true;
    this.showConfermaAnnulla = false;
    this.showCUD = true;
    this.showCerca = true;
    this.showRisultati = true;
    this.showAggiungi = true;
  }
  /**
   * cambia stato in aggiungi quando si preme su aggiungi
   */
  aggiungiState() {
    this.msg = null;
    this.showPanel = true;
    this.inputNotEditable = false;
    this.showConfermaAnnulla = true;
    this.showCUD = false;
    this.showCerca = false;
    this.showRisultati = false;
    this.showAggiungi = true;

    this.state = this.statoAggiungi;
    this.codice = '';
    this.descrizione = '';
  }

  /**
   * Crea un colore, lo invia al server e lo aggiunge aggiornando e visualizzando la lista dei colori
   */
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
      dto.token = this.ac.token;
      // preparo la richiesta http
      const obs: Observable<void> = this.http.post<void>(
        this.ac.hostUrl + '/create-colori',
        dto
      );
      obs.subscribe(data => {
        // dopo la risposta del server visualizza gli elementi salvati nel database
        this.searchColori();
      });
    }
  }

  /**
   * Ricerca i colori presenti nel Database che corrispondono alla searchKey e li visualizza nella tabella
   * Nel caso il campo è vuoto mostrerà tutta la lista
   */
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
    dto.token = this.ac.token;
    // preparo la richiesta http
    const obs: Observable<ColoriSearchResultsDto> = this.http.post<
      ColoriSearchResultsDto
    >(this.ac.hostUrl + '/search-colori', dto);
    obs.subscribe(data => {
      // salva la lista di risultati ottenuti dal server nell' array locale che viene visualizzato
      this.result = data.result;
      if (this.result.length === 0) {
        this.msg = 'Nessun risultato trovato!';
      } else {
        this.msg = 'Trovati ' + this.result.length + ' colori';
      }
    });
  }

  /**
   * Elimina il colore con l'id corrispondente a quello trovato nel DataBase
   * @param c (colore da eliminare tramite il parametro id)
   */
  deleteColori(c: Colori): void {
    // preparo i dati da inviare al server
    const dto: ColoriDeleteDto = new ColoriDeleteDto();
    dto.idToDelete = c.id;
    dto.token = this.ac.token;
    // preparo la richiesta http
    const obs: Observable<void> = this.http.post<void>(
      this.ac.hostUrl + '/delete-colori',
      dto
    );
    obs.subscribe(data => {
      // dopo la risposta del server visualizza gli elementi salvati nel database
      this.searchColori();
    });
  }

  /**
   * Sul client faccio le modifiche al colore e invio al server il colore modificato tramite il Dto
   * @param c (colore da modificare)
   */
  updateColori(c: Colori): void {
    // preparo i dati da inviare al server
    const dto: ColoriUpdateDto = new ColoriUpdateDto();
    dto.dati = c;
    dto.token = this.ac.token;
    // preparo la richiesta http
    const obs: Observable<void> = this.http.post<void>(
      this.ac.hostUrl + '/update-colori',
      dto
    );
    obs.subscribe(data => {
      // dopo la risposta del server visualizza gli elementi salvati nel database
      this.searchColori();
    });
  }
}
