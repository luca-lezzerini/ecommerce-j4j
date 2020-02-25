import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';
import { Taglia } from '../classi/taglia';

@Component({
  selector: 'app-taglie-colori',
  templateUrl: './taglie-colori.component.html',
  styleUrls: ['./taglie-colori.component.css']
})
export class TaglieColoriComponent implements OnInit {

  prodotto: Prodotto;
  taglia: Taglia;
  constructor() { }

  ngOnInit() {
  }

  esci(){
    console.log("uscita");
  }
}
