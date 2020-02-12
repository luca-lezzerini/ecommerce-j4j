import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { LoginResponseDto } from '../classi/login-response-dto';
import { LoginRequestDto } from '../classi/login-request-dto';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  token: string;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  login(){
    // preparo i dati da inviare al server
    let lg: LoginRequestDto = new LoginRequestDto();
    lg.username = this.username;
    lg.password = this.password;

    // invio i dati al server
    let og: Observable<LoginResponseDto> =
      this.http.
      post<LoginResponseDto>('http://localhost:8080/login', lg);

    // creo al callback
    og.subscribe(data => {
      this.token = data.token;

    });
  }

  passwordDimenticata(){
    // preparo i dati da inviare al server
    let pd: LoginRequestDto = new LoginRequestDto();
    pd.username = this.username;
    pd.password = this.password;


    // invio i dati al server
    let op: Observable<LoginResponseDto> =
    this.http.
    post<LoginResponseDto>('http://localhost:8080/passwordDimenticata', pd);

    // creo al callback
    op.subscribe(data => {
      this.token = data.token;
    });
  }

}
