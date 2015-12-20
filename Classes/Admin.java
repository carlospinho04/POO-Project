package Agencia;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static Agencia.Agencia.*;
import static Agencia.Autocarro.*;
import static Agencia.Data.inserirdata;
import static Agencia.FicheiroDeObjeto.*;
import static Agencia.Viagem.*;

public class Admin extends Utilizador {
    public Admin(String id, String nome, long nif, String morada, long telefone, String email, String password) {
        super(id, nome, nif, morada, telefone, email, password);
    }
    /**
     * Funcao para retornar o estatuto do utilizador.
     * @return Retorna "Admin".
     */
    @Override
    public String getTipo(){
        return "Admin";
    }
    @Override
    public String toString() {
        return super.toString()+"{Admin}";
    }

    /**
     * Funcao responsavel por gerar um menu com as opcoes que os administradores tem disponiveis.
     * @param lista_u Lista de utilizadores
     * @param lista_v Lista de viagens
     * @param lista_v_r Lista de viagens realizadas
     * @param lista_a Lista de autocarros
     * @param d_inicial Data atual do sistema(Pedida no inicio do programa).
     */
    @Override
    protected void menu(List<Utilizador> lista_u, List<Viagem> lista_v, List<Viagem> lista_v_r, List<Autocarro> lista_a,Data d_inicial){
        int aux,aux2,opcao;
        String s_aux;
        Utilizador u_aux;
        Viagem v_aux;
        Autocarro a_aux;

        imprimeString("Menu do Administrador:");
        imprimeString("1-Gerir clientes.");
        imprimeString("2-Cerir viagens.");
        imprimeString("3-Gerir autocarros.");
        imprimeString("4-Consultar estatísticas.");
        imprimeString("5-Consultar reservas.");
        imprimeString("0-Logout.");
        opcao=escolhaopcao(0, 5);
        switch(opcao){
            case 1:
                imprimeString("Gestão de clientes:");
                imprimeString("1-Criar utilizador.");
                imprimeString("2-Eliminar utilizador.");
                imprimeString("3-Alterar utilizador.");
                imprimeString("4-Lista de utilizadores.");
                imprimeString("0-Voltar atras.");
                switch(escolhaopcao(0,4)) {
                    case 1:
                        imprimeString("Criar utilizador:");
                        imprimeString("1-Cliente Premium.");
                        imprimeString("2-Cliente Regular.");
                        imprimeString("3-Administrador");
                        aux = escolhaopcao(1,3);
                        u_aux = criarUtilizador(aux, lista_u);
                        if (u_aux != null){
                            imprimeString("Cliente criado com sucesso!");
                            imprimeUtilizador(u_aux);
                        }
                        else
                            imprimeString("Erro a criar cliente.");
                        break;
                    case 2:
                        imprimeString("Eliminar utilizador:");
                        imprimeString("Insira o ID:");
                        do {
                            s_aux = scanStr();
                        }while(!verificaCredenciais(s_aux));
                        u_aux=eliminarUtilizador(s_aux,lista_u);
                        if (u_aux!=null)
                            imprimeString("Utilizador com os dados:" + u_aux + "foi apagado.");
                        else
                            imprimeString("Utilizador nao encontrado.");
                        break;
                    case 3:
                        imprimeString("Alterar utilizador:");
                        imprimeString("Insira o ID:");
                        s_aux=scanStr();
                        u_aux=alterarUtilizador(s_aux,lista_u);
                        if (u_aux!=null)
                            imprimeString("Utilizador com os dados:" + u_aux + "foi alterado.");
                        else
                            imprimeString("Utilizador nao encontrado.");
                        break;
                    case 4:
                        imprimeString("Lista de utilizadores:");
                        listarUtilizador(lista_u);
                        break;
                    case 0:
                        break;
                }
                break;
            case 2:
                imprimeString("Gestão de viagens:");
                imprimeString("1-Criar viagem.");
                imprimeString("2-Eliminar viagem.");
                imprimeString("3-Alterar viagem.");
                imprimeString("4-Listar viagem.");
                imprimeString("0-Voltar atras.");
                switch(escolhaopcao(0,4)){
                    case 1:
                        v_aux=criarViagem(lista_v,lista_v_r,lista_a,d_inicial);
                        imprimeString("Erro a criar viagem.");
                        if(v_aux!=null){
                            imprimeString("Viagem criada com sucesso!");
                            imprimeViagem(v_aux);
                        }
                        else
                            imprimeString("Erro a criar viagem!");
                        break;
                    case 2:
                        imprimeString("Eliminar viagem:");
                        imprimeString("Insira o codigo:");
                        aux=scanINT("Codigo invalido");
                        v_aux=eliminarViagem(aux,lista_v,lista_v_r);
                        if (v_aux!=null) {
                            imprimeViagem(v_aux);
                            imprimeString(" foi apagada.");
                        }
                        else
                            imprimeString("Erro a eliminar a viagem.");
                        break;
                    case 3:
                        imprimeString("Alterar viagem:");
                        imprimeString("Insira o codigo:");
                        aux=scanINT("Codigo invalido");
                        v_aux=alterarViagem(aux,lista_v,lista_v_r,lista_a,d_inicial);
                        if (v_aux!=null){
                            imprimeViagem(v_aux);
                            imprimeString(" foi alterada.");
                        }
                        else
                            imprimeString("Erro a alterar a viagem.");

                        break;
                    case 4:
                        imprimeString("Lista de viagens por realizar:");
                        Viagem.listarViagem(lista_v);
                        imprimeString("Lista de viagens realizadas:");
                        Viagem.listarViagem(lista_v_r);
                        break;
                    case 0:
                        break;
                }
                break;
            case 3:
                imprimeString("Gestão de autocarros:");
                imprimeString("1-Criar autocarro.");
                imprimeString("2-Eliminar autocarro.");
                imprimeString("3-Alterar autocarro.");
                imprimeString("4-Listar autocarro.");
                imprimeString("0-Voltar atras.");
                switch(escolhaopcao(0,4)){
                    case 1:
                        a_aux=criarAutocarro(lista_a);
                        if (a_aux!=null) {
                            imprimeString("Autocarro criado com sucesso!");
                            imprimeAutocarro(a_aux);
                        }
                        else
                            imprimeString("Erro a criar autocarro");
                        break;
                    case 2:
                        imprimeString("Eliminar autocarro:");
                        imprimeString("Insira a matricula:");
                        s_aux=scanStr();
                        a_aux=eliminarAutocarro(s_aux,lista_a,lista_v);
                        if (a_aux!=null) {
                            imprimeAutocarro(a_aux);
                            imprimeString(" foi apagado.");
                        }
                        else
                            imprimeString("Erro a elminar autocarro.");
                        break;
                    case 3:
                        imprimeString("Alterar autocarro:");
                        imprimeString("Insira a matricula:");
                        s_aux=scanStr();
                        a_aux=alterarAutocarro(s_aux,lista_a,lista_v);
                        if (a_aux!=null) {
                            imprimeAutocarro(a_aux);
                            imprimeString(" foi alterado.");
                        }
                        else
                            imprimeString("Erro a alterar autocarro.");
                        break;
                    case 4:
                        imprimeString("Lista de autocarros:");
                        listarAutocarro(lista_a);
                        break;
                    case 0:
                        break;
                }
                break;
            case 4:
                imprimeString("Gestor de estatisticas:");
                imprimeString("1-Estatistica anual.");
                imprimeString("2-Viagem mais vendida num determinado mês.");
                imprimeString("3-Cliente com mais viagens num determinado mês.");
                imprimeString("4-Viagem com melhor pontuação.");
                imprimeString("5-Viagens sem reservas num determinado mês.");
                imprimeString("0-Voltar atras.");
                switch(escolhaopcao(0,5)){
                    case 1:
                        imprimeString("Estatistica anual");
                        imprimeString("Insira o ano:");
                        do {
                            aux = scanINT("Ano invalido");
                            if (aux<2000 || aux>3000)
                                imprimeString("Ano invalido");
                        }while(aux<2000 || aux>3000);
                        estatisticaAnual(aux,lista_u);
                        break;
                    case 2:
                        imprimeString("Viagem mais vendida num determinado mes");
                        imprimeString("Insira o mes:");
                        do {
                            aux = scanINT("Mes invalido");
                            if (aux<0 || aux>12)
                                imprimeString("Mes invalido");
                        }while(aux<0 || aux>12);
                        imprimeString("Insira o ano:");
                        do {
                            aux2 = scanINT("Ano invalido");
                            if (aux2<2000 || aux2>3000)
                                imprimeString("Ano invalido");
                        }while(aux2<2000 || aux2>3000);
                        viagemMaisVendida(lista_u,lista_v,lista_v_r, aux, aux2);
                        break;
                    case 3:
                        imprimeString("Cliente com mais viagens num determinado mes");
                        imprimeString("Insira o mes:");
                        do {
                            aux = scanINT("Mes invalido");
                            if (aux<0 || aux>12)
                                imprimeString("Mes invalido");
                        }while(aux<0 || aux>12);
                        imprimeString("Insira o ano:");
                        do {
                            aux2 = scanINT("Ano invalido");
                            if (aux2<2000 || aux2>3000)
                                imprimeString("Ano invalido");
                        }while(aux2<2000 || aux2>3000);
                        clienteMaisViagens(aux,aux2,lista_u);
                        break;
                    case 4:
                        imprimeString("Viagem(s) com melhor pontuacao num determinado mes:");
                        imprimeString("Insira o mes:");
                        do {
                            aux = scanINT("Mes invalido");
                            if (aux<0 || aux>12)
                                imprimeString("Mes invalido");
                        }while(aux<0 || aux>12);
                        imprimeString("Insira o ano:");
                        do {
                            aux2 = scanINT("Ano invalido");
                            if (aux2<2000 || aux2>3000)
                                imprimeString("Ano invalido");
                        }while(aux2<2000 || aux2>3000);
                        viagemMelhorPontuacao(lista_v_r,aux,aux2);
                        break;
                    case 5:
                        imprimeString("Viagens sem reservas num determinado mes");
                        imprimeString("Insira o mes:");
                        do {
                            aux = scanINT("Mes invalido");
                            if (aux<0 || aux>12)
                                imprimeString("Mes invalido");
                        }while(aux<0 || aux>12);
                        imprimeString("Insira o ano:");
                        do {
                            aux2 = scanINT("Ano invalido");
                            if (aux2<2000 || aux2>3000)
                                imprimeString("Ano invalido");
                        }while(aux2<2000 || aux2>3000);
                        viagensSemReserva(aux,aux2,lista_v_r);
                    case 0:
                        break;
                }
                break;
            case 5:
                imprimeString("Gestor de reservas:");
                imprimeString("1-Listar reservas de uma viagem.");
                imprimeString("2-Listar reservas canceladas de uma viagem.");
                imprimeString("3-Listar reservas em espera.");
                imprimeString("0-Voltar atras.");
                switch(escolhaopcao(0,3)){
                    case 1:
                        imprimeString("Listar reservas de uma viagem.");
                        imprimeString("Insira o codigo da viagem:");
                        aux=scanINT("Codigo invalido");
                        reservasViagem(lista_u,aux,lista_v,lista_v_r);
                        break;
                    case 2:
                        imprimeString("Listar reservas canceladas de uma viagem.");
                        imprimeString("Insira o codigo da viagem:");
                        aux=scanINT("Codigo invalido");
                        reservasCanceladasViagem(aux,lista_u,lista_v,lista_v_r);
                        break;
                    case 3:
                        reservasEspera(lista_u);
                        break;
                    case 0:
                        break;
                }
            case 0:
                break;
        }
        if(opcao!=0)
            menu(lista_u,lista_v,lista_v_r,lista_a,d_inicial);
    }
    //GERIR CLIENTES

