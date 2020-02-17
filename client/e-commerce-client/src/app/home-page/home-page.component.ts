import { Observable } from 'rxjs';
import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProdottoSearchDto } from './../classi/prodotto-search-dto';
import { ProdottoSearchResultsDto } from '../classi/prodotto-search-results';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';





@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  prodotti: Prodotto[] = [];
  ricerca: string;
  showResults: boolean;


  constructor(private router: Router, private acService: AreaComuneService, private http: HttpClient) { }

  visualizzaCarrello() {
    this.router.navigateByUrl('/view-carrello');
  }

  search() {
    // Preparo i dto
    let dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.searchKey = this.ricerca;
    dto.token = this.acService.token;

    // Preparo richiesta http
    let oss: Observable<ProdottoSearchResultsDto> =
      this.http.post<ProdottoSearchResultsDto>('http://localhost:8080/search-prodotto', dto);

    // Invio la richiesta
    oss.subscribe(risposta => {
      this.prodotti = risposta.results;
      this.showResults = this.prodotti.length > 0;
    });

  }

  aggiungiAlCarrello() {

  }

  ngOnInit() {
  }

}
