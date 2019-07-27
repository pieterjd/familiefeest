import {Component, OnInit} from '@angular/core';
import {EventRegistrationService} from "../event-registration.service";
import {EventRegistration} from "../../model/eventregistration";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {
  eventRegistrations: EventRegistration[];

  attendeesMapping: any = {
    '=1': 'aanwezige',
    'other': 'aanwezigen'
  };


  daysToEventMapping: any = {
    '=1': 'dag',
    'other': 'dagen'
  };


  constructor(private eventRegistrationService: EventRegistrationService) {
  }

  ngOnInit() {
    this.eventRegistrationService.getAllEventRegistration(1)
      .subscribe(
        data => this.eventRegistrations = data
      )
  }

  getNumberAttendees(): number {
    let attendees: number = 0;
    if (this.eventRegistrations != undefined) {
      for (let er of this.eventRegistrations) {
        attendees = attendees + er.purchasedItems.length;
      }
    }
    return attendees;
  }

  getDaysToEvent(): number {
    let diffInDays: number = 0;
    if(this.eventRegistrations != undefined) {
      let eventDate = new Date(this.eventRegistrations[0].event.date);
      let now = new Date();

      let diff = eventDate.getTime() - now.getTime();
      diffInDays = Math.ceil(diff / (1000 * 3600 * 24));
    }
    return diffInDays;
  }
}
