import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PurchaseService } from 'src/app/services/purchase-service/purchase.service';
import { Purchase } from 'src/model/purchase';

@Component({
  selector: 'app-purchase-details',
  templateUrl: './purchase-details.component.html',
  styleUrls: ['./purchase-details.component.css']
})
export class PurchaseDetailsComponent implements OnInit {

  public purchase:Purchase;
  public totalPuzzles:number;
  public shippingCharge:number;

  constructor(private route:ActivatedRoute, private purchaseService:PurchaseService) { 
    this.totalPuzzles = 0;
    this.shippingCharge = 0;
    this.purchase = new Purchase(0,new Date(),"","",0,"");
    let id = this.route.snapshot.paramMap.get('id');

    if(id!=null)
    {
      purchaseService.find(+id).subscribe(
        (response: Purchase) => {
          let purchase = response;
          if(purchase!=null)
          {
            this.purchase = purchase;
            this.totalPuzzles = this.getProductsTotal();
            this.shippingCharge = Math.round(this.purchase.shippingTax * this.totalPuzzles);
          }
        }
      );
    }
  }

  ngOnInit(): void {
  }


  getProductsTotal(){
    let total = 0;
    this.purchase.puzzleOrders.forEach(puzzleOrder => {
      total += puzzleOrder.puzzle.price * puzzleOrder.count;
    });
    return total;
  }

}
