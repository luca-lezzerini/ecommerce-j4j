import { ProdottoSearchResultsDto } from './../classi/prodotto-search-results';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Prodotto } from '../classi/prodotto';
import { ProdottoSearchDto } from '../classi/prodotto-search-dto';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-elenco-offerte',
  templateUrl: './elenco-offerte.component.html',
  styleUrls: ['./elenco-offerte.component.css']
})
export class ElencoOfferteComponent implements OnInit {
  constructor(private http: HttpClient, private router: Router) { }
  prezzoSearch: string;
  prodotti: Prodotto[] = [];
  ngOnInit() { }
  ricerca() {

    // preparo i dati da inviare al service
    this.prezzoSearch += '&offerta';
    let dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.searchKey = this.prezzoSearch;

    // preparo la richesta http
    let obs: Observable<ProdottoSearchResultsDto> = this.http.post<ProdottoSearchResultsDto>('http://localhost:8080/search-prodotti', dto);
    obs.subscribe(data =>{
      this.prodotti = data.result;
    })
  }
}
