import { Component, Input, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart-service/cart.service';
import { Cart } from 'src/model/cart';
import { Puzzle } from 'src/model/puzzle';
import { PuzzleOrder } from 'src/model/puzzleOrder';

@Component({
  selector: 'app-product-order',
  templateUrl: './product-order.component.html',
  styleUrls: ['./product-order.component.css']
})
export class ProductOrderComponent implements OnInit {

  @Input() public puzzleOrders:PuzzleOrder [];
  
  constructor(private cartService:CartService) {
    this.puzzleOrders = [];
   }

  ngOnInit(): void {
  }

  public onClickRemoveFromCart(selectedPuzzle:PuzzleOrder){
    let userid = localStorage.getItem("user") || "";
    this.cartService.removePuzzle(selectedPuzzle, userid).subscribe((cart:Cart)=>{
      this.puzzleOrders = cart.puzzleOrders;
    });
    alert("Item removed from the shopping cart");
  }
}
