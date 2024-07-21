import {Component} from '@angular/core';
import {ConsultationService} from "../../../consultation/services/consultation.service";
import {SecurityService} from "../../../auth/service/security.service";

@Component({
  selector: 'app-avocat-consultation',
  templateUrl: './avocat-consultation.component.html',
  styleUrls: ['./avocat-consultation.component.css']
})
export class AvocatConsultationComponent {
  // data: any;
  status: boolean = false;
  data: any = [
    {
      id: 208,
      sjt_consultation: "je veuu",
      type: "Ecrite",
      reponse: "false",
      date_creneau: "2024-07-20 01:00:00.0",
      creneau: {
        id: 1,
        heureDebut: 8,
        heureFin: 9,
        consultations: [],
      },
      clientId: "d7de2b94-94e3-4a8c-9a0f-16c8d835baba",
      avocat: null,
      id_creneaux: 1,
      avocatId: "752a7325-a846-4fa1-9566-efb7d51a9d89",
      client: {
        id: null,
        email: "clientOne@gmail.com",
        nom: "client",
        prenom: "clientOne",
        ville: "Casablanca"
      },
    },
  ];

  constructor(private consultationService: ConsultationService, private securityService: SecurityService) {
  }

  ngOnInit(): void {
    // this.searchApi()
  }

  searchApi() {


    this.securityService.getUserProfile().subscribe((data: any) => {
      // this.data = data

      this.consultationService.consultatonByAvocat(data.id).subscribe({
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


    });


  }

  validerConsultation(id: any) {
    this.status = true;
    // this.consultationService.consultatonValider(id).subscribe({
    //   next: (data) => {
    //
    //   },
    //   complete: () => {
    //     this.searchApi();
    //     console.log('Request complete.');
    //   },
    //   error: (error) => {
    //     console.log(error);
    //   }
    // })
  }
}
