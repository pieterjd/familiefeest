import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {EventRegistrationService} from "../event-registration.service";
import {PurchaseService} from "../../service/purchase.service";
import {MailService} from "../../service/mail.service";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit, OnChanges {
  @Input() eventCode: string;
  eventRegistration: any;
  emailSent: boolean;

  constructor(private eventCodeService: EventRegistrationService,
              private purchaseSerivce: PurchaseService,
              private mailService: MailService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.emailSent = false;
  }

  ngOnChanges() {
    //whenever the eventCode input changes, then call the service
    if (this.eventCode != null) {
      this.refresh();
    }
  }

  refresh(): void {

    this.eventCodeService.checkEventcode(this.eventCode)
      .subscribe(
        data => this.eventRegistration = data
      );

  }

  sendPurchaseMail(): void {
    this.mailService.sendPurchaseMail(this.eventCode);
    this.emailSent = true;
    let snackBar = this.snackBar.open('Email verzonden', null, {duration: 3000});
  }

}
