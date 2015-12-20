package Agencia;

public class Espera extends Reserva {
    public Espera(String user_id, int cod_viagem, int lugar, double pagamento, Data d_atual) {
        super(user_id, cod_viagem, lugar, pagamento, d_atual);
    }

    /**
     * Funcao para retornar o estado da reserva.
     * @return Retorna "Reserva_Espera".
     */
    @Override
    public String getTipo(){
        return "Reserva_Espera";
    }
    @Override
    public String toString() {
        return super.toString()+"Espera";
    }
}
