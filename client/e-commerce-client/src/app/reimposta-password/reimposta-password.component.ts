import { AreaComuneService } from './../area-comune.service';
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
  doiCode: string;
  err: string;

  constructor(private http: HttpClient, private ac: AreaComuneService) { }
  ngOnInit() { }

  /**
   * Genera il Double Optin e imposta la nuova Password
   * Se le due password inserite non coincidono genera un errore e blanka i campi
   */
  conferma() {
    if (this.nuovaPassword === this.confermaPassword) {
      // preparo i dati da inviare
      this.doiCode = this.ac.doi;
      let dto: ChangePasswordRequestDto = new ChangePasswordRequestDto();
      // il doi verrà preso dalla pagina precedente del client(codice DoubleOptin)
      dto.doiCode = this.doiCode;
      dto.newPassword = this.nuovaPassword;
      // preparo la richiesta
      let cp: Observable<void> = this.http.post<void>(this.ac.hostUrl + '/reimposta-password', dto);
      cp.subscribe(data => {
       });

    } else {
      this.err = 'Le due password non coincidono!';
      this.nuovaPassword = '';
      this.confermaPassword = '';
    }
  }
}
