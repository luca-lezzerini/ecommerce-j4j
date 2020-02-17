import { ProdottoSearchResultsDto } from './../classi/prodotto-search-results';
import { Observable } from 'rxjs';
import { AreaComuneService } from './../area-comune.service';
import { HttpClient } from '@angular/common/http';
import { ProdottoSearchDto } from './../classi/prodotto-search-dto';
import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gestione-offerte',
  templateUrl: './gestione-offerte.component.html',
  styleUrls: ['./gestione-offerte.component.css']
})
export class GestioneOfferteComponent implements OnInit {

  newPrice: number;
  inOfferta: boolean;
  searchCode: string;
  selezionaEnabled = false;
  prodotti: Prodotto[] = [];

  constructor(
    private http: HttpClient,
    private sessione: AreaComuneService
  ) {
    // FIXME: stub
    this.sessione.token = '123';
  }

  ngOnInit() {

  }

  conferma() {

  }

  annulla() {
    this.selezionaEnabled = false;
  }

  cercaCodice() {
    // prepara richiesta http
    const dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.searchKey = this.searchCode;
    dto.token = this.sessione.token;
    // preparo richiesta al server
    const obs: Observable<ProdottoSearchResultsDto> =
      this.http.post<ProdottoSearchResultsDto>('http://localhost:8080/search-prodotto', dto);
    // invio richiesta al server
    obs.subscribe(response => {
      if (response) {
        this.prodotti = response.results;
      }
    });
  }

  seleziona() {
    this.selezionaEnabled = true;
  }
}
