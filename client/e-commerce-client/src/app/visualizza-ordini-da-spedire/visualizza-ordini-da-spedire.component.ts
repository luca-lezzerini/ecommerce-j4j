import { Router } from '@angular/router';
import { AreaComuneService } from './../area-comune.service';
import { HttpClient } from '@angular/common/http';
import { Ordine } from './../classi/ordine';
import { Component, OnInit } from '@angular/core';
import { OrdineSearchDto } from '../classi/ordine-search-dto';
import { OrdineSearchResultsDto } from '../classi/ordine-search-results-dto';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-visualizza-ordini-da-spedire',
  templateUrl: './visualizza-ordini-da-spedire.component.html',
  styleUrls: ['./visualizza-ordini-da-spedire.component.css']
})
export class VisualizzaOrdiniDaSpedireComponent implements OnInit {
  ordini: Ordine[] = [];

  ordineSelezionato: Ordine = new Ordine();

  showResults: boolean;
  searchKeyData: Date;
  searchKeyNumOrd: number;

  constructor(
    private http: HttpClient,
    private sessione: AreaComuneService,
    private router: Router,

  ) {

  }

  ngOnInit() {
    if (!this.sessione.token) {
      this.router.navigateByUrl('login');
    }
  }

  cerca() {
    // Se la stringa di ricerca è vuota ritorna tutti gli elementi della lista
    if (this.searchKeyData === null && !this.searchKeyNumOrd) {
      this.showResults = true;
      this.eseguiRicerca();
      // Se invece è piena esegue la ricerca
    } else {
      this.eseguiRicerca();
    }
  }

  eseguiRicerca() {
    // Preparo il dto
    let dto: OrdineSearchDto = new OrdineSearchDto();
    dto.searchKeyData = this.searchKeyData;
    dto.searchKeyNumeroOrdine = this.searchKeyNumOrd;
    dto.token = this.sessione.token;

    // Preparo la richiesta http
    let oss: Observable<OrdineSearchResultsDto> =
      this.http.post<OrdineSearchResultsDto>(this.sessione.hostUrl + '/search-ordini-da-spedire', dto);

    // Callback
    oss.subscribe(risposta => {
      console.log(this.searchKeyNumOrd);
      // Aggiorno la lista degli ordini
      this.ordini = risposta.results;
      // Se ci sono risultati li visualizzo
      this.showResults = risposta.results.length > 0;
      // pulisco il campo ricerca
      this.searchKeyData = null;
      this.searchKeyNumOrd = null;
    })
  }



  dettagli(ordine: Ordine) {
    this.ordineSelezionato = ordine;
  }

  spedisci(ordine: Ordine) {


  }
}
