import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ColoriCreateDto } from '../classi/colori-create-dto';
import { ColoriUpdateDto } from '../classi/colori-update-dto';
import { ColoriSearchDto } from '../classi/colori-search-dto';
import { ColoriSearchResultsDto } from '../classi/colori-search-results-dto';
import { ColoriDeleteDto } from '../classi/colori-delete-dto';
import { Colori } from '../classi/colori';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-anagrafica-colori',
  templateUrl: './anagrafica-colori.component.html',
  styleUrls: ['./anagrafica-colori.component.css']
})
export class AnagraficaColoriComponent implements OnInit {
  codice: string;
  descrizione: string;
  colore: Colori;
  ricerca: string;
  result: Colori[];

  panelHidden: boolean;
  inputEditable: boolean;
  confermaHidden: boolean;
  annullaHidden: boolean;
  creaHidden: boolean;
  modificaHidden: boolean;
  rimuoviHidden: boolean;
  cercaHidden: boolean;
  risultatiHidden: boolean;
  aggiungiHidden: boolean;

  constructor(private http: HttpClient) {}

  ngOnInit() {}

  createColori(): void {
    // preparo i dati da inviare al server
    let dto: ColoriCreateDto = new ColoriCreateDto();
    this.colore.codice = this.codice;
    this.colore.descrizione = this.descrizione;
    dto.dati = this.colore;
    // preparo la richiesta http
    let obs: Observable<void> = this.http.post<void>(
      'http://localhost:8080/create-colori',
      dto
    );
    obs.subscribe(data => {});
  }
  searchColori(): void {
    // preparo i dati da inviare al server
    let dto: ColoriSearchDto = new ColoriSearchDto();
    dto.searchKey = this.ricerca;
    // preparo la richiesta http
    let obs: Observable<ColoriSearchResultsDto> = this.http.post<
      ColoriSearchResultsDto
    >('http://localhost:8080/search-colori', dto);
    obs.subscribe(data => {
      this.result = data.result;
    });
  }
  deleteColori(): void {
    // preparo i dati da inviare al server
    let dto: ColoriDeleteDto = new ColoriDeleteDto();
    dto.idToDelete = this.colore.id;
    // preparo la richiesta http
    let obs: Observable<void> = this.http.post<void>(
      'http://localhost:8080/delete-colori',
      dto
    );
    obs.subscribe(data => {});
  }
  updateColori(): void {
        // preparo i dati da inviare al server
        let dto: ColoriUpdateDto = new ColoriUpdateDto();
        dto.dati = this.colore;
        // preparo la richiesta http
        let obs: Observable<void> = this.http.post<void>(
          'http://localhost:8080/update-colori',
          dto
        );
        obs.subscribe(data => {});
  }
}
