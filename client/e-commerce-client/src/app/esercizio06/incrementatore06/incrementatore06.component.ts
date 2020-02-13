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

  aumenta(){
   this.aumentato.emit(1);
 }
}
