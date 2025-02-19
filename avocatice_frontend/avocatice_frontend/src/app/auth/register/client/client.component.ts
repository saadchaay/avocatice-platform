import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserServiceService} from "../../service/user-service.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  registerForm!: FormGroup;
  sendingRequest: boolean = false;
  showRegisterAlert:boolean = false;

  villes = ['Casablanca', 'Rabat', 'Marrakech', 'Fès', 'Tanger', 'Agadir', 'Meknès', 'Oujda', 'Kenitra', 'Tétouan'];

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserServiceService,
    private router: Router
  ) {
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
      "role": "CLIENT",
      "spécialité": "null",
      "image_url": "null"
    })

  }

  registerClient() {
    if (this.registerForm.valid) {
      this.sendingRequest = true
      // this.registerForm.value.name = `${this.registerForm.value.firstName} ${this.registerForm.value.lastName}`;
      this.userService.registerClient(this.registerForm).subscribe({
        complete: () => {
          setTimeout(() => {
            this.sendingRequest = false;
            this.registerForm.reset()
            setTimeout(() => this.showRegisterAlert = true, 1000)
            setTimeout(() => this.showRegisterAlert = false, 3000)
          }, 5000);

          this.showSuccessMsg("Votre compte a été créé avec succès");
          this.router.navigate(['/']).then();
        },
        error: (error) => {
          console.log(error);
        }
      })
    }
  }

  register2() {
    this.sendingRequest = true

    console.log(this.registerForm);

    console.log("zezez")
    if (this.registerForm.valid) {

      console.log(this.registerForm.value)
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
