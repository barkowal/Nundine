import { Component, inject, OnInit, signal } from '@angular/core';
import { RotatingCoin } from '../rotating-coin/rotating-coin';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth-service';
import { AccountService } from '../../services/account-service';

@Component({
  selector: 'app-account-section',
  imports: [RotatingCoin, RouterLink],
  templateUrl: './account-section.html',
  styleUrl: './account-section.scss',
})
export class AccountSection implements OnInit {
  private readonly authService = inject(AuthService);
  private readonly accountService = inject(AccountService);
  signedIn = signal(true);
  username = signal("");
  seller = signal(false);
  accountBalance = signal<number>(0);

  constructor() {
  }

  async ngOnInit(): Promise<void> {
    if (this.authService == null) return;

    this.signedIn.update(() => this.authService.isAuthenticated());

    this.username.update(() => {
      const name = this.authService.getUsername();
      return name ? name.toUpperCase() : "";
    });

    if (!this.authService.isAuthenticated()) return;

    this.seller.set(this.authService.hasRealmRole("seller"));
    this.accountService.getAccountBalance().subscribe((data) => {
      this.accountBalance.set(data);
    })

  }

  onLogin() {
    this.authService.login();
  }

  onLogout() {
    this.authService.logout();
  }

}
