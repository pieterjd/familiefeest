import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {AbstractControl, ValidationErrors} from "@angular/forms";
import {EventRegistration} from "../model/eventregistration";

@Injectable()
export class EventRegistrationService {
  private ENDPOINT: string = "/api/eventregistration";

  constructor(private http: HttpClient) {
  }

  public checkEventcode(code: string): Observable<EventRegistration> {
    return this.http.get<EventRegistration>(this.ENDPOINT + "/" + code);
  }

  public getAllEventRegistration(eventId): Observable<EventRegistration[]>{
    return this.http.get<EventRegistration[]>(this.ENDPOINT+"/event/"+eventId);
  }
}
