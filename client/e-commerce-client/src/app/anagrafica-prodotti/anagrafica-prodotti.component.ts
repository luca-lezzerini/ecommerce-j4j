import { Component, OnInit } from '@angular/core';
import { Prodotto } from '../classi/prodotto';

@Component({
  selector: 'app-anagrafica-prodotti',
  templateUrl: './anagrafica-prodotti.component.html',
  styleUrls: ['./anagrafica-prodotti.component.css']
})
export class AnagraficaProdottiComponent implements OnInit {

  codice: '';
  descrizione: '';
  prezzo: '';
  prodotti: Prodotto[] = [];
  search: '';

  constructor() { }

  ngOnInit() {
  }

  conferma() {

  }

  annulla() {

  }

  crea() {

  }

  modifica() {

  }

  rimuovi() {

  }

  cerca() {

  }

  view() {

  }
}
