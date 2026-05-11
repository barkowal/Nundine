import { Component, inject, OnInit, signal } from '@angular/core';
import { ProductStock, UpdateProductStockRequest } from '../../models/Product';
import { ProductService } from '../../services/product-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-products-stock',
  imports: [FormsModule],
  templateUrl: './products-stock.html',
  styleUrl: './products-stock.scss',
})
export class ProductsStock implements OnInit {
  private readonly productService: ProductService = inject(ProductService);

  protected productsStock = signal<ProductStock[]>([]);
  protected lastIndexUpdate = signal<number>(0);
  protected updateMsg = signal<string>("");

  ngOnInit() {
    this.productService.getProductsStock()
      .subscribe(data => {
        this.productsStock.set(data);
      });
  }

  onInputChange(event: Event, index: number) {
    const input = (event.target as HTMLInputElement).value;

    if (input === '') {
      this.setProductQuantity(0, index);
      return;
    }

    this.setProductQuantity(Number(input), index);
  }

  onIncrement(index: number) {
    this.setProductQuantity(this.productsStock()[index].quantity + 1, index);
  }

  onDecrement(index: number) {
    this.setProductQuantity(this.productsStock()[index].quantity - 1, index);
  }

  submitProductStock(productIndex: number) {
    this.lastIndexUpdate.set(productIndex);

    const productId = this.productsStock()[productIndex].productId;
    const req: UpdateProductStockRequest = { quantity: this.productsStock()[productIndex].quantity };
    this.productService.updateProductStock(productId, req).subscribe({
      next: () => {
        this.updateMsg.set("Updated succesfully");
      },
      error: (error) => {
        this.updateMsg.set("Something went wrong");
        console.error(`Status: ${error.status}, Error: ${error.error.error}`);
      }
    });

    setTimeout(() => { this.updateMsg.set("") }, 3000);

  }

  private setProductQuantity(value: number, index: number) {
    const rows = this.productsStock();
    const copy = [...rows];
    copy[index].quantity = value;
    this.productsStock.set(copy);
  }

}
