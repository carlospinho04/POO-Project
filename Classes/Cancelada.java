package Agencia;

public class Cancelada extends Reserva {
    public Cancelada(String user_id, int cod_viagem, int lugar, double pagamento, Data d_atual) {
        super(user_id, cod_viagem, lugar, pagamento, d_atual);
    }

    /**
     * Funcao para retornar o estado da reserva.
     * @return Retorna "Reserva_Cancelada".
     */
    @Override
    public String getTipo(){
        return "Reserva_Cancelada";
    }
    @Override
    public String toString() {
        return super.toString()+"Cancelada";
    }
}
