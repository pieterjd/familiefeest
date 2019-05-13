import {Component, OnInit} from '@angular/core';
import {EventcodeService} from "../eventcode.service";
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

  constructor(private fb: FormBuilder, private eventCodeService: EventcodeService) {
    this.myForm = this.fb.group({
      'eventCode': ['', Validators.required]
    });
  }

  ngOnInit() {
    this.validEventCode = false;
    this.checkedEventCode = false;
  }

  onSubmit(values: any) {
    console.log(values);
    this.eventCodeService.checkEventcode(values.eventCode).subscribe(
      success => {
        this.checkedEventCode = true;
        this.validEventCode = true;
      },
      error => {
        this.checkedEventCode = true;
        this.validEventCode = false;
      }
    );
  }


}
