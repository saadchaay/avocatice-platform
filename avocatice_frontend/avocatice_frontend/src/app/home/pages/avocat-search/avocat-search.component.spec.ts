import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvocatSearchComponent } from './avocat-search.component';

describe('AvocatSearchComponent', () => {
  let component: AvocatSearchComponent;
  let fixture: ComponentFixture<AvocatSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvocatSearchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvocatSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
