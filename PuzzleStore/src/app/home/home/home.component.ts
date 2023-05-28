import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Person } from 'src/model/person';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public isHidden:boolean;

  constructor(){
    let user = localStorage.getItem("rol");
    if(user == environment.adminRol){
      this.isHidden = false;
    }
    else{
      this.isHidden = true;
    }
   }

  ngOnInit(): void {
  }

}
