import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { PipesComponent } from './pipes/pipes.component';
import {NavComponent} from "./nav/nav.component";
import {RouterLink} from "@angular/router";
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";



@NgModule({
    declarations: [
        NavComponent,
        HeaderComponent,
        FooterComponent,
        PipesComponent,
        UnauthorizedComponent
    ],
    imports: [
        CommonModule,
        RouterLink,
        FontAwesomeModule
    ],
    exports: [
        NavComponent
    ]
})
export class SharedModule { }
