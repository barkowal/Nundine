import { Component, input } from '@angular/core';
import { Product } from '../../models/Product';

@Component({
  selector: 'app-large-product-card',
  imports: [],
  templateUrl: './large-product-card.html',
  styleUrl: './large-product-card.scss',
})
export class LargeProductCard {
  product = input.required<Product>();
}
