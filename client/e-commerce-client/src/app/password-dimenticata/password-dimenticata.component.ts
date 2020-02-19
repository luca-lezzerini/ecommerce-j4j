import { AreaComuneService } from './../area-comune.service';
import { Router } from '@angular/router';
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
  doi: string;

  constructor(private http: HttpClient, private router: Router, private ac: AreaComuneService) { }

  ngOnInit() { }

  /**
   * invio il Dto contenente lo username al server e recupera il Double Optin dal server
   */
  passDimenticata() {
    // preparo i dati da inviare al server
    let dto: LoginRequestDto = new LoginRequestDto();
    dto.username = this.username;
    // preparo la richiesta http
    let obs: Observable<LoginResponseDto> = this.http.post<LoginResponseDto>(
      this.ac.hostUrl + '/password-dimenticata',
      dto
    );
    obs.subscribe(data => { // data.token è il doi generato dal service, NON LA SESSIONE
      this.doi = data.token;
    });
  }

  /**
   * controllo sul Double Optin per verificare la ricezione della mail
   */
  checkDoubleOptin() {
    // prepara i dati
    let dto: LoginResponseDto = new LoginResponseDto();
    dto.token = this.doi;

    // prepara la richiesta
    let obs: Observable<void> = this.http.post<void>(
      this.ac.hostUrl + '/check-double-optin',
      dto
    );

    obs.subscribe(data => {
      this.ac.doi = this.doi;
      // cambia se non riceve un errore
      if (this.ac.doi) {
        console.log(this.doi);
        this.router.navigateByUrl('/reimposta-password')
      }
    });
  }
}
