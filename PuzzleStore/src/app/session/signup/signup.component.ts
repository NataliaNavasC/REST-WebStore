import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonService } from 'src/app/services/person-service/person.service';
import { Person } from 'src/model/person';
import { Role } from 'src/model/role';
import { User } from 'src/model/user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public username:string;
  public password:string;
  public confirmPassword:string;
  public showMessage:boolean;
  public message:string;

  constructor(private personService:PersonService, private router:Router) {
    this.username = "";
    this.password = "";
    this.confirmPassword = "";
    this.showMessage = false;
    this.message = "";
   }

  ngOnInit(): void {
  }

  // This method valitates the passwords and delegates the signup logic to the person service
  public signUp()
  {
    if(this.password === this.confirmPassword)
    {
      let newUser:User = new User(0, this.username, this.password, new Role(1, "USER"));
      this.personService.create(newUser).subscribe(()=>{
        alert("User created!");
        this.router.navigate(['']);
      });
      
    } 
    else{
      this.message = "Passwords do not match!";
      this.showMessage = true;
    }
  }

  // This method redirects to the login page
  public login()
  {
    this.router.navigate(['']);
  }
}