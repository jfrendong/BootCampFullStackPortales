import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from '../client';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit{

  clients!: Client[];

  constructor (private clientService: ClientService,
    private router: Router) {}
  
  ngOnInit (): void {
    this.getClients();
  }

  private getClients(){
    this.clientService.getClientList().subscribe(data => {
      this.clients = data;
    });
  }

  updateClient(id: number) {
    this.router.navigate(['update-client', id]);
  }

  deleteClient(id: number){
    this.clientService.deleteClient(id).subscribe( data => {
      console.log(data);
      this.getClients();
    })
  }

  clientAccounts(id:number) {
    this.router.navigate(['client', id, 'accounts']);  
  }

  createAccount(id:number) {
    this.router.navigate(['create-account/client', id, 'accounts'])
  }

}
