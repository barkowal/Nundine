import { Component, inject, OnInit, signal } from '@angular/core';
import { CategoryService } from '../../services/category-service';
import { Category } from '../../models/Category';
import { FormsModule } from '@angular/forms';
import { Product } from '../../models/Product';
import { ProductService } from '../../services/product-service';

@Component({
  selector: 'app-shop-page',
  imports: [FormsModule],
  templateUrl: './shop-page.html',
  styleUrl: './shop-page.scss',
})
export class ShopPage implements OnInit {

  categoryService: CategoryService = inject(CategoryService);
  productService: ProductService = inject(ProductService);

  categories = signal<Category[]>([]);
  products = signal<Product[]>([]);

  ngOnInit() {
    this.categoryService.getCategories()
      .subscribe(data => {
        this.categories.set(data);
      });

    this.productService.getProducts()
      .subscribe(data => {
        this.products.set(data);
      });
  }

}
