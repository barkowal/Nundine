import { Component, EventEmitter, inject, Input, input, OnInit, Output, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CreateProductRequest, Product, UpdateProductRequest } from '../../models/Product';
import { CategoryService } from '../../services/category-service';
import { ProductService } from '../../services/product-service';
import { Category } from '../../models/Category';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-product-form',
  imports: [ReactiveFormsModule, NgClass],
  templateUrl: './product-form.html',
  styleUrl: './product-form.scss',
})
export class ProductForm implements OnInit {
  @Input() public product: Product | null = null;
  @Output('onUpdate')
  onUpdate = new EventEmitter<void>();
  protected image = signal("/assets/icons/X_Icon.png");

  private categoryService: CategoryService = inject(CategoryService);
  private productService: ProductService = inject(ProductService);

  protected categories = signal<Category[]>([]);
  protected products = signal<Product[]>([]);

  protected successMsg = signal("");
  protected requestError = signal(false);


  protected submitForm: FormGroup = new FormGroup({
    name: new FormControl(""),
    description: new FormControl(""),
    image: new FormControl(""),
    price: new FormControl(0),
    category: new FormControl(""),
  });

  ngOnInit() {
    this.categoryService.getCategories()
      .subscribe(data => {
        this.categories.set(data);
        this.submitForm.get("category")?.setValue(this.categories()[0].id);
      });


    if (this.product) {
      this.submitForm.patchValue({
        name: this.product.name,
        description: this.product.description,
        price: this.product.currentPrice,
        cateogory: this.product.category
      });
      this.image.set(this.product.image);
    }
  }

  handleImage(event: any) {
    const file = event.target.files[0];

    // small 32x32 images
    if (file.size > 1_000) {
      alert('File too large');
      this.submitForm.get("image")?.setValue("");
      return;
    }
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      const image = reader.result as string;
      this.image.set(image);
    };
  }

  // TODO: instead of 2 basically the same components with product form
  // make only one for adding and updating

  submitProduct() {
    if (this.submitForm == null) return;

    if (this.product == null) return;

    // This means the image is too small/wasn't changed
    if (this.image().length <= 32) {
      alert("Wrong image.");
      return;
    }

    let request: UpdateProductRequest = {
      productId: this.product.id,
      name: this.submitForm.get("name")?.value,
      description: this.submitForm.get("description")?.value,
      image: this.image(),
      currentPrice: this.submitForm.get("price")?.value,
      category: this.submitForm.get("category")?.value
    }

    this.productService.updateProduct(this.product.id, request).subscribe(
      {
        next: (data) => {
          this.successMsg.set(`Product ${data.name} successfully updated.`);
          this.requestError.set(false);
          this.onUpdate.emit();
        },
        error: (error) => {
          console.error(`Status: ${error.status}, Error: ${error.error.error}`);
          this.successMsg.set("Something went wrong, " + error.error.error);
          this.requestError.set(true);
        }
      });
  }

}
