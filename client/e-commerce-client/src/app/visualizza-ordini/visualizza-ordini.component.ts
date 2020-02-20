import { Ordine } from './../classi/ordine';
import { Component, OnInit } from '@angular/core';
import { AreaComuneService } from '../area-comune.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-visualizza-ordini',
  templateUrl: './visualizza-ordini.component.html',
  styleUrls: ['./visualizza-ordini.component.css']
})
export class VisualizzaOrdiniComponent implements OnInit {

  ordini: Ordine[] = [];
  numeroOrdine: string = '';
  data: string;
  showResults: boolean;
  stato: string;

  constructor(private http: HttpClient, private acService: AreaComuneService) { }

  ngOnInit() {
  }

  searchOrdine() {
    // Tutte le ricerche vengono effettuate anche per stato che di default è "carrello"
    // Se il campo di data è vuoto effettuo ricerca per numero
    if (this.data === '' && this.numeroOrdine != null && this.stato) {
      this.eseguiRicerca(this.numeroOrdine, this.data, this.stato);
    } else {
      // Se il campo di numero è vuoto eseguo ricerca per data
      if (this.data != null && this.numeroOrdine === '' && this.stato) {
        this.eseguiRicerca(this.data, this.numeroOrdine, this.stato);
      } else {
        // Se tutti i campi sono pieni eseguo ricerca per data e numero
        if (this.data != null && this.numeroOrdine != null && this.stato) {
          this.eseguiRicerca(this.data, this.numeroOrdine, this.stato);
        } else {
          // Se tutti i campi sono  vuoti eseguo ricerca solo per stato
          if (this.data === '' && this.numeroOrdine === '' && this.stato) {
            this.eseguiRicerca(this.data, this.numeroOrdine, this.stato);
          }
        }
      }
    }
  }


  eseguiRicerca(dataSearch: string, numeroSearch: string, stato: string) { };

}
