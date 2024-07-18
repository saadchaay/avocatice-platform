import {APP_INITIALIZER, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {KeycloakService} from "keycloak-angular";
import { RegistrationComponent } from './registration/registration.component';
import { AvocatComponent } from './register/avocat/avocat.component';
import { ClientComponent } from './register/client/client.component';
import {ReactiveFormsModule} from "@angular/forms";


function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'avocatice-realm',
        clientId: 'users-client'
      },
      initOptions: {
        onLoad: 'check-sso',
        checkLoginIframe : true,

      }
    });
}

@NgModule({
  declarations: [
    RegistrationComponent,
    AvocatComponent,
    ClientComponent
  ],
    imports: [
        CommonModule,
        ReactiveFormsModule
    ],
  providers: [{
    provide: APP_INITIALIZER,
    useFactory: initializeKeycloak,
    multi: true,
    deps: [KeycloakService]
  }]
})
export class AuthModule {
}
