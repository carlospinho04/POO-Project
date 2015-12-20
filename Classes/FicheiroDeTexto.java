package Agencia;

import java.io.*;

public class FicheiroDeTexto {

    private BufferedReader fR;
    private BufferedWriter fW;


    /**
     * Funcao responsavel por abrir um determinado ficheiro de texto para leitura.
     * @param nomeDoFicheiro Nome do ficheiro texto a ser aberto para leitura.
     */
    public void abreLeituraT(String nomeDoFicheiro) throws IOException {
        fR = new BufferedReader(new FileReader(nomeDoFicheiro));
    }

    /**
     * Funcao responsavel por abrir um determinado ficheiro de texto para escrita.
     * @param nomeDoFicheiro Nome do ficheiro de texto a ser aberto para escrita.
     */
    public void abreEscritaT(String nomeDoFicheiro) throws IOException {
        fW = new BufferedWriter(new FileWriter(nomeDoFicheiro,true));
    }
    /**
     * Funcao responsavel por abrir um determinado ficheiro de texto para escrita em modo overwrite.
     * @param nomeDoFicheiro Nome do ficheiro de texto a ser aberto para escrita em modo overwrite.
     */
    public void abreEscritaTOW(String nomeDoFicheiro) throws IOException {
        fW = new BufferedWriter(new FileWriter(nomeDoFicheiro,false));
    }

    /**
     * Funcao responsavel por ler uma linha de um ficheiro de texto anteriormente aberto em modo de leitura.
     * @return Retornara a linha lida.
     */
    public String leLinha() throws IOException {
        return fR.readLine();
    }

    /**
     * Funcao responsavel por escrever uma linha num determinado ficheiro de texto anteriormente aberto em modo de escrita.
     * @param linha String que sera escrita num ficheiro de texto anteriormente aberto em modo de escrita.
     */
    public void escreveLinha(String linha) throws IOException {
        fW.write(linha);
    }

    /**
     * Funcao responsavel por fechar um ficheiro de texto anteriormente aberto em modo de leitura.
     */
    public void fechaLeituraT() throws IOException {
        fR.close();
    }

    /**
     * Funcao responsavel por fechar um ficheiro de texto anteriormente aberto em modo de escrita.
     */
    public void fechaEscritaT() throws IOException {
        fW.close();
    }

}