import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RotatingCoin } from './rotating-coin';

describe('RotatingCoin', () => {
  let component: RotatingCoin;
  let fixture: ComponentFixture<RotatingCoin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RotatingCoin],
    }).compileComponents();

    fixture = TestBed.createComponent(RotatingCoin);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
