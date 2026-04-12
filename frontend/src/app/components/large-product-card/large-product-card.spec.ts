import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LargeProductCard } from './large-product-card';

describe('LargeProductCard', () => {
  let component: LargeProductCard;
  let fixture: ComponentFixture<LargeProductCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LargeProductCard],
    }).compileComponents();

    fixture = TestBed.createComponent(LargeProductCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
