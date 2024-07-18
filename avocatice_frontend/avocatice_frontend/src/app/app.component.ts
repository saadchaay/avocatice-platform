import {Component} from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Router, RouterState, RouterStateSnapshot} from "@angular/router";
import {map, Observable} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'avocatice_frontend';
  private currentRouteString: any;
  private currentUrls: string;

  constructor(private router: Router, private route: ActivatedRoute) {

    // this.currentRouteString = this.route && this.route['_routerState'] && this.route['_routerState']['snapshot'] && this.route['_routerState']['snapshot']?.url;
    const state: RouterState = router.routerState;
    const snapshot: RouterStateSnapshot = state.snapshot;
    const root: ActivatedRouteSnapshot = snapshot.root;
    const child = root.firstChild;

    const routerState = router.routerState.snapshot as RouterStateSnapshot;
    this.currentUrls = routerState.url;
    // console.log(this.route['_routerState']['snapshot'].url);
    // console.log(window.location.href);
    console.log(snapshot.root.children);

    console.log(this.currentUrls);
    console.log(routerState);

  }


}
