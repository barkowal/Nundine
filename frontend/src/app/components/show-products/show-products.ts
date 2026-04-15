import { Component, inject, OnInit, signal } from '@angular/core';
import { ProductService } from '../../services/product-service';
import { Product } from '../../models/Product';
import { SmallProductCard } from '../small-product-card/small-product-card';
import { LargeProductCard } from '../large-product-card/large-product-card';
import { AuthService } from '../../services/auth-service';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-show-products',
  imports: [SmallProductCard, LargeProductCard, NgClass],
  templateUrl: './show-products.html',
  styleUrl: './show-products.scss',
})
export class ShowProducts implements OnInit {
  private readonly authService = inject(AuthService);
  private readonly productService: ProductService = inject(ProductService);

  protected shouldRefreshProducts = true;
  protected successMsg = signal("");
  protected requestError = signal(false);

  protected products = signal<Product[]>([]);
  protected selectedProduct = signal<Product | null>(null);


  ngOnInit() {
    this.refreshProducts();
  }

  showDetails(productIndex: number) {
    if (this.products()[productIndex] !== null)
      this.selectedProduct.set(this.products()[productIndex]);
  }

  closeCard() {
    this.selectedProduct.set(null);
    this.clearMsg();
    this.refreshProducts();
  }

  updateProduct() {
    //TODO: update
  }

  deleteProduct() {
    const product = this.selectedProduct();
    if (product === null) return;

    this.productService.deleteProduct(product.id).subscribe({
      next: () => {
        this.successMsg.set("Deleted successfully.");
        this.requestError.set(false);
        this.shouldRefreshProducts = true;
      },
      error: (error) => {
        this.successMsg.set("Something went wrong.");
        this.requestError.set(true);
        console.error(error);
      }
    });
  }

  refreshProducts() {
    if (!this.shouldRefreshProducts) return;

    let userId: string = this.authService.getUserId();
    this.productService.getUserProducts(userId)
      .subscribe(data => {

        // TODO: this is ugly, fix parsing products
        data.map((element: any) => {
          const created: string = element.createdAt;
          element.createdAt = new Date(created).toLocaleDateString();
          const updated: string = element.updatedAt;
          element.updatedAt = new Date(updated).toLocaleDateString();
        });

        this.products.set(data);
        this.shouldRefreshProducts = false;
      });
  }

  clearMsg() {
    this.successMsg.set("");
    this.requestError.set(false);
  }

}
