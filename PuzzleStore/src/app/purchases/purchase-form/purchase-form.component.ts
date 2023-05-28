import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart-service/cart.service';
import { PersonService } from 'src/app/services/person-service/person.service';
import { PurchaseService } from 'src/app/services/purchase-service/purchase.service';
import { Cart } from 'src/model/cart';
import { Person } from 'src/model/person';
import { Purchase } from 'src/model/purchase';
import { User } from 'src/model/user';

@Component({
  selector: 'app-purchase-form',
  templateUrl: './purchase-form.component.html',
  styleUrls: ['./purchase-form.component.css']
})
export class PurchaseFormComponent implements OnInit {

  //Cart
  public cart!: Cart;
  //Purchase
  public purchase:Purchase;
  //Payment form
  public paymentMethod:string;


  constructor(public cartService:CartService, public personService:PersonService, public purchaseService:PurchaseService,private router:Router) {
    //Purchase
    let currentDate = new Date();
    this.purchase = new Purchase(NaN,currentDate,"","",0,"");
    //Payment form
    this.paymentMethod= "Cash";
    
    let userID = localStorage.getItem("user") || "";
    if(userID!=null)
    { 
      cartService.findByUsername(userID).subscribe((cartFound:Cart)=>{
        if(cartFound!=null)
        {
          this.cart = cartFound;
          this.purchase.puzzleOrders = this.cart.puzzleOrders;
          this.calculateValues();
          this.personService.findByUsername(userID).subscribe((personFound:Person)=>{
            if(personFound!=null){
              this.purchase.username = personFound.username;
            }
          });
        }
      });
    }
   }

  public calculateValues(){
    this.purchase.shippingCharge = Math.round(this.purchase.shippingTax * this.getProductsTotal(this.cart));
    this.purchase.productsTotal = this.getProductsTotal(this.cart);
    this.purchase.total = this.purchase.productsTotal + this.purchase.shippingCharge;
    this.purchaseService.update(this.purchase);
  }
  ngOnInit(): void {
  }

  getProductsTotal(cart:Cart){
    let total:number = 0;
    cart.puzzleOrders.forEach(puzzleOrder => {
      total += puzzleOrder.puzzle.price*puzzleOrder.count;
    });
    return total;
  }

  buyNow()
  {
    if(this.validateFields())
    {
      this.personService.findByUsername(this.purchase.username).subscribe((personFound:Person)=>{
        if(personFound!=null){
          this.purchaseService.create(this.purchase).subscribe();
          this.cart.puzzleOrders = [];
          this.router.navigate(['purchase-history']);
        }
      });
    }
  }

  validateFields()
  {
    let validated = false;
    if(this.purchase.deliveryAddress=="")
    {
      validated = false;
      alert("You have to type a delivery address!");
    } 
    else if( this.purchase.puzzleOrders.length==0 )
    {
      validated = false;
      alert("No items in the shopping cart!");
    }
    else{
      validated = true;
    }
    return validated;
  }


}
