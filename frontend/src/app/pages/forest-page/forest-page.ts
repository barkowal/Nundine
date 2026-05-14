import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';

const ENEMY_SPRITE = "url(/assets/enemies/centaur.png)";
const ENEMY_SPRITE_DAMAGED = "url(/assets/enemies/centaur_damage.png)";

@Component({
  selector: 'app-forest-page',
  imports: [FormsModule],
  templateUrl: './forest-page.html',
  styleUrl: './forest-page.scss',
})
export class ForestPage {
  protected enemyHealth = signal(100);
  protected enemySprite = signal(ENEMY_SPRITE);
  protected prizeGold = signal(0);

  onAttack() {
    this.enemySprite.set(ENEMY_SPRITE_DAMAGED)
    setTimeout(() => this.enemySprite.set(ENEMY_SPRITE), 50);

    this.enemyHealth.update(val => val - 10);

    if (this.enemyHealth() <= 10) {
      this.enemyHealth.set(100);
      this.prizeGold.update(val => val + 10);
    }

  }

  //TODO: send request to backend
  collectGold() {
    this.prizeGold.set(0);
  }

}
