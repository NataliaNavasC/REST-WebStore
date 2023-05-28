import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonService } from 'src/app/services/person-service/person.service';
import { PurchaseService } from 'src/app/services/purchase-service/purchase.service';
import { environment } from 'src/environments/environment';
import { Purchase } from 'src/model/purchase';
import { IntegerResponse } from 'src/model/Response/IntegerResponse';
import { User } from 'src/model/user';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {

  public isAdmin:boolean;
  public totalSales:number;
  public profits:number;

  public purchases:Purchase[];

  public page:number;
  public size:number;
  public totalPages:number;
  public filter:string;

  constructor(public purchaseService:PurchaseService, public personService:PersonService, public router:Router) { 
    let userRol = localStorage.getItem("rol");
    let userID = localStorage.getItem("user");
    this.totalSales = 0;
    this.profits = 0;
    this.purchases = [];
    this.page = 0;
    this.size = 3;
    this.totalPages = 0;
    this.filter = "all";
    if(userID!=null)
    {
        personService.findUserByUsername(userID).subscribe((personFound:User)=>{
        if(personFound!=null)
        {
          if(userRol == environment.adminRol){
            this.filterPurchases();
          }
          else{
            this.purchases = personFound.purchases;
            this.totalPages = 1;
          }
        }
      });
    }
    if(userRol == environment.adminRol){
      this.isAdmin = true;
    }
    else{
      this.isAdmin = false;
    }
  }

  ngOnInit(): void {
  }

  findProfits(){
    this.purchaseService.getProfits(this.filter).subscribe(
      (response: IntegerResponse) => {
        this.profits = response.value;
      }
    );
  }

  previousPage() {
    if(this.page>0)
    {
      this.page--;
      this.filterPurchases();
    }
  }

  nextPage() {
    if(this.page<this.totalPages-1)
    {
      this.page++;
      this.filterPurchases();
    }
  }

  resetFilterParameters(){
    this.purchases = [];
    this.page = 0;
    this.filterPurchases();
  }

  filterPurchases() {
    this.purchaseService.findByFilter(this.page,this.size, this.filter).subscribe(
      (response: any) => {
        this.purchases = response.content;
        if(this.filter == "all" || this.filter == "last-month"){
          this.totalPages = response.totalPages;
          this.totalSales = response.totalElements;
        }
        else{
          this.totalPages = 1;
          this.totalSales = this.purchases.length;
        }
        this.findProfits();
      }
    );
  }
}
