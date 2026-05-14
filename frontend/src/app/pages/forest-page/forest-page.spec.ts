import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForestPage } from './forest-page';

describe('ForestPage', () => {
  let component: ForestPage;
  let fixture: ComponentFixture<ForestPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForestPage],
    }).compileComponents();

    fixture = TestBed.createComponent(ForestPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
