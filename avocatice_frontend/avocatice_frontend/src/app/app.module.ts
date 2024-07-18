import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {AuthModule} from "./auth/auth.module";
import {AvocatModule} from "./avocat/avocat.module";
import {AgentModule} from "./agent/agent.module";
import {ConsultationModule} from "./consultation/consultation.module";
import {HomeModule} from "./home/home.module";
import {SharedModule} from "./shared/shared.module";
import { HttpClientModule } from '@angular/common/http';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    AvocatModule,
    HomeModule,
    AgentModule,
    ConsultationModule,
    SharedModule,
    FontAwesomeModule


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
