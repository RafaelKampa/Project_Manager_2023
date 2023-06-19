export class AvaliacoesModel {
    public tipoServico: string = "";
    public centroDeCusto: string = "";
    public localExecucao: string = "";
    public dataAvaliacao: Date = new Date();
    public dataReavaliacao: any = null;
    public usuConf: string = "";
    public resultado: boolean = false;
    public resultReaval: any = null;
    public obs: string = "";
}