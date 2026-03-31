import { Component, signal } from '@angular/core';
import { NavigationButton } from '../navigation-button/navigation-button';
import { AccountSection } from '../account-section/account-section';

@Component({
  selector: 'app-navigation-bar',
  imports: [NavigationButton, AccountSection],
  templateUrl: './navigation-bar.html',
  styleUrl: './navigation-bar.scss',
})
export class NavigationBar {
  pages = signal<Array<string>>(["HOME", "PRODUCTS", "INVENTORY", "SHOP"]);
}
