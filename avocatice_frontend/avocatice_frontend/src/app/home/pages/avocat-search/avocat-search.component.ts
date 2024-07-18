import {Component, OnInit} from '@angular/core';
import {AvocatserviceService} from "../../../avocat/services/avocatservice.service";
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-avocat-search',
  templateUrl: './avocat-search.component.html',
  styleUrls: ['./avocat-search.component.css']
})
export class AvocatSearchComponent implements OnInit {
  villes = ['Casablanca','settat', 'Rabat', 'Marrakech', 'Fès', 'Tanger', 'Agadir', 'Meknès', 'Oujda', 'Kenitra', 'Tétouan'];
  specialties = ['Droit du travail', 'Droit pénal', 'Droit de la famille', 'Droit immobilier', 'Droit des affaires', 'Droit fiscal', 'Droit de la propriété intellectuelle', 'Droit international', 'Droit de l\'environnement', 'Droit des successions'];
  // data = {
  //   "nom": "",
  //   "prneom": "",
  //   "ville": "casa",
  //   "specialite": ""
  //
  // }

  data: any;
  avocat = {
    nomComplet: '',
    ville: '',
    specialite: '',

  };
  avocatSearch = {
    "prénom": "",
    "nom": "",
    "ville": "",
    "spécialité": ""
  }


  constructor(private avocatserviceService: AvocatserviceService, private keycloakService: KeycloakService) {

  }

  ngOnInit(): void {
    // this.getUserProfile();

    this.searchApi()
  }



  search() {
    let nomComplet = this.avocat.nomComplet.split(" ");
    this.avocatSearch = {
      "prénom": nomComplet[0],
      "nom": (nomComplet.length == 1) ? "" : nomComplet[nomComplet.length - 1],
      "ville": this.avocat.ville,
      "spécialité": this.avocat.specialite
    }
    this.searchApi();


    console.log(this.avocatSearch)
  }

  searchApi() {
    this.avocatserviceService.getSearchAvocats(this.avocatSearch).subscribe({
      next: (data) => {
        console.log(data)
        this.data = data;
      },
      complete: () => {
        console.log('Request complete.');
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

}
