import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart-service/cart.service';
import { environment } from 'src/environments/environment';
import { Cart } from 'src/model/cart';

@Component({
  selector: 'app-navegation-bar',
  templateUrl: './navegation-bar.component.html',
  styleUrls: ['./navegation-bar.component.css']
})
export class NavegationBarComponent implements OnInit {

  public isAdmin:boolean;
  public cart!:Cart;

  constructor(private router:Router, private cartService:CartService) {
    let userID = localStorage.getItem("user");
    if(userID!=null)
    {
      cartService.findByUsername(userID).subscribe((cartFound:Cart)=>{
        if(cartFound != null){
          this.cart = cartFound;
        }
      });
    }
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

  public logout(){
    localStorage.removeItem("user");
    localStorage.removeItem("rol");
    sessionStorage.removeItem("token");
    this.router.navigate(['']);
  }

}
