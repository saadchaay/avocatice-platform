import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardAvocatComponent } from './components/card-avocat/card-avocat.component';



@NgModule({
  declarations: [
    CardAvocatComponent
  ],
  exports: [
    CardAvocatComponent
  ],
  imports: [
    CommonModule
  ]
})
export class AvocatModule { }
