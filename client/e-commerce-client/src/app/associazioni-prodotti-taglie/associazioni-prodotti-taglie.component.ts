import { ProdottoTagliaResultsDto } from './../classi/prodotto-taglia-results-dto';
import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { Router } from '@angular/router';
import { Taglia } from '../classi/taglia';
import { Observable } from 'rxjs';
import { ProdottoTagliaRequestDto } from '../classi/prodotto-taglia-request-dto';

@Component({
  selector: 'app-associazioni-prodotti-taglie',
  templateUrl: './associazioni-prodotti-taglie.component.html',
  styleUrls: ['./associazioni-prodotti-taglie.component.css']
})
export class AssociazioniProdottiTaglieComponent implements OnInit {

  prodotti: Prodotto[] = [];
  taglieSelezionate: Taglia[];
  taglieDisponibili: Taglia[];
  prodotto = new Prodotto();
  tagliaSelezionata = new Taglia();
  checkSelezionati: boolean[] = [];
  checkDisponibili: boolean[] = [];
  prodottoSelezionato: Prodotto;
  prodSel: boolean;

  constructor(private http: HttpClient,
    private acService: AreaComuneService,
    private router: Router) { }

  ngOnInit() {
    this.prodottoSelezionato = this.acService.prodottoSelezionato;
    this.getListe();
  }

  aggiungiASelezionate() {
    // let dto: ProdottoTagliaRequestDto = new ProdottoTagliaRequestDto();
    // console.log(this.checkDisponibili);
    // this.checkDisponibili.
    // dto.prodotto = this.prodottoSelezionato;

    // let obs: Observable<ProdottoTagliaResultsDto> =
    //   this.http.post<ProdottoTagliaResultsDto>(this.acService.hostUrl + '/add-taglia', dto);
    // obs.subscribe(risposta => {
    //   this.taglieSelezionate = risposta.taglieDisponibili;
    //   this.taglieDisponibili = risposta.taglieNonDisponibili;

    //   this.checkSelezionati.length = this.taglieSelezionate.length;
    //   this.checkDisponibili.length = this.taglieDisponibili.length;
    //   console.log(risposta.taglieNonDisponibili);
    // });
    // this.checkDisponibili = [];


  }

  togliASelezionate() {


  }

  esci() {

  }

  getListe() {
    let obs: Observable<ProdottoTagliaResultsDto> =
      this.http.post<ProdottoTagliaResultsDto>(this.acService.hostUrl + '/get-taglie', this.acService.prodottoSelezionato);
    obs.subscribe(risposta => {
      this.taglieSelezionate = risposta.taglieDisponibili;
      this.taglieDisponibili = risposta.taglieNonDisponibili;

      this.checkSelezionati.length = this.taglieSelezionate.length;
      this.checkDisponibili.length = this.taglieDisponibili.length;
      console.log(risposta.taglieNonDisponibili);
    })
  }
}
