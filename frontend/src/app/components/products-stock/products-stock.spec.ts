import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsStock } from './products-stock';

describe('ProductsStock', () => {
  let component: ProductsStock;
  let fixture: ComponentFixture<ProductsStock>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductsStock],
    }).compileComponents();

    fixture = TestBed.createComponent(ProductsStock);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
