import { Router } from '@angular/router';
import { AreaComuneService } from './../area-comune.service';
import { HttpClient } from '@angular/common/http';
import { Ordine } from './../classi/ordine';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-visualizza-ordini-da-spedire',
  templateUrl: './visualizza-ordini-da-spedire.component.html',
  styleUrls: ['./visualizza-ordini-da-spedire.component.css']
})
export class VisualizzaOrdiniDaSpedireComponent implements OnInit 

{ordini: Ordine[] = [];

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
