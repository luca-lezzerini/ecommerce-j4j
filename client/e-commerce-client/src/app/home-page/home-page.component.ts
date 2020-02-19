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

  constructor(private router: Router, private acService: AreaComuneService, private http: HttpClient) { }

  visualizzaCarrello() {
    this.router.navigateByUrl('/view-carrello');
    if (this.acService.token || this.acService.tokenAnonimo) {
      this.(p, this.acService.token);
      //preparo la richiesta 
      this.aggiungiAutenticatoAlCarrello(p, this.acService.tokenAnonimo);
    } else {


  }

  search() {
    // Preparo i dto
    let dto: ProdottoSearchDto = new ProdottoSearchDto();
    dto.searchKey = this.ricerca;
    dto.token = this.acService.token;

    // Preparo richiesta http
    let oss: Observable<ProdottoSearchResultsDto> =
      this.http.post<ProdottoSearchResultsDto>(this.acService.hostUrl + '/search-prodotto-descrizione', dto);

    // Invio la richiesta
    oss.subscribe(risposta => {
      this.prodotti = risposta.result;
      console.log(risposta.result)
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
      let oss: Observable<LoginResponseDto> =
        this.http.post<LoginResponseDto>(this.acService.hostUrl + '/generate-token-anonimo', null);

      // Invio la richiesta
      oss.subscribe(data => {
        this.acService.tokenAnonimo = data.token;
        this.aggiungiAutenticatoAlCarrello(p, this.acService.tokenAnonimo);
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
    let dto: OrdineCreateDto = new OrdineCreateDto();
    dto.dati = p;
    dto.token = token;

    // Preparo richiesta http
    let oss: Observable<void> =
      this.http.post<void>(this.acService.hostUrl + '/add-carrello', dto);

    // Invio la richiesta
    oss.subscribe(data => {
      console.log('prodotto aggiunto al carrello');
    }
    );
  }


  ngOnInit() {
  }

}
