package Agencia;

import java.util.*;
public abstract class Utilizador implements java.io.Serializable{
    protected String id;
    protected String nome;
    protected long nif;
    protected String morada;
    protected long telefone;
    protected String email;
    protected String password;

    public Utilizador(String id, String nome, long nif, String morada, long telefone, String email, String password) {
        this.id = id;
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setNif(long nif) {
        this.nif = nif;
    }
    public void setMorada(String morada) {
        this.morada = morada;
    }
    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public abstract String getTipo();

    //LOGIN

    /**
     * Funcao para verificar se a password passada como argumento corresponde a devido user_id.
     * @param pw Password a confirmar.
     * @return Retorna o utilizador caso se verifique, null caso contrario.
     */
    public Utilizador checklogin(String pw) {
        if (Objects.equals(this.password, pw)) {
            return this;
        } else return null;
    }
    //MENU
    protected abstract void menu(List<Utilizador> lista_u, List<Viagem> lista_v,List<Viagem> lista_v_r, List<Autocarro> lista_a, Data d_inicial);
    @Override
    public String toString() {
        return "Utilizador{" +
                "email='" + email + '\'' +
                ", telefone=" + telefone +
                ", morada='" + morada + '\'' +
                ", nif=" + nif +
                ", nome='" + nome + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