    /**
     * Funcao para procurar um utilizador numa lista de utilizadores.
     * @param id Username do utilizador a procurar.
     * @param lista_u Lista de utilizadores onde procurar.
     * @return Retorna o utilizador caso encontre ou null caso contrario.
     */
    private Utilizador procuraUtilizador(String id,List<Utilizador> lista_u){
        int encontrado=0;
        Utilizador user = null;
        Iterator<Utilizador> it = lista_u.iterator();
        while(it.hasNext() && encontrado!=1) {
            user= it.next();
            if (Objects.equals(user.getId().toLowerCase(), id.toLowerCase()))
                encontrado = 1;
        }
        if (encontrado == 1){
            return user;
        }
        else return null;
    }

    /**
     * Funcao para adicionar um novo utilizador com o estatuto indicado, na lista indicada. O administrador insere os dados a atribuir ao novo utilizador e caso sejam validos este ira ser criado.
     * @param estatuto Estatuto do utilizador(Premium(1),Regular(2) e Admin(3)).
     * @param lista_u Lista de utilizadores a adicionar o novo utilizador.
     * @return Retorna o utilizador se foi criado com sucesso ou null caso contrario.
     */
    private Utilizador criarUtilizador(int estatuto, List<Utilizador> lista_u){
        Utilizador user = null;
        String id, nome, morada, email, password;
        long nif, telefone;
        imprimeString("Criar Utilizador");
        imprimeString("ID:");
        do {
            id = scanStr();
            while(procuraUtilizador(id,lista_u)!=null) {
                imprimeString("Utilizador ja existente, insira novamente:");
                id = scanStr();
            }
        }while(!verificaCredenciais(id));

        imprimeString("Nome:");
        do {
            nome = scanStr();

        }while(!verificaString(nome,"Nome"));
        imprimeString("NIF:");
        do {
            nif=scanLong("Nif invalido");
        }while(!verificaNumeros(nif));
        imprimeString("Morada:");
        do {
            morada = scanStr();
        }while(!verificaMorada(morada));
        imprimeString("Telefone:");
        do {
            telefone=scanLong("Telefone invalido");
        }while(!verificaNumeros(telefone));
        imprimeString("Email:");
        do {
            email =scanStr();
        }while(!verificaEmail(email));
        imprimeString("Password:");
        do {
            password = scanStr();
        }while(!verificaCredenciais(password));

        if(estatuto==1) {
            user = new Premium(id, nome, nif, morada, telefone, email, password);
        } else if(estatuto==2){
            user = new Regular(id, nome, nif, morada, telefone, email,password);
        } else if(estatuto==3){
            user = new Admin(id, nome, nif, morada, telefone, email, password);
        }
        if(user!=null) {
            lista_u.add(user);
            guardaUtilizadores(lista_u);
        }
        return user;
    }

