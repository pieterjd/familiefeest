import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {AbstractControl, ValidationErrors} from "@angular/forms";

@Injectable()
export class EventCodeService {
  private ENDPOINT: string = "/api/eventregistration";

  constructor(private http: HttpClient) {
  }

  public checkEventcode(code: string): Observable<any> {
    return this.http.get(this.ENDPOINT + "/" + code);
  }
}
