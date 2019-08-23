import {Event} from "./event";
import {User} from "./user";
import {Purchase} from "./purchase";

export class EventRegistration {
  code: string;
  user: User;
  event: Event;
  purchasedItems: Purchase[];
  purchaseTotal: number;
  amountAlreadyPaid: number;
  openAmount: number;
  willAttend: boolean;
}
