import { ColoriSearchResultsDto } from './../classi/colori-search-results-dto';
import { Observable } from 'rxjs';
import { ColoriSearchDto } from './../classi/colori-search-dto';
import { Colori } from './../classi/colori';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-elenco-colori',
  templateUrl: './elenco-colori.component.html',
  styleUrls: ['./elenco-colori.component.css']
})
export class ElencoColoriComponent implements OnInit {

  colori: Colori[] = [];
  searchKey = '';

  showResults: boolean;

  constructor(private http: HttpClient,
    private acService: AreaComuneService,
    private router: Router) { }

  ngOnInit() {
    // se l'utente non è loggato viene aperta la pagina di login
    if (!this.acService.token) {
      this.router.navigateByUrl('/login');
    }
  }

  searchColori() {
    // Se la stringa di ricerca è vuota ritorna tutti gli elementi della lista
    if (this.searchKey === '') {
      this.showResults = true;
      this.eseguiRicerca(this.searchKey);
      // Se invece è piena esegue la ricerca
    } else {
      this.eseguiRicerca(this.searchKey);
    }
  }

  eseguiRicerca(search: string) {
    // Preparo il dto
    let dto: ColoriSearchDto = new ColoriSearchDto();
    dto.searchKey = this.searchKey;
    dto.token = this.acService.token;

    // Preparo la richiesta http
    let oss: Observable<ColoriSearchResultsDto> =
      this.http.post<ColoriSearchResultsDto>(this.acService.hostUrl + '/search-colori-per-descrizione', dto);

    // Callback
    oss.subscribe(risposta => {
      // Aggiorno la lista dei colori
      this.colori = risposta.result;
      // Se ci sono risultati li visualizzo
      this.showResults = risposta.result.length > 0;
      // pulisco il campo ricerca
      this.searchKey = '';
    })
  }

}
