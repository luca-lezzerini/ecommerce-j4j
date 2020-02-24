import { OrdineSendDto } from './../classi/ordine-send-dto';
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

  searchKeyData: Date;
  searchKeyNumOrd: number;
  paginaAttuale: number;
  backDisabled: boolean;
  forwardDisabled: boolean;

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
    this.eseguiRicerca(0);
  }

  eseguiRicerca(paginaRichiesta: number) {

    // Preparo il dto
    const dto: OrdineSearchDto = new OrdineSearchDto();
    dto.searchData = this.searchKeyData;
    dto.searchNumeroOrdine = this.searchKeyNumOrd;
    dto.page = paginaRichiesta;
    dto.token = this.sessione.token;
    console.log(dto.page);
    // Preparo la richiesta http
    const oss: Observable<OrdineSearchResultsDto> =
      this.http.post<OrdineSearchResultsDto>(this.sessione.hostUrl + '/search-ordini-da-spedire', dto);

    // Callback
    oss.subscribe(risposta => {
      // Aggiorno la lista degli ordini
      this.ordini = risposta.results;
      // pulisco il campo ricerca
      this.searchKeyData = null;
      this.searchKeyNumOrd = null;
      // assegno valore della pagina
      this.paginaAttuale = risposta.page;
      console.log(risposta.page);
      // Se la pagina restituita è la numero 0, disabilito i bottoni per le pagine precedenti
      this.backDisabled = !risposta.page;
      // Se la pagina restituita è l'ultima, disabilito i bottoni per le pagine successive
      this.forwardDisabled = risposta.ultimaPagina;
    });
  }



  dettagli(ordine: Ordine) {
    this.ordineSelezionato = ordine;
  }

  spedisci(ordine: Ordine) {
    this.ordineSelezionato = ordine;
    const dto: OrdineSendDto = new OrdineSendDto();

    // Preparo la richiesta http
    const oss: Observable<any> =
      this.http.post<any>(this.sessione.hostUrl + '/spedisci-ordine', dto);

    // Callback
    oss.subscribe(risposta => {
      this.eseguiRicerca(0);
    });
  }
}
