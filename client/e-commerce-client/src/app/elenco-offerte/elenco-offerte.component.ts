import { AreaComuneService } from './../area-comune.service';
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
  constructor(
    private http: HttpClient,
    private router: Router,
    private ac: AreaComuneService
  ) {}

  prezzoSearch: number;
  prodotti: Prodotto[];
  msg: string;

  ngOnInit() {
    if (!this.ac.token) {
      this.router.navigateByUrl('login');
    }
  }
  /**
   * Effettua la ricerca per prezzo sui prodotti in offerta.
   * prezzoSearch è la chiave di ricerca, verranno tornati solo i prodotti con prezzo inferiore a quello dato.
   * Se il campo viene lasciato vuoto, cerca tutti i prodotti in offerta.
   */
  ricerca() {
    if (this.prezzoSearch === undefined || this.prezzoSearch === null) {
      this.prezzoSearch = Number.MAX_VALUE;
    }
    // preparo i dati da inviare al service
    const dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.token = this.ac.token;
    // converte prezzoSearch a string in quanto searchKey è una stringa
    dto.searchKey = this.prezzoSearch.toString();

    // preparo la richesta http
    const obs: Observable<ProdottoSearchResultsDto> = this.http.post<ProdottoSearchResultsDto>
      (this.ac.hostUrl + '/search-offerte', dto);

    obs.subscribe(data => {
      if (data.result.length == 0) {
        this.msg = 'Nessun risultato trovato';
        this.prodotti = null;
      } else {
        this.prodotti = data.result;
        this.msg = 'Risultati trovati: ' + data.result.length;
      }
    });
    this.prezzoSearch = null;
  }
}
