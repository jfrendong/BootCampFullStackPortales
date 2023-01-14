import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from './account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private baseURL = "http://localhost:8090/v0/api/clients";
  constructor(private httpClient: HttpClient) { }

  getAccountsList(id: number): Observable<Account[]>{
    return this.httpClient.get<Account[]>(`${this.baseURL}/${id}/accounts`);  
  }

  createAccount(account: Account, id: number): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/${id}/accounts`, account);
  }

  getAccountByIdA(id: number, idA:number): Observable<Account>{
    return this.httpClient.get<Account>(`${this.baseURL}/${id}/accounts/${idA}`);
  }

  updateAccount (id: number, idA: number, account: Account): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}/accounts/${idA}`, account) 
  }
}
