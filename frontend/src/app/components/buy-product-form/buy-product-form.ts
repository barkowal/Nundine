import { Component, inject, Input, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BuyProductRequest, ShopProduct } from '../../models/Product';
import { ProductService } from '../../services/product-service';

@Component({
  selector: 'app-buy-product-form',
  imports: [FormsModule],
  templateUrl: './buy-product-form.html',
  styleUrl: './buy-product-form.scss',
})
export class BuyProductForm {
  @Input() public product!: ShopProduct;

  private readonly productService: ProductService = inject(ProductService);
  protected quantity = signal<number>(0);
  protected message = signal<string>("");

  buyProduct() {
    const request: BuyProductRequest = {
      inventoryId: this.product.inventoryId,
      productIds: [this.product.productId],
      quantities: [this.quantity()]
    }

    this.productService.buyProduct(request).subscribe({
      next: () => {
        this.message.set("Bought");
      },
      error: (error) => {
        this.message.set("Something went wrong.");
        console.error(error);
      }
    });
  }

  onInputChange(event: Event) {
    const input = (event.target as HTMLInputElement).value;

    if (input === '') {
      this.setQuantity(0);
      return;
    }

    this.setQuantity(Number(input));
  }

  onDecrement() {
    this.setQuantity(this.quantity() - 1);
  }

  onIncrement() {
    this.setQuantity(this.quantity() + 1);
  }

  setQuantity(value: number) {
    if (value < 0) value = 0;
    if (value > this.product.quantity) value = this.product.quantity;

    this.quantity.set(value);

  }

}
