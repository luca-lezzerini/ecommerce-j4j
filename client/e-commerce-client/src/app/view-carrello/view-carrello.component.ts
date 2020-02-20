import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';
import { AreaComuneService } from '../area-comune.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-view-carrello',
  templateUrl: './view-carrello.component.html',
  styleUrls: ['./view-carrello.component.css']
})
export class ViewCarrelloComponent implements OnInit {

  constructor(private http: HttpClient, private ac: AreaComuneService) { }
  : Prodotto[] = [];
  nOrdine: string;
  nData: string;
  nTotale: string;


  ngOnInit() {
  }

}
