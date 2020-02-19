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
  data: Date;

  constructor(private http: HttpClient, private acService: AreaComuneService) { }

  ngOnInit() {
  }

  searchOrdine() {

  }

}
