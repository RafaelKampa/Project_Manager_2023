import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService {
  private _isLoggedIn$ = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this._isLoggedIn$.asObservable();

  constructor(private apiService: ApiService) {
    const token = localStorage.getItem('token');
    this._isLoggedIn$.next(!!token);
  }

  login(username: string, password: string) {
    return this.apiService.login(username, password).pipe(
      tap((response: any) => {
        this._isLoggedIn$.next(true);
        localStorage.setItem('token', response.token);
      })
    );
  }
}