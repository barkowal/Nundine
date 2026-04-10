import { Component, inject, OnInit, signal } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CreateProductRequest, Product } from '../../models/Product';
import { CategoryService } from '../../services/category-service';
import { ProductService } from '../../services/product-service';
import { Category } from '../../models/Category';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-add-product-form',
  imports: [FormsModule, NgClass],
  templateUrl: './add-product-form.html',
  styleUrl: './add-product-form.scss',
})
export class AddProductForm implements OnInit {
  private image: string | null = null;
  protected successMsg = signal("");
  protected requestError = signal(false);

  categoryService: CategoryService = inject(CategoryService);
  productService: ProductService = inject(ProductService);

  categories = signal<Category[]>([]);
  products = signal<Product[]>([]);

  ngOnInit() {
    this.categoryService.getCategories()
      .subscribe(data => {
        this.categories.set(data);
      });
  }

  onSubmit(f: NgForm) {

    if (this.image === null) {
      alert("Image file not uploaded.");
      return;
    }

    let request: CreateProductRequest = {
      name: f.value.name,
      description: f.value.description,
      image: this.image,
      currentPrice: f.value.price,
      category: f.value.category
    }

    this.productService.createProduct(request).subscribe(
      {
        next: (data) => {
          this.successMsg.set(`Product ${data.name} successfully created.`);
          this.requestError.set(false);
        },
        error: (error) => {
          console.error(`Status: ${error.status}, Error: ${error.error.error}`);
          this.successMsg.set("Something went wrong, " + error.error.error);
          this.requestError.set(true);
        }
      });

  }

  handleImage(event: any) {
    const file = event.target.files[0];

    // small 32x32 images
    if (file.size > 1_000) {
      alert('File too large');
      this.image = null;
      return;
    }
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.image = reader.result as string;
    };

  }

}
