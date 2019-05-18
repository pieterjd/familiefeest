import {Component, Inject, Input, OnChanges, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {EventItemService} from "../../service/eventitem.service";
import {EventItem} from "../../model/eventitem";


@Component({
  selector: 'app-menulist',
  templateUrl: './menulist.component.html',
  styleUrls: ['./menulist.component.css']
})
export class MenulistComponent implements OnInit, OnChanges {
  @Input() eventCode: string;
  eventItems: EventItem[];
  animal: string;

  constructor(private dialog: MatDialog, private eventItemService: EventItemService) {
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
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(AddMenuDialog, {
      width: '250px',
      data: {animal: undefined}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.animal = result;
    });
  }

}

export interface DialogData {
  animal: string;
}


@Component({
  selector: 'dialog-menulist-add',
  templateUrl: 'menulist.component-add.html',
})

export class AddMenuDialog {

  constructor(
    public dialogRef: MatDialogRef<AddMenuDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
