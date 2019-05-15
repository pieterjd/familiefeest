import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-menulist',
  templateUrl: './menulist.component.html',
  styleUrls: ['./menulist.component.css']
})
export class MenulistComponent implements OnInit {
  animal: string;
  constructor(private dialog: MatDialog) { }

  ngOnInit() {
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
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
