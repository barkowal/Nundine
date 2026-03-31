import { Component } from '@angular/core';
import { RotatingCoin } from '../rotating-coin/rotating-coin';

@Component({
  selector: 'app-account-section',
  imports: [RotatingCoin],
  templateUrl: './account-section.html',
  styleUrl: './account-section.scss',
})
export class AccountSection { }
