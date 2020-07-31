import { Injectable } from '@angular/core';
import {RestService} from "./rest.service";
import {Snack} from '../model/Snack'

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private readonly RESOURCE_PREFIX_PATH = 'orders';

  constructor(private restService: RestService) { }

  public submitOrder(snack: Snack) {
    return this.restService.doPost(this.RESOURCE_PREFIX_PATH, snack);
  }
}
