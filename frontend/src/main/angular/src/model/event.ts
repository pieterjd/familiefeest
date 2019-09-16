import {EventItem} from "./eventitem";


export class Event {
  title: string;
  description: string;
  eventItems: EventItem[];
  date: string;
  registrationDeadline: Date;
}
