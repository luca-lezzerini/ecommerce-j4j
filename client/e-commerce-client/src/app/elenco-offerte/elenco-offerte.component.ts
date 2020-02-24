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
  ) { }

  prezzoSearch: number;
  prodotti: Prodotto[];
  msg: string;
  pagina = 1;

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
  ricerca(pagina: number) {

    // preparo i dati da inviare al service
    const dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.token = this.ac.token;
    // converte prezzoSearch a string in quanto searchKey è una stringa
    if (this.prezzoSearch === undefined || this.prezzoSearch === null) {
      dto.searchKey = Number.MAX_VALUE.toString();
    } else {
    dto.searchKey = this.prezzoSearch.toString();
    }
    dto.numeroPagina = pagina;
    // preparo la richesta http
    const obs: Observable<ProdottoSearchResultsDto> = this.http.post<ProdottoSearchResultsDto>
      (this.ac.hostUrl + '/search-offerte', dto);

    obs.subscribe(data => {
      this.pagina = data.numeroPagina;
      console.log(data.numeroPagina);
      if (data.result.length === 0) {
        this.msg = 'Nessun risultato trovato';
        this.prodotti = null;
      } else {
        this.prodotti = data.result;
        this.msg = null;
      }
    });
    this.prezzoSearch = null;
  }

  cambiaPagina(pagina: number) {
    if (pagina === -1) { pagina = 0; }
    this.ricerca(pagina);
  }
}
