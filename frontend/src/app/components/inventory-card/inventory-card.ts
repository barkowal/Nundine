import { Component, input } from '@angular/core';
import { ProductStock } from '../../models/Product';

@Component({
  selector: 'app-inventory-card',
  imports: [],
  templateUrl: './inventory-card.html',
  styleUrl: './inventory-card.scss',
})
export class InventoryCard {
  product = input.required<ProductStock>();
}
