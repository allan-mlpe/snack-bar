import { Injectable } from '@angular/core';
import {RestService} from "./rest.service";

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  private readonly RESOURCE_PREFIX_PATH = 'ingredients';

  constructor(private restService: RestService) { }

  public getIngredients() {
    return this.restService.doGet(this.RESOURCE_PREFIX_PATH);
  }
}
