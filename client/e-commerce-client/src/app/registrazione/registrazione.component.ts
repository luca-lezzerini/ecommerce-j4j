import { RegistrazioneResponseDto } from './../classi/registrazione-response-dto';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { RegistrazioneRequestDto } from '../classi/registrazione-request-dto';

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css']
})
export class RegistrazioneComponent implements OnInit {

  username = '';
  password = '';
  confermapassword = '';
  email = '';


  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit() {
  }

  registrami() {
    // verifica se i campi siano valorizzati
    if (this.username !== '' && this.password !== '' && this.confermapassword !== '' && this.email !== '') {
      // verifica che password e conferma password siano uguali
      if (this.password === this.confermapassword) {
        // prepara la richiesta http
        const dto: RegistrazioneRequestDto = new RegistrazioneRequestDto();
        dto.email = this.email;
        dto.password = this.password;
        dto.username = this.username;
        const obs: Observable<RegistrazioneResponseDto> =
          this.http.post<RegistrazioneResponseDto>('http://localhost:8080/registrami', dto);
        // crea la callback
        obs.subscribe(risposta => {
          if (risposta.registrato) {
            // se registrato è true l'utente è stato registrato e mostra messaggio di successo
            // TODO
          } else {
            // se registrato è false l'utente non è stato registrato e mostra messaggio di errore
            // TODO
          }
          this.router.navigateByUrl('registrazione-double-optin');
        });
      } else {
        // mostra messaggio di errore
        // TODO
      }
    } else {
      // mostra messaggio di errore
      // TODO
    }
  }

  annulla() {
    // pulisci i campi username, password, conferma password e email
    this.email = '';
    this.password = '';
    this.confermapassword = '';
    this.username = '';
    // torna alla home page
    this.router.navigateByUrl('home-pubblica');
  }
}
