import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EventcodeService} from "../eventcode.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  eventCodeFormGroup: FormGroup;
  menuFormGroup: FormGroup;
  constructor(private fb: FormBuilder, private eventCodeService: EventcodeService) { }

  ngOnInit() {
    this.eventCodeFormGroup = this.fb.group({
      eventCodeInput: ['', Validators.required]
    });

    this.menuFormGroup = this.fb.group({
      nameInput: ['', Validators.required],
      menuSelect: ['', Validators.required],
    });

    console.log("calling elvis");
    this.eventCodeService.checkEventcode('I5POAQ')
      .subscribe(data => console.log(data),
        error => console.log(error));
  }

}
