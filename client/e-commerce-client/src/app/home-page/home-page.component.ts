import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  prodotti: Prodotto [] = [];

  constructor() { }

  visualizzaCarrello() {

  }

  login() {

  }

  search() {

  }

  aggiungiAlCarrello() {

  }

  ngOnInit() {
  }

}
