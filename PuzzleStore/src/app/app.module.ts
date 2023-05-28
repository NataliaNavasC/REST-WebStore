import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PersonService } from './services/person-service/person.service';
import { LoginComponent } from './session/login/login.component';
import { SignupComponent } from './session/signup/signup.component';
import { ProductFormComponent } from './products/product-form/product-form.component';
import { HomeComponent } from './home/home/home.component';
import { NavegationBarComponent } from './shared/navegation-bar/navegation-bar.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { ProductComponent } from './products/product/product.component';
import { PurchaseHistoryComponent } from './purchases/purchase-history/purchase-history.component';
import { PurchaseFormComponent } from './purchases/purchase-form/purchase-form.component';
import { ShoppingcartComponent } from './purchases/shoppingcart/shoppingcart.component';
import { PurchaseDetailsComponent } from './purchases/purchase-details/purchase-details.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptorService } from './services/interceptor-service/auth-interceptor.service';
import { ProductOrderComponent } from './products/product-order/product-order/product-order.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ProductFormComponent,
    HomeComponent,
    NavegationBarComponent,
    ProductListComponent,
    ProductComponent,
    PurchaseHistoryComponent,
    PurchaseFormComponent,
    ShoppingcartComponent,
    PurchaseDetailsComponent,
    ProductOrderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [PersonService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
