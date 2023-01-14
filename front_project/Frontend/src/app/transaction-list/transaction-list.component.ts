import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Transaction } from '../transaction';
import { TransactionService } from '../transaction.service';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent implements OnInit {
  
  id!: number;
  idA!: number;
  transactions!: Transaction[];

  constructor(private transactionService: TransactionService,
    private route: ActivatedRoute) {}
  
  ngOnInit(): void {
    
    this.id = this.route.snapshot.params['id'];

    this.idA = this.route.snapshot.params['idA'];
    
    this.getTransactions(this.id, this.idA);
  }

  private getTransactions(id: number, idA: number) {
    this.transactionService.getTransactionsList(id, idA).subscribe(data =>{
      this.transactions = data;
    });
  }  
}