    /**
     * Funcao para eliminar um determinado utilizador da lista indicada. O utilizador e procurado atraves da funcao procuraUtilizador, que retorna o utilizador caso ele exista para depois ser eliminado da lista de utilizadores.
     * @param id Username do utilizador a eliminar.
     * @param lista_u Lista de utilizadores onde sera removido o utilizador.
     * @return Retorna o utilizador se foi eliminado com sucesso ou null caso contrario.
     */
    private Utilizador eliminarUtilizador(String id,List<Utilizador> lista_u){
        Utilizador aux=procuraUtilizador(id,lista_u);
        if (aux!=null){
            lista_u.remove(aux);
            guardaUtilizadores(lista_u);
        }
        return aux;
    }

    /**
     * Funcao para alterar um determinado utilizador da lista indicada. O utilizador e procurado atraves da funcao procuraUtilizador, que retorna o utilizador caso ele exista para depois ser alterado da lista de utilizadores.
     * O administrador tem a possibilidade de alterar apenas o atributo que desejar.
     * @param id Username do utilizador a alterar.
     * @param lista_u Lista de utilizadores onde esta o utilizador a ser alterado.
     * @return Retorna o utilizador se foi alterado com sucesso ou null caso contrario.
     */
    private Utilizador alterarUtilizador(String id,List<Utilizador> lista_u){
        Utilizador aux=procuraUtilizador(id,lista_u);
        if (aux!=null){
            int index=lista_u.indexOf(aux);
            imprimeString("Alterar utilizador com o username: " + id + ":");
            imprimeString("1-Alterar nome.");
            imprimeString("2-Alterar morada.");
            imprimeString("3-Alterar NIF.");
            imprimeString("4-Alterar numero de telefone.");
            imprimeString("5-Alterar email.");
            imprimeString("6-Alterar password.");
            imprimeString("Escolha a opcao:");
            int option=escolhaopcao(1,6);
            switch (option){
                case 1:
                    String nome;
                    imprimeString("Novo nome:");
                    do {
                        nome = scanStr();
                    }while(!verificaString(nome,"Nome"));
                    lista_u.get(index).setNome(nome);
                    break;
                case 2:
                    imprimeString("Nova morada:");
                    String morada;
                    do {
                        morada = scanStr();
                    }while(!verificaMorada(morada));
                    lista_u.get(index).setMorada(morada);
                    break;
                case 3:
                    long nif;
                    imprimeString("Novo NIF:");
                    do {
                        nif=scanLong("Nif invalido");
                    }while(!verificaNumeros(nif));
                    lista_u.get(index).setNif(nif);
                    break;
                case 4:
                    long telefone;
                    imprimeString("Novo telefone:");
                    do {
                        telefone=scanLong("Telefone invalido");
                    }while(!verificaNumeros(telefone));
                    lista_u.get(index).setTelefone(telefone);
                    break;
                case 5:
                    imprimeString("Novo email:");
                    String email;
                    do {
                        email =scanStr();
                    }while(!verificaEmail(email));
                    lista_u.get(index).setEmail(email);
                    break;
                case 6:
                    imprimeString("Nova password:");
                    String password;
                    do {
                        password = scanStr();
                    }while(!verificaCredenciais(password));
                    lista_u.get(index).setPassword(password);
                    break;
            }
            guardaUtilizadores(lista_u);

            return lista_u.get(index);
        }
        else return null;
    }

    /**
     * Funcao que percorre uma lista de utilizadores e os imprime.
     * @param lista_u Lista que sera impressa.
     */
    private void listarUtilizador(List<Utilizador> lista_u){
        for (Utilizador u : lista_u)
            imprimeUtilizador(u);

    }

    /**
     * Funcao para obter/adicionar a capacidade a uma viagem, immprime os autocarros disponiveis para essa capacidade.
     * Vai percorrer a lista de autocarros e confirmar se estao adicionados a alguma viagem e se a data dessa viagem vai coincidir com a data da viagem a criar.
     * @param lista_a Lista de autocarros.
     * @param lista_v Lista de viagens.
     * @param data Data da viagem.
     * @param duracao Duracao da viagem.
     * @return Retorna a capacidade e o numero de autocarros disponiveis para essa capacidade, caso nao existam autocarros livres retorna {0,0}.
     */
    private int[] capacidadeViagem(List<Autocarro> lista_a, List<Viagem> lista_v, Data data, int duracao){
        int cap,cont_aux,cont_aux2,ocupado;

        imprimeString("Insira o numero de lugares para a viagem:");
        do {
            cap = scanINT("Numero invalido");
            if (cap<=0)
                imprimeString("A viagem necessita de pelo menos um lugar.");
            cont_aux=0;
            cont_aux2=0;
            for (Autocarro a_aux : lista_a) {
                ocupado=0;
                for (Viagem v:lista_v){
                    if (procuraAutocarro(a_aux.getMatricula(),v.getAutocarros())!=null){
                        if ((v.getData().comparaDatas(data)>0 && v.getData().diferencaDatas(data)<=duracao) || (v.getData().comparaDatas(data)<0 && Math.abs(v.getData().diferencaDatas(data))<=v.getDuracao())){
                            ocupado=1;
                            break;
                        }
                    }
                }
                if (ocupado==0)
                    cont_aux2++;
                if (a_aux.getCapacidade()>=cap && ocupado==0)
                    imprimeString("Autocarro disponivel com a matricula:"+a_aux.getMatricula());
                    cont_aux++;
            }
            if (cont_aux2==0) {
                imprimeString("Nao existem autocarros disponiveis para a hora da viagem.");
                return new int[]{0,0};
            }
            imprimeString("Numero de autocarros disponiveis com "+ String.valueOf(cap)+" lugares:"+String.valueOf(cont_aux));
            if(cont_aux==0)
                imprimeString("Insira o numero de lugares novamente:");
        }while(cap<=0 || cont_aux==0);
        return new int[]{cap,cont_aux};
    }

