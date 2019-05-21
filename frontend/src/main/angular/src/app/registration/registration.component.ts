import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatHorizontalStepper} from "@angular/material";
import {EventRegistration} from "../../model/eventregistration";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  eventRegistration: EventRegistration;
  @ViewChild(MatHorizontalStepper) stepper: MatHorizontalStepper;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {

  }

  setEventRegistration(eventRegistration: EventRegistration) {
    this.eventRegistration = eventRegistration;
    if (this.eventRegistration != null) {
      this.stepper.next();
    }
  }

}
