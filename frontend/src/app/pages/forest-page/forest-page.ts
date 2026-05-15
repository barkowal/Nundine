import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../../services/account-service';
import { DepositAccountBalanceRequest } from '../../models/AccountBalance';
import { AuthService } from '../../services/auth-service';

const ENEMY_SPRITE = "url(/assets/enemies/centaur.png)";
const ENEMY_SPRITE_DAMAGED = "url(/assets/enemies/centaur_damage.png)";

@Component({
  selector: 'app-forest-page',
  imports: [FormsModule],
  templateUrl: './forest-page.html',
  styleUrl: './forest-page.scss',
})
export class ForestPage {
  protected accountService = inject(AccountService);
  protected authService = inject(AuthService);
  protected enemyHealth = signal(100);
  protected enemySprite = signal(ENEMY_SPRITE);
  protected prizeGold = signal(0);
  protected msg = signal("");

  onAttack() {
    this.enemySprite.set(ENEMY_SPRITE_DAMAGED)
    setTimeout(() => this.enemySprite.set(ENEMY_SPRITE), 50);

    this.enemyHealth.update(val => val - 10);

    if (this.enemyHealth() <= 10) {
      this.enemyHealth.set(100);
      this.prizeGold.update(val => val + 10);
    }

  }

  collectGold() {
    if (this.prizeGold() == 0) {
      return;
    }

    if (!this.authService.isAuthenticated()) {
      this.setMsg("U need to be authenticated.")
      return;
    }

    const request: DepositAccountBalanceRequest = { balance: this.prizeGold() };
    this.accountService.depositToAccountBalance(request).subscribe({
      next: () => {
        this.setMsg("Added to account.")
      },
      error: (error) => {
        this.setMsg("Something went wrong.")
        console.error(error);
      }
    });

    this.prizeGold.set(0);
  }

  setMsg(message: string) {
    this.msg.set(message)
    setTimeout(() => this.msg.set(""), 3000);
  }

}
