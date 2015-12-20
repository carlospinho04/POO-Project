package Agencia;
import java.util.*;

public class Comentario implements java.io.Serializable {
    private int avalicao;
    private String comentario;
    private int cod_viagem;
    private String user_id;

    public Comentario(int avalicao, String comentario, int cod_viagem, String user_id) {
        this.avalicao = avalicao;
        this.comentario = comentario;
        this.cod_viagem = cod_viagem;
        this.user_id = user_id;
    }

    /**
     * Funcao para verificar se um comentario que se encontra na lista de comentarios de uma viagem foi feito por um determinado user.Com o ciclo while percorremos a lista de comentarios e verificamos se o user_id corresponde ao id que estamos a comparar.
     * @param id Id de um utilizador que vai servir para comparar com os autores dos comentarios de uma certa lista de comentarios.
     * @param lista_c Lista de comentarios onde vamos verificar se um user ja inseriu um comentario ou nao.
     * @return Retorna true caso o user_id for igual ao id passado como argumento, false caso contrario.
     */
    public static boolean procuraComentario(String id,List<Comentario> lista_c){
        Iterator<Comentario> it = lista_c.iterator();
        while(it.hasNext()){
            Comentario c= it.next();
                if(c.getUser_id().equals(id))
                    return true;
        }
        return false;
    }

    public int getAvalicao() {
        return avalicao;
    }

    public String getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "avalicao=" + avalicao +
                ", comentario='" + comentario + '\'' +
                ", cod_viagem=" + cod_viagem +
                ", cliente=" + user_id +
                '}';
    }
}
