import {Routes} from "@angular/router";
import {HomeComponent} from "./pages/home/home.component";
import {DecouvrirComponent} from "./pages/decouvrir/decouvrir.component";
import {AvocatSearchComponent} from "./pages/avocat-search/avocat-search.component";
import {AuthGuard} from "../shared/guards/guards.component";
import {UnauthorizedComponent} from "../shared/unauthorized/unauthorized.component";
import {ConsultationComponent} from "./pages/consultation/consultation.component";
import {ClientConsultationComponent} from "./pages/client-consultation/client-consultation.component";
import {AvocatConsultationComponent} from "./pages/avocat-consultation/avocat-consultation.component";

export const HOME_ROUTES: Routes = [
  {path: '', component: HomeComponent},
  {path: 'découvrir', component: DecouvrirComponent},
  {path: 'avocats', component: AvocatSearchComponent, canActivate: [AuthGuard]},
  {path: 'consultation', component: ConsultationComponent, canActivate: [AuthGuard]},
  {path: 'profile', component: ClientConsultationComponent, canActivate: [AuthGuard]},
  {path: 'panel', component: AvocatConsultationComponent, canActivate: [AuthGuard]},
  {path: 'unauthorized', component: UnauthorizedComponent},

  // { path: '**', pathMatch: 'full',
  //   component: NotFoundComponent },
];
