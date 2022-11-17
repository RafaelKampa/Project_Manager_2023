export class UsuarioModel {
    public idUsuario?: number;
    public login?: string;
    public senha?: string;
    public tipoUsuario?: number;
    public nome?: string;
    public dataNascimento?: any;
    public cpf?: string;
    public enderecoResidencial?: string;
    public telefone?: string;
    public email?: string;
    public contratante?: string;
    public dataAdmissao?: Date;
    public dataDesligamento?: Date;
    public cargo?: string = '';
    public remuneracao?: number;
}