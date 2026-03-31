import { Component, Input, signal } from '@angular/core';

@Component({
  selector: 'app-rotating-coin',
  imports: [],
  templateUrl: './rotating-coin.html',
  styleUrl: './rotating-coin.scss',
})
export class RotatingCoin {
  @Input() goldAmount: number = 9999999999;
}
