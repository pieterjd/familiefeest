import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {Purchase} from "../model/purchase";

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {
  private PURCHASE_ENDPOINT = "/api/purchase/";

  constructor(private http: HttpClient) {
  }

  getPurchases(eventCode: string): Observable<Purchase[]> {
    return this.http.get<Purchase[]>(this.PURCHASE_ENDPOINT + eventCode);
  }

  addPurchase(eventCode: string, purchase: Purchase): Observable<Purchase>{
    return this.http.post<Purchase>(this.PURCHASE_ENDPOINT + eventCode, purchase);
}

}
