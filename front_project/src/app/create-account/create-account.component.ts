import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from '../account';
import { AccountService } from '../account.service';
import { Client } from '../client';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {
  
  id!: number;
  client: Client = new Client();
  account: Account = new Account();
  

  constructor (private accountService: AccountService,
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService) {}
  
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.clientService.getClientById(this.id).subscribe( data =>{
      this.client = data;
    }, error => console.log(error));
  }

  saveAccount(){
    this.accountService.createAccount(this.account,this.id).subscribe( data =>{
      console.log(data);
      this.goToAccountsList(this.id);
    },
    error => console.log(error));
    
  }

  goToAccountsList(id: number){
    this.router.navigate(['client', id, 'accounts'])
  }

  onSubmit() {
    console.log(this.account);
    this.saveAccount();
  }

}
