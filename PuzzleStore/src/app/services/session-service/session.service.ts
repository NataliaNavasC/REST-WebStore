import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { PersonDTO } from 'src/model/Dtos/PersonDto';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http: HttpClient) { }

  public login(username:string, password:string){
    let person:PersonDTO = new PersonDTO(0,username, password);
    return this.http.post(
      `${environment.urlBackend}login`, person, {observe: 'response'})
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
        const err = error || '';
        errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
        errMsg = error.message ? error.message : error.toString();
    }

    return throwError(errMsg);
  }
}
