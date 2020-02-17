import { ColoriSearchResultsDto } from './../classi/colori-search-results-dto';
import { Observable } from 'rxjs';
import { ColoriSearchDto } from './../classi/colori-search-dto';
import { Colori } from './../classi/colori';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';

@Component({
  selector: 'app-elenco-colori',
  templateUrl: './elenco-colori.component.html',
  styleUrls: ['./elenco-colori.component.css']
})
export class ElencoColoriComponent implements OnInit {

  colori: Colori[] = [];
  searchKey = '';

  showResults: boolean;

  constructor(private http: HttpClient, private acService: AreaComuneService) { }

  ngOnInit() {
  }

  searchColori(){
    if(this.searchKey === ''){
    this.showResults = true;
    this.eseguiRicerca(this.searchKey);
    }else{
    this.eseguiRicerca(this.searchKey);
    }
  }

  eseguiRicerca(search: string){
    // Preparo il dto
    let dto: ColoriSearchDto = new ColoriSearchDto();
    dto.searchKey = this.searchKey;
    dto.token = this.acService.token;

    // Preparo la richiesta http
    let oss: Observable<ColoriSearchResultsDto> =
    this.http.post<ColoriSearchResultsDto>('http://localhost:8080/search-colori', dto);

    // Callback
    oss.subscribe(risposta => {
      // Aggiorno la lista dei colori
      this.colori = risposta.result;
      // Se ci sono risultati li visualizzo
      this.showResults = risposta.result.length > 0;
       // pulisco il campo ricerca
       this.searchKey = '';
    })
  }

}
