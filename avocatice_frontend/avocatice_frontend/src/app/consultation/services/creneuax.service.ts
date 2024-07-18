import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class CreneuaxService {

  private CRENEAUX_SEARCH_ENDPOINT = "http://localhost:9991/consultation-service/api/crenaux/";

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  getCreneaux(date: any) {
    return this.httpClient.get(this.CRENEAUX_SEARCH_ENDPOINT + date)
  }
}
