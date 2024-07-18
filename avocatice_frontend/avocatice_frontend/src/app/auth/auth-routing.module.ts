import {Routes} from "@angular/router";
import {RegistrationComponent} from "./registration/registration.component";
import {AvocatComponent} from "./register/avocat/avocat.component";
import {ClientComponent} from "./register/client/client.component";


export const AUTH_MODULE: Routes = [
  {path: '', component: ClientComponent},
  {path: 'avocat', component:AvocatComponent },
  {path: 'client', component: ClientComponent},

  // { path: '**', pathMatch: 'full',
  //   component: NotFoundComponent },

];
