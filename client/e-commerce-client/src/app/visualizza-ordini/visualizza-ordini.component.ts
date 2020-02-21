import { OrdineSearchResultsDto } from './../classi/ordine-search-results-dto';
import { Observable } from 'rxjs';
import { OrdineSearchDto } from './../classi/ordine-search-dto';
import { Ordine } from './../classi/ordine';
import { Component, OnInit } from '@angular/core';
import { AreaComuneService } from '../area-comune.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-visualizza-ordini',
  templateUrl: './visualizza-ordini.component.html',
  styleUrls: ['./visualizza-ordini.component.css']
})
export class VisualizzaOrdiniComponent implements OnInit {

  ordini: Ordine[] = [];
  numeroOrdine: number;
  data: Date;
  showResults: boolean;
  stato: string;

  constructor(private http: HttpClient,
              private acService: AreaComuneService,
              private router: Router) { }

  ngOnInit() {
    // se l'utente non è loggato viene aperta la pagina di login
    if (!this.acService.token) {
      this.router.navigateByUrl('/login');
    }
  }

  searchOrdine() {
    // Tutte le ricerche vengono effettuate anche per stato che di default è "carrello"
    // Preparo il dto
    let dto: OrdineSearchDto = new OrdineSearchDto();
    dto.searchData = this.data;
    dto.searchNumeroOrdine = this.numeroOrdine;
    dto.stato = this.stato;
    dto.token = this.acService.token;

    // Preparo la richiesta http
    let oss: Observable<OrdineSearchResultsDto> =
      this.http.post<OrdineSearchResultsDto>(this.acService.hostUrl + '/search-ordini', dto);

    // Callback
    oss.subscribe(risposta => {
      // Aggiorno la lista di ordini
      this.ordini = risposta.results;
      // Se ci sono risultati li visualizzo
      this.showResults = risposta.results.length > 0;
      // Pulisco i campi di ricerca
      this.numeroOrdine = null;
      this.data = null;
      this.stato = 'carrello';
    });
  }
}
