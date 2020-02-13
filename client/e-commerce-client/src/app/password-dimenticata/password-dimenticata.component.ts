import { LoginRequestDto } from './../classi/login-request-dto';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginResponseDto } from '../classi/login-response-dto';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-password-dimenticata',
  templateUrl: './password-dimenticata.component.html',
  styleUrls: ['./password-dimenticata.component.css']
})
export class PasswordDimenticataComponent implements OnInit {

  username: string;
  password: string;
  token: string;

  constructor(private http: HttpClient) { }

  ngOnInit() { }

  // richiesta per password dimenticata
  passDimenticata() {
    // preparo i dati da inviare al server
    let dto: LoginRequestDto = new LoginRequestDto();
    dto.username = this.username;
    // preparo la richiesta http
    let obs: Observable<LoginResponseDto> = this.http.post<LoginResponseDto>(
      'http://localhost:8080/password-dimenticata',
      dto
    );
    obs.subscribe(data => {
      this.token = data.token;
    });
  }
  // controllo sul token per verificare la ricezione della mail
  checkDoubleOptin() {
    // prepara i dati
    let dto: LoginResponseDto = new LoginResponseDto();
    dto.token = this.token;

    // prepara la richiesta
    let obs: Observable<void> = this.http.post<void>(
      'http://localhost:8080/check-double-optin',
      dto
    );

    obs.subscribe(data => {});
  }
}
