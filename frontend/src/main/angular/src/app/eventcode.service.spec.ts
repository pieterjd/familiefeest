import { TestBed } from '@angular/core/testing';

import { EventCodeService } from './eventcode.service';

describe('EventCodeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EventCodeService = TestBed.get(EventCodeService);
    expect(service).toBeTruthy();
  });
});
