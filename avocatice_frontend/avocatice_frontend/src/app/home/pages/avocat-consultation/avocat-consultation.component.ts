import {Component} from '@angular/core';
import {ConsultationService} from "../../../consultation/services/consultation.service";
import {SecurityService} from "../../../auth/service/security.service";

@Component({
  selector: 'app-avocat-consultation',
  templateUrl: './avocat-consultation.component.html',
  styleUrls: ['./avocat-consultation.component.css']
})
export class AvocatConsultationComponent {
  data: any;

  constructor(private consultationService: ConsultationService, private securityService: SecurityService) {
  }

  ngOnInit(): void {
    this.searchApi()
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
    this.consultationService.consultatonValider(id).subscribe({
      next: (data) => {

      },
      complete: () => {
        this.searchApi();
        console.log('Request complete.');
      },
      error: (error) => {
        console.log(error);
      }
    })
  }
}
