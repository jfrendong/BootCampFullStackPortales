import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from './transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseURL = "http://localhost:8090/v0/api/clients"
  
  constructor(private httpClient: HttpClient) { }

  getTransactionsList(id:number, idA: number): Observable<Transaction[]>{
    return this.httpClient.get<Transaction[]>(`${this.baseURL}/${id}/accounts/${idA}/transactions`)
  }

  createTransaction(transaction: Transaction, id: number, idA: number): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/${id}/accounts/${idA}/transactions`, transaction);
  }
}
