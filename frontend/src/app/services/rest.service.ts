import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  private readonly API_URL = 'http://localhost:8000/'

  constructor(private httClient: HttpClient) { }

  public doGet(path: string): Observable<any> {
    const resourceUrl = this.buildResourceUrl(path);
    const request = this.httClient.get(resourceUrl)

    return this.handleRequestResponse(request);
  }

  public doPost(path: string, payload: object): Observable<any> {
    const resourceUrl = this.buildResourceUrl(path);
    return this.httClient.post(resourceUrl, payload);
  }

  private buildResourceUrl(path: string) {
    return `${this.API_URL}/${path}`;
  }

  private handleRequestResponse(request): Observable<any> {
    return request.pipe(
        catchError((err) => {
            throw this.handleError(err);
        })
    );
  }

  private handleError(error: HttpErrorResponse) {
    const message = error.message || 'Unknown error';
    const status = error.status;

    return {
        status,
        message
    };
  }
}
