package Agencia;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Autocarro implements java.io.Serializable{
    private String matricula;
    private int capacidade;

    public Autocarro(String matricula, int capacidade) {
        this.matricula = matricula;
        this.capacidade = capacidade;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return "Autocarro{" +
                "matricula='" + matricula + '\'' +
                ", capacidade=" + capacidade +
                '}';
    }

    /**
     * Funcao responsavel pela procura de um autocarro(matricula) na lista de autocarros existentes.Com um ciclo while vamos percorrer a lista de autocarros passada como argumento e verificar se existe uma matricula igual aquela que estamos a procura.
     * @param matricula Matricula a procurar na lista de autocarros.
     * @param lista_a Lista de autocarros, onde se vai procurar se um autocarro com uma determinada matricula se encontra na mesma.
     * @return Caso exista e retornado o autocarro, caso contrario e retornado null.
     */
    public static Autocarro procuraAutocarro(String matricula,List<Autocarro> lista_a){
        int encontrado=0;
        if (lista_a!=null) {
            Autocarro autocarro = null;
            Iterator<Autocarro> it = lista_a.iterator();
            while (it.hasNext() && encontrado != 1) {
                autocarro = it.next();
                if (Objects.equals(autocarro.getMatricula().toLowerCase(), matricula.toLowerCase()))
                    encontrado = 1;
            }
            if (encontrado == 1) {
                return autocarro;
            }
        }
        return null;
    }

    /**
     * Funcao que vai verificar se um autocarro esta associado a uma viagem.Percorre a lista de viagens passada como argumento, para cada viagem verifica os autocarros associados a mesma.
     * @param lista_v Lista de viagens existentes.
     * @return Se a matricula corresponder a matricula que estamos a verificar e retornado true,caso contrario retorna false.
     */
    public boolean autocarroOcupado(List<Viagem> lista_v){
        for (Viagem v:lista_v){
            for (Autocarro a:v.getAutocarros()){
                if(a.getMatricula().equals(this.matricula)){
                    return true;
                }
            }
        }
        return false;
    }
}
