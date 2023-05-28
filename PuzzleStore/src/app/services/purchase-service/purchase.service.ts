import { Injectable } from '@angular/core';
import { Purchase } from 'src/model/purchase';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { IntegerResponse } from 'src/model/Response/IntegerResponse';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {


  constructor(private http: HttpClient){

  }

  public create(purchase:Purchase){
    return this.http.post<Purchase>(
      `${environment.urlPurchase}`, purchase).pipe(
          catchError(this.handleError)
      );
  }

  public find(id:number){
    return this.http.get<Purchase>(
      `${environment.urlPurchase}${id}`).pipe(
          catchError(this.handleError)
      );
  }

  public getProfits(filter:string){
    return this.http.get<IntegerResponse>(
      `${environment.urlPurchase}profits/${filter}`).pipe(
          catchError(this.handleError)
      );
  }

  public findByFilter(page:number, size:number, filter:string){
    let url:string = "";
    switch(filter){
      case "last-month":
        url = "month-report";
        break;
      case "top-five":
        url = "top-purchases-report";
        size = 5;
        break;
      case "top-ten":
        url = "top-purchases-report";
        size = 10;
        break;
      default:
        url = "history";
        break;
    }
    return this.http.get<Purchase[]>(
      `${environment.urlPurchase}${url}/${page}/${size}`).pipe(
          catchError(this.handleError)
      );
  }

  public update(purchase:Purchase){
    /*let purchaseToUpdate = this.find(purchase.id);
    if(purchaseToUpdate!=null)
    {
      purchaseToUpdate.date  = purchase.date;
      purchaseToUpdate.deliveryAddress  = purchase.deliveryAddress;
      purchaseToUpdate.paymentMethod  = purchase.paymentMethod;
      purchaseToUpdate.total = purchase.total;
      purchaseToUpdate.username = purchase.username;
    }*/
    
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
