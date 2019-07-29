import {Component, OnInit} from '@angular/core';
import {StatisticsService} from "../../service/statistics.service";
import {Statistics} from "../../model/statistics";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {
  statistics: Statistics;

  attendeesMapping: any = {
    '=1': 'aanwezige',
    'other': 'aanwezigen'
  };


  daysToEventMapping: any = {
    '=1': 'dag',
    'other': 'dagen'
  };


  constructor(private statService: StatisticsService) {
  }

  ngOnInit() {
    this.statService.getStatistics(1)
      .subscribe(
        data => this.statistics = data
      )
  }

  getNumberAttendees(): number {
    return this.statistics? this.statistics.numberOfAttendees : 0;
  }

  getDaysToEvent(): number {
    return this.statistics? this.statistics.daysToEvent : 0;
  }

  getEventDate(): Date{
    return this.statistics? this.statistics.eventDate : new Date();
  }
}
