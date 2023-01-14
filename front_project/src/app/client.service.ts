import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Client } from './client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private baseUrl = "http://localhost:8090/v0/api/clients";
  constructor(private httpClient: HttpClient) { }

  getClientList(): Observable<Client[]>{
    return this.httpClient.get<Client[]>(`${this.baseUrl}`);
  }

  createClient(client: Client): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}`, client);
  }

  getClientById(id: number): Observable<Client>{
    return this.httpClient.get<Client>(`${this.baseUrl}/${id}`);
  }

  updateClient(id: number, client: Client): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`, client)
  }

  deleteClient(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}
