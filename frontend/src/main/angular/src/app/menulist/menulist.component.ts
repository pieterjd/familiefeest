import {Component, Inject, Input, OnChanges, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {EventItemService} from "../../service/eventitem.service";
import {EventItem} from "../../model/eventitem";
import {Purchase} from "../../model/purchase";
import {PurchaseService} from "../../service/purchase.service";


@Component({
  selector: 'app-menulist',
  templateUrl: './menulist.component.html',
  styleUrls: ['./menulist.component.css']
})
export class MenulistComponent implements OnInit, OnChanges {
  @Input() eventCode: string;
  eventItems: EventItem[];
  beneficiary: string;
  purchases: Purchase[];

  constructor(private dialog: MatDialog,
              private eventItemService: EventItemService,
              private purchaseService: PurchaseService) {
  }

  ngOnInit() {


  }

  ngOnChanges() {
    //whenever the eventCode input changes, then call the service
    if (this.eventCode != null) {
      this.eventItemService.getEventItems(this.eventCode)
        .subscribe(
          data => this.eventItems = data
        );
      this.purchaseService.getPurchases(this.eventCode)
        .subscribe(
          data => this.purchases = data
        )

    }
  }

  openDialog(ei: EventItem): void {
    let purchase = new Purchase(ei, null);
    console.log("showing dialog for: ");
    console.log(purchase);
    const dialogRef = this.dialog.open(AddMenuDialog, {
      width: '250px',
      data: {purchase: purchase}
    });

    dialogRef.afterClosed().subscribe(purchase => {
      console.log('The dialog was closed');
      console.log("result from dialig");
      console.log(purchase);
      this.purchaseService.addPurchase(this.eventCode, purchase).subscribe(
        purchase => {console.log("purchase succeeded");this.purchases.push(purchase)},
        error => console.log("purchase failed")
      );
    });
  }

}

export interface MenuData {
  beneficiary: string;
}


@Component({
  selector: 'dialog-menulist-add',
  templateUrl: 'menulist.component-add.html',
})

export class AddMenuDialog {

  constructor(
    public dialogRef: MatDialogRef<AddMenuDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Purchase) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
