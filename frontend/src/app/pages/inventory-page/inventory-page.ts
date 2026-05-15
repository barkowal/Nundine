import { Component, inject, OnInit, signal } from '@angular/core';
import { ProductService } from '../../services/product-service';
import { ProductStock } from '../../models/Product';
import { InventoryCard } from '../../components/inventory-card/inventory-card';

@Component({
  selector: 'app-inventory-page',
  imports: [InventoryCard],
  templateUrl: './inventory-page.html',
  styleUrl: './inventory-page.scss',
})
export class InventoryPage implements OnInit {
  private readonly productService: ProductService = inject(ProductService);
  protected products = signal<ProductStock[]>([]);

  ngOnInit(): void {
    this.productService.getInventoryProducts()
      .subscribe(data => {
        this.products.set(data);
      });
  }
}
