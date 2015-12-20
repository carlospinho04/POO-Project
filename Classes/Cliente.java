package Agencia;
import java.util.*;
import static Agencia.Agencia.*;
import static Agencia.Viagem.*;
import static Agencia.FicheiroDeObjeto.*;
import static Agencia.Comentario.*;

public abstract class Cliente extends Utilizador {

    protected List<Reserva> reservas;

    public Cliente(String id, String nome, long nif, String morada, long telefone, String email, String password) {
        super(id, nome, nif, morada, telefone, email, password);
        this.reservas= new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Funcao responsavel por gerar um menu com as opcoes que os clientes tem disponiveis.
     * @param lista_u Lista de utilizadores.
     * @param lista_v Lista de viagens por realizar.
     * @param lista_v_r Lista de viagens realizadas.
     * @param lista_a Lista de autocarros.
     * @param d_atual Data inserida no inicio do programa.
     */
    @Override
    protected void menu(List<Utilizador> lista_u, List<Viagem> lista_v, List<Viagem> lista_v_r, List<Autocarro> lista_a, Data d_atual){
        int opcao,aux;
        Reserva r_aux;
        infoReservaEspera(lista_u,lista_v,lista_v_r,d_atual);
        imprimeString("Menu Cliente");
        imprimeString("1-Consultar viagens.");
        imprimeString("2-Reservar viagem.");
        imprimeString("3-Cancelar reserva.");
        imprimeString("4-Consultar reservas.");
        imprimeString("5-Inserir comentario.");
        imprimeString("6-Listar comentarios.");
        imprimeString("0-Logout.");
        opcao=escolhaopcao(0,6);
        switch (opcao){
            case 1:
                imprimeString("Lista de viagens por realizar:");
                Viagem.listarViagem(lista_v);
                imprimeString("Lista de viagens realizadas:");
                Viagem.listarViagem(lista_v_r);
                break;
            case 2:
                imprimeString("Reservar viagem.");
                imprimeString("Insira o codigo da viagem:");
                aux=scanINT("Codigo invalido");
                r_aux=reservarViagem(aux,lista_v,lista_v_r,lista_u,d_atual);
                if(r_aux!=null)
                    imprimeReserva(r_aux);
                else
                    imprimeString("Erro a reservar viagem.");
                break;
            case 3:
                imprimeString("Cancelar reserva.");
                imprimeString("Insira o codigo da viagem:");
                aux=scanINT("Codigo invalido");
                r_aux=cancelarReserva(aux,lista_v,lista_v_r,lista_u, d_atual);
                if(r_aux!=null)
                    imprimeReserva(r_aux);
                else
                    imprimeString("Erro a cancelar reserva.");
                break;
            case 4:
                imprimeString("Lista de reservas");
                consultarReserva();
                break;
            case 5:
                imprimeString("Inserir comentario.");
                imprimeString("Insira o codigo da viagem:");
                aux=scanINT("Codigo invalido");
                Comentario c_aux;
                c_aux=inserirComentario(aux,lista_v,lista_v_r);
                if(c_aux!=null)
                    imprimeComentario(c_aux);
                else
                    imprimeString("Erro a inserir comentario.");

                break;
            case 6:
                imprimeString("Lista de comentarios.");
                imprimeString("Insira o codigo da viagem:");
                aux=scanINT("Codigo invalido");
                listarComentario(lista_v_r, aux);
                break;
            case 0:
                break;
        }
        if (opcao!=0)
            menu(lista_u,lista_v,lista_v_r,lista_a,d_atual);
    }

    protected abstract Reserva reservarViagem(int cod, List<Viagem> lista_v,List<Viagem> lista_v_r,List<Utilizador> lista_u,Data d_atual);
    protected abstract Reserva cancelarReserva(int cod, List<Viagem> lista_v,List<Viagem> lista_v_r,List<Utilizador> lista_u, Data d_atual);
    protected abstract Reserva ativaReserva(Reserva r, List<Viagem> lista_v,List<Viagem> lista_v_r, List<Utilizador> lista_u, Data d_atual);

    /**
     * Funcao responsavel por percorrer as reservas do cliente e lista-las.
     */
    private void consultarReserva(){
        for (Reserva r : this.reservas)
            imprimeReserva(r);
    }

    /**
     * Funcao responsavel por associar um comentario, feito por um cliente que participou na viagem, na mesma.Procura se existe uma viagem com o codigo passado como argumento na lista de viagens realizadas e se esse utilizador nao efetuou ja um comentario nessa viagem.Apenas e aceite avaliacao entre 1 a 5 em que 1 e o minimo e 5 o maximo.
     * @param cod Codigo da viagem na qual sera inserido o comentario.
     * @param lista_v Lista de viagens por realizar que vao ser guardadas juntamente com as viagens realizadas no momento de guardar a informacao para o ficheiro de objeto.
     * @param lista_v_r Lista de viagens realizadas, onde se encontra a viagem na qual vai ser inserido um comentario.
     * @return Retorna o cometario caso todos os requisitios sejam cumpridos, null caso contrario.
     */
    private Comentario inserirComentario(int cod,List<Viagem> lista_v,List<Viagem> lista_v_r){
        Viagem v_aux=Viagem.procurarViagem(cod,lista_v_r);
        if (v_aux!=null) {
            int encontrado=0;
            Reserva r;
            Iterator<Reserva> it = this.reservas.iterator();
            while(it.hasNext()) {
                r=it.next();
                if (r.getTipo().equals("Reserva_Aceite") && r.getCod_viagem()==cod){
                    encontrado=1;
                    break;
                }
            }
            if (encontrado==1) {
                if (procuraComentario(this.id,v_aux.getComentarios())){
                    imprimeString("Ja comentou esta viagem.");
                    return null;
                }
                String aux;
                int aval;
                imprimeString("Insira o comentario:");
                do {
                    aux = scanStr();
                }while(!verificaString(aux,"Comentario"));
                imprimeString("Insira a avaliacao(1-5):");
                do{
                    aval = scanINT("Valor invalido");
                    if (aval<1 && aval>5)
                        imprimeString("Valor entre 1-5, insira novamente:");
                }while(aval<1 && aval>5);
                Comentario com = new Comentario(aval, aux, cod, this.id);
                imprimeComentario(com);
                int index = lista_v_r.indexOf(v_aux);
                lista_v_r.get(index).addComentario(com);
                guardaViagens(lista_v,lista_v_r);
                return com;
            }
            else return null;
        }
        else {
            imprimeString("Viagem nao encontrada!");
            return null;
        }
    }

    /**
     * Funcao responsavel por percorrer e listar os comentarios de uma viagem com codigo passado como argumento e que ja tenha sido realizada.
     * @param lista_v_r Lista de viagens realizadas.
     * @param cod Codigo da viagem a pesquisar.
     */
    private void listarComentario(List<Viagem> lista_v_r, int cod){
        Viagem v_aux=Viagem.procurarViagem(cod,lista_v_r);
        if (v_aux!=null) {
            for(Comentario c:v_aux.getComentarios())
            imprimeComentario(c);
        }
        else
            imprimeString("Viagem não encontrada");
    }

    /**
     * Funcao responsavel por alertar os clientes que tem uma certa reserva em espera, no momento em que fazem o login, caso essa viagem ja se encontre com pelo menos um lugar livre.Tendo o cliente opcao de ativar a sua reserva ou nao.Caso a resposta seja positiva, com a funcao ativaReserva, o tipo de reserva passa de "Espera" a "Aceite".
     * @param lista_u Lista de utilizadores.
     * @param lista_v Lista de viagens por realizar.
     * @param lista_v_r Lista de viagens realizadas.
     * @param d_atual Data inserida no inicio do programa.
     */
    private void infoReservaEspera(List<Utilizador> lista_u,List<Viagem> lista_v, List<Viagem> lista_v_r, Data d_atual){
        for (Reserva r : this.reservas) {
            Viagem v = Viagem.procurarViagem(r.getCod_viagem(), lista_v);
            if (v != null) {
                if (r.getTipo().equals("Reserva_Espera") && r.getData().comparaDatas(v.getData()) < 0) {
                    int n = v.lugaresLivres();
                    if (n > 0) {
                        imprimeString("A viagem " + String.valueOf(v.getCodigo()) + " ja se encontra disponivel!");
                        imprimeString("Deseja ativar a sua reserva e fazer o pagamento?");
                        imprimeString("1-Sim.");
                        imprimeString("2-Não.");
                        int opt;
                        do {
                            opt = scanINT("Opcao invalida");
                            if (opt < 1 || opt > 2)
                                imprimeString("Opcao invalida");
                        } while (opt < 1 || opt > 2);
                        if (opt == 1) {
                            Reserva r_aux = ativaReserva(r, lista_v, lista_v_r, lista_u, d_atual);
                            imprimeReserva(r_aux);
                        }
                    }
                }
            }
        }
    }
}
