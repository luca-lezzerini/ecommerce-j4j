import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-visualizzatore06',
  templateUrl: './visualizzatore06.component.html',
  styleUrls: ['./visualizzatore06.component.css']
})
export class Visualizzatore06Component implements OnInit {

  @Input() numero: number;

  constructor() { }

  ngOnInit() {
  }

  onAumenta(numero: number){}
}
