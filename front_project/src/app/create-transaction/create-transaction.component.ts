import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Transaction } from '../transaction';
import { TransactionService } from '../transaction.service';

@Component({
  selector: 'app-create-transaction',
  templateUrl: './create-transaction.component.html',
  styleUrls: ['./create-transaction.component.css']
})
export class CreateTransactionComponent implements OnInit {
  
  id!: number;
  idA!: number;
  transaction: Transaction = new Transaction();

  constructor(private transactionService: TransactionService,
    private route: ActivatedRoute,
    private router: Router) {}
  
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.idA = this.route.snapshot.params['idA'];
  }

  saveTransaction(){
    this.transactionService.createTransaction(this.transaction, this.id, this.idA).subscribe( data =>{
      console.log(data);
      this.goToAccountsList(this.id, this.idA)
    },
    error => console.log(error));
  }

  goToAccountsList(id:number, idA: number){
    this.router.navigate(['client', id, 'account', idA, 'transactions'])
  }

  onSubmit() {
    console.log(this.transaction);
    this.saveTransaction();
  }

}
