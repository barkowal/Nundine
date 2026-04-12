import { Component, inject, OnInit, signal } from '@angular/core';
import { ProductService } from '../../services/product-service';
import { Product } from '../../models/Product';
import { SmallProductCard } from '../small-product-card/small-product-card';
import { LargeProductCard } from '../large-product-card/large-product-card';

@Component({
  selector: 'app-show-products',
  imports: [SmallProductCard, LargeProductCard],
  templateUrl: './show-products.html',
  styleUrl: './show-products.scss',
})
export class ShowProducts implements OnInit {
  productService: ProductService = inject(ProductService);

  products = signal<Product[]>([]);
  selectedProduct = signal<Product | null>(null);

  ngOnInit() {
    this.productService.getProducts()
      .subscribe(data => {

        // TODO: this is ugly, fix parsing products
        data.map((element: any) => {
          const created: string = element.createdAt;
          element.createdAt = new Date(created).toLocaleDateString();
          const updated: string = element.updatedAt;
          element.updatedAt = new Date(updated).toLocaleDateString();
        });

        this.products.set(data);
      });
  }

  showDetails(productIndex: number) {
    if (this.products()[productIndex] !== null)
      this.selectedProduct.set(this.products()[productIndex]);
  }

  closeCard() {
    this.selectedProduct.set(null);
  }

  updateProduct() {
    //TODO: update
  }

  deleteProduct() {
    // TODO: delete
  }
}
