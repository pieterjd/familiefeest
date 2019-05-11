import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  eventCodeFormGroup: FormGroup;
  menuFormGroup: FormGroup;
  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.eventCodeFormGroup = this.fb.group({
      eventCodeInput: ['', Validators.required]
    });

    this.menuFormGroup = this.fb.group({
      nameInput: ['', Validators.required],
      menuSelect: ['', Validators.required],
    });
  }

}
