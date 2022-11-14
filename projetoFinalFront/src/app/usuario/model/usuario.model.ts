export class UsuarioModel {
    id: number = 0;
    login: string = '';
    senha: string = '';
    tipoUsuario: number = 2;
    nome: string = '';
    dataNascimento: Date = new Date();
    cpf: string = '';
    enderecoResidencial: string = '';
    telefone: string = '';
    email: string = '';
    contratante: string = '';
    dataAdmissao: Date = new Date();
    dataDesligamento: Date = new Date();
    cargo: string = '';
    remuneracao: number = 0;
}