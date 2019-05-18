import {EventItem} from "./eventitem";

export class Purchase {
  eventItem: EventItem;
  beneficiary: string;

  constructor( eventItem:EventItem, beneficiary: string){
    this.eventItem = eventItem;
    this.beneficiary = beneficiary;
  }
}
