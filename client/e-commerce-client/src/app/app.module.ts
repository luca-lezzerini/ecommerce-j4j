import { AnagraficaProdottiComponent } from './anagrafica-prodotti/anagrafica-prodotti.component';
import { AnagraficaTaglieComponent } from './anagrafica-taglie/anagrafica-taglie.component';
import { AnagraficaSpedizioniComponent } from './anagrafica-spedizioni/anagrafica-spedizioni.component';
import { HomeRiservataComponent } from './home-riservata/home-riservata.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { HomePageComponent } from './home-page/home-page.component';
import { LoginComponent } from './login/login.component';
import { RegistrazioneComponent } from './registrazione/registrazione.component';
import { PasswordDimenticataComponent } from './password-dimenticata/password-dimenticata.component';
import { ReimpostaPasswordComponent } from './reimposta-password/reimposta-password.component';
import { Routes, RouterModule } from '@angular/router';
import { ViewCarrelloComponent } from './view-carrello/view-carrello.component';
import { RegistrazioneDoubleOptinComponent } from './registrazione-double-optin/registrazione-double-optin.component';
import { RegistrazioneSuccessoComponent } from './registrazione-successo/registrazione-successo.component';
import { RegistrazioneErroreComponent } from './registrazione-errore/registrazione-errore.component';
import { Visualizzatore06Component } from './esercizio/esercizio06/visualizzatore06/visualizzatore06.component';
import { Incrementatore06Component } from './esercizio/esercizio06/incrementatore06/incrementatore06.component';
import { Visualizzatore02Component } from './esercizio/esercizio02/visualizzatore02/visualizzatore02.component';
import { Incrementatore02Component } from './esercizio/esercizio02/incrementatore02/incrementatore02.component';
import { Visualizzatore08Component } from './esercizio/esercizio08/visualizzatore08/visualizzatore08.component';
import { Incrementatore08Component } from './esercizio/esercizio08/incrementatore08/incrementatore08.component';

const appRoutes: Routes = [
  { path: 'anagrafica-prodotti', component: AnagraficaProdottiComponent },
  { path: 'anagrafica-spedizioni', component: AnagraficaSpedizioniComponent },
  { path: 'home-pubblica', component: HomePageComponent },
  { path: 'home-riservata', component: HomeRiservataComponent },
  { path: 'login', component: LoginComponent },
  { path: 'anagrafica-taglie', component: AnagraficaTaglieComponent },
  { path: 'registrazione', component: RegistrazioneComponent },
  { path: 'view-carrello', component: ViewCarrelloComponent },
  { path: 'registrazione-double-optin', component: RegistrazioneDoubleOptinComponent },
  { path: 'registrazione-successo', component: RegistrazioneSuccessoComponent },
  { path: 'registrazione-errore', component: RegistrazioneErroreComponent },
  { path: 'visualizzatore02', component: Visualizzatore02Component },
  { path: 'visualizzatore08', component: Visualizzatore08Component },
  {
    path: '',
    redirectTo: '/home-pubblica',
    pathMatch: 'full'
  },
  { path: 'password-dimenticata', component: PasswordDimenticataComponent },
  { path: 'reimposta-password', component: ReimpostaPasswordComponent },
  { path: 'esercizio06', component: Visualizzatore06Component }
];

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    HomeRiservataComponent,
    LoginComponent,
    RegistrazioneComponent,
    PasswordDimenticataComponent,
    ReimpostaPasswordComponent,
    ViewCarrelloComponent,
    RegistrazioneDoubleOptinComponent,
    RegistrazioneSuccessoComponent,
    RegistrazioneErroreComponent,
    AnagraficaTaglieComponent,
    AnagraficaProdottiComponent,
    AnagraficaSpedizioniComponent,
    Visualizzatore06Component,
    Incrementatore06Component,
    Visualizzatore02Component,
    Incrementatore02Component,
    Visualizzatore08Component,
    Incrementatore08Component,
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    ),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
