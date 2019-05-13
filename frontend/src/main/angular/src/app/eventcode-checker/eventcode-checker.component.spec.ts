import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventcodeCheckerComponent } from './eventcode-checker.component';

describe('EventcodeCheckerComponent', () => {
  let component: EventcodeCheckerComponent;
  let fixture: ComponentFixture<EventcodeCheckerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventcodeCheckerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventcodeCheckerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
