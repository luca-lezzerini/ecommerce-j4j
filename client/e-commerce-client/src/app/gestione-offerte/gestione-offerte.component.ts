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

  searchCode: string;
  selezionaEnabled = false;
  prodotti: Prodotto [] = [];

  constructor(
    private http: HttpClient,
    private sessione: AreaComuneService
  ) {
    // FIXME: stub
    this.sessione.token = '123';
  }

  ngOnInit() {

  }

  cercaCodice() {
    const dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.searchKey = this.searchCode;
    dto.token = this.sessione.token;

    const obs: Observable<ProdottoSearchResultsDto> =
      this.http.post<ProdottoSearchResultsDto>('http://localhost:8080/search-prodotto', dto);


  }

  seleziona() {
    console.log("Seleziona");
  }
}
