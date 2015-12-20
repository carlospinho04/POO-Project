package Agencia;

import java.util.Iterator;
import java.util.List;

import static Agencia.FicheiroDeObjeto.*;

public class Premium extends Cliente {
    public Premium(String id, String nome, long nif, String morada, long telefone, String email, String password) {
        super(id, nome, nif, morada, telefone, email, password);
    }

    /**
     * Funcao que verifica o estatuto do cliente.
     * @return Retorna "Cliente_Premium".
     */
    @Override
    public String getTipo(){
        return "Cliente_Premium";
    }

    /**
     * Funcao que tem como objetivo reservar um lugar numa viagem que ainda nao aconteceu.E verificado se o codigo da viagem existe.So sera possivel reservar um lugar na viagem.Se existir lugares vagos o cliente, por ser premium, so ira pagar 90% do valor total da viagem.
     * @param cod Codigo da viagem a efetuar uma reserva.
     * @param lista_v Lista de viagens por realizar.
     * @param lista_v_r Lista de viagens realizadas, juntamente com as viagens por realizar, serve para guardar as viagens num ficheiro depois de alterar uma reserva nela.
     * @param lista_u Lista de utilizadores que vai servir para guardar no ficheiro as alteracoes feitas num determinado cliente.
     * @param d_atual Data inserida no inicio do programa.
     * @return Retorna uma reserva da classe Aceite caso exista lugares livres, reserva da classe Espera, caso os lugares estejam todos ocupados e null caso o codigo da viagem nao exista na lista de viagens por realizar.
     */
    @Override
    protected Reserva reservarViagem(int cod, List<Viagem> lista_v,List<Viagem> lista_v_r, List<Utilizador> lista_u, Data d_atual) {
        Viagem v_aux=Viagem.procurarViagem(cod,lista_v);
        if(v_aux!=null){
            boolean flag=false;
            for(Reserva r:this.reservas){
                if (r.getCod_viagem()==cod && !r.getTipo().equals("Reserva_Cancelada")){
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                int lugar = v_aux.ocupaLugar();
                if (lugar != -1) {
                    Reserva r_aux = new Aceite(this.id, v_aux.getCodigo(), lugar + 1, v_aux.getPreco()*0.9, d_atual);
                    this.reservas.add(r_aux);
                    guardaUtilizadores(lista_u);
                    guardaViagens(lista_v, lista_v_r);
                    return r_aux;
                } else {
                    Reserva r_aux = new Espera(this.id, v_aux.getCodigo(), 0, 0, d_atual);
                    this.reservas.add(r_aux);
                    guardaUtilizadores(lista_u);
                    guardaViagens(lista_v, lista_v_r);
                    return r_aux;
                }
            }
        }
        return null;
    }

    /**
     * Funcao responsavel pelo cancelamento de uma reserva de um utilizador premium.Primeiro e verificada a existencia da viagem passada como argumento, caso exista verificamos a data na qual o cancelamento esta a ser efetuado, caso a diferenca de datas seja superior a dois dias e a reserva seja da classe Aceite e retornado o valor total da reserva, e o estado da reserva passa de Aceite/Espera para Cancelada, caso contrario nao e retornado nenhum valor do pagamento e passa tambem de Aceite/Espera para Cancelada.
     * @param cod Codigo da viagem na qual vai ser cancelada uma reserva.
     * @param lista_v Lista de viagens por realizar.
     * @param lista_v_r Lista de viagens realizadas, juntamente com as viagens por realizar, serve para guardar as viagens num ficheiro depois de cancelar uma reserva nela.
     * @param lista_u Lista de utilizadores que vai servir para guardar no ficheiro as alteracoes feitas num determinado cliente.
     * @param d_atual Data inserida no inicio do programa.
     * @return Retorna uma reserva da classe Cancelada com o retorno total do dinheiro caso o cancelamento seja feito ate 2 dias antes, ou sem retorno do dinheiro caso o cancelamento seja feito depois de dois dias, e null caso a viagem nao seja encontrada.
     */
    @Override
    protected Reserva cancelarReserva(int cod, List<Viagem> lista_v,List<Viagem> lista_v_r , List<Utilizador> lista_u, Data d_atual) {
        Viagem v_aux=Viagem.procurarViagem(cod,lista_v);
        if(v_aux!=null){
            Reserva r;
            Iterator<Reserva> it = this.reservas.iterator();
            while(it.hasNext()) {
                r=it.next();
                if (r.getCod_viagem()==v_aux.getCodigo() && !r.getTipo().equals("Reserva_Cancelada")) {
                    lista_v.remove(v_aux);
                    v_aux.desocupaLugar(r.getLugar()-1);
                    lista_v.add(v_aux);
                    Reserva aux;
                    if (d_atual.diferencaDatas(v_aux.getData())>(2*24*60)) {
                        aux = new Cancelada(this.id, cod, r.getLugar(), 0, d_atual);
                    }
                    else {
                        aux = new Cancelada(this.id, cod, r.getLugar(), r.getPagamento(), d_atual);
                    }
                    this.reservas.remove(r);
                    this.reservas.add(aux);
                    guardaUtilizadores(lista_u);
                    guardaViagens(lista_v, lista_v_r);
                    return aux;
                }
            }
        }
        return null;
    }

    /**
     * Funcao responsavel por ativar uma reserva em espera.Sera procurada a viagem na qual a reserva foi feita, a reserva passa a Aceite e o cliente paga 90% do valor por ser um cliente premium.
     * @param r Reserva a ativar
     * @param lista_v Lista de viagens por realizar onde se ira encontrar a reserva.
     * @param lista_v_r Lista de viagens realizadas, juntamente com as viagens por realizar, serve para guardar as viagens num ficheiro depois de ativar uma reserva nela.
     * @param lista_u Lista de utilizadores que vai servir para guardar no ficheiro as alteracoes feitas num determinado cliente.
     * @param d_atual Data inserida no inicio da programa.
     * @return Retorna uma reserva do estado Aceite.
     */
    @Override
    protected Reserva ativaReserva(Reserva r, List<Viagem> lista_v,List<Viagem> lista_v_r, List<Utilizador> lista_u, Data d_atual) {
        Viagem v_aux=Viagem.procurarViagem(r.getCod_viagem(),lista_v);
        if (v_aux!=null) {
            int lugar = v_aux.ocupaLugar();
            Reserva r_aux = new Aceite(this.id, v_aux.getCodigo(), lugar + 1, v_aux.getPreco() * 0.9, d_atual);
            this.reservas.remove(r);
            this.reservas.add(r_aux);
            guardaUtilizadores(lista_u);
            guardaViagens(lista_v, lista_v_r);
            return r_aux;
        }
        return null;
    }
    @Override
    public String toString() {
        return super.toString()+"{Premium}";
    }
}
