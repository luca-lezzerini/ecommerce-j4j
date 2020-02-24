import { Taglia } from './../classi/taglia';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { TagliaSearchDto } from '../classi/taglia-search-dto';
import { TagliaSearchResultsDto } from '../classi/taglia-search-results-dto';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-elenco-taglie',
  templateUrl: './elenco-taglie.component.html',
  styleUrls: ['./elenco-taglie.component.css']
})
export class ElencoTaglieComponent implements OnInit {

  taglie: Taglia[] = [];
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
    let dto: TagliaSearchDto = new TagliaSearchDto();
    dto.searchKey = search;
    dto.token = this.acService.token;
    dto.page = paginaRichiesta;

    // Preparo la richiesta http
    let oss: Observable<TagliaSearchResultsDto> =
      this.http.post<TagliaSearchResultsDto>(this.acService.hostUrl + '/search-taglia', dto);

    // Callback
    oss.subscribe(risposta => {
      // Aggiorno la lista delle taglie
      this.taglie = risposta.result;
      // Se ci sono risultati li visualizzo
      this.showResults = risposta.result.length > 0;
      // Aggiorno il numero di pagina
      this.pagina = risposta.page;
      // Se la pagina restituita è la numero 0, disabilito i bottoni per le pagine precedenti
      this.backDisabled = risposta.first;
      // Se la pagina restituita è l'ultima, disabilito i bottoni per le pagine successive
      this.forwardDisabled = risposta.last;
      // Salvo la chiave di ricerca
      this.searchKeyPrecedente = search;
      // Pulisco il campo ricerca
      this.searchKey = '';
    })
  }
}
