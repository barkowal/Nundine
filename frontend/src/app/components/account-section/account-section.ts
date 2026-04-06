import { Component, inject, OnInit, signal } from '@angular/core';
import { RotatingCoin } from '../rotating-coin/rotating-coin';
import Keycloak from 'keycloak-js';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-account-section',
  imports: [RotatingCoin, RouterLink],
  templateUrl: './account-section.html',
  styleUrl: './account-section.scss',
})
export class AccountSection implements OnInit {
  private readonly keycloak = inject(Keycloak);
  signedIn = signal(true);
  username = signal("");


  constructor() {
  }

  async ngOnInit(): Promise<void> {
    if (this.keycloak == null) return;

    this.signedIn.update(() => this.keycloak.authenticated);

    if (!this.keycloak.authenticated) return;

    this.keycloak.loadUserInfo().then(data => {
      if (data["preferred_username"] != null) {
        const name: string = data["preferred_username"];
        this.username.update(() => name.toUpperCase());
      }
    });
  }

  onLogin() {
    if (!this.keycloak?.authenticated) {
      this.keycloak.login();
    }
  }

  onLogout() {
    if (this.keycloak?.authenticated) {
      this.keycloak.logout();
    }
  }

}
