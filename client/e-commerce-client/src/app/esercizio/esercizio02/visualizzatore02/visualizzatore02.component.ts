import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-visualizzatore02',
  templateUrl: './visualizzatore02.component.html',
  styleUrls: ['./visualizzatore02.component.css']
})
export class Visualizzatore02Component implements OnInit {

  c = 0;

  constructor() { }

  ngOnInit() {
  }

  inc(contatore: number) {
    this.c = contatore;
  }

}
