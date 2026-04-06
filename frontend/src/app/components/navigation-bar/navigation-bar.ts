import { Component, signal } from '@angular/core';
import { NavigationButton } from '../navigation-button/navigation-button';
import { AccountSection } from '../account-section/account-section';
import { RouterLink } from '@angular/router';

export type NavigationListType = {
  name: string,
  link: string
};

@Component({
  selector: 'app-navigation-bar',
  imports: [RouterLink, NavigationButton, AccountSection],
  templateUrl: './navigation-bar.html',
  styleUrl: './navigation-bar.scss',
})

export class NavigationBar {
  pages = signal<Array<NavigationListType>>([
    {
      name: "HOME",
      link: ""
    },
    {
      name: "PRODUCTS",
      link: "/products"
    },
    {
      name: "INVENTORY",
      link: "/inventory"
    },
    {
      name: "SHOP",
      link: "/shop"
    },
  ]);
}
