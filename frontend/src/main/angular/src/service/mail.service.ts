import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class MailService {
  private PURCHASEMAIL_ENDPOINT = "/api/mail/purchasemail/";

  constructor(private http: HttpClient) {
  }

  sendPurchaseMail(eventCode: string): void{
    console.log("Mail service "+eventCode);
    this.http.post(this.PURCHASEMAIL_ENDPOINT + eventCode,{}).subscribe(
      success => console.log("sent"),
      error => console.log("error sending mail")
    );
  }
}
