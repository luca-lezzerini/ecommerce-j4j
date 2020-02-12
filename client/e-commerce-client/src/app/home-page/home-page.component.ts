import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  prodotti: Prodotto [] = [];
  ricerca: string;

  constructor(private router: Router) { }

  visualizzaCarrello() {
    this.router.navigateByUrl('/view-carrello');
  }

  search() {

  }

  aggiungiAlCarrello() {

  }

  ngOnInit() {
  }

}
