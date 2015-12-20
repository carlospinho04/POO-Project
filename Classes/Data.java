package Agencia;

import static Agencia.Agencia.imprimeString;

public class Data implements java.io.Serializable{
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int min;

    public Data(int dia, int mes, int ano, int hora, int min) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.min = min;
    }

    /**
     * Funcao que pede ao utilizador uma data e confirma se esta e valida.
     * @return Retorna a data inserida pelo utilizador.
     */
    public static Data inserirdata(){
        int dia,mes,ano,hora,min;
        imprimeString("Insira a data:");
        imprimeString("Ano:");
        do {
            ano= Agencia.scanINT("Ano Invalido");
            if(ano < 2000 || ano > 3000)
                imprimeString("Ano invalido, insira novamente:");
        }while(ano < 2000 || ano > 3000);
        imprimeString("Mes:");
        do {
            mes= Agencia.scanINT("Mes Invalido");
            if(mes < 1 || mes > 12)
                imprimeString("Mes invalido, insira novamente:");
        }while(mes < 1 || mes > 12);
        int dia_max;
        dia_max = diasMes(mes, ano);
        imprimeString("Dia:");
        do{
            dia= Agencia.scanINT("Dia Invalido");
            if(dia < 1 || dia > dia_max)
                imprimeString("Dia invalido, insira novamente:");
        }while(dia < 1 || dia > dia_max);
        imprimeString("Hora");
        do {
            hora= Agencia.scanINT("Hora Invalida");
            if(hora < 0 || hora > 23)
                imprimeString("Hora invalida, insira novamente:");
        }while(hora < 0 || hora > 23);
        imprimeString("Minutos:");
        do {
            min= Agencia.scanINT("Minutos Invalidos");
            if(min < 0 || min > 59)
                imprimeString("Minutos invalidos, insira novamente:");
        }while(min < 0 || min > 59);
        return new Data(dia,mes,ano,hora,min);
    }

    /**
     * Compara uma determinada data com uma data passada como argumento.
     * @param d Data com a qual vai ser comparada.
     * @return Retorna 0 se as datas forem iguais, 1 se a data d for menor e -1 se a data d for maior.
     */
    public int comparaDatas(Data d){
        if (this.ano>d.getAno())
            return 1;
        else if (this.ano<d.getAno())
            return -1;
        else if (this.mes>d.getMes())
            return 1;
        else if (this.mes<d.getMes())
            return -1;
        else if (this.dia>d.getDia())
            return 1;
        else if (this.dia<d.getDia())
            return -1;
        else if (this.hora>d.getHora())
            return 1;
        else if (this.hora<d.getHora())
            return -1;
        else if (this.min>d.getMin())
            return 1;
        else if (this.min<d.getMin())
            return -1;
        else
            return 0;
    }

    /**
     * Funcao responsavel pelo calculo da diferenca entre duas datas em minutos.
     * Converte o parametro da data para minutos quando os dois sao diferentes e depois retorna a diferenca entre a soma dos parametros.
     * @param d Data
     * @return Retorna a diferenca entre as duas datas em minutos.
     */
    public int diferencaDatas(Data d){
        int data1=0;
        int data2=0;
        int aux;
        if (this.ano!=d.getAno()) {
            for (int i = 1; i < this.ano; i++) {
                if ((i % 4 == 0) && (i % 100 != 0) || (i % 400 == 0))
                    aux = 7 * 31 * 24 * 60 + 4 * 30 * 24 * 60 + 29;
                else
                    aux = 7 * 31 * 24 * 60 + 4 * 30 * 24 * 60 + 28;
                data1 += aux;
            }
            for (int j = 1; j < d.getAno(); j++) {
                if ((j % 4 == 0) && (j % 100 != 0) || (j % 400 == 0))
                    aux = 7 * 31 * 24 * 60 + 4 * 30 * 24 * 60 + 29;
                else
                    aux = 7 * 31 * 24 * 60 + 4 * 30 * 24 * 60 + 28;
                data2 += aux;
            }
        }
        if (this.mes!=d.getMes()) {
            for (int i = 1; i < this.mes; i++) {
                aux = diasMes(i, this.ano) * 24 * 60;
                data1 += aux;
            }
            for (int j = 1; j < d.getMes(); j++) {
                aux = diasMes(j, d.getAno()) * 24 * 60;
                data2 += aux;
            }
        }
        if (this.dia!=d.getDia()) {
            for (int i = 1; i < this.dia; i++) {
                aux = 24 * 60;
                data1 += aux;
            }
            for (int j = 1; j < d.getDia(); j++) {
                aux = 24 * 60;
                data2 += aux;
            }
        }
        if (this.hora!=d.getHora()) {
            for (int i = 1; i < this.hora; i++) {
                aux = 60;
                data1 += aux;
            }
            for (int j = 1; j < d.getHora(); j++) {
                aux = 60;
                data2 += aux;
            }
        }
        if (this.min!=d.getMin()) {
            for (int i = 1; i < this.min; i++) {
                data1++;
            }
            for (int j = 1; j < d.getMin(); j++) {
                data2++;
            }
        }
        return data2-data1;
    }

    /**
     * Funcao que dado o mes e o ano, retorna o numero de dias desse mes.
     * @param mes Mes pertendido.
     * @param ano Ano pertendido.
     * @return Retorna o numero de dias do mes pertendido.
     */
    public static int diasMes(int mes,int ano)
    {
        int f,dias=0;
        if((ano%4==0)&&(ano%100!=0) || (ano%400==0))
            f=29;
        else
            f=28;
        switch(mes)
        {
            case 1:
                dias=31;
                break;
            case 2:
                dias=f;
                break;
            case 3:
                dias=31;
                break;
            case 4:
                dias=30;
                break;
            case 5:
                dias=31;
                break;
            case 6:
                dias=30;
                break;
            case 7:
                dias=31;
                break;
            case 8:
                dias=31;
                break;
            case 9:
                dias=30;
                break;
            case 10:
                dias=31;
                break;
            case 11:
                dias=30;
                break;
            case 12:
                dias=31;
                break;
        }
        return dias;
    }

    @Override
    public String toString() {
        return "Data{" +
                "dia=" + dia +
                ", mes=" + mes +
                ", ano=" + ano +
                ", hora=" + hora +
                ", min=" + min +
                '}';
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public int getHora() {
        return hora;
    }

    public int getMin() {
        return min;
    }
}
