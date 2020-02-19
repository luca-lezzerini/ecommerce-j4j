import { Ordine } from './../classi/ordine';
import { Router } from '@angular/router';
import { AreaComuneService } from './../area-comune.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-ordini-da-spedire',
  templateUrl: './ordini-da-spedire.component.html',
  styleUrls: ['./ordini-da-spedire.component.css']
})
export class OrdiniDaSpedireComponent implements OnInit {

  ordini: Ordine[] = [];

  numeroOrdine: string;
  data: string;
  ordineSelezionato: Ordine = new Ordine();


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

  cerca(){

  }

  dettagli(ordine: Ordine){
    this.ordineSelezionato = ordine; 
  }

  spedisci(ordine: Ordine){

  }
}

