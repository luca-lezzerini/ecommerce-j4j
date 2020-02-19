import { AreaComuneService } from './../area-comune.service';
import { RegistrazioneResponseDto } from './../classi/registrazione-response-dto';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-registrazione-double-optin',
  templateUrl: './registrazione-double-optin.component.html',
  styleUrls: ['./registrazione-double-optin.component.css']
})
export class RegistrazioneDoubleOptinComponent implements OnInit {

  codiceDoubleOptin: string;

  constructor(private http: HttpClient, private ac: AreaComuneService) {
    this.checkDoubleOptin();
  }

  ngOnInit() {
  }

  checkDoubleOptin() {

    const obs: Observable<RegistrazioneResponseDto> =
      this.http.post<RegistrazioneResponseDto>(this.ac.hostUrl + '/check-double-optin', this.codiceDoubleOptin);
  }

}
