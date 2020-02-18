import { Spedizione } from './../classi/spedizione';
import { Component, OnInit } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { ActivatedRoute } from '@angular/router';
import { SpedizioneSearchDto } from '../classi/spedizione-search-dto';
import { Observable } from 'rxjs';
import { SpedizioneSearchResultsDto } from '../classi/spedizione-search-results-dto';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-elenco-spedizioni',
  templateUrl: './elenco-spedizioni.component.html',
  styleUrls: ['./elenco-spedizioni.component.css']
})
export class ElencoSpedizioniComponent implements OnInit {
  spedizioni: Spedizione[] = [];
  searchKey = '';
  showResults: boolean;
  prezzo: number;

  constructor(private http: HttpClient, private singleton: AreaComuneService) {

  }

  ngOnInit() {
  }

  searchSpedizione() {
    console.log('siamo in search spedizione');
    if (this.searchKey === '') {
      this.showResults = true;
      this.eseguiRicerca(this.searchKey);
    } else {
      this.eseguiRicerca(this.searchKey);
    }
  }

  eseguiRicerca(search: string) {
    console.log('siamo in esegui ricerca');
    // Preparo il dto
    const dto: SpedizioneSearchDto = new SpedizioneSearchDto();
    dto.searchKey = this.searchKey;
    dto.token = this.singleton.token;

    // Preparo la richiesta http
    const obs: Observable<SpedizioneSearchResultsDto> =
      this.http.post<SpedizioneSearchResultsDto>('http://localhost:8080/search-spedizione', dto);

    // Callback
    obs.subscribe(risposta => {
      console.log('siamo in observable'); //FIX ME : Entra in observable ma non elabora risposta
      // Aggiorno la lista spedizione
      this.spedizioni = risposta.result;
      // Se ci sono risultati li visualizzo
      this.showResults = risposta.result.length > 0;
      // pulisco il campo ricerca
      this.searchKey = '';
    });
  }
}
