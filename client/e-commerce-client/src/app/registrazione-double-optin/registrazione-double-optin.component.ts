import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-registrazione-double-optin',
  templateUrl: './registrazione-double-optin.component.html',
  styleUrls: ['./registrazione-double-optin.component.css']
})
export class RegistrazioneDoubleOptinComponent implements OnInit {

  constructor(private http: HttpClient) {
    this.checkDoubleOptin();
  }

  ngOnInit() {
  }
  checkDoubleOptin(){
    //const obs: Observable<> = http.post<>('' + d);
  }
}
