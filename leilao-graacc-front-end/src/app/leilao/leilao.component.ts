import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LeilaoService } from '../services/leilao.service';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-leilao',
  templateUrl: './leilao.component.html',
  styleUrls: ['./leilao.component.css']
})
export class LeilaoComponent implements OnInit {

  participando: boolean;
  leilao: any;
  meuId;
  meuLance = 0;
  meuUltimoLance = 0;


  constructor(private route: ActivatedRoute, private leilaoService: LeilaoService, private http: HttpClient) { }

  ngOnInit() {
    this.participando = false;
    this.route.paramMap.subscribe(params => {
      this.meuId = params.get('leilaoId');
      console.log(this.meuId);
    });


    this.leilaoService.getLeilaoById(this.meuId).subscribe(data => {
      // console.log("Local Data");
      console.log(this.meuId);
      this.leilao = data;
    });

  }

  participar() {
    this.participando = true;
  }


  darLance() {
    if (this.meuLance > this.leilao.incremento) {
      this.meuUltimoLance = this.meuLance;
    }
  }
}
