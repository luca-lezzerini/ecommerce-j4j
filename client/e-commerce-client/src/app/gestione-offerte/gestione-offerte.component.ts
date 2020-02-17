import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gestione-offerte',
  templateUrl: './gestione-offerte.component.html',
  styleUrls: ['./gestione-offerte.component.css']
})
export class GestioneOfferteComponent implements OnInit {

  newPrice: number;
  inOfferta: boolean;

  constructor() { }

  ngOnInit() {
  }

  conferma() {

  }

  annulla() {
    // this.selezionaEnabled = false;
  }

}
