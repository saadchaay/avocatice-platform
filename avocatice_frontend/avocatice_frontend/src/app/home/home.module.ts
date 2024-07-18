import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './pages/home/home.component';
import { DecouvrirComponent } from './pages/decouvrir/decouvrir.component';
import { AvocatSearchComponent } from './pages/avocat-search/avocat-search.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AvocatModule} from "../avocat/avocat.module";
import { ConsultationComponent } from './pages/consultation/consultation.component';
import { ClientConsultationComponent } from './pages/client-consultation/client-consultation.component';
import { AvocatConsultationComponent } from './pages/avocat-consultation/avocat-consultation.component';



@NgModule({
  declarations: [
    HomeComponent,
    DecouvrirComponent,
    AvocatSearchComponent,
    ConsultationComponent,
    ClientConsultationComponent,
    AvocatConsultationComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AvocatModule,
    ReactiveFormsModule
  ]
})
export class HomeModule { }
