import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatHorizontalStepper} from "@angular/material";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  eventCode: any;
  @ViewChild(MatHorizontalStepper) stepper: MatHorizontalStepper;
  constructor(private fb: FormBuilder) { }

  ngOnInit() {

  }

  setEventCode(eventCode: any){
    this.eventCode = eventCode;
    if(eventCode != null){
      this.stepper.next();
    }
  }

}
