import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-navigation-button',
  imports: [],
  templateUrl: './navigation-button.html',
  styleUrl: './navigation-button.scss',
})
export class NavigationButton {
  @Input() pageName: string = "";
}