    /**
     * Funcao para adicionar autocarros na viagem, nao deixa adicionar mais autocarros do que a funcao capacidadeViagem retornou e apenas deixa adicionar os que estiverem disponiveis para a data da realizacao da viagem.
     * @param cap Numero de lugares livres da viagem.
     * @param max_a Numero maximo de autocarros disponiveis para a capacidade da viagem.
     * @param lista_a Lista de autocarros.
     * @param lista_v Lista de viagens.
     * @param data Data da viagem.
     * @param duracao Duracao da viagem.
     * @return Retorna a lista de autocarros a ser usados na viagem.
     */
    private List<Autocarro> inserirAutocarros(int cap, int max_a,List<Autocarro> lista_a, List<Viagem> lista_v, Data data, int duracao){
        List<Autocarro> autocarros= new ArrayList<>();
        String mat;
        int n_a,ocupado;
        imprimeString("Insira o numero de autocarros que pretende usar:");
        do {
            n_a = scanINT("Numero invalido");
            if (n_a<=0)
                imprimeString("A viagem necessita de pelo menos um autocarro.");
            if (n_a>max_a)
                imprimeString("Nao existem autocarros suficientes para a capacidade indicada.");
        }while(n_a<=0 || n_a>max_a);

        int i=0;
        while(i<n_a){
            imprimeString("[" + String.valueOf(i) + "]" + "Insira a matricula do autocarro a adicionar:"); mat=scanStr();
            Autocarro aux=procuraAutocarro(mat, lista_a);
            if(aux!=null){
                //Confirma se o autocaro está disponivel
                ocupado=0;
                for (Viagem v:lista_v){
                    if (procuraAutocarro(aux.getMatricula(),v.getAutocarros())!=null){
                        if ((v.getData().comparaDatas(data)>0 && v.getData().diferencaDatas(data)<=duracao) || (v.getData().comparaDatas(data)<0 && Math.abs(v.getData().diferencaDatas(data))<=v.getDuracao())){
                            ocupado=1;
                            break;
                        }
                    }
                }
                if(ocupado==0) {
                    if (aux.getCapacidade() >= cap) {
                        autocarros.add(aux);
                        i++;
                    } else {
                        imprimeString("Autocarro invalido");
                    }
                }
                else{
                    imprimeString("Autocarro ocupado");
                }

            }
            else imprimeString("Autocarro não encontrado.");
        }
        return autocarros;
    }

    /**
     * Funcao responsavel pela criacao de uma viagem, o utilizador insere os dados da viagem e caso estes sejam validos ira ser criada uma viagem com esses mesmo dados.
     * Cada viagem tem um codigo que e obtido atraves de um ficheiro "contador.txt" com o valor a atribuir, que quando e criada uma viagem com sucesso esse valor é incrementado.
     * @param lista_v Lista de viagens onde sera adicionada a viagem.
     * @param lista_v_r Lista de viagens reservadas.
     * @param lista_a Lista de autocarros.
     * @param d_atual Data atual do sistema(Pedida no inicio do programa).
     * @return Retorna a viagem caso criada com sucesso ou null caso contrário.
     */
    private Viagem criarViagem(List<Viagem> lista_v,List<Viagem> lista_v_r, List<Autocarro> lista_a, Data d_atual){
        if (lista_a.isEmpty()) {
            imprimeString("Nao existem autocarros");
            return null;
        }

        int codigo=0;
        FicheiroDeTexto contador=new FicheiroDeTexto();
        try {
            contador.abreLeituraT("contador.txt");
            codigo=Integer.parseInt(contador.leLinha());
            contador.fechaLeituraT();
        }catch(java.io.FileNotFoundException e){
            try {
                contador.abreEscritaTOW("contador.txt");
                contador.escreveLinha("0");
                contador.fechaEscritaT();
            } catch (java.io.IOException e1) {
                imprimeString("Erro no contador das viagens.");
            }
        } catch (java.io.IOException e) {
            imprimeString("Erro no contador das viagens.");
        }
        codigo++;

        int duracao;
        String origem,destino;
        double preco;
        List<Autocarro> autocarros;

        imprimeString("Criar viagem:");
        imprimeString("Insira a origem:");
        do {
            origem = scanStr();
        }while(!verificaString(origem,"Origem"));
        imprimeString("Insira o destino:");
        do {
            destino=scanStr();
        }while(!verificaString(destino,"Destino"));
        imprimeString("Insira o preco:");
        do {
            preco = scanLong("Preco invalido");
            if (preco<=0)
                imprimeString("Preco tem que ser maior que 0.");
        }while(preco<0);
        Data data;
        do {
            data = inserirdata();
            if (data.comparaDatas(d_atual)<0)
                imprimeString("Data invalida, da ja passou, insira novamente:");
        }while(data.comparaDatas(d_atual)<0);
        imprimeString("Insira a duracao da viagem(minutos):");
        do{
            duracao=scanINT("Duracao invalida");
            if (duracao<=0)
                imprimeString("Duracao tem que ser maior que 0.");
        }while(duracao<=0);
        //Inserir autocarros
        int info_a[]=capacidadeViagem(lista_a,lista_v,data,duracao);
        if (info_a[0]==0 && info_a[1]==0)
            return null;
        autocarros=inserirAutocarros(info_a[0],info_a[1],lista_a,lista_v,data,duracao);
        List<Character> lugares_livres = new ArrayList<>();
        for (int j = 0; j < info_a[0]; j++)
            lugares_livres.add('L');
        Viagem vg=new Viagem(codigo,origem,destino,preco,autocarros,data,duracao,lugares_livres);
        lista_v.add(vg);
        guardaViagens(lista_v, lista_v_r);

        try {
            contador.abreEscritaTOW("contador.txt");
            contador.escreveLinha(String.valueOf(codigo));
            contador.fechaEscritaT();
        } catch (java.io.IOException e) {
            imprimeString("Erro no contador de viagens.");
        }

        return vg;
    }

