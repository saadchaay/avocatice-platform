import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ConsultationService {

  private CONSULTATION_ADD_ENDPOINT = "http://localhost:9991/consultation-service/api/consultation";
  private CONSULTATION_PER_CLIENT_ENDPOINT = "http://localhost:9991/consultation-service/api/consultation/client/";
  private CONSULTATION_PER_AVOCAT_ENDPOINT = "http://localhost:9991/consultation-service/api/consultation/avocat/";
  private CONSULTATION_VALIDATION_ENDPOINT = "http://localhost:9991/consultation-service/api/consultation/valider/";

  constructor(private httpClient: HttpClient, private router: Router, private keycloakService: KeycloakService) {
  }

  consultationAdd(consultationInfo: any) {
    return this.httpClient.post(this.CONSULTATION_ADD_ENDPOINT, consultationInfo)
  }

  consultatonByClient(clientID: any) {
    return this.httpClient.get(this.CONSULTATION_PER_CLIENT_ENDPOINT + clientID)
  }

  consultatonByAvocat(avocatID: any) {
    return this.httpClient.get(this.CONSULTATION_PER_AVOCAT_ENDPOINT + avocatID)
  }

  consultatonValider(consultationID: any) {
    return this.httpClient.get(this.CONSULTATION_VALIDATION_ENDPOINT + consultationID)
  }
}
