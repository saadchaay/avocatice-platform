import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvocatConsultationComponent } from './avocat-consultation.component';

describe('AvocatConsultationComponent', () => {
  let component: AvocatConsultationComponent;
  let fixture: ComponentFixture<AvocatConsultationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvocatConsultationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvocatConsultationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
