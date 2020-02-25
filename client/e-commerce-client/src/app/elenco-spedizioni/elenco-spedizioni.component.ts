import { Spedizione } from './../classi/spedizione';
import { Component, OnInit } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { ActivatedRoute } from '@angular/router';

import { Observable } from 'rxjs';
import { SpedizioneSearchResultsDto } from '../classi/spedizione-search-results-dto';
import { NgIf } from '@angular/common';
import { SpedizioneSearchPrezzoDto } from '../classi/spedizione-search-prezzo-dto';
import { SpedizioneSearchPrezzoResultsDto } from '../classi/spedizione-search-prezzo-results-dto';

@Component({
  selector: 'app-elenco-spedizioni',
  templateUrl: './elenco-spedizioni.component.html',
  styleUrls: ['./elenco-spedizioni.component.css']
})
export class ElencoSpedizioniComponent implements OnInit {
  spedizioni: Spedizione[] = [];
  searchKey: number;
  showResults: boolean;
  prezzo: number;
  tableHeaderEnabled: boolean;
  pagina = 0;
  paginazione: boolean;

  constructor(private http: HttpClient, private singleton: AreaComuneService) {

  }

  ngOnInit() {
  }
  cambiaPagina(pagina: number) {
    if (pagina === -1) { pagina = 0; }
    this.searchSpedizione(pagina);
  }

  searchSpedizione(pagina: number,) {
    this.paginazione = true;

    if (this.searchKey === null) {
      this.showResults = true;
      this.eseguiRicerca(this.searchKey);
    } else {
      this.showResults = true;
      this.eseguiRicerca(this.searchKey);
    }
  }

  eseguiRicerca(search: number) {
    this.tableHeaderEnabled = true;
    // console.log('siamo in esegui ricerca per prezzo');
    // Preparo il dto
    const dto: SpedizioneSearchPrezzoDto = new SpedizioneSearchPrezzoDto();
    dto.searchKey = this.searchKey;
    dto.token = this.singleton.token;

    // Preparo la richiesta http
    const obs: Observable<SpedizioneSearchPrezzoResultsDto> =
      this.http.post<SpedizioneSearchPrezzoResultsDto>(this.singleton.hostUrl + '/search-prezzo-spedizione', dto);

    // Callback
    obs.subscribe(risposta => {
      // console.log('siamo in observable'); //FIX ME : Entra in observable ma non elabora risposta
      // Aggiorno la lista spedizione
      // console.log(this.spedizioni);
      // console.log(risposta);
      this.spedizioni = risposta.result;
      // console.log(this.spedizioni);
      // Se ci sono risultati li visualizzo
      this.showResults = risposta.result.length > 0;
      // pulisco il campo ricerca
      this.searchKey = null;
    });
  }
}
