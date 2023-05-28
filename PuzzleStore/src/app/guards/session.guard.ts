import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionGuard implements CanActivate {
  constructor(private router:Router){  }

  private redirect(Logged : boolean){
    if(!Logged){
      this.router.navigate(['']);
    }
  }

  public canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let user = localStorage.getItem("user");
    if (localStorage.getItem("user") === null) {
      this.redirect(false);
      return false;
    }
    return true;
  }
  
}
