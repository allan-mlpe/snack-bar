import { Injectable } from '@angular/core';
import {RestService} from "./rest.service";

@Injectable({
  providedIn: 'root'
})
export class SnackService {

  private readonly RESOURCE_PREFIX_PATH = 'snacks';

  constructor(private restService: RestService) { }

  public getMenuSnacks() {
    return this.restService.doGet(this.RESOURCE_PREFIX_PATH);
  }
}
