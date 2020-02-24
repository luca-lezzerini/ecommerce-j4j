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
  stato = 'Carrello';
  numeroPrecedente: number;
  dataPrecedente: Date;
  showResultsPrecedente: boolean;
  statoPrecedente = '';
  pagina = 0;
  backDisabled: boolean;
  forwardDisabled: boolean;

  constructor(private http: HttpClient,
    private acService: AreaComuneService,
    private router: Router) { }

  ngOnInit() {
    // se l'utente non è loggato viene aperta la pagina di login
    if (!this.acService.token) {
      this.router.navigateByUrl('/login');
    }
  }

  /**
   * Esegue la ricerca, imposta la visibilità dei risultati e abilita i bottoni per cambiare pagina
   *
   * @param numeroSearch: string contenente il numero di ordine da cercare
   *
   * @param dataSearch: la data da cercare
   *
   * @param statoSearch: lo stato dell'ordine di cui effettuare la ricerca
   *
   * @param paginaRichiesta: il numero di pagina da cercare
   */
  searchOrdine(numeroSearch: number, dataSearch: Date, statoSearch: string, paginaRichiesta: number) {
    // Preparo il dto
    let dto: OrdineSearchDto = new OrdineSearchDto();
    dto.searchData = dataSearch;
    dto.searchNumeroOrdine = numeroSearch;
    dto.stato = statoSearch;
    dto.token = this.acService.token;
    dto.page = paginaRichiesta;

    // Preparo la richiesta http
    let oss: Observable<OrdineSearchResultsDto> =
      this.http.post<OrdineSearchResultsDto>(this.acService.hostUrl + '/search-ordini', dto);

    // Callback
    oss.subscribe(risposta => {
      // Aggiorno la lista di ordini
      this.ordini = risposta.results;
      // Se ci sono risultati li visualizzo
      this.showResults = risposta.results.length > 0;
      // Aggiorno il numero di pagina
      this.pagina = risposta.page;
      // Se la pagina restituita è la numero 0, disabilito i bottoni per le pagine precedenti
      this.backDisabled = !risposta.page;
      // Se la pagina restituita è l'ultima, disabilito i bottoni per le pagine successive
      this.forwardDisabled = risposta.ultimaPagina;
      // Salvo la chiave di ricerca
      this.numeroPrecedente = numeroSearch;
      this.dataPrecedente = dataSearch;
      this.statoPrecedente = statoSearch;
      // Pulisco i campi di ricerca
      this.numeroOrdine = null;
      this.data = null;
      this.stato = 'carrello';
    });
  }
}
