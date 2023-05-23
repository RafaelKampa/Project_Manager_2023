export class AvaliacaoModel {
    public idAvaliacao: number = 0;
    public tipoServico: string = "";
    public idServico: number = 0;
    public usuExect: string = "";
    public usuConf: string = "";
    public resultado: boolean = false;
    public dataAvaliacao: Date = new Date();
    public dataReavaliacao: any = null;
    public resultReaval: any = null;
    public obs?: string = "";
}