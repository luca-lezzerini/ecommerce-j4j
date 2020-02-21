import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AreaComuneService {

  token: string;
  doi: string;
  tokenAnonimo: string;
  constructor() { }

  // const hostUrl: string = 'http://84.22.108.21.8080';
 public readonly hostUrl: string = 'http://localhost:8080';


}
