import {Component, Input} from '@angular/core';
import {AvocatModel} from "../../models/avocatModel";
import {Router} from "@angular/router";

@Component({
  selector: 'app-card-avocat',
  templateUrl: './card-avocat.component.html',
  styleUrls: ['./card-avocat.component.css']
})
export class CardAvocatComponent {

  @Input() data: any;
  constructor(private router: Router) {}

  redirectToOtherComponent(data:any) {
    this.router.navigate(['/consultation'], { queryParams: data });
  }
}
