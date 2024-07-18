import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {FormGroup} from "@angular/forms";
import {KeycloakService} from "keycloak-angular";

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private CLIENT_REGISTER_ENDPOINT = "http://localhost:9991/user-service/api/client";
  private AVOCAT_REGISTER_ENDPOINT = "http://localhost:9991/user-service/api/avocat";

  constructor(private httpClient:HttpClient,private router:Router,private keycloakService: KeycloakService) { }
  registerClient(registerForm: FormGroup){
    return this.httpClient.post(this.CLIENT_REGISTER_ENDPOINT,registerForm.value)
  }
  registerAvocat(registerForm: FormGroup){
    return this.httpClient.post(this.AVOCAT_REGISTER_ENDPOINT,registerForm.value)
  }

  getToken(): Promise<string> {
    return this.keycloakService.getToken();
  }

  getUsername(): string {
    return this.keycloakService.getUsername();
  }
  async isLoggedIn(): Promise<boolean> {
    return await this.keycloakService.isLoggedIn();
  }

}
