import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {Statistics} from "../model/statistics";

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {
  private STATISTICS_ENDPOINT = "/api/eventstatistics/";

  constructor(private http: HttpClient) { }

  public getStatistics(eventId: number): Observable<Statistics>{
    return this.http.get<Statistics>(this.STATISTICS_ENDPOINT + eventId);
  }
}
