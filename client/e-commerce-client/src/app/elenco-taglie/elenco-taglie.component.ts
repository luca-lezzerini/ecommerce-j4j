import { Taglia } from './../classi/taglia';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AreaComuneService } from '../area-comune.service';
import { TagliaSearchDto } from '../classi/taglia-search-dto';
import { TagliaSearchResultsDto } from '../classi/taglia-search-results-dto';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-elenco-taglie',
  templateUrl: './elenco-taglie.component.html',
  styleUrls: ['./elenco-taglie.component.css']
})
export class ElencoTaglieComponent implements OnInit {

  taglie: Taglia[] = [];
  searchKey = '';
  showResults: boolean;

  constructor(private http: HttpClient, private acService: AreaComuneService) { }

  ngOnInit() {
  }

  searchTaglie(){
    if(this.searchKey === ''){
    this.showResults = true;
    this.eseguiRicerca(this.searchKey);
    }else{
    this.eseguiRicerca(this.searchKey);
    }
  }

  eseguiRicerca(search: string){
    // Preparo il dto
    let dto: TagliaSearchDto = new TagliaSearchDto();
    dto.searchKey = this.searchKey;
    dto.token = this.acService.token;

    // Preparo la richiesta http
    let oss: Observable<TagliaSearchResultsDto> =
    this.http.post<TagliaSearchResultsDto>('http://localhost:8080/search-taglia', dto);

    // Callback
    oss.subscribe(risposta => {
      // Aggiorno la lista dei colori
      this.taglie = risposta.result;
      // Se ci sono risultati li visualizzo
      this.showResults = risposta.result.length > 0;
       // pulisco il campo ricerca
       this.searchKey = '';
    })
  }

}
