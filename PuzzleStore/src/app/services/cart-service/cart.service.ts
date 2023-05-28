import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Cart } from 'src/model/cart';
import { Puzzle } from 'src/model/puzzle';
import { PuzzleOrder } from 'src/model/puzzleOrder';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient){
    
  }

  public addPuzzle(puzzle:Puzzle,username:string){
    return this.http.post<Cart>(
      `${environment.urlShoppingCart}/${username}/orders`, puzzle).pipe(
          catchError(this.handleError)
    );
  }

  public removePuzzle(puzzleOrderToRemove:PuzzleOrder,username:string){
    return this.http.delete<Cart>(
      `${environment.urlShoppingCart}/${username}/orders/${puzzleOrderToRemove.id}`).pipe(
          catchError(this.handleError)
    );
  }

  public findByUsername(username:string){
    return this.http.get<Cart>(
      `${environment.urlShoppingCart}/${username}`).pipe(
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
