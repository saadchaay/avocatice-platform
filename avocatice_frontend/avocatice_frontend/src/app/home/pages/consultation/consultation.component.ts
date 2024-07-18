import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CreneuaxService} from "../../../consultation/services/creneuax.service";
import {DatePipe} from "@angular/common";
import {SecurityService} from "../../../auth/service/security.service";
import {ConsultationService} from "../../../consultation/services/consultation.service";

@Component({
  selector: 'app-consultation',
  templateUrl: './consultation.component.html',
  styleUrls: ['./consultation.component.css'],
  providers: [DatePipe],
})
export class ConsultationComponent implements OnInit {
  registerForm: any;

  date = new Date();
  data: any;
  formattedDate: any;

  consultationInfo = {
    "avocatId": "",
    "clientId": "",
    "date_créneau": "",
    "sjt_consultation": "",
    "type": "",
    "id_creneaux": ""
  }
   done: boolean = false;

  constructor(private route: ActivatedRoute, private creneuaxService: CreneuaxService, private datePipe: DatePipe, private kc: SecurityService, private consultationService: ConsultationService) {

  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      console.log(params)
      this.consultationInfo.avocatId = params['email'];
    });
    this.formattedDate = this.datePipe.transform(this.date, 'dd/MM/yyyy', 'fr');


    console.log(this.formattedDate)
    this.searchApi()
  }

  registerAvocat() {

  }

  searchApi() {
    this.formattedDate = this.datePipe.transform(this.date, 'dd-MM-yyyy', 'fr');

    this.creneuaxService.getCreneaux(this.formattedDate).subscribe({
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


  getCrenaux() {
    this.searchApi()
  }


  validerConsultation() {
    this.kc.getUserProfile().subscribe((data: any) => {
      // this.data = data
      this.consultationInfo.date_créneau = this.formattedDate
      this.consultationInfo.clientId = data.id;
      this.consultationService.consultationAdd(this.consultationInfo).subscribe({
        complete: () => {
          this.done = true;
        },
        error: (error) => {
          console.log(error);
        }
      })


    });

    this.kc.getUserProfile()


  }
}
