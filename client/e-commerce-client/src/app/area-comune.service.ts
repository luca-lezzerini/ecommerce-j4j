import { Injectable } from '@angular/core';
import { Prodotto } from './classi/prodotto';

@Injectable({
  providedIn: 'root'
})
export class AreaComuneService {

  token: string;
  doi: string;
  tokenAnonimo: string;
  prodottoSelezionato = new Prodotto();
  constructor() { }

  // const hostUrl: string = 'http://84.22.108.21.8080';
 public readonly hostUrl: string = 'http://localhost:8080';


}
