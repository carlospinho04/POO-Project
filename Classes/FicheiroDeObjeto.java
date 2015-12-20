package Agencia;

import java.io.*;
import java.util.List;
import static Agencia.Agencia.*;

public class FicheiroDeObjeto {
    private ObjectInputStream iS;
    private ObjectOutputStream oS;

    /**
     * Funcao responsavel por abrir o ficheiro, com o nome passado como atributo, para leitura.
     * @param nomeDoFicheiro Nome do ficheiro a ser aberto para leitura.
     * @return Retornara true caso a operacao seja executada com sucesso, false caso contrario.
     */
    public boolean abreLeitura(String nomeDoFicheiro) {
        try{
            iS = new ObjectInputStream(new FileInputStream(nomeDoFicheiro));
            return true;
        } catch (IOException e){
            return false;
        }
    }

    /**
     * Funcao responsavel por abrir o ficheiro, com o nome passado como atributo, para escrita em modo overwrite.
     * @param nomeDoFicheiro Nome do ficheiro a ser aberto para escrita.
     */
    public void abreEscritaOW(String nomeDoFicheiro) throws IOException {
        oS = new ObjectOutputStream(new FileOutputStream(nomeDoFicheiro,false));
    }

    /**
     * Funcao responsavel pela leitura de um objeto do ficheiro anteriormente aberto para leitura.
     * @return Retorna o objeto lido de um certo ficheiro
     */
    public Object leObjeto() throws IOException, ClassNotFoundException {
        return iS.readObject();
    }

    /**
     * Funcao responsavel pela escrita de um objeto em um determinado ficheiro anteriormente aberto para escrita.
     * @param o Objeto a ser escrito em um determinado ficheiro.
     */
    public void escreveObjeto(Object o) throws IOException {
        oS.writeObject(o);
    }

    /**
     * Funcao responsavel por fechar um ficheiro anteriormente aberto para leitura.
     */
    public void fechaLeitura() throws IOException {
        iS.close();
    }

    /**
     * Funcao responsavel por fechar um ficheiro anteriormente aberto para escrita.
     */
    public void fechaEscrita() throws IOException {
        oS.close();
    }

    /**
     * Funcao responsavel por carregar utilizadores de um ficheiro de objeto do tipo Utilizador chamado "utilizadores.txt" para a lista de utilizadores passada como argumento.
     * @param lista Lista na qual os utilizadores carregados de um ficheiro serao guardados.
     */
    public static void carregaUtilizadores(List<Utilizador> lista){
        FicheiroDeObjeto file=new FicheiroDeObjeto();
        file.abreLeitura("utilizadores.txt");
        while(true){
            try {
                Utilizador aux = (Utilizador) file.leObjeto();
                lista.add(aux);
            } catch (EOFException e) {
                break;
            } catch (ClassNotFoundException | IOException e) {
                break;
            } catch (NullPointerException e){
                break;
            }
        }
        try {
            file.fechaLeitura();
        } catch (IOException | NullPointerException e) {
            imprimeString("Nao existem utilizadores");
        }

    }

