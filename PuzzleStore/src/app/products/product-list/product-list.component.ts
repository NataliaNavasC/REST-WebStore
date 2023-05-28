import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart-service/cart.service';
import { PuzzleService } from 'src/app/services/puzzle-service/puzzle.service';
import { environment } from 'src/environments/environment';
import { Puzzle } from 'src/model/puzzle';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  public isAdmin:boolean;
  public puzzles:Puzzle[];
  public page:number;
  public size:number;
  public totalPages:number;


  constructor(public puzzleService:PuzzleService, public cartService:CartService, public router:Router) { 
    this.puzzles = [];
    this.page = 0;
    this.size = 6;
    this.totalPages = 0;
    this.findPuzzles();
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

  previousPage() {
    if(this.page>0)
    {
      this.page--;
      this.findPuzzles();
    }
  }

  nextPage() {
    if(this.page<this.totalPages-1)
    {
      this.page++;
      this.findPuzzles();
    }
    
  }

  findPuzzles(){
    this.puzzleService.findAll(this.page, this.size).subscribe(
      (response : any) => {
        this.puzzles = response.content;
        this.totalPages = response.totalPages;
      }
    );
  }
}
