package Agencia;

public abstract class Reserva implements java.io.Serializable{
    private String user_id;
    private int cod_viagem;
    private int lugar;
    private double pagamento;
    private Data data;

    public Reserva(String user_id, int cod_viagem, int lugar, double pagamento, Data data) {
        this.user_id = user_id;
        this.cod_viagem = cod_viagem;
        this.lugar = lugar;
        this.pagamento = pagamento;
        this.data = data;
    }

    public abstract String getTipo();

    public int getCod_viagem() {
        return cod_viagem;
    }

    public int getLugar() {
        return lugar;
    }

    public Data getData() {
        return data;
    }

    public double getPagamento() {
        return pagamento;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "user_id='" + user_id + '\'' +
                ", cod_viagem=" + cod_viagem +
                ", lugar=" + lugar +
                ", pagamento=" + pagamento +
                ", data=" + data +
                '}';
    }
}
