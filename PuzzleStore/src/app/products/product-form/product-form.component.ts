import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { PuzzleService } from 'src/app/services/puzzle-service/puzzle.service';
import { Puzzle } from 'src/model/puzzle';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  public puzzle:Puzzle;
  public isEdit:boolean;

  constructor(public puzzleService:PuzzleService, private router:Router, private route:ActivatedRoute) {
    this.puzzle = new Puzzle(0, "", "", "", NaN);
    let idPuzzle = this.route.snapshot.paramMap.get('id');
    if(idPuzzle != null){
      this.puzzleService.find(Number(idPuzzle)).subscribe(
        (response: Puzzle) => {
          this.puzzle = response
        }
      );
      this.isEdit = true;
    }
    else{
      this.isEdit = false;
    }
  }

  ngOnInit(): void {
  }

  public onClickCreateProduct():void{
    if(this.validFields()){
      this.puzzleService.create(this.puzzle).subscribe(
        (response: Puzzle) =>{
          alert("Puzzle was created");
          this.router.navigate(['home']);
        }
      );
    }
    else{
      alert("You must complete all fields");
    }
  }

  public onClickEditProduct():void{
    if(this.validFields()){
      this.puzzleService.update(this.puzzle).subscribe(
        (response : Puzzle) => {
          alert("Item updated successfully");
          this.router.navigate(["home"]);
        }
      );
    }
    else{
      alert("You must complete all fields");
    }
  }

  public onClickDeleteProduct():void{
    this.puzzleService.delete(this.puzzle.id).subscribe(
      (response : any) => {
        alert("Item deleted successfully");
        this.router.navigate(["home"]);
      }
    );
  }

  private validFields(){
    let isValid:boolean = false;
    if(this.puzzle.name != ""){
      if(this.puzzle.description != ""){
        if(this.puzzle.imageSource != ""){
          if(this.puzzle.price != NaN && this.puzzle.price > 0){
            isValid = true;
          }
        }
      }
    }
    return isValid;
  }
}
