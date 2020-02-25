import { TagliaColoriResponseDto } from './../classi/taglia-colori-response-dto';
import { Colori } from './../classi/colori';
import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';
import { Taglia } from '../classi/taglia';
import { Router } from '@angular/router';
import { AreaComuneService } from '../area-comune.service';
import { HttpClient } from '@angular/common/http';
import { TagliaColoriRequestDto } from '../classi/taglia-colori-request-dto';
import { TagliaColoriUpdateDto } from '../classi/taglia-colori-update-dto';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-taglie-colori',
  templateUrl: './taglie-colori.component.html',
  styleUrls: ['./taglie-colori.component.css']
})
export class TaglieColoriComponent implements OnInit {
  // TODO: eliminare commenti
  //prodotto: Prodotto = { id: 1, codice: '1', offerta: true, righe: null, descrizione: 'scarpe', prezzo: 50 };
  //taglia: Taglia = { id: 1, codice: '1', descrizione: 'S' };

  // TODO: svuota array
  coloriAssociati: Colori[] = [{ id: 1, codice: '1', descrizione: 'rosso' },
  { id: 2, codice: '2', descrizione: 'cocco' }];
  coloriNonAssociati: Colori[] = [{ id: 3, codice: '3', descrizione: 'verde' },
  { id: 4, codice: '4', descrizione: 'blu' }];
  checkAssociati: boolean[] = [false, false];
  checkNonAssociati: boolean[] = [false, false];

  abilitaAssociati: boolean;
  abilitaNonAssociati: boolean;

  constructor(
    private http: HttpClient,
    private acService: AreaComuneService,
    private router: Router
  ) { }

  ngOnInit() {
    let dto = new TagliaColoriRequestDto();
    dto.token = this.acService.token;
    dto.prodotto = this.acService.prodottoSelezionato;
    dto.taglia = this.acService.tagliaSelezionata;

    // prepara la richiesta HTTP
    const oss: Observable<TagliaColoriResponseDto> =
      this.http.post<TagliaColoriResponseDto>(this.acService.hostUrl + '/aggiungi-taglia-colori', dto);

    // invio la richiesta
    oss.subscribe(risposta => {

      this.coloriAssociati = risposta.listaColoriAssociati;
      this.coloriNonAssociati = risposta.listaColori.filter(colore => !risposta.listaColoriAssociati.includes(colore));
    });
  }

  aggiungi() {
    let dto = new TagliaColoriUpdateDto();
    dto.token = this.acService.token;
    dto.prodotto = this.acService.prodottoSelezionato;
    dto.taglia = this.acService.tagliaSelezionata;
    dto.coloriSelezionati = this.coloriNonAssociati.filter((colore, index) => this.checkNonAssociati[index]);

    // prepara la richiesta HTTP
    const oss: Observable<TagliaColoriResponseDto> =
      this.http.post<TagliaColoriResponseDto>(this.acService.hostUrl + '/aggiungi-taglia-colori', dto);

    // invio la richiesta
    oss.subscribe(risposta => {

      // una volta eseguito l'inserimento, eseguo di nuovo l'ultima ricerca effettuata
      this.coloriAssociati = risposta.listaColoriAssociati;
      this.coloriNonAssociati = risposta.listaColori.filter(colore => !risposta.listaColoriAssociati.includes(colore));
    });
  }


  rimuovi() {
    let dto = new TagliaColoriUpdateDto();
    dto.token = this.acService.token;
    dto.prodotto = this.acService.prodottoSelezionato;
    dto.taglia = this.acService.tagliaSelezionata;
    // this.coloriNonAssociati.forEach((colore, index) => { if (this.checkNonAssociati[index]) { dto.coloriSelezionati.push(colore); } });
    dto.coloriSelezionati = this.coloriAssociati.filter((colore, index) => this.checkAssociati[index]);

    // prepara la richiesta HTTP
    const oss: Observable<TagliaColoriResponseDto> =
      this.http.post<TagliaColoriResponseDto>(this.acService.hostUrl + '/rimuovi-taglia-colori', dto);

    // invio la richiesta
    oss.subscribe(risposta => {

      this.coloriAssociati = risposta.listaColoriAssociati;
      this.coloriNonAssociati = risposta.listaColori.filter(colore => !risposta.listaColoriAssociati.includes(colore));
    });
  }

  aggiornaAssociati() {
    this.abilitaNonAssociati = false;
    this.checkAssociati.forEach(c => {
      if (c) {
        this.abilitaNonAssociati = true;
      }
    });
  }

  aggiornaNonAssociati() {
    this.abilitaAssociati = false;
    this.checkNonAssociati.forEach(c => {
      if (c) {
        this.abilitaAssociati = true;
      }
    });
  }

  esci() {
    console.log('uscita');
  }
}
