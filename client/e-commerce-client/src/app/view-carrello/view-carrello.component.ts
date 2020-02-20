import { ViewCarrelloResponseDto } from './../classi/view-carrello-response-dto';
import { LoginResponseDto } from './../classi/login-response-dto';
import { Prodotto } from './../classi/prodotto';
import { Component, OnInit } from '@angular/core';
import { AreaComuneService } from '../area-comune.service';
import { HttpClient } from '@angular/common/http';
import { RigaOrdine } from '../classi/riga-ordine';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-view-carrello',
  templateUrl: './view-carrello.component.html',
  styleUrls: ['./view-carrello.component.css']
})
export class ViewCarrelloComponent implements OnInit {

  constructor(private http: HttpClient, private acService: AreaComuneService) {
    this.checkToken();
  }
  righeCarrello: RigaOrdine[] = [];
  nOrdine: string;
  nData: string;
  nTotale: number;

  ngOnInit() {
  }

  checkToken() {
    if (this.acService.token) {
      this.viewCarrello(this.acService.token);
    } else if (this.acService.tokenAnonimo) {
      this.viewCarrello(this.acService.tokenAnonimo);
    } else {
      console.log('token');
    }
  }

  viewCarrello(token: string) {
    // preparo dto
    const dto: LoginResponseDto = new LoginResponseDto();
    dto.token = token;

    // Preparo richiesta http
    const oss: Observable<ViewCarrelloResponseDto> =
      this.http.post<ViewCarrelloResponseDto>(this.acService.hostUrl + '/view-carrello', dto);

    // Invio la richiesta
    oss.subscribe(data => {
      console.log('ricevo la risposta');
      console.log(data.carrello);
      this.righeCarrello = data.carrello;
      this.nOrdine = '' + data.ordine.numero;
      this.nData = '' + data.ordine.data;
      this.nTotale = data.totale;
    }
    );
  }
}
