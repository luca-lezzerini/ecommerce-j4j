import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css']
})
export class RegistrazioneComponent implements OnInit {

  username: string;
  password: string;
  confermapassword: string;
  email: string;


  constructor() { }

  ngOnInit() {
  }

  registrami() {
    // verifica se i campi siano valorizzati
    // verifica che password e conferma password siano uguali
    // prepara la richiesta http
    // crea la callback
    // se registrato è true l'utente è stato registrato e mostra messaggio di successo
    // se registrato è false l'utente non è stato registrato e mostra messaggio di errore
  }

  annulla() {
    // pulisci i campi username, password, conferma password e email
    // torna alla home page
  }
}