    /**
     * Funcao responsavel por guardar a lista passada como argumento para um ficheiro de objeto do tipo Utilizador de nome "utilizadores.txt".
     * @param lista Lista que contem os utilizadores a serem guardados num ficheiro.
     */
    public static void guardaUtilizadores(List<Utilizador> lista){
        FicheiroDeObjeto file = new FicheiroDeObjeto();
        try {
            file.abreEscritaOW("utilizadores.txt");
        } catch (IOException e) {
            imprimeString("Erro a abrir o ficheiro de utilizadores.txt");
        }
        for (Utilizador u : lista)
            try {
                file.escreveObjeto(u);
            } catch (IOException e) {
                imprimeString("Erro a escrever no ficheiro de utilizadores.txt");
            }
        try {
            file.fechaEscrita();
        } catch (IOException e) {
            imprimeString("Erro a fechar o ficheiro de utilizadores.txt");
        }
    }
    /**
     * Funcao responsavel por carregar viagens de um ficheiro de objeto do tipo Viagem chamado "viagens.txt" para a lista de viagens passada como argumento.
     * @param lista Lista na qual as viagens carregadas de um ficheiro serao guardadas.
     */
    public static void carregaViagens(List<Viagem> lista, List<Viagem> lista_r, Data d_atual){
        FicheiroDeObjeto file=new FicheiroDeObjeto();
        file.abreLeitura("viagens.txt");
        while(true){
            try {
                Viagem v_aux = (Viagem) file.leObjeto();
                Data d_aux=v_aux.getData();
                if (d_aux.comparaDatas(d_atual)==1)
                    lista.add(v_aux);
                else
                    lista_r.add(v_aux);
            } catch (EOFException e) {
                break;
            } catch (ClassNotFoundException | IOException e) {
                break;
            } catch (NullPointerException e){
                break;
            }
        }
        try {
            file.fechaLeitura();
        } catch (IOException | NullPointerException e) {
            imprimeString("Nao existem viagens");
        }

    }
    /**
     * Funcao responsavel por guardar a lista passada como argumento para um ficheiro de objeto do tipo Viagem de nome "viagem.txt".
     * @param lista Lista que contem as viagens a serem guardadas num ficheiro.
     */
    public static void guardaViagens(List<Viagem> lista, List<Viagem> lista_r){
        FicheiroDeObjeto file = new FicheiroDeObjeto();
        try {
            file.abreEscritaOW("viagens.txt");
        } catch (IOException e) {
            imprimeString("Erro a abrir o ficheiro de viagens.txt");
        }
        for (Viagem u : lista)
            try {
                file.escreveObjeto(u);
            } catch (IOException e) {
                imprimeString("Erro a escrever para o ficheiro de viagens.txt");
            }
        for (Viagem u_r : lista_r)
            try {
                file.escreveObjeto(u_r);
            } catch (IOException e) {
                imprimeString("Erro a escrever para o ficheiro de viagens.txt");
            }
        try {
            file.fechaEscrita();
        } catch (IOException e) {
            imprimeString("Erro a fechar o ficheiro de viagens.txt");
        }
    }
    /**
     * Funcao responsavel por carregar autocarros de um ficheiro de objeto do tipo Autocarro chamado "autocarro.txt" para a lista de autocarros passada como argumento.
     * @param lista Lista na qual os autocarros carregados de um ficheiro serao guardados.
     */
    public static void carregaAutocarros(List<Autocarro> lista){
        FicheiroDeObjeto file=new FicheiroDeObjeto();
        file.abreLeitura("autocarros.txt");
        while(true){
            try {
                Autocarro aux = (Autocarro) file.leObjeto();
                lista.add(aux);
            } catch (EOFException e) {
                break;
            } catch (ClassNotFoundException | IOException e) {
                break;
            } catch (NullPointerException e){
                break;
            }
        }
        try {
            file.fechaLeitura();
        } catch (IOException | NullPointerException e) {
            imprimeString("Nao existem autocarros");
        }

    }
    /**
     * Funcao responsavel por guardar a lista passada como argumento para um ficheiro de objeto do tipo Autocarro de nome "autocarros.txt".
     * @param lista Lista que contem os autocarros a serem guardados num ficheiro.
     */
    public static void guardaAutocarros(List<Autocarro> lista){
        FicheiroDeObjeto file = new FicheiroDeObjeto();
        try {
            file.abreEscritaOW("autocarros.txt");
        } catch (IOException e) {
            imprimeString("Erro a abrir o ficheiro de autocarros.txt");
        }
        for (Autocarro u : lista)
            try {
                file.escreveObjeto(u);
            } catch (IOException e) {
                imprimeString("Erro a escrever para o ficheiro de autocarros.txt");
            }
        try {
            file.fechaEscrita();
        } catch (IOException e) {
            imprimeString("Erro a fechar o ficheiro de autocarros.txt");
        }
    }
}