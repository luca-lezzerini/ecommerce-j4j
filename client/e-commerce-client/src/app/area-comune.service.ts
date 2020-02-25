import { Injectable } from '@angular/core';
import { Prodotto } from './classi/prodotto';
import { Taglia } from './classi/taglia';

@Injectable({
  providedIn: 'root'
})
export class AreaComuneService {

  token: string;
  doi: string;
  tokenAnonimo: string;
  prodottoSelezionato = new Prodotto();
  tagliaSelezionata = new Taglia();
  constructor() { }

  // const hostUrl: string = 'http://84.22.108.21.8080';
 public readonly hostUrl: string = 'http://localhost:8080';


}
