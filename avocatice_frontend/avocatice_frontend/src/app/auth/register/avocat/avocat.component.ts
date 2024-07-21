import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserServiceService} from "../../service/user-service.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: 'app-avocat',
  templateUrl: './avocat.component.html',
  styleUrls: ['./avocat.component.css']
})
export class AvocatComponent {
  registerForm!: FormGroup;
  sendingRequest: boolean = false;
  showRegisterAlert:boolean = false;

  villes = ['Casablanca', 'Rabat', 'Marrakech', 'Fès', 'Tanger', 'Agadir', 'Meknès', 'Oujda', 'Kenitra', 'Tétouan'];
  specialties = [  'Droit du travail',  'Droit pénal',  'Droit de la famille',  'Droit immobilier',  'Droit des affaires',  'Droit fiscal',  'Droit de la propriété intellectuelle',  'Droit international',  'Droit de l\'environnement',  'Droit des successions'];

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserServiceService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      "nom": this.formBuilder.control('', Validators.minLength(4)),
      "prénom": this.formBuilder.control('', Validators.minLength(4)),
      "ville": this.formBuilder.control('', Validators.minLength(2)),
      "email": this.formBuilder.control('', [Validators.email, Validators.required]),
      "gender": this.formBuilder.control('', [Validators.min(4), Validators.required]),
      "cin": this.formBuilder.control('', [Validators.min(4), Validators.required]),
      "password": this.formBuilder.control('', [Validators.min(4), Validators.required]),
      "spécialité": this.formBuilder.control('', [Validators.min(4), Validators.required]),
      "role": "AVOCAT",
      "image_url": "null"
    })

  }

  registerAvocat() {
    if (this.registerForm.valid) {
      this.sendingRequest = true
      // this.registerForm.value.name = `${this.registerForm.value.firstName} ${this.registerForm.value.lastName}`;
      console.log(this.registerForm.value)
      this.userService.registerAvocat(this.registerForm).subscribe({
        complete: () => {
          setTimeout(() => {
            this.sendingRequest = false;
            this.registerForm.reset()
            setTimeout(() => this.showRegisterAlert = true, 1000)
            setTimeout(() => this.showRegisterAlert = false, 5000)
          }, 5000);
          // Swal
          this.showSuccessMsg("Votre compte a été créé avec succès");
          this.router.navigate(['/']).then();
        },
        error: (error) => {
          console.log(error);
        }
      })
    }
  }


  showSuccessMsg(message: string): void {
    Swal.fire({
      icon: 'success',
      title: "Succès",
      text: message,
      confirmButtonColor: "#4359a5",
      reverseButtons: true
    }).then();
  }
}
