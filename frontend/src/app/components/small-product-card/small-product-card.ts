import { Component, input } from '@angular/core';
import { Product } from '../../models/Product';

@Component({
  selector: 'app-small-product-card',
  imports: [],
  templateUrl: './small-product-card.html',
  styleUrl: './small-product-card.scss',
})
export class SmallProductCard {
  product = input.required<Product>();
}
