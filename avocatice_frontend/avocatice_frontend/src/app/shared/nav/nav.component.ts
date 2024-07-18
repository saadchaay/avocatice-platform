import {SecurityService} from "../../auth/service/security.service";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  show: boolean = true;
  isLogged: boolean = true;

  constructor(public securityService: SecurityService,private router :Router) {
    this.show = (window.location.pathname.includes("register")) ? false : true;
    this.show = (window.location.pathname.includes("consultation")) ? false : true;
    this.show = (window.location.pathname.includes("panel")) ? false : true;

    console.log(this.show);

    // console.log(this.securityService.profile?.email);
  }

  navigateTo(url: string) {
    this.router.navigate([url]);
  }
  public async ngOnInit() {
    this.securityService.isLogged().subscribe((data: any) => {
      console.log(data)
      this.isLogged = data;
    })
  }


  onLogout() {
    this.securityService.onLogout()
  }

  public async getToken() {
  }

  async login() {
    await this.securityService.login()
  }

}

import {Component, OnInit} from '@angular/core';

import {Router} from "@angular/router";
