import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountListComponent } from './account-list/account-list.component';
import { ClientListComponent } from './client-list/client-list.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { CreateClientComponent } from './create-client/create-client.component';
import { CreateTransactionComponent } from './create-transaction/create-transaction.component';
import { HomeComponent } from './home/home.component';
import { ProfileJfrgComponent } from './profile-jfrg/profile-jfrg.component';
import { TransactionListComponent } from './transaction-list/transaction-list.component';
import { UpdateAccountComponent } from './update-account/update-account.component';
import { UpdateClientComponent } from './update-client/update-client.component';

const routes: Routes = [
  
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'clients', component: ClientListComponent},
  {path: 'create-client', component: CreateClientComponent},
  {path: 'update-client/:id', component: UpdateClientComponent},
  {path: 'client/:id/accounts', component: AccountListComponent},
  {path: 'create-account/client/:id/accounts', component: CreateAccountComponent},
  {path: 'update-account/client/:id/account/:idA', component: UpdateAccountComponent},
  {path: 'client/:id/account/:idA/transactions', component: TransactionListComponent},
  {path: 'create-transaction/client/:id/account/:idA/transactions', component: CreateTransactionComponent},
  {path: 'jfrg-profile', component: ProfileJfrgComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
