import { ProdottoTagliaResultsDto } from './../classi/prodotto-taglia-results-dto';
import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { Router } from '@angular/router';
import { Taglia } from '../classi/taglia';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-associazioni-prodotti-taglie',
  templateUrl: './associazioni-prodotti-taglie.component.html',
  styleUrls: ['./associazioni-prodotti-taglie.component.css']
})
export class AssociazioniProdottiTaglieComponent implements OnInit {

  prodotti: Prodotto[] = [];
  taglieSelezionate: Taglia[] = [];
  taglieDisponibili: Taglia[] = [];
  prodotto = new Prodotto();
  tagliaSelezionata = new Taglia();
  checkSelezionati: boolean[];
  checkDisponibili: boolean[];
  prodottoSelezionato : Prodotto;
  prodSel: boolean;

  constructor(private http: HttpClient,
    private acService: AreaComuneService,
    private router: Router) { }

  ngOnInit() {
    this.prodottoSelezionato = this.acService.prodottoSelezionato;
    this.getListe();
  }

  aggiungiASelezionate(taglieDisponibili: Taglia) {

    // for()


    // // Preparo il dto
    // let dto: ProdottoTagliaGetDto = new ProdottoTagliaGetDto();
    // dto.taglia = this.tagliaSelezionata;
    // dto.token = this.acService.token;
    // dto.prodotto= this.prodotto;

    // // Preparo la richiesta http
    // let oss: Observable<ProdottoTagliaGetDto> =
    //   this.http.post<ProdottoTagliaGetDto>(this.acService.hostUrl + '/get-taglie', dto);

    // // Callback
    // oss.subscribe(risposta => {
    //   // Aggiorno la lista delle taglie
    //   this.taglia = risposta.result;

    // })

  }

  togliASelezionate() {

  }

  esci() {

  }

  getListe() {
    let obs: Observable<ProdottoTagliaResultsDto> =
      this.http.post<ProdottoTagliaResultsDto>(this.acService.hostUrl + '/get-taglie', this.prodottoSelezionato);
    obs.subscribe(risposta => {
      this.taglieSelezionate = risposta.listaSelezionati;
      this.taglieDisponibili = risposta.listaDisponibili;
      console.log(this.taglieDisponibili);
    })
  }
}
