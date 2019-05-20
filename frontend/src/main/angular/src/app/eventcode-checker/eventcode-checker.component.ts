import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {EventRegistrationService} from "../event-registration.service";
import {AbstractControl, AsyncValidator, FormBuilder, FormGroup, ValidationErrors, Validators} from "@angular/forms";


@Component({
  selector: 'app-eventcode-checker',
  templateUrl: './eventcode-checker.component.html',
  styleUrls: ['./eventcode-checker.component.css']
})
export class EventcodeCheckerComponent implements OnInit {
  myForm: FormGroup;
  validEventCode: boolean;
  checkedEventCode: boolean;
  eventCode: any;
  @Output() eventCodeValid: EventEmitter<any>;

  constructor(private fb: FormBuilder, private eventCodeService: EventRegistrationService) {
    this.myForm = this.fb.group({
      'eventCode': ['', Validators.required]
    });
    this.eventCodeValid = new EventEmitter<any>();
  }

  ngOnInit() {
    this.validEventCode = false;
    this.checkedEventCode = false;
  }

  onSubmit(values: any) {
    console.log(values);
    this.eventCodeService.checkEventcode(values.eventCode).subscribe(
      eventRegistration => {
        this.checkedEventCode = true;
        this.validEventCode = true;
        this.eventCode = eventRegistration.code;
        this.eventCodeValid.emit(eventRegistration.code);
      },
      error => {
        this.checkedEventCode = true;
        this.validEventCode = false;
        this.eventCodeValid.emit(null);
      }
    );
  }


}
