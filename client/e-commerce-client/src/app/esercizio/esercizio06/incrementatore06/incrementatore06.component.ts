import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-incrementatore06',
  templateUrl: './incrementatore06.component.html',
  styleUrls: ['./incrementatore06.component.css']
})
export class Incrementatore06Component implements OnInit {

  @Output() aumentato = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

  aumenta(numero: number) {
    // emette l'evento che fa aumentrare il numero in visualizzatore
   this.aumentato.emit(numero);
 }
}
