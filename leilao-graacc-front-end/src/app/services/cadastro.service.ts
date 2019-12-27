import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../usuario';

@Injectable({
  providedIn: 'root'
})
export class CadastroService {

  constructor(private http: HttpClient) { }


  postNewUser(usuario: Usuario): Observable<{}>{
    return this.http.post("mirifici/customer", usuario);
  }

}
