export class ServicosModel{
    public idServico: number = 0;
    public tipoServico: string = "";
    public valorUnitario: number = 0;
    public dimensao: number = 0;
    public unidadeMedida: string = "";
    public centroDeCusto: string = "";
    public localExecucao: string = "";
    public executor: string = "";
    public conferente: string = "";
    public dataInicio: Date = new Date();
    public previsaoTermino: Date = new Date();
    public dataFinal: Date = new Date();
    public valorTotal: number = 0;
    public obs?: any;
    public indConcluido?: Boolean;
    public idAvaliacao?: number = 0;
}