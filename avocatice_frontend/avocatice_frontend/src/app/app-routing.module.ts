import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {KeycloakAngularModule} from "keycloak-angular";
import {HOME_ROUTES} from "./home/home-routing.module";
import {AUTH_MODULE} from "./auth/auth-routing.module"
const routes: Routes = [
  {path: '', children: HOME_ROUTES},
  {path:'register',children:AUTH_MODULE}
];

@NgModule({
  imports: [RouterModule.forRoot(routes), KeycloakAngularModule,
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
