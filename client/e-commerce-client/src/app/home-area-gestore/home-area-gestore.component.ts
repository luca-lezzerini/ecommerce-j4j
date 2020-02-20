import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AreaComuneService } from '../area-comune.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home-area-gestore',
  templateUrl: './home-area-gestore.component.html',
  styleUrls: ['./home-area-gestore.component.css']
})
export class HomeAreaGestoreComponent implements OnInit {

  constructor(private router: Router, private acService: AreaComuneService, private http: HttpClient) { }

  ngOnInit() {
  }

}
