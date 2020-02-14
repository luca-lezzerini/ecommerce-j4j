import { Component, OnInit } from '@angular/core';
import { Prodotto } from '../classi/prodotto';

@Component({
  selector: 'app-anagrafica-prodotti',
  templateUrl: './anagrafica-prodotti.component.html',
  styleUrls: ['./anagrafica-prodotti.component.css']
})
export class AnagraficaProdottiComponent implements OnInit {

  codice: '';
  descrizione: '';
  prezzo: '';
  prodotti: Prodotto[] = [];
  search: '';
  showPanel: boolean;
  inputDisabled: boolean;
  showConferma: boolean;
  showAnnulla: boolean;
  showCrea: boolean;
  showModifica: boolean;
  showRimuovi: boolean;
  showSearchPanel: boolean;
  showResults: boolean;
  showAggiungi: boolean;
  trovatoQualcosa: boolean;
  venivoDaView: boolean;

  constructor() {
    this.showPanel = false;
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = false;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = true;
    this.showResults = false;
    this.showAggiungi = true;
  }

  ngOnInit() {

  }

  conferma() {
    this.showPanel = false;
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = true;
    this.showResults = this.trovatoQualcosa;
    this.showAggiungi = true;
  }

  annulla() {
    this.showPanel = this.venivoDaView;
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = true;
    this.showResults = true;
    this.showAggiungi = true;
  }

  crea() {
    this.showPanel = true;
    this.inputDisabled = false;
    this.showConferma = true;
    this.showAnnulla = true;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = false;
    this.showResults = false;
    this.showAggiungi = false;
  }

  modifica() {
    this.showPanel = true;
    this.inputDisabled = false;
    this.showConferma = true;
    this.showAnnulla = true;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = false;
    this.showResults = false;
    this.showAggiungi = false;
  }

  rimuovi() {
    this.showPanel = true;
    this.inputDisabled = true;
    this.showConferma = true;
    this.showAnnulla = true;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = true;
    this.showResults = false;
    this.showAggiungi = true;
  }

  cerca() {
    this.showPanel = false;
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = false;
    this.showModifica = false;
    this.showRimuovi = false;
    this.showSearchPanel = true;
    this.showResults = this.trovatoQualcosa;
    this.showAggiungi = true;
  }

  view() {
    this.showPanel = true;
    this.inputDisabled = true;
    this.showConferma = false;
    this.showAnnulla = false;
    this.showCrea = true;
    this.showModifica = true;
    this.showRimuovi = true;
    this.showSearchPanel = true;
    this.showResults = true;
    this.showAggiungi = true;
  }
}
