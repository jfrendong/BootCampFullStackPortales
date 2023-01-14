import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Account } from '../account';
import { AccountService } from '../account.service';
import { Client } from '../client';


@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {

  id!: number;
  client!: Client;
  idA!: number;
  accounts!: Account[];

  constructor (private route: ActivatedRoute,
    private accountService: AccountService,
    private router: Router
    ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.idA = this.route.snapshot.params['idA'];

    this.client = new Client();
    
    this.getAccounts(this.id);
  }

  private getAccounts(id: number) {
    this.accountService.getAccountsList(id).subscribe(data => {
      this.accounts = data;
    });
  }

  createTransaction (idA:number) {
    this.router.navigate(['create-transaction/client', this.id, 'account', idA, 'transactions'])
  }

  accountState(idA:number) {
    this.router.navigate(['client', this.id, 'account', idA, 'transactions']);
  }

  updateAccount(idA: number){
   this.router.navigate(['update-account/client', this.id, 'account', idA]); 
  }
  
}
