import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
<<<<<<< HEAD
import { HomePageComponent } from './home-page/home-page.component';
=======
import { RegistrazioneComponent } from './registrazione/registrazione.component';
import { PasswordDimenticataComponent } from './password-dimenticata/password-dimenticata.component';
>>>>>>> 5aa34f00a247c0ada26a8d1bdd2931a7fedbc44a

@NgModule({
  declarations: [
    AppComponent,
<<<<<<< HEAD
    HomePageComponent
=======
    RegistrazioneComponent,
    PasswordDimenticataComponent
>>>>>>> 5aa34f00a247c0ada26a8d1bdd2931a7fedbc44a
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
