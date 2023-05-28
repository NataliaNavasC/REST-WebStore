import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class AdminGuard implements CanActivate {

  constructor(private router:Router){  }

  private redirect(){
    this.router.navigate(['']);
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let rol = localStorage.getItem("rol");
    let status:boolean;
    if ( rol != null && rol == environment.adminRol) {
      status = true;
    }
    else{
      this.redirect();
      status = false;
    }
    return status;
  }
  
}
