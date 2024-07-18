import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class AvocatserviceService {
  private AVOCAT_SEARCH_ENDPOINT = "http://localhost:9991/user-service/api/avocat_search";

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  getSearchAvocats(searchDto: any) {
    return this.httpClient.post(this.AVOCAT_SEARCH_ENDPOINT, searchDto)
  }
}
