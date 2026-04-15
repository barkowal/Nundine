import { inject, Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly keycloakInstance = inject(Keycloak);

  constructor() { }

  isAuthenticated(): boolean {

    if (this.keycloakInstance == null) return false;

    return this.keycloakInstance.authenticated;
  }

  getToken(): string | null {
    return this.keycloakInstance?.token ?? null;
  }

  getUserId(): string {
    return (this.keycloakInstance?.tokenParsed as any)?.sub ?? "";
  }

  getUsername(): string | null {
    const parsed = this.keycloakInstance?.tokenParsed as any | undefined;
    return parsed?.preferred_username ?? parsed?.name ?? null;
  }

  hasRealmRole(role: string): boolean {
    if (!this.keycloakInstance || !this.keycloakInstance.tokenParsed) return false;
    return this.keycloakInstance.hasRealmRole(role);
  }

  login() {
    if (!this.isAuthenticated()) {
      this.keycloakInstance.login();
    }
  }

  logout() {
    this.keycloakInstance?.logout();
  }
}
