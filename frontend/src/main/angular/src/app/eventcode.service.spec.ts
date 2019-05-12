import { TestBed } from '@angular/core/testing';

import { EventcodeService } from './eventcode.service';

describe('EventcodeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EventcodeService = TestBed.get(EventcodeService);
    expect(service).toBeTruthy();
  });
});
