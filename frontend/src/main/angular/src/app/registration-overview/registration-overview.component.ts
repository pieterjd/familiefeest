import {Component, Inject, OnInit} from '@angular/core';
import {Router, ActivatedRoute, ParamMap} from "@angular/router";
import {EventRegistrationService} from "../event-registration.service";
import {EventRegistration} from "../../model/eventregistration";
import {PaymentService} from "../../service/payment.service";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef, MatSnackBar} from "@angular/material";
import {Payment} from "../../model/payment";
import {MailService} from "../../service/mail.service";


@Component({
  selector: 'app-registration-overview',
  templateUrl: './registration-overview.component.html',
  styleUrls: ['./registration-overview.component.css']
})
export class RegistrationOverviewComponent implements OnInit {
  eventRegistrations: EventRegistration[];
  token: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private eventRegistrationService: EventRegistrationService,
              private paymentService: PaymentService,
              private mailService: MailService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar) {

  }

  update(): void {
    const eventId = this.route.snapshot.paramMap.get('eventId');
    this.eventRegistrationService.getAllEventRegistration(eventId).subscribe(
      data => this.eventRegistrations = data
    );
  }

  ngOnInit() {
    this.token = "default token";
    this.update();
  }

  sendPurchaseMail(er: EventRegistration): void {
    this.mailService.sendPurchaseMail(er.code);
    let snackBar = this.snackBar.open('Purchase mail sent to ' + er.user.email, null, {duration: 3000});
  }

  openDialog(er: EventRegistration): void {
    console.log(er);
    let p = new Payment();

    const dialogRef = this.dialog.open(AddPaymentDialog, {
      width: '250px',
      data: {
        payment: p,
        eventRegistration: er
      }
    });

    dialogRef.afterClosed().subscribe(data => {
      this.paymentService.addPayment(data.eventRegistration.code, data.payment, this.token).subscribe(
        success => this.update(),
        error => console.log("errrrorrrr")
      );
    });
  }


}

@Component({
  selector: 'dialog-payment-add',
  templateUrl: 'registration-overview.payment-add.html',
})

export class AddPaymentDialog {

  constructor(
    public dialogRef: MatDialogRef<AddPaymentDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}

