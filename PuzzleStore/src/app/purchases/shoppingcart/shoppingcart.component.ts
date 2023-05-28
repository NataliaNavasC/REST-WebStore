import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart-service/cart.service';
import { Cart } from 'src/model/cart';

@Component({
  selector: 'app-shoppingcart',
  templateUrl: './shoppingcart.component.html',
  styleUrls: ['./shoppingcart.component.css']
})
export class ShoppingcartComponent implements OnInit {

  //Cart
  public cart!: Cart;

  public isEmpty:boolean;

  constructor(private cartService:CartService) { 
    let userID = localStorage.getItem("user");
    this.isEmpty = true;
    if(userID!=null)
    { 
      cartService.findByUsername(userID).subscribe((cartFound:Cart)=>{
        if(cartFound != null){
          this.cart = cartFound;
          if(this.cart.puzzleOrders.length > 0){
            this.isEmpty = false;
          }
        }
      });
    }
  }

  ngOnInit(): void {
  }

}
