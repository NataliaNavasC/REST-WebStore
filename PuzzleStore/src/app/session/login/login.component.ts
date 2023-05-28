import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonService } from 'src/app/services/person-service/person.service';
import { SessionService } from 'src/app/services/session-service/session.service';
import { Person } from 'src/model/person';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username:string;
  public password:string;
  public invalidCredentials:boolean;
  constructor(public sessionService:SessionService, public personService:PersonService, private router:Router) {
    this.username = "";
    this.password = "";
    this.invalidCredentials = false;
   }

  ngOnInit(): void {
  }

  // This method delegate the credentials validation to the session service
  public login()
  {
    this.sessionService.login(this.username,this.password).subscribe( 
    (resp) => {
      sessionStorage.setItem('token', resp.headers.get('Authorization') || "");

      if(this.loginSuccess())
      {
        let user = this.personService.findByUsername(this.username).subscribe(
          (res:Person) => {
            if(res!=null){
              localStorage.setItem("user",res.username);
              localStorage.setItem("rol",res.rol.name);
              this.router.navigate(['home']);
            }
          });
      }else{
        this.invalidCredentials = true;
      }
    });
    
  }

  private loginSuccess(){
    let token:string = sessionStorage.getItem('token') || "";
    if(token === ""){
      return false;
    }
    return true;
  }
  // This method redirects to the signUp page 
  public signUp(){
    this.router.navigate(['signup']);
  }
}
