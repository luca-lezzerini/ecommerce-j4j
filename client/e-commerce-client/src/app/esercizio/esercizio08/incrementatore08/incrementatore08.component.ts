import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-incrementatore08',
  templateUrl: './incrementatore08.component.html',
  styleUrls: ['./incrementatore08.component.css']
})
export class Incrementatore08Component implements OnInit {

  @Input() c: number;
  @Output() aggiunto = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

  add() {
    this.c++;
    this.aggiunto.emit(this.c);
  }

}
