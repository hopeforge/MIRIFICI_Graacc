import { Component, OnInit } from '@angular/core';
import { CadastroService } from '../services/cadastro.service';
import { Usuario } from '../usuario';


@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  nickName: string = '';
  nome: string = '';
  ultimoNome: string = '';
  email: string = '';
  telefone: string = '';
  cpf: string = '';
  dataNascimento: string = '';
  password: string = '';

  temCamposVazios: boolean = false;
  cadastroComSucesso: boolean = false;

  usuario: Usuario;

  constructor(private cadastroService: CadastroService) { }

  ngOnInit() {

  }


  cadastrar() {
    if (this.validaCampos()) {
      this.temCamposVazios = true;
      return;
    }else{

      this.usuario = {
        idCustomer: null,
        cpf: this.cpf,
        name: this.nome,
        lastName: this.ultimoNome,
        bornDate: this.dataNascimento,
        nick: this.nickName
      }

      this.cadastroService.postNewUser(this.usuario).subscribe(result => {
        this.cadastroComSucesso = true;
      }, erro => {});
    }

  }

  reset() {
    this.temCamposVazios = false;
  }

  validaCampos() {
    if (this.nickName || this.nome || this.ultimoNome
      || this.email || this.telefone || this.cpf ||
      this.dataNascimento == null) {
      return false;
    }
    return true;
  }
}
