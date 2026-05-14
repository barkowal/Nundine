import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyProductForm } from './buy-product-form';

describe('BuyProductForm', () => {
  let component: BuyProductForm;
  let fixture: ComponentFixture<BuyProductForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuyProductForm],
    }).compileComponents();

    fixture = TestBed.createComponent(BuyProductForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
