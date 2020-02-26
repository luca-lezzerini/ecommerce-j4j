import { ProdottoTagliaResultsDto } from '../classi/prodotto-taglia-results-dto';
import { Prodotto } from '../classi/prodotto';
import { Component, OnInit, OnChanges } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { Router } from '@angular/router';
import { Taglia } from '../classi/taglia';
import { Observable } from 'rxjs';
import { ProdottoTagliaRequestDto } from '../classi/prodotto-taglia-request-dto';

@Component({
  selector: 'app-prodotti-taglie',
  templateUrl: './prodotti-taglie.component.html',
  styleUrls: ['./prodotti-taglie.component.css']
})
export class ProdottiTaglieComponent implements OnInit {

  prodotti: Prodotto[] = [];
  taglieDisponibili: Taglia[];
  taglieNonDisponibili: Taglia[];
  checkDisponibili: boolean[] = [];
  checkNonDisponibili: boolean[] = [];
  prodottoSelezionato: Prodotto;
  prodSel: boolean;
  dnd = false;
  dd = false;
  button = true;

  constructor(private http: HttpClient,
              private acService: AreaComuneService,
              private router: Router) { }

  ngOnInit() {
    if (!this.acService.token) {
      this.router.navigateByUrl('/login');
    }
    this.prodottoSelezionato = this.acService.prodottoSelezionato;
    this.getListe();
  }

  /**
   * Crea un dto contenente la lista delle taglie da rendere disponibili e il prodotto selezionato
   */
  aggiungiADisponibili() {
    const dto: ProdottoTagliaRequestDto = new ProdottoTagliaRequestDto();
    // tramite checkNonDisponibili si ricava gli indici delle taglie da aggiungere a quelle disponibili
    // riprende all'interno di taglieNonDisponibili quelle che hanno indice uguale agli elementi selezionati
    // imposta il checkselezionato a false (altrimenti troverebbe gli stessi elementi)
    this.checkNonDisponibili.forEach(e => {
      const index = this.checkNonDisponibili.indexOf(true);
      dto.taglia.push(this.taglieNonDisponibili[index]);
      this.checkNonDisponibili[index] = false;
    });
    dto.prodotto = this.prodottoSelezionato;
    const obs: Observable<ProdottoTagliaResultsDto> =
      this.http.post<ProdottoTagliaResultsDto>(this.acService.hostUrl + '/add-taglia', dto);
    obs.subscribe(risposta => {
      // chiama il metodo per ottenere le liste e imposta la visibilità dei disponibili a visibile
      this.getListe();
      this.dd = false;
    });
    // pulisce gli array degli elementi selezionati
    this.checkNonDisponibili = [];
    this.checkDisponibili = [];
  }

  /**
   * Crea un dto contenente la lista delle taglie da rendere  non disponibili e il prodotto selezionato
   */
  togliDaDisponibili() {
    // il funzionamento è lo stesso del metodo precedente ma fatto con gli elementi disponibili
    const dto: ProdottoTagliaRequestDto = new ProdottoTagliaRequestDto();
    this.checkDisponibili.forEach(e => {
      const index = this.checkDisponibili.indexOf(true);
      dto.taglia.push(this.taglieDisponibili[index]);
      this.checkDisponibili[index] = false;
    });
    dto.prodotto = this.prodottoSelezionato;
    const obs: Observable<ProdottoTagliaResultsDto> =
      this.http.post<ProdottoTagliaResultsDto>(this.acService.hostUrl + '/remove-taglia', dto);
    obs.subscribe(risposta => {
      this.getListe();
      this.dnd = false;
    });
    this.checkDisponibili = [];
    this.checkNonDisponibili = [];
  }

  /**
   * Torna all'anagrafica prodotti
   */
  esci() {
    this.router.navigateByUrl('/anagrafica-prodotti');
  }

  /**
   * recupera le due liste di taglie (disponibili e non disponibili) per il prodotto selezionato
   */
  getListe() {
    const obs: Observable<ProdottoTagliaResultsDto> =
      this.http.post<ProdottoTagliaResultsDto>(this.acService.hostUrl + '/get-taglie', this.prodottoSelezionato);
    obs.subscribe(risposta => {
      this.taglieDisponibili = risposta.taglieDisponibili;
      this.taglieNonDisponibili = risposta.taglieNonDisponibili;

      this.checkDisponibili.length = this.taglieDisponibili.length;
      this.checkNonDisponibili.length = this.taglieNonDisponibili.length;
      console.log(risposta.taglieNonDisponibili);
    });
  }
  /**
   * Salva la taglia selezionata in area comune e si sposta sulla pagina per associare taglie e colori
   */
  colori() {
    this.acService.tagliaSelezionata = this.taglieDisponibili[this.checkDisponibili.findIndex(e => e == true)];
    console.log(this.acService.tagliaSelezionata);
    this.router.navigateByUrl('/taglie-colori');
  }

  /**
   * Disabilita le checkbox delle taglie disponibili e il pulsante della rimozione
   * in caso siano state selezionate taglie non disponibili
   */
  disableDisponibili() {
    let i = 0;
    // per ogni checkbox spuntata incrementa il contatore
    this.checkNonDisponibili.forEach(c => {
      if (c) {
        i++;
      }
    });
    // se maggiore o uguale a uno disabilita le checkbox delle taglie disponibili
    if (i >= 1) {
      this.dd = true;
    } else {
      this.dd = false;
    }
  }

  /**
   * Disabilita le checkbox delle taglie non disponibili e il pulsante dell'aggiunta
   * in caso siano state selezionate taglie disponibili, in più rende disponibile il pulsante per accedere
   * all'associazione dei colori
   */
  disableNonDisponibili() {
    let i = 0;
    this.checkDisponibili.forEach(c => {
      if (c) {
        i++;
      }
    });
    if (i > 1) {
      this.button = true;
      this.dnd = true;
    } else if (i == 1) {
      this.button = false;
      this.dnd = true;
    } else {
      this.button = true;
      this.dnd = false;
    }
  }
}
