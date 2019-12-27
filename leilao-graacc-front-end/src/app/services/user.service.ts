import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Leilao } from '../leilao';
import { post } from 'selenium-webdriver/http';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  leilao: any;
  constructor(private http: HttpClient) {}

  getAllLeiloes() {
    return this.http.get("mirifici/product");
  }

  // alguns métodos deverão ser adaptados de get() para post()
  getLeilaoById(id): any {
    //a string do get("") deve ser mudada para concatenar com o parâmetro id recebido pela função getLeilaoById(id)
    return this.http.get("../assets/leilao.json");
  }

}



