import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { LoginResponseDto } from '../classi/login-response-dto';
import { LoginRequestDto } from '../classi/login-request-dto';
import { AreaComuneService } from '../area-comune.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  token: string;

  constructor(
    private http: HttpClient,
    private ac: AreaComuneService,
    private router: Router
  ) { }

  ngOnInit() { }

  login() {
    if (this.username && this.password) {
      // preparo i dati da inviare al server
      const lg: LoginRequestDto = new LoginRequestDto();
      lg.username = this.username;
      lg.password = this.password;
      if (this.ac.tokenAnonimo) {
        lg.token = this.ac.tokenAnonimo;
      }


      // invio i dati al server
      const og: Observable<LoginResponseDto> = this.http.post<LoginResponseDto>(this.ac.hostUrl + '/login', lg);

      // creo la callback
      og.subscribe(data => {
        console.log(data);
        this.ac.token = data.token;
        if (data.token) {
          this.router.navigateByUrl('/home-riservata');
        } else {
          this.username = '';
          this.password = '';
        }
      });
    }
  }

  passwordDimenticata() {
    // preparo i dati da inviare al server
    const pd: LoginRequestDto = new LoginRequestDto();
    pd.username = this.username;
    pd.password = this.password;

    // invio i dati al server
    const op: Observable<LoginResponseDto> = this.http.post<LoginResponseDto>(this.ac.hostUrl + '/passwordDimenticata', pd);

    // creo al callback
    op.subscribe(data => {
      this.token = data.token;
    });
  }
}
