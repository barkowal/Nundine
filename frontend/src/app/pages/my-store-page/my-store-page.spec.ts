import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyStorePage } from './my-store-page';

describe('MyStorePage', () => {
  let component: MyStorePage;
  let fixture: ComponentFixture<MyStorePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyStorePage],
    }).compileComponents();

    fixture = TestBed.createComponent(MyStorePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