    /**
     * Funcao para eliminar uma viagem de uma determinada lista de viagens, atraves da funcao procurarViagem que retorna a viagem caso esta seja encontrada na lista.
     * A viagem so e eliminada se nao existirem reservas ativas nessa viagem.
     * @param cod Codigo da viagem a eliminar.
     * @param lista_v Lista de viagens.
     * @param lista_v_r Lista de viagens realizadas.
     * @return Retorna a viagem se eliminada com sucesso ou null caso contrario.
     */
    private Viagem eliminarViagem(int cod,List<Viagem> lista_v,List<Viagem> lista_v_r){
        Viagem aux=Viagem.procurarViagem(cod,lista_v);
        if(aux!=null && aux.lugaresLivres()!=aux.getLugares().size()){
            imprimeString("Nao e possivel eliminar esta viagem.");
        }
        if (aux!=null && aux.lugaresLivres()==aux.getLugares().size()) {
            lista_v.remove(aux);
            guardaViagens(lista_v, lista_v_r);
            return aux;
        }
        return null;
    }

    /**
     * Funcao para alterar uma determinada viagem, sendo esta procurada na lista atraves da funcao procurarViagem, que caso exista sera retornada.
     * A viagem so e alterada se nao existirem reservas ativas nessa viagem.
     * O administrador tem possibilidade de alterar apenas o atributo que desejar.
     * @param cod Codigo da viagem a alterar.
     * @param lista_v Lista de viagens com a viagem a alterar.
     * @param lista_v_r Lista de viagens realizadas.
     * @param lista_a Lista de autocarros.
     * @param d_atual Data atual do sistema(Pedida no inicio do programa).
     * @return Retorna a viagem caso alterada com sucesso ou null caso contrário.
     */
    private Viagem alterarViagem(int cod,List<Viagem> lista_v,List<Viagem> lista_v_r, List<Autocarro> lista_a, Data d_atual){
        Viagem aux=Viagem.procurarViagem(cod, lista_v);
        if(aux!=null && aux.lugaresLivres()!=aux.getLugares().size()){
            imprimeString("Nao e possivel alterar esta viagem.");
        }
        if (aux!=null && aux.lugaresLivres()==aux.getLugares().size()){
            int index=lista_v.indexOf(aux);
            imprimeString("Alterar viagem com o codigo " + String.valueOf(cod) + ":");
            imprimeString("1-Alterar origem.");
            imprimeString("2-Alterar destino.");
            imprimeString("3-Alterar preco.");
            imprimeString("4-Alterar data.");
            imprimeString("5-Alterar duracao.");
            imprimeString("6-Alterar autocarros e capacidade.");
            imprimeString("Escolha a opcao:");
            int option=escolhaopcao(1,6);
            switch(option){
                case 1:
                    imprimeString("Insira a origem:");
                    String origem;
                    do {
                        origem = scanStr();
                    }while(!verificaString(origem,"Origem"));
                    lista_v.get(index).setOrigem(origem);
                    break;
                case 2:
                    imprimeString("Insira o destino:");
                    String destino;
                    do {
                        destino=scanStr();
                    }while(!verificaString(destino,"Destino"));
                    lista_v.get(index).setDestino(destino);
                    break;
                case 3:
                    imprimeString("Insira o preco:");
                    long preco;
                    do {
                        preco = scanLong("Preco invalido");
                        if (preco<=0)
                            imprimeString("Preco tem que ser maior que 0.");
                    }while(preco<=0);
                    lista_v.get(index).setPreco(preco);
                    break;
                case 4:
                    Data data;
                    do {
                        data = inserirdata();
                        imprimeString("Data invalida, da ja passou, insira novamente:");
                    }while(data.comparaDatas(d_atual)<0);
                    lista_v.get(index).setData(data);
                    break;
                case 5:
                    imprimeString("Insira a duracao da viagem(minutos):");
                    int duracao;
                    do{
                        duracao=scanINT("Duracao invalida");
                        if (duracao<=0)
                            imprimeString("Duracao tem que ser maior que 0.");
                    }while(duracao<=0);
                    lista_v.get(index).setDuracao(duracao);
                    break;
                case 6:
                    List<Autocarro> autocarros;
                    //reseta os autocarros para poderem aparecer como livres
                    lista_v.get(index).setAutocarros(null);
                    int info_a[]=capacidadeViagem(lista_a,lista_v,aux.getData(),aux.getDuracao());
                    if (info_a[0]==0 && info_a[1]==0)
                        return null;
                    autocarros=inserirAutocarros(info_a[0],info_a[1],lista_a,lista_v,aux.getData(),aux.getDuracao());
                    List<Character> lugares_livres = new ArrayList<>();
                    for (int j = 0; j < info_a[0]; j++)
                        lugares_livres.add('L');
                    lista_v.get(index).setAutocarros(autocarros);
                    lista_v.get(index).setLugares(lugares_livres);
                    break;
            }
            guardaViagens(lista_v, lista_v_r);
            return lista_v.get(index);
        }
        else return null;
    }
    //GERIR AUTOCARROS

    /**
     * Funcao responsavel pela criacao de um novo autocarro. O autocarro e criado com sucesso se os dados atribuidos pelo adminstrador forem validos.
     * @param lista_a Lista de autocarros onde sera adicionado o novo autocarro.
     * @return Retorna o autocarro caso criado com sucesso ou null caso contrario.
     */
    private Autocarro criarAutocarro( List<Autocarro> lista_a){
        String matricula;
        int capacidade;
        Autocarro autocarro;
        imprimeString("Criar Autocarro");
        imprimeString("Matricula:");
        do {
            do {
                matricula = scanStr();
            }while(!verificaMatricula(matricula));
        } while(Autocarro.procuraAutocarro(matricula, lista_a)!=null);

        imprimeString("Capacidade:");
        do{
            capacidade=scanINT("Capacidade invalida");
            if (capacidade<=0)
                imprimeString("Capacidade tem que ser superior a 0");
        }while(capacidade<=0);
        autocarro=new Autocarro(matricula,capacidade);

        lista_a.add(autocarro);
        guardaAutocarros(lista_a);

        return autocarro;
    }

