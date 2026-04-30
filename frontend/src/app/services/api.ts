import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private BASE_URL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // Sesión
  login(nombre: string, contrasenia: string): Observable<any> {
    return this.http.post(`${this.BASE_URL}/usuarios/login`, { nombre, contrasenia });
  }

  // Genéricos
  getAll(endpoint: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.BASE_URL}/${endpoint}`);
  }
  getById(endpoint: string, id: any): Observable<any> {
    return this.http.get(`${this.BASE_URL}/${endpoint}/${id}`);
  }
  post(endpoint: string, body: any): Observable<any> {
    return this.http.post(`${this.BASE_URL}/${endpoint}`, body);
  }
  put(endpoint: string, id: any, body: any): Observable<any> {
    return this.http.put(`${this.BASE_URL}/${endpoint}/${id}`, body);
  }
  delete(endpoint: string, id: any): Observable<any> {
    return this.http.delete(`${this.BASE_URL}/${endpoint}/${id}`);
  }
}