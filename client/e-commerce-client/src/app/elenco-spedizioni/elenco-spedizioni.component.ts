import { Component, OnInit } from '@angular/core';
import { Spedizione } from '../classi/spedizione';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { ActivatedRoute } from '@angular/router';
import { SpedizioneSearchDto } from '../classi/spedizione-search-dto';
import { Observable } from 'rxjs';
import { SpedizioneSearchResultsDto } from '../classi/spedizione-search-results-dto';

@Component({
  selector: 'app-elenco-spedizioni',
  templateUrl: './elenco-spedizioni.component.html',
  styleUrls: ['./elenco-spedizioni.component.css']
})
export class ElencoSpedizioniComponent implements OnInit {
  codice: '';
  descrizione: '';
  prezzo: '';

  spedizioni: Spedizione[] = [];

  searchKey: '';
  idToDelete: number;

  searchPanelEnabled: boolean;
  cercaInputDisabled: boolean;

  cercaEnabled: boolean;
  resultsEnabled: boolean;
  homeEnabled: boolean;

  viewEnabled: boolean;
  editEnabled: boolean;
  deleteEnabled: boolean;


  constructor(private http: HttpClient, private singleton: AreaComuneService, private root: ActivatedRoute) { }

  ngOnInit() {
  }

  showResults() {
    this.resultsEnabled = true;
    this.viewEnabled = true;
    this.editEnabled = true;
    this.deleteEnabled = true;
  }

  cercaSpedizione() {    // CRUD read

    // DEBUG only
    console.log('Sono in cercaSpedizione');

    /*  codice da testare */
    // prepara la chiamata al server
    const dto: SpedizioneSearchDto = new SpedizioneSearchDto();
    dto.token = this.singleton.token;
    dto.searchKey = this.searchKey;
    const obs: Observable<SpedizioneSearchResultsDto> =
      this.http.post<SpedizioneSearchResultsDto>('http://localhost:8080/search-spedizione', dto);

    // invia la richiesta al server
    obs.subscribe(risposta => {
      this.spedizioni = risposta.result;
      if (this.spedizioni && this.spedizioni.length > 0) {
       // se trova qualcosa lo fa vedere
       this.showResults();
      } else {
        this.resultsEnabled = false;
      }
    });
  }
}
