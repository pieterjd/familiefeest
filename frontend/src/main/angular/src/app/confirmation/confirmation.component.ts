import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {EventCodeService} from "../eventcode.service";
import {PurchaseService} from "../../service/purchase.service";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit, OnChanges {
  @Input() eventCode: string;
  private eventRegistration: any;
  constructor(private eventCodeService: EventCodeService,private purchaseSerivce: PurchaseService) { }

  ngOnInit() {

  }
  ngOnChanges() {
    //whenever the eventCode input changes, then call the service
    if (this.eventCode != null) {
      this.eventCodeService.checkEventcode(this.eventCode)
        .subscribe(
          data => this.eventRegistration = data
        );
    }
  }

}
