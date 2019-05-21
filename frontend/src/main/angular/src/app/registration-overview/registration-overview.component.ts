import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute, ParamMap} from "@angular/router";
import {EventRegistrationService} from "../event-registration.service";
import {EventRegistration} from "../../model/eventregistration";

@Component({
  selector: 'app-registration-overview',
  templateUrl: './registration-overview.component.html',
  styleUrls: ['./registration-overview.component.css']
})
export class RegistrationOverviewComponent implements OnInit {
  eventRegistrations: EventRegistration[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private eventRegistrationService: EventRegistrationService) {
  }

  ngOnInit() {
    const eventId = this.route.snapshot.paramMap.get('eventId');
    this.eventRegistrationService.getAllEventRegistration(eventId).subscribe(
      data => this.eventRegistrations = data
    );
  }

}