    /**
     * Funcao responsavel pela eliminacao de um autocarro ja existente da lista de autocarros.
     * O autocarro apenas e eliminado se nao estiver associado a nenhuma viagem.
     * @param matricula Matricula do autocarro a eliminar.
     * @param lista_a Lista de autocarros, com o autocarro a eliminar.
     * @param lista_v Lista de viagens, para verificar se esta ocupado.
     * @return Retorna o autocarro se eliminado com sucesso ou null caso contrario.
     */
    private Autocarro eliminarAutocarro(String matricula,List<Autocarro> lista_a, List<Viagem> lista_v) {
        Autocarro aux=Autocarro.procuraAutocarro(matricula, lista_a);
        if (aux!=null && aux.autocarroOcupado(lista_v)){
            imprimeString("Nao e possivel eliminar o autocarro.");
        }
        if (aux!=null && !aux.autocarroOcupado(lista_v)) {
            lista_a.remove(aux);
            guardaAutocarros(lista_a);
            return aux;
        }
        return null;
    }

    /**
     * Funcao para alterar um autocarro com uma determinada matricula existente na lista de autocarros.
     * O autocarro apenas pode ser alterado se nao estiver associado a nenhuma viagem.
     * O administrador tem a possibilidade de alterar apenas o atributo que desejar.
     * @param matricula Matricula do autocarro a alterar.
     * @param lista_a Lista de autocarros, com o autocarro a alterar.
     * @param lista_v Lista de viagens, para verificar se esta ocupado.
     * @return Retorna o autocarro se alterado com sucesso ou null caso contrario.
     */
    private Autocarro alterarAutocarro(String matricula, List<Autocarro> lista_a, List<Viagem> lista_v){
        Autocarro aux=Autocarro.procuraAutocarro(matricula, lista_a);
        if (aux!=null && aux.autocarroOcupado(lista_v)){
            imprimeString("Nao e possivel alterar o autocarro.");
        }
        if (aux!=null && !aux.autocarroOcupado(lista_v)){
            int index=lista_a.indexOf(aux);
            imprimeString("Alterar o autocarro com a matricula "+matricula+":");
            imprimeString("1-Alterar matricula.");
            imprimeString("2-Alterar capacidade.");
            imprimeString("Escolha a opcao:");
            int option=escolhaopcao(1,2);
            switch(option){
                case 1:
                    imprimeString("Nova matricula:");
                    do {
                        do {
                            matricula = scanStr();
                        }while(!verificaMatricula(matricula));
                        if (Autocarro.procuraAutocarro(matricula, lista_a)!=null)
                            imprimeString("Matricula ja existe, insira novamente:");
                    } while(Autocarro.procuraAutocarro(matricula, lista_a)!=null);
                    lista_a.get(index).setMatricula(matricula);
                    break;
                case 2:
                    imprimeString("Nova Capacidade:");
                    int cap;
                    do{
                        cap=scanINT("Capacidade invalida");
                        if (cap<=0)
                            imprimeString("Capacidade tem que ser superior a 0");
                    }while(cap<=0);
                    lista_a.get(index).setCapacidade(cap);
                    break;
            }
            guardaAutocarros(lista_a);
            return lista_a.get(index);
        }
        else return null;
    }

    /**
     * Funcao que percorre uma lista de autocarros e os imprime.
     * @param lista_a Lista de autocarros.
     */
    private void listarAutocarro(List<Autocarro> lista_a){
        for (Autocarro a : lista_a)
            imprimeAutocarro(a);
    }

    //ESTATISTICAS
    /**
     * Funcao responsavel pela impressao/calculo das estatisticas anuais, sendo estas guardadas num ficheiro chamado "estatisticas.txt".
     * Todos os utilizadores sao percorridos para verificar se alguma das sua reservas aceite foi realizada no ano passado como argumento, sendo depois o valor nas posicoes que correspondem ao dia e mes dessa reserva de um array bidimensional incrementado.
     * O volume de vendas de cada mes e obtido atraves da soma de todos os valores da linha do array bidimensional correspondente a esse mes.
     * O dia do ano com mais vendas e obtido atraves do maximo(s) do array bidimensional.
     * @param ano Ano cujas estatisticas serao impressas.
     * @param lista_u Lista de utilizadores.
     */
    private void estatisticaAnual(int ano, List<Utilizador> lista_u) {
        int dias[][]=new int[12][31];
        String estatisticas = "Estatisticas anuais de "+String.valueOf(ano)+":";
        for (Utilizador u:lista_u){
            if(u.getTipo().equals("Cliente_Premium") || u.getTipo().equals("Cliente_Regular")){
                Cliente c=(Cliente) u;
                for(Reserva r:c.getReservas()){
                    if (r.getData().getAno()==ano && r.getTipo().equals("Reserva_Aceite")){
                        dias[r.getData().getMes()-1][r.getData().getDia()-1]+=1;
                    }
                }
            }
        }
        imprimeString("Volume de vendas de cada mes:");
        for (int i=0;i<12;i++){
            int aux=0;
            for (int j=0;j<31;j++){
                aux+=dias[i][j];
            }
            estatisticas = estatisticas.concat(" Mes "+String.valueOf(i + 1)+":"+String.valueOf(aux));
            imprimeString("Mes "+String.valueOf(i + 1)+":"+String.valueOf(aux));
        }
        imprimeString("Dia(s) do ano com mais vendas:");
        int max=0;
        Data d_aux;
        List<Data> datas = new ArrayList<>();
        for (int i=0;i<12;i++){
            for (int j=0;j<31;j++){
                if(dias[i][j]>max){
                    max=dias[i][j];
                    d_aux=new Data(j+1,i+1,ano,0,0);
                    datas.clear();
                    datas.add(d_aux);
                }
                else if(dias[i][j]==max){
                    if (max!=0) {
                        d_aux = new Data(j + 1, i + 1, ano, 0, 0);
                        datas.add(d_aux);
                    }
                }
            }
        }
        estatisticas = estatisticas.concat(" Dia(s) com mais vendas:");
        for(Data d:datas){
            estatisticas = estatisticas.concat(" "+ String.valueOf(d.getDia()) + "/" + String.valueOf(d.getMes()) + "->" + String.valueOf(max) + " reservas vendidas");
            imprimeString("Dia " + String.valueOf(d.getDia()) + " do mes " + String.valueOf(d.getMes()) + " com " + String.valueOf(max) + " reservas vendidas");
        }
        escreveEstatisticas(estatisticas);
    }

