import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-visualizzatore08',
  templateUrl: './visualizzatore08.component.html',
  styleUrls: ['./visualizzatore08.component.css']
})
export class Visualizzatore08Component implements OnInit {

  c = 0;

  constructor() { }

  ngOnInit() {
  }

  inc(contatore: number) {
    this.c = contatore;
  }

}
