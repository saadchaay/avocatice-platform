import {Component, OnInit} from '@angular/core';
import {ConsultationService} from "../../../consultation/services/consultation.service";
import {SecurityService} from "../../../auth/service/security.service";

@Component({
  selector: 'app-client-consultation',
  templateUrl: './client-consultation.component.html',
  styleUrls: ['./client-consultation.component.css']
})
export class ClientConsultationComponent implements OnInit {
   data: any;

  constructor(private consultationService: ConsultationService, private securityService: SecurityService) {
  }

  ngOnInit(): void {
    this.searchApi()
  }

  searchApi() {


    this.securityService.getUserProfile().subscribe((data: any) => {
      // this.data = data

      this.consultationService.consultatonByClient(data.id).subscribe({
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

}
