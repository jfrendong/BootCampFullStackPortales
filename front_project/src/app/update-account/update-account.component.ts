import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from '../account';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-update-account',
  templateUrl: './update-account.component.html',
  styleUrls: ['./update-account.component.css']
})
export class UpdateAccountComponent implements OnInit {
  
  id!: number;
  idA!: number;

  account: Account = new Account();

  constructor(private accountService: AccountService,
    private route: ActivatedRoute,
    private router: Router) {}
  
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.idA = this.route.snapshot.params['idA'];

    this.accountService.getAccountByIdA(this.id, this.idA).subscribe(data => {
      this.account = data;
    }, error => console.log(error));
  }

  goToAccountsList(id: number){
    this.router.navigate(['client', id, 'accounts'])
  }

  onSubmit() {
    this.accountService.updateAccount(this.id, this.idA, this.account).subscribe( data =>{
      this.goToAccountsList(this.id);    
    }, error => console.log(error));
  }


}
