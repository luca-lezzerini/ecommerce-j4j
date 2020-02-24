import { LoginResponseDto } from './../classi/login-response-dto';
import { Observable } from 'rxjs';
import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProdottoSearchDto } from './../classi/prodotto-search-dto';
import { OrdineCreateDto } from './../classi/ordine-create-dto';
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
  ricerca = '';
  showResults: boolean;
  pagina = 0;

  constructor(private router: Router, private acService: AreaComuneService, private http: HttpClient) { }

  visualizzaCarrello() {
    this.router.navigateByUrl('/view-carrello');
    if (this.acService.token || this.acService.tokenAnonimo) {

    } else {

    }
  }
  search(pagina: number) {
    // Preparo i dto
    const dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.searchKey = this.ricerca;
    dto.token = this.acService.token;
    dto.numeroPagina = pagina;

    // Preparo richiesta http
    const oss: Observable<ProdottoSearchResultsDto> =
      this.http.post<ProdottoSearchResultsDto>(this.acService.hostUrl + '/search-prodotto-descrizione', dto);

    // Invio la richiesta
    oss.subscribe(risposta => {
      this.prodotti = risposta.result;
      this.pagina = risposta.numeroPagina;
      console.log(risposta.result);
      this.showResults = true; // this.prodotti.length > 0;
    });
  }


  /**
   * Verifica che l'utente sia loggato, se non ha effettuato l'accesso lo identifica come utente anonimo.
   * Chiama il metodo aggiungiAutenticatoAlCarrello passando il token.
   * @param p il prodotto da aggiungere
   */
  aggiungiAlCarrello(p: Prodotto) {
    if (this.acService.token) {
      this.aggiungiAutenticatoAlCarrello(p, this.acService.token);
    } else if (this.acService.tokenAnonimo) {
      this.aggiungiAutenticatoAlCarrello(p, this.acService.tokenAnonimo);
    } else {
      // chiamata per generare token anonimo

      // Preparo richiesta http
      const oss: Observable<LoginResponseDto> =
        this.http.post<LoginResponseDto>(this.acService.hostUrl + '/generate-token-anonimo', null);

      // Invio la richiesta
      oss.subscribe(data => {
        this.acService.tokenAnonimo = data.token;
        this.aggiungiAutenticatoAlCarrello(p, this.acService.tokenAnonimo);
        console.log(data.token);
      }
      );
    }

  }
  /**
   * Aggiunge il prodotto selezionato al carrello dell'utente autenticato o anonimo
   * @param p Il prodotto da aggiungere
   * @param token il token dell'utente, se registrato sarà ac.token, altriemnti sarà ac.tokenAnonimo
   */
  private aggiungiAutenticatoAlCarrello(p: Prodotto, token: string) {
    // Preparo i dto
    const dto: OrdineCreateDto = new OrdineCreateDto();
    dto.dati = p;
    dto.token = token;

    // Preparo richiesta http
    let oss: Observable<any> =
      this.http.post<any>(this.acService.hostUrl + '/add-carrello', dto);

    // Invio la richiesta
    oss.subscribe(data => {
      console.log('prodotto aggiunto al carrello');
    }
    );
  }


  ngOnInit() {
  }
  cambiaPagina(pagina: number) {
    if (pagina === -1) { pagina = 0; }
    this.search(pagina);
  }

}
