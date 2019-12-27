import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { LeilaoService } from '../services/leilao.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  leiloes: any;
  
  constructor(private leilaoService: LeilaoService) { }

  ngOnInit() {
    this.leilaoService.getAllLeiloes().subscribe(data => {
      //console.log("Local Data");
      console.log(data);

      this.leiloes = data;
    })

  }

}