    /**
     * Funcao para listar a viagem mais vendida num determinado mes.
     * Os utilizadores sao percorridos, para verificar se possuem alguma reserva no mes passado como argumento, caso isto se verifique é adicionada a viagem da reserva a um array e o numero de ocorrencias a outro array na mesma posicao da viagem, caso a viagem ja tenha sido adicionada ao primeiro array o valor no segundo array e incrementado.
     * No fim verificar qual a viagem que mais vendas teve durante esse mes e imprime toda a informacao sobre essa viagem, sendo esta informacao guardada no ficheiro "estatisticas.txt".
     * @param lista_u Lista de utilizadores.
     * @param lista_v Lista de viagens.
     * @param lista_v_r Lista de viagens realizadas.
     * @param mes Mes a verificar.
     * @param ano Ano a verificar.
     */
    private void viagemMaisVendida(List<Utilizador> lista_u,List<Viagem> lista_v,List<Viagem> lista_v_r, int mes, int ano){
        for (Utilizador u:lista_u){
            if(u.getTipo().equals("Cliente_Premium") || u.getTipo().equals("Cliente_Regular")){
                String estatisticas = "Viagens mais vendidas no mes "+String.valueOf(mes)+"/"+String.valueOf(ano)+":";
                Cliente c= (Cliente) u;
                List<Integer> contador = new ArrayList<>();
                List<Integer> viagens = new ArrayList<>();
                for(Reserva r:c.getReservas()){
                    if(r.getTipo().equals("Reserva_Aceite")) {
                        if (r.getData().getMes() == mes && r.getData().getAno()==ano){
                            if (viagens.contains(r.getCod_viagem())){
                                int index=viagens.indexOf(r.getCod_viagem());
                                contador.set(index,contador.get(index)+1);
                            }
                            else {
                                viagens.add(r.getCod_viagem());
                                contador.add(0);
                            }
                        }
                    }
                }
                List<Integer> index_mv = new ArrayList<>();
                int cont=0,i=0;
                for(int aux:contador){
                    if (aux>cont){
                        cont=aux;
                        index_mv.clear();
                        index_mv.add(i);
                    }
                    else if (aux==cont){
                        index_mv.add(i);
                    }
                    i++;
                }
                for(int ind:index_mv){
                    List<Viagem> l_aux;
                    for(i=0;i<2;i++){
                        if (i==0)
                            l_aux=lista_v;
                        else
                            l_aux=lista_v_r;
                        for (Viagem v:l_aux){
                            if (v.getCodigo()==viagens.get(ind)) {
                                imprimeViagem(v);
                                estatisticas = estatisticas.concat(String.valueOf(v.getCodigo()));
                                escreveEstatisticas(estatisticas);
                            }
                        }

                    }
                }
            }
        }

    }

    /**
     * Funcao para listar cliente que mais viagens comprou num determinado mes.
     * A lista de utilizadores e percorrida utilizador a utilizador, sendo a lista de reservas dos mesmos percorrida reserva por reserva e confirmado se a reserva e ativa e foi realizada no mes indicado, sendo um contador incrementado caso isto se verifique, cada vez que encontre um utilizador com mais viagens que o anteriormente guardado, o novo é guardado numa variavel auxiliar, para depois ser imprimido.
     * A informacao do utilizador com mais viagens compradas e guardada no ficheiro "estatisticas.txt".
     * @param mes Mes a verificar.
     * @param ano Ano a verificar.
     * @param lista_u Lista de utilizadores
     */
    private void clienteMaisViagens(int mes,int ano,List <Utilizador> lista_u) {
        List<Cliente> clienteMaisViagens = new ArrayList<>();
        String estatisticas = "Cliente que mais viagens comprou em "+String.valueOf(mes)+"/"+String.valueOf(ano)+":";
        int viagensAux = 0, viagemAdicionada = 0;
        for (Utilizador aux : lista_u) {
            if (aux.getTipo().equals("Cliente_Premium") || aux.getTipo().equals("Cliente_Regular")) {
                Cliente clienteaux = (Cliente) aux;
                for (Reserva reserva_aux : clienteaux.getReservas()) {
                    if (reserva_aux.getData().getMes() == mes && reserva_aux.getData().getAno() == ano && reserva_aux.getTipo().equals("Reserva_Aceite")) {
                        viagensAux++;
                    }
                }
                if (viagensAux > viagemAdicionada) {
                    viagemAdicionada=viagensAux;
                    clienteMaisViagens.clear();
                    clienteMaisViagens.add(clienteaux);
                }
                else if(viagensAux==viagemAdicionada)
                    clienteMaisViagens.add(clienteaux);
                viagensAux=0;
            }
        }
        imprimeString(String.valueOf(viagemAdicionada));
        estatisticas = estatisticas.concat("(total="+String.valueOf(viagemAdicionada)+")");
        for(Cliente aux_c:clienteMaisViagens) {
            imprimeUtilizador(aux_c);
            estatisticas = estatisticas.concat(" "+aux_c.getId());
        }
        escreveEstatisticas(estatisticas);
    }

