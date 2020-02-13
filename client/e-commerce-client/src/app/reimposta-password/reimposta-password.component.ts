import { Observable } from 'rxjs';
import { ChangePasswordRequestDto } from './../classi/change-password-request-dto';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reimposta-password',
  templateUrl: './reimposta-password.component.html',
  styleUrls: ['./reimposta-password.component.css']
})
export class ReimpostaPasswordComponent implements OnInit {
  nuovaPassword: string;
  confermaPassword: string;
  doi: string;
  err: string;
  constructor(private http: HttpClient) { }

  ngOnInit() { }
  // invio richiesta al server di cambio password
  conferma() {
    if (this.nuovaPassword === this.confermaPassword) {
      // preparo i dati da inviare

      let dto: ChangePasswordRequestDto = new ChangePasswordRequestDto();
      // il doi verrà preso dalla pagina precedente del client(codice DoubleOptin)
      dto.doi = this.doi;
      dto.password = this.nuovaPassword;
      // preparo la richiesta
      let cp: Observable<void> = this.http.post<void>('http://localhost:8080/reimposta-password', dto);
      cp.subscribe(data => { });

    } else {
      this.err = 'Le due password non coincidono!';
      this.nuovaPassword = '';
      this.confermaPassword = '';
    }
  }
}
