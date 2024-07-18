import { TestBed } from '@angular/core/testing';

import { CreneuaxService } from './creneuax.service';

describe('CreneuaxService', () => {
  let service: CreneuaxService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreneuaxService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