    /**
     * Funcao responsavel por verificar se uma viagem realizada teve reservas ou nao.
     * A lista de viagens realizadas e percorrida viagem a viagem, e caso esta tenha ocorrido no mes e ano indicados ira confirmar se o numero de lugares livres('L') e igual ao numero de lugares disponiveis, caso isto se verifique a viagem e impressa no ecra.
     * Caso a viagem nao tenha reservas a informacao e tambem guardada no ficheiro "estatisticas.txt".
     * @param mes Mes a verificar.
     * @param ano Ano a verificar.
     * @param lista_v_r Lista de viagens realizadas.
     */
    private void viagensSemReserva(int mes,int ano, List<Viagem> lista_v_r){
        String estatisticas="Viagens sem reservas em "+String.valueOf(mes)+"/"+String.valueOf(ano)+":";
        for (Viagem v:lista_v_r){
            if (v.getData().getMes()==mes && v.getData().getAno()==ano){
                boolean flag=false;
                for (int i=0;i<v.getLugares().size();i++){
                    if (v.getLugares().get(i)!='L') {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    imprimeViagem(v);
                    estatisticas = estatisticas.concat(" "+String.valueOf(v.getCodigo()));
                }
            }
        }
        escreveEstatisticas(estatisticas);
    }

    /**
     * Funcao para listar todas as reservas de uma determinada viagem. Procura pela viagem na lista de viagens ou na lista de viagens realizadas e caso exista, vai percorrer todos os utilizadores e verificar se possuem alguma reserva com a viagem desejada.
     * A informacao e impressa no ecra e guardada no ficheiro "estatisticas.txt".
     * @param lista_u Lista de utilizadores.
     * @param cod_aux Codigo da viagem a procurar.
     * @param lista_v Lista de viagens.
     * @param lista_v_r Lista de viagens realizadas.
     */
    private void reservasViagem(List<Utilizador> lista_u,int cod_aux,List<Viagem> lista_v, List<Viagem> lista_v_r){
        Viagem v_aux=procurarViagem(cod_aux,lista_v);
        if (v_aux==null){
            v_aux=procurarViagem(cod_aux,lista_v_r);
        }
        if (v_aux!=null) {
            String estatisticas = "Reservas da viagem " + String.valueOf(cod_aux) + ":";
            for (Utilizador aux : lista_u) {
                if (aux.getTipo().equals("Cliente_Premium") || aux.getTipo().equals("Cliente_Regular")) {
                    Cliente cliente_aux = (Cliente) aux;
                    for (Reserva reserva_aux : cliente_aux.getReservas()) {
                        if (reserva_aux.getCod_viagem() == cod_aux) {
                            imprimeReserva(reserva_aux);
                            estatisticas = estatisticas.concat(" "+String.valueOf(reserva_aux));
                        }
                    }
                }
            }
            escreveEstatisticas(estatisticas);
        }
        else
            imprimeString("Viagem nao encontrada");
    }

    /**
     * Funcao para listar apenas as reservas canceladas de uma determinada viagem. Procura pela viagem na lista de viagens ou na lista de viagens realizadas e caso exista, vai percorrer todos os utilizadores e verificar se possuem alguma reserva com a viagem desejada.
     * A informacao e impressa no ecra e guardada no ficheiro "estatisticas.txt".
     * @param lista_u Lista de utilizadores.
     * @param cod_aux Codigo da viagem a procurar.
     * @param lista_v Lista de viagens.
     * @param lista_v_r Lista de viagens realizadas.
     */
    private void reservasCanceladasViagem(int cod_aux,List<Utilizador> lista_u,List<Viagem> lista_v, List<Viagem> lista_v_r){
        Viagem v_aux=procurarViagem(cod_aux,lista_v);
        if (v_aux==null){
            v_aux=procurarViagem(cod_aux,lista_v_r);
        }
        if (v_aux!=null) {
            List<Reserva> reservas_canceladadas = new ArrayList<>();
            for (Utilizador u_aux : lista_u) {
                if (u_aux.getTipo().equals("Cliente_Premium") || u_aux.getTipo().equals("Cliente_Regular")) {
                    Cliente cliente_aux = (Cliente) u_aux;
                    for (Reserva reserva_aux : cliente_aux.getReservas()) {
                        if (reserva_aux.getCod_viagem() == cod_aux) {
                            if (reserva_aux.getTipo().equals("Reserva_Cancelada")) {
                                reservas_canceladadas.add(reserva_aux);
                            }
                        }
                    }
                }
            }
            String estatisticas = "Reservas canceladas da viagem " + String.valueOf(cod_aux) + ":";
            for (Reserva aux_r : reservas_canceladadas) {
                imprimeReserva(aux_r);
                estatisticas = estatisticas.concat(" " + String.valueOf(aux_r));
            }
            escreveEstatisticas(estatisticas);
        }
        else
            imprimeString("Viagem nao encontrada");
    }

    /**
     * Funcao responsavel por listar todas as reservas em espera de todos os utilizadores.
     * A informacao e impressa no ecra e guardada no ficheiro "estatisticas.txt".
     * @param lista_u Lista de utilizadores.
     */
    private void reservasEspera(List<Utilizador> lista_u){
        String estatisticas = "Reservas em espera :";
        for(Utilizador u_aux:lista_u){
            if(u_aux.getTipo().equals("Cliente_Premium") || u_aux.getTipo().equals("Cliente_Regular")){
                Cliente c_aux=(Cliente)u_aux;
                for(Reserva r_aux:c_aux.getReservas()){
                    if(r_aux.getTipo().equals("Reserva_Espera")){
                        imprimeReserva(r_aux);
                        estatisticas = estatisticas.concat(" "+String.valueOf(r_aux));
                    }
                }
            }
        }
        escreveEstatisticas(estatisticas);
    }

    /**
     * Funcao para verificar qual a viagem num determinado mes que teve melhor pontuacao, atraves da media das avaliacoes inseridas pelos utilizadores.
     * A lista de viagens realizadas e percorrida viagem a viagem, e caso o mes e ano coincidam e calculada a media da avaliacao dessa viagem, sendo sempre guardada a viagem com melhor media, para depois ser impressa e guardada no ficheiro estatisticas.txt.
     * @param lista_v_r Lista de viagens realizadas.
     * @param mes Mes a verificar.
     * @param ano Ano a verificar.
     */
    private void viagemMelhorPontuacao(List<Viagem> lista_v_r,int mes,int ano){
        List<Viagem> viagensMelhorPontuacao=new ArrayList<>();
        float media_adicionada=0;
        float media_aux;
        for(Viagem viagem_aux:lista_v_r){
            if(viagem_aux.getData().getAno()==ano && viagem_aux.getData().getMes()==mes) {
                media_aux = viagem_aux.calculaMedia();
                if (media_aux > media_adicionada) {
                    media_adicionada = media_aux;
                    viagensMelhorPontuacao.clear();
                    viagensMelhorPontuacao.add(viagem_aux);
                } else if (media_aux == media_adicionada)
                    viagensMelhorPontuacao.add(viagem_aux);
            }
        }
        String estatisticas = "Viagem com melhor pontuacao em "+String.valueOf(mes)+"/"+String.valueOf(ano)+":";
        estatisticas = estatisticas.concat("(media pontuacao="+String.valueOf(media_adicionada)+")");
        for(Viagem imprimeViagem:viagensMelhorPontuacao) {
            imprimeString(String.valueOf(media_adicionada));
            imprimeViagem(imprimeViagem);
            estatisticas = estatisticas.concat(" "+String.valueOf(imprimeViagem));
        }
        escreveEstatisticas(estatisticas);

    }

    /**
     * Funcao responsavel por escrever a informacao das estatisticas no ficheiro estatisticas.txt.
     * @param info Informacao a escrever no ficheiro.
     */
    private void escreveEstatisticas(String info) {
        FicheiroDeTexto file_est = new FicheiroDeTexto();
        try {
            file_est.abreEscritaT("estatisticas.txt");
            file_est.escreveLinha(info+"\n");
            file_est.fechaEscritaT();
        } catch (java.io.IOException e) {
            imprimeString("Erro a escrever para o ficheiro estatisticas.txt.");
        }


    }
}