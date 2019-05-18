import { TestBed } from '@angular/core/testing';

import { EventitemService } from './eventitem.service';

describe('EventitemService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EventitemService = TestBed.get(EventitemService);
    expect(service).toBeTruthy();
  });
});
