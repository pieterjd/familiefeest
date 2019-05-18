import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {EventItem} from "../model/eventitem";

@Injectable({
  providedIn: 'root'
})
export class EventItemService {
  private ENDPOINT = "/api/eventitem/";
  constructor(private http: HttpClient) { }

  getEventItems(eventCode: string): Observable<EventItem[]>{
    return this.http.get<EventItem[]>(this.ENDPOINT+eventCode);
  }
}
