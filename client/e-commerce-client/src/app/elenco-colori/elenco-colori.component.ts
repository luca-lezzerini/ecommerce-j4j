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
  searchKeyPrecedente = '';
  showResults: boolean;
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
   * @param search: la string contenente la chiave di ricerca
   *
   * @param paginaRichiesta: il numero di pagina da cercare
   */
  eseguiRicerca(search: string, paginaRichiesta: number) {
    // Preparo il dto
    let dto: ColoriSearchDto = new ColoriSearchDto();
    dto.searchKey = search;
    dto.token = this.acService.token;
    dto.numeroPagina = paginaRichiesta;

    // Preparo la richiesta http
    let oss: Observable<ColoriSearchResultsDto> =
      this.http.post<ColoriSearchResultsDto>(this.acService.hostUrl + '/search-colori-per-descrizione', dto);

    // Callback
    oss.subscribe(risposta => {
      // Aggiorno la lista dei colori
      this.colori = risposta.result;
      // Se ci sono risultati li visualizzo
      this.showResults = risposta.result.length > 0;
      // Aggiorno il numero di pagina
      this.pagina = risposta.numeroPagina;
      // Se la pagina restituita è la numero 0, disabilito i bottoni per le pagine precedenti
      this.backDisabled = !risposta.numeroPagina;
      // Se la pagina restituita è l'ultima, disabilito i bottoni per le pagine successive
      this.forwardDisabled = risposta.ultimaPagina;
      // Salvo la chiave di ricerca
      this.searchKeyPrecedente = search;
      // Pulisco il campo ricerca
      this.searchKey = '';
    })
  }
}
