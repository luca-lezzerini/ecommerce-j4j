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
  tagliaSelezionata: Taglia = new Taglia();
  paginaAttuale: number;
  pageIsFirst: boolean;
  pageIsLast: boolean;

  constructor(
    private http: HttpClient,
    private sessione: AreaComuneService,
    private router: Router
  ) {
    this.initVis();
  }

  /**
   * Se il token è nullo vieni reindirizzato alla pagina di login.
   */
  ngOnInit() {
    if (!this.sessione.token) {
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

  /**
   * Imposta la visibilità in base allo stato precedente.
   */
  visAnnulla() {
    switch (this.visPrecedente) {
      case 'aggiungi':
        this.cerca(this.paginaAttuale);
        break;
      case 'crea':
        this.cerca(this.paginaAttuale);
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

  /**
   * Manda una richiesta al server con un oggetto contenente il token per poter
   * verificare l'accesso e la stringa contenente il parametro di ricerca. Se
   * il server ritorna una lista di prodotti non nulla, mostro il suo contenuto.
   * @param pagina la pagina attuale. Serve al server per decidere che pagina
   * deve far mostrare
   */
  cerca(pagina: number) {
    // prepara la chiamata al server
    const dto: TagliaSearchDto = new TagliaSearchDto();
    dto.token = this.sessione.token;
    dto.searchKey = this.searchKey;
    dto.page = pagina;
    const obs: Observable<TagliaSearchResultsDto> =
      this.http.post<TagliaSearchResultsDto>(this.sessione.hostUrl + '/search-taglia', dto);

    // invia la richiesta al server
    obs.subscribe(risposta => {
      this.taglie = risposta.result;
      this.paginaAttuale = risposta.page;
      this.pageIsFirst = risposta.first;
      this.pageIsLast = risposta.last;
      if (this.taglie && this.taglie.length > 0) {
        // se trova qualcosa mostra il risultato
        this.visCercaConRisultato();
      } else {
        // altrimenti nasconde la tabella dei risultati
        this.visCercaSenzaRisultato();
      }
      if (this.visPrecedente === 'edit') {
        this.view(this.tagliaSelezionata);
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
        this.view(this.tagliaSelezionata);
        break;
      case 'edit':
        this.view(this.tagliaSelezionata);
        break;
      case 'rimuovi':
        this.view(this.tagliaSelezionata);
        break;
      case 'delete':
        this.cerca(this.paginaAttuale);
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

  modifica() {
    // imposta la visibilità su VisAggiungi
    this.visAttesaConferma();
    this.visPrecedente = 'modifica';
  }

  rimuovi() {
    this.visAttesaConfermaDelete();
    this.visPrecedente = 'rimuovi';
  }

  view(t: Taglia) {
    this.visView();
    this.setDettagli(t);
  }

  edit(t: Taglia) {
    this.visAttesaConferma();
    this.setDettagli(t);
    this.visPrecedente = 'edit';
  }

  delete(t: Taglia) {
    this.visAttesaConfermaDelete();
    this.setDettagli(t);
    this.visPrecedente = 'delete';
  }

  /**
   * Prende una taglia e popola i campi di testo con i suoi valori.
   * @param id Viene passata la taglia selezionata.
   */
  setDettagli(t: Taglia) {
    this.tagliaSelezionata = t;
    this.id = t.id;
    this.codice = t.codice;
    this.descrizione = t.descrizione;
  }

  /**
   * Manda una richiesta al server con un oggetto contenente il token per poter
   * verificare l'accesso e la taglia da registrare. Dopodoché ripete l'ultima
   * ricerca effettuata e pulisce i campi.
   */
  confermaAggiungi() {
    if (this.checkCampi()) {
      // prepara la chiamata al server
      const dto: TagliaCreateDto = new TagliaCreateDto();
      dto.token = this.sessione.token;
      dto.dati = new Taglia();
      dto.dati.codice = this.codice;
      dto.dati.descrizione = this.descrizione;
      const obs: Observable<any> = this.http.post(this.sessione.hostUrl + '/create-taglia', dto);
      // invia la richiesta al server
      obs.subscribe(risposta => {
        // ripete ultima ricerca
        this.cerca(0);
      });
      this.pulisciCampi();
    }
  }

  /**
   * Manda una richiesta al server con un oggetto contenente il token per poter
   * verificare l'accesso e la taglia da modificare. Dopodoché ripete l'ultima
   * ricerca effettuata e pulisce i campi.
   */
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
      const obs: Observable<any> = this.http.post(this.sessione.hostUrl + '/update-taglia', dto);
      // invia la richiesta al server
      obs.subscribe(response => {
        this.cerca(this.paginaAttuale);
      });
      this.pulisciCampi();
    }
  }

  /**
   * Manda una richiesta al server con un oggetto contenente il token per poter
   * verificare l'accesso e la taglia da rimuovere. Dopodoché ripete l'ultima
   * ricerca effettuata e pulisce i campi.
   */
  confermaDelete() {
    const dto: TagliaDeleteDto = new TagliaDeleteDto();
    dto.token = this.sessione.token;
    dto.idToDelete = this.id;
    const obs: Observable<any> = this.http.post<any>(this.sessione.hostUrl + '/delete-taglia', dto);
    obs.subscribe(reponse => {
      this.cerca(0);
    });
    this.pulisciCampi();
  }

  /**
   * Verica che i campi siano popolati
   */
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
