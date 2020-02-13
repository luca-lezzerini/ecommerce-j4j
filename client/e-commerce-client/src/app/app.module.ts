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

const appRoutes: Routes = [
  { path: 'home-pubblica', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registrazione', component: RegistrazioneComponent },
  { path: 'view-carrello', component: ViewCarrelloComponent },
  { path: 'registrazione-double-optin', component: RegistrazioneDoubleOptinComponent },
  {
    path: '',
    redirectTo: '/home-pubblica',
    pathMatch: 'full'
  },
  { path: 'password-dimenticata', component: PasswordDimenticataComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginComponent,
    RegistrazioneComponent,
    PasswordDimenticataComponent,
    ReimpostaPasswordComponent,
    ViewCarrelloComponent,
    RegistrazioneDoubleOptinComponent
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
