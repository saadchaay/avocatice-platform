import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardAvocatComponent } from './card-avocat.component';

describe('CardAvocatComponent', () => {
  let component: CardAvocatComponent;
  let fixture: ComponentFixture<CardAvocatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardAvocatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardAvocatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
