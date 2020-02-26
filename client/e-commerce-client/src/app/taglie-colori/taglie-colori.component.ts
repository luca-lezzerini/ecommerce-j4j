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
  coloriAssociati: Colori[] = [];
  coloriNonAssociati: Colori[] = [];
  checkAssociati: boolean[] = [];
  checkNonAssociati: boolean[] = [];
  prodotto: Prodotto;
  taglia: Taglia;
  abilitaAssociati: boolean;
  abilitaNonAssociati: boolean;

  constructor(
    private http: HttpClient,
    private acService: AreaComuneService,
    private router: Router
  ) { }

  ngOnInit() {
    this.taglia = this.acService.tagliaSelezionata;
    this.prodotto = this.acService.prodottoSelezionato;
    const dto = new TagliaColoriRequestDto();
    dto.token = this.acService.token;
    dto.prodotto = this.acService.prodottoSelezionato;
    dto.taglia = this.acService.tagliaSelezionata;

    // prepara la richiesta HTTP
    const oss: Observable<TagliaColoriResponseDto> =
      this.http.post<TagliaColoriResponseDto>(this.acService.hostUrl + '/richiedi-taglia-colori', dto);

    // invio la richiesta
    oss.subscribe(risposta => { this.filtraNonAssociati(risposta); });
  }

  aggiungi() {
    const dto = new TagliaColoriUpdateDto();
    dto.token = this.acService.token;
    dto.prodotto = this.acService.prodottoSelezionato;
    dto.taglia = this.acService.tagliaSelezionata;
    dto.coloriSelezionati = this.coloriNonAssociati.filter((colore, index) => this.checkNonAssociati[index]);

    // prepara la richiesta HTTP
    const oss: Observable<TagliaColoriResponseDto> =
      this.http.post<TagliaColoriResponseDto>(this.acService.hostUrl + '/aggiungi-taglia-colori', dto);

    // invio la richiesta
    oss.subscribe(risposta => { this.filtraNonAssociati(risposta); });
  }

  filtraNonAssociati(risposta: TagliaColoriResponseDto) {

    this.coloriAssociati = risposta.listaColoriAssociati;
    this.coloriNonAssociati = [];
    risposta.listaColori.forEach(colore => {
      let trovato = false;
      risposta.listaColoriAssociati.forEach(c => {
        if (c.id === colore.id) {
          trovato = true;
        }
      });
      if (!trovato) {
        this.coloriNonAssociati.push(colore);
      }
    });
    this.checkNonAssociati = [];
    this.coloriNonAssociati.forEach(x => this.checkNonAssociati.push(false));
    this.checkAssociati = [];
    this.coloriAssociati.forEach(x => this.checkAssociati.push(false));
    this.aggiornaAssociati();
    this.aggiornaNonAssociati();
  }

  rimuovi() {
    const dto = new TagliaColoriUpdateDto();
    dto.token = this.acService.token;
    dto.prodotto = this.acService.prodottoSelezionato;
    dto.taglia = this.acService.tagliaSelezionata;
    // this.coloriNonAssociati.forEach((colore, index) => { if (this.checkNonAssociati[index]) { dto.coloriSelezionati.push(colore); } });
    dto.coloriSelezionati = this.coloriAssociati.filter((colore, index) => this.checkAssociati[index]);

    // prepara la richiesta HTTP
    const oss: Observable<TagliaColoriResponseDto> =
      this.http.post<TagliaColoriResponseDto>(this.acService.hostUrl + '/rimuovi-taglia-colori', dto);

    // invio la richiesta
    oss.subscribe(risposta => { this.filtraNonAssociati(risposta); });
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
    this.router.navigateByUrl('associazioni-prodotti-taglie');
  }
}
