import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {EventItem} from "../model/eventitem";
import {Purchase} from "../model/purchase";
import {isBoolean} from "util";

@Injectable({
  providedIn: 'root'
})
export class EventItemService {
  private ENDPOINT = "/api/eventitem/";
  private PURCHASE_ENDPOINT = "/api/purchase/";
  constructor(private http: HttpClient) { }

  getEventItems(eventCode: string): Observable<EventItem[]>{
    return this.http.get<EventItem[]>(this.ENDPOINT+eventCode);
  }

  purchaseEventItem(eventCode: string, purchase: Purchase): Observable<any>{
    return this.http.post(this.PURCHASE_ENDPOINT+eventCode, purchase);
  }
}
