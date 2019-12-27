import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LeilaoService {


  constructor(private http: HttpClient) { }


  // getLeilaoById(id: number): Observable<{}>{
  //   return this.http.get("mirifici/product/" + id);
  // }

  getLeilaoById(id: number): Observable<{}> {
    return this.http.get("../assets/" + id + ".json");
  }

  getAllLeiloes() {
    return this.http.get("../assets/leiloes.json");
  }

  // getDataLeilaoById(id: number): Observable<{}> {
  //   return this.http.get("mirifici/auction/" + id);
  // }
}
