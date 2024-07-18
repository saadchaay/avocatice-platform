import {Injectable} from "@angular/core";
import {KeycloakProfile} from "keycloak-js";
import {KeycloakEventType, KeycloakService} from "keycloak-angular";
import {from, Observable} from "rxjs";

@Injectable({providedIn: "root"})
export class SecurityService {
  userProfile: any;

  constructor(private keycloakService: KeycloakService) {

  }

  getUserProfile(): Observable<any> {
    return from(this.keycloakService.loadUserProfile());

  }


  isLogged(): Observable<any> {
    return from(this.keycloakService.isLoggedIn());
  }


  async login() {
    return this.keycloakService.login({
      redirectUri: window.location.origin
    })
    // await th

  }
  onLogout(){
    this.keycloakService.logout(window.location.origin);
  }
}



