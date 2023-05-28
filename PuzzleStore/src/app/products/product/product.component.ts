import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart-service/cart.service';
import { environment } from 'src/environments/environment';
import { Cart } from 'src/model/cart';
import { Puzzle } from 'src/model/puzzle';
import { PuzzleOrder } from 'src/model/puzzleOrder';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() public puzzles:Puzzle [];
  @Input() public isShoppingCart:boolean;
  public isAdmin: boolean;


  constructor(public cartService:CartService, public router:Router) {
    this.isShoppingCart = false;
    this.puzzles = [];
    let user = localStorage.getItem("rol");
    if(user == environment.adminRol){
      this.isAdmin = true;
    }
    else{
      this.isAdmin = false;
    }
  }

  ngOnInit(): void {
  }

  public onClickAddToCart(selectedPuzzle:Puzzle){
    let userid = localStorage.getItem("user") || "";
    this.cartService.addPuzzle(selectedPuzzle, userid).subscribe();
    alert("Item added to the shopping cart");
  }

}
