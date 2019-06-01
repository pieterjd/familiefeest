import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {Payment} from "../model/payment";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private PAYMENT_ENDPOINT = "/api/payment/";

  constructor(private http: HttpClient) {
  }

  addPayment(eventCode: string, payment: Payment, secretToken: string): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'secret-token': secretToken
      })
    };
    return this.http.post<any>(this.PAYMENT_ENDPOINT+eventCode, payment, httpOptions);
  }
}
