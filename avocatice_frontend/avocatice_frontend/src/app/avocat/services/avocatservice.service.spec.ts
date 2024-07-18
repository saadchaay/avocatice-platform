import { TestBed } from '@angular/core/testing';

import { AvocatserviceService } from './avocatservice.service';

describe('AvocatserviceService', () => {
  let service: AvocatserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AvocatserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
