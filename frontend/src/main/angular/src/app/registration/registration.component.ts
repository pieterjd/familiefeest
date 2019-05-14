import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EventcodeService} from "../eventcode.service";
import {MatHorizontalStepper} from "@angular/material";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  eventCode: any;
  menuFormGroup: FormGroup;
  @ViewChild(MatHorizontalStepper) stepper: MatHorizontalStepper;
  constructor(private fb: FormBuilder, private eventCodeService: EventcodeService) { }

  ngOnInit() {

    this.menuFormGroup = this.fb.group({
      nameInput: ['', Validators.required],
      menuSelect: ['', Validators.required],
    });
  }

  setEventCode(eventCode: any){
    this.eventCode = eventCode;
    if(eventCode != null){
      this.stepper.next();
    }
  }

}
