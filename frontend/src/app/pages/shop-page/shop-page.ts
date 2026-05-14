import { Component, inject, OnInit, signal } from '@angular/core';
import { CategoryService } from '../../services/category-service';
import { Category } from '../../models/Category';
import { ShopProduct } from '../../models/Product';
import { ProductService } from '../../services/product-service';
import { AuthService } from '../../services/auth-service';
import { BuyProductForm } from '../../components/buy-product-form/buy-product-form';

@Component({
  selector: 'app-shop-page',
  imports: [BuyProductForm],
  templateUrl: './shop-page.html',
  styleUrl: './shop-page.scss',
})
export class ShopPage implements OnInit {
  private authService = inject(AuthService);
  private categoryService: CategoryService = inject(CategoryService);
  private productService: ProductService = inject(ProductService);

  categories = signal<Category[]>([]);
  products = signal<ShopProduct[]>([]);
  isAuthenticated = signal<boolean>(false);

  ngOnInit() {
    this.categoryService.getCategories()
      .subscribe(data => {
        this.categories.set(data);
      });

    this.productService.getShopProducts()
      .subscribe(data => {
        this.products.set(data);
      });

    this.isAuthenticated.set(this.authService.isAuthenticated());
  }

}
