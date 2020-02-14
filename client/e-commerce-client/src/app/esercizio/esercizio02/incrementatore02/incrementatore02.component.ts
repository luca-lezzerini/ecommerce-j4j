import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-incrementatore02',
  templateUrl: './incrementatore02.component.html',
  styleUrls: ['./incrementatore02.component.css']
})
export class Incrementatore02Component implements OnInit {

  @Input() c: number;
  @Output() incrementato = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

  incrementa() {
    this.c++;
    this.incrementato.emit(this.c);
  }

}
