import { Client } from "./client";

export class Account {
    idA!: number;
    accType!: string;
    accNumber!: string;
    status!: string;
    accB!: number;
    accAvailableB!: number;
    gmf!: boolean;
    creationDate!: Date;
    creationUser!: string;
    modDate!: Date;
    modUser!: string;
    client!: Client;
}
