import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Person } from 'src/model/person';
import { User } from 'src/model/user';
import { CartService } from '../cart-service/cart.service';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private cartService:CartService, private http: HttpClient){
  }

  public create(newUser:User){
    return this.http.post<User>(
      `${environment.urlUsers}`, newUser).pipe(
          catchError(this.handleError)
    );
  }

  public findByUsername(username:string){
    return this.http.get<Person>(
      `${environment.urlPersons}/${username}`).pipe(
          catchError(this.handleError)
    );
  }

  public findUserByUsername(username:string){
    return this.http.get<User>(
      `${environment.urlUsers}/${username}`).pipe(
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
