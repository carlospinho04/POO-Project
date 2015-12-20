package Agencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static Agencia.Agencia.imprimeViagem;

public class Viagem  implements java.io.Serializable{
    private int codigo;
    private String origem;
    private String destino;
    private double preco;
    private List<Autocarro> autocarros;
    private Data data;
    private int duracao;
    private List<Comentario> comentarios;
    private List<Character> lugares;

    public Viagem(int codigo, String origem, String destino, double preco, List<Autocarro> autocarros, Data data, int duracao, List<Character> lugares) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.preco = preco;
        this.autocarros = autocarros;
        this.data = data;
        this.duracao = duracao;
        this.comentarios = new ArrayList<>();
        this.lugares = lugares;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Autocarro> getAutocarros() {
        return autocarros;
    }

    public void setAutocarros(List<Autocarro> autocarros) {
        this.autocarros = autocarros;
    }

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void addComentario(Comentario com){
            this.comentarios.add(com);
    }
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public List<Character> getLugares() {
        return lugares;
    }
    public void setLugares(List<Character> lugares) {
        this.lugares = lugares;
    }

    /**
     * Funcao responsavel pela ocupacao de um lugar disponivel.Vai percorrer os lugares de uma viagem ate encontrar um lugar vago, assim que o encontre ocupa-o.
     * @return Retorna a posicao do lugar caso exista algum disponivel, -1 caso contrario.
     */
    public int ocupaLugar(){
        int i=0;
            for (char c : this.lugares) {
                if (c!='O') {
                    this.lugares.set(i, 'O');
                    return i;
                }
                i++;
            }
        return -1;
    }

    /**
     * Funcao responsavel pela desocupacao de um lugar passado como argumento.
     * @param n_l Numero do lugar a desocupar.
     */
    public void desocupaLugar(int n_l){
        this.lugares.set(n_l,'C');
    }

    /**
     * Funcao responsavel por calcular a media da classificacao de uma certa viagem.
     * @return Retorna a media caso exista pelo menos uma classificacao, caso contrario retorna 0.
     */
    public float calculaMedia(){
        float media_aux=0;
        int i=0;
        for(Comentario coment_aux:this.comentarios){
            i++;
            media_aux+=coment_aux.getAvalicao();
        }
        if(i!=0 && media_aux!=0)
            return media_aux/i;
        else
            return 0;
    }

    /**
     * Funcao responsavel por procurar uma viagem(id) passada como argumento na lista de viagens.Com o ciclo while percorremos a lista ate encontrar a viagem com id identico aquele passado como argumento.
     * @param cod Codigo da viagem a ser procurada.
     * @param lista_v Lista de viagens onde a viagem vai ser procurada.
     * @return Retorna a viagem caso esta seja encontrada, null caso contrario.
     */
    public static Viagem procurarViagem(int cod,List<Viagem> lista_v){
        int encontrado=0;
        Viagem vg = null;
        Iterator<Viagem> it = lista_v.iterator();
        while(it.hasNext() && encontrado!=1) {
            vg= it.next();
            if (vg.getCodigo() == cod)
                encontrado = 1;
        }
        if (encontrado == 1){
            return vg;
        }
        else return null;
    }

    /**
     * Funcao responsavel por percorrer a lista de viagens e lista-las.
     * @param lista_v Lista de viagens a ser listada.
     */
    public static void listarViagem(List<Viagem> lista_v){
        for (Viagem v : lista_v)
            imprimeViagem(v);
    }

    /**
     * Funcao reponsavel pelo calculo do numero de lugares livres numa viagem.Percorre todos os lugares dessa viagem, caso o lugar nao esteja ocupado incrementa a variavel a ser retornada.
     * @return Retorna o numero de lugares livres numa viagem.
     */
    public int lugaresLivres(){
        int i=0;
        for (char c : this.lugares) {
            if (c!='O') {
                i++;
            }
        }
        return i;
    }


    @Override
    public String toString() {
        return "Viagem{" +
                "codigo=" + codigo +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", preco=" + preco +
                ", autocarros=" + autocarros +
                ", data=" + data +
                ", duracao=" + duracao +
                ", comentarios=" + comentarios +
                ", lugares=" + lugares +
                '}';
    }


}

