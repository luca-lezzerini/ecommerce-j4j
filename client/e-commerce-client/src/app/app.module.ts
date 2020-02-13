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
<<<<<<< HEAD
import { DoubleOptinComponent } from './double-optin/double-optin.component';
import { HomeRiservataComponent } from './home-riservata/home-riservata.component';
=======
import { RegistrazioneDoubleOptinComponent } from './registrazione-double-optin/registrazione-double-optin.component';
>>>>>>> b1d31f19b5c2124b34828ca3ed9d7c85e8f0ef06

const appRoutes: Routes = [
  { path: 'home-pubblica', component: HomePageComponent },
  { path: 'home-riservata', component: HomeRiservataComponent },
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
<<<<<<< HEAD
    DoubleOptinComponent,
    HomeRiservataComponent
=======
    RegistrazioneDoubleOptinComponent
>>>>>>> b1d31f19b5c2124b34828ca3ed9d7c85e8f0ef06
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
