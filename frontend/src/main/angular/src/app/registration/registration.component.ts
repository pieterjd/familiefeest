import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EventcodeService} from "../eventcode.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  eventCode: any;
  menuFormGroup: FormGroup;
  constructor(private fb: FormBuilder, private eventCodeService: EventcodeService) { }

  ngOnInit() {

    this.menuFormGroup = this.fb.group({
      nameInput: ['', Validators.required],
      menuSelect: ['', Validators.required],
    });
  }

  setEventCode(eventCode: any){
    this.eventCode = eventCode;
  }

}
