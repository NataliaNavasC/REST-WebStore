import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from './guards/admin.guard';
import { SessionGuard } from './guards/session.guard';
import { HomeComponent } from './home/home/home.component';
import { ProductFormComponent } from './products/product-form/product-form.component';
import { PurchaseDetailsComponent } from './purchases/purchase-details/purchase-details.component';
import { PurchaseFormComponent } from './purchases/purchase-form/purchase-form.component';
import { PurchaseHistoryComponent } from './purchases/purchase-history/purchase-history.component';
import { ShoppingcartComponent } from './purchases/shoppingcart/shoppingcart.component';
import { LoginComponent } from './session/login/login.component';
import { SignupComponent } from './session/signup/signup.component';

const routes: Routes = [
  {path:'', component:LoginComponent},
  {path:'signup', component:SignupComponent},
  {path:'product-form/:id', component:ProductFormComponent,canActivate:[SessionGuard,AdminGuard]},
  {path:'product-form', component:ProductFormComponent,canActivate:[SessionGuard, AdminGuard]},
  {path:'home', component:HomeComponent,canActivate:[SessionGuard]},
  {path:'purchase-history', component:PurchaseHistoryComponent, canActivate:[SessionGuard]},
  {path:'cart', component:ShoppingcartComponent, canActivate:[SessionGuard]},
  {path:'purchase/:id', component:PurchaseDetailsComponent, canActivate:[SessionGuard]},
  {path:'purchase-form', component:PurchaseFormComponent, canActivate:[SessionGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
