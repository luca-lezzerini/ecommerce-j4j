import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AreaComuneService {

  token: string;
  doi: string;
  constructor() { }

  // const hostUrl: string = 'http://84.22.108.21.8080';
   readonly hostUrl: string = 'http://localhost:4200';


}
