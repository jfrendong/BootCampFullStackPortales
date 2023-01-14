import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { ClientListComponent } from './client-list/client-list.component';
import { AppRoutingModule } from './app-routing.module';
import { CreateClientComponent } from './create-client/create-client.component';
import { FormsModule } from '@angular/forms';
import { UpdateClientComponent } from './update-client/update-client.component';
import { AccountListComponent } from './account-list/account-list.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { UpdateAccountComponent } from './update-account/update-account.component';
import { CreateTransactionComponent } from './create-transaction/create-transaction.component';
import { TransactionListComponent } from './transaction-list/transaction-list.component';
import { HomeComponent } from './home/home.component';
import { ProfileJfrgComponent } from './profile-jfrg/profile-jfrg.component'

@NgModule({
  declarations: [
    AppComponent,
    ClientListComponent,
    CreateClientComponent,
    UpdateClientComponent,
    AccountListComponent,
    CreateAccountComponent,
    UpdateAccountComponent,
    CreateTransactionComponent,
    TransactionListComponent,
    HomeComponent,
    ProfileJfrgComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
