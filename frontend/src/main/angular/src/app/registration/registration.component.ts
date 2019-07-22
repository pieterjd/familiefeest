import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatHorizontalStepper} from "@angular/material";
import {EventRegistration} from "../../model/eventregistration";
import {ConfirmationComponent} from "../confirmation/confirmation.component";
import {ActivatedRoute} from "@angular/router";
import {EventRegistrationService} from "../event-registration.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  eventRegistration: EventRegistration;
  @ViewChild(MatHorizontalStepper) stepper: MatHorizontalStepper;
  @ViewChild(ConfirmationComponent) confirmationComponent: ConfirmationComponent;
  constructor(private route: ActivatedRoute,private eventRegistrationService: EventRegistrationService) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      data =>{
        console.log(data.eventCode);
        this.eventRegistrationService.checkEventcode(data.eventCode).subscribe(
          data => this.setEventRegistration(data)
        );
      }
    )
  }

  setEventRegistration(eventRegistration: EventRegistration) {
    this.eventRegistration = eventRegistration;
    if (this.eventRegistration != null) {
      this.next();
    }
  }

  refreshConfirmationComponent(){
    this.confirmationComponent.refresh();
  }

  next(): void{
    this.stepper.next();
  }

}
