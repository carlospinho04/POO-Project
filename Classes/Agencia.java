package Agencia;

import java.util.*;

import static Agencia.FicheiroDeObjeto.*;

public class Agencia {
    /**
     *Funcao principal do projeto, cuja funcao e carregar toda a informacao encontrada nos ficheiros para as devidas listas e chamar a funcao login.
     */
    public static void main(String[] args){

        Data d_atual=Data.inserirdata();

        List<Utilizador> lista_utilizadores = new ArrayList<>();
        List<Viagem> lista_viagens = new ArrayList<>();
        List<Viagem> lista_viagens_realizadas = new ArrayList<>();
        List<Autocarro> lista_autocarros = new ArrayList<>();

        carregaUtilizadores(lista_utilizadores);
        carregaViagens(lista_viagens, lista_viagens_realizadas, d_atual);
        carregaAutocarros(lista_autocarros);

        //Em caso de nao existirem admins tirar os comentarios
        //Admin temp= new Admin("dcm","Dinis Marques", 123456789, "Coimbra - Portugal",911111111,"dinis@dcm.com","password");
        //temp.menu(lista_utilizadores,lista_viagens,lista_viagens_realizadas,lista_autocarros,d_atual);

        while(true){
            Utilizador user=login(lista_utilizadores);
            if (user!=null){
                imprimeString("Login efetuado com sucesso!");
                    user.menu(lista_utilizadores,lista_viagens,lista_viagens_realizadas,lista_autocarros,d_atual);
            }
            else
                imprimeString("Erro no login.");
            imprimeString("Deseja entrar com outra conta?");
            imprimeString("1-Sim.");
            imprimeString("0-Nao.");
            int sair=escolhaopcao(0,1);
            if (sair==0) {
                imprimeString("Programa a desligar.");
                System.exit(0);
            }
        }
    }

    /**
     * Funcao responsavel pelo login, esta funcao vai procurar na lista de utlizadores, que e passada como argumento, se o id existe e chama a funcao " checklogin" em que o argumento passado e a password pedida no inicio da funcao login e vai verificar se a password pertence ao id anteriormente referido, caso pertenca da return do utilizador, caso contrario retorna null.
     * @param lista_u Lista de utilizadores na qual as credenciais sao verificadas.
     * @return A funcao vai retornar o utilizador caso o login aconteca com sucesso, caso contrario retorna null.
     */
    public static Utilizador login(List<Utilizador> lista_u){
        String id,pw;
        Scanner sc = new Scanner(System.in);
        imprimeString("##### Login #####");
        imprimeString("ID:");
        id=sc.nextLine();
        imprimeString("PW:");
        pw=sc.nextLine();
        Utilizador user;
        Iterator<Utilizador> it = lista_u.iterator();
        while(it.hasNext()) {
            user=it.next();
            if (Objects.equals(user.getId().toLowerCase(), id.toLowerCase())) {
                if (user.checklogin(pw)!=null)
                    return user;
            }
        }
        return null;

    }

    /**
     * Funcao responsavel pela impressao das Strings.
     * @param aux String a imprimir no ecra.
     */
    public static void imprimeString(String aux){

        System.out.println(aux);
    }

    /**
     * Funcao responsavel pela impressao da classe Viagem.
     * @param v_aux Viagem a imprimir no ecra.
     */
    public static void imprimeViagem(Viagem v_aux){
        System.out.println(v_aux);
    }

    /**
     * Funcao responsavel pela impressao da classe Reserva.
     * @param r_aux Reserva a imprimir no ecra.
     */
    public static void imprimeReserva(Reserva r_aux){
        System.out.println(r_aux);
    }

    /**
     * Funcao responsavel pela impressao da classe Utilizador
     * @param u_aux Utilizador a imprimir no ecra.
     */
    public static void imprimeUtilizador(Utilizador u_aux){
        System.out.println(u_aux);
    }

    /**
     * Funcao responsavel pela impressao da classe Autocarro
     * @param a_aux Autocarro a imprimir no ecra.
     */
    public static void imprimeAutocarro(Autocarro a_aux){
        System.out.println(a_aux);
    }

    /**
     * Funcao responsavel pela impressao da classe Comentario
     * @param c_aux Comentario a imprimir no ecra.
     */
    public static void imprimeComentario(Comentario c_aux){
        System.out.println(c_aux);
    }

    /**
     * Funcao responsavel pelo scan de um inteiro, so saira da funcao while quando nao for inserido algo diferente de um inteiro.
     * @param printAux String passada como argumento para uma correta impressao da mensagem de erro.
     * @return Retorna o inteiro inserido.
     */
    public static int scanINT(String printAux){
        Scanner sc=new Scanner(System.in);
        int aux;
        while (!sc.hasNextInt()) {
            imprimeString(printAux + ", insira novamente:");
            sc.next();
        }
        aux = sc.nextInt();sc.nextLine();
        return aux;
    }

    /**
     * Funcao responsavel pelo scan de um long, so saira da funcao while quando nao for inserido algo diferente de um long.
     * @param printAux String passada como argumento para uma correta impressao da mensagem de erro.
     * @return Retorna o long inserido.
     */
    public static long scanLong(String printAux){
        Scanner sc=new Scanner(System.in);
        long aux;
        while (!sc.hasNextLong()) {
            imprimeString(printAux + ", insira novamente:");
            sc.next();
        }
        aux = sc.nextLong();sc.nextLine();
        return aux;
    }

    /**
     * Funcao responsavel pelo scan de uma String.
     * @return Retorna a String inserida.
     */
    public static String scanStr(){
        Scanner sc=new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Funcao responsavel pela averiguacao da escolha optada, essa escolha tera de estar dentro de um intervalo delimitado pelos parametros recebidos (min,max).
     * @param min Int passado para estabelecer o limite minimo do intervalo.
     * @param max Int passado para estabelecer o limite maximo do intervalo.
     * @return Retorna a opcao pretendida.
     */
    public static int escolhaopcao(int min,int max){
        int opcao;
        imprimeString("Introduza a opcao:");
        do{
            opcao= Agencia.scanINT("Opcao Invalida");
            if(opcao<min || opcao >max)
                imprimeString("Opcao invalida, insira novamente:");
        }while(opcao<min || opcao >max);
        return opcao;
    }

    /**
     * Funcao responsavel pela verificacao do email passado como parametro.Esta funcao verifica se existe um "@" e pelo menos um ".", nao podendo estes estarem no inicio ou no fim do email, ainda verifica a existencia de espacos.
     * @param email Email a verificar.
     * @return Retornara true caso todas os requisitos sejam cumpridos, caso contrario retornara false.
     */
    public static boolean verificaEmail(String email){
        int len_aux=email.length(),contador_ponto=0,contador_arroba=0;
        for(int i=0;i<len_aux;i++){
            if(email.charAt(0)=='@'){
                imprimeString("Email invalido, insira novamente:");
                return false;
            }
            else if(email.charAt(i)==' '){
                imprimeString("Email invalido, insira novamente:");
                return false;
            }
            else if(i+1==len_aux){
                if(email.charAt(i)=='@' || email.charAt(i)=='.') {
                    imprimeString("Email invalido, insira novamente:");
                    return false;
                }
            }
            else if(email.charAt(i)=='@')
                contador_arroba++;
            else if(email.charAt(i)=='.')
                contador_ponto++;
        }
        if(contador_arroba==1 && contador_ponto>=1)
            return true;
        else
            imprimeString("Email invalido, insira novamente:");
        return false;
    }

    /**
     * Funcao responsavel pela verificacao da morada passada como argumento.Verifica se a morada nao e uma string vazia, se nao termina num espaco, ou ainda se nao tem dois espacos seguidos.
     * @param morada Morada a verificar.
     * @return Retornara true se todos os requisitos forem cumpridos, caso contrario retorna false.
     */
    public static boolean verificaMorada(String morada){
        int len_aux=morada.length();
        if(morada.equals(" ")){
            imprimeString("Morada invalida, insira novamente:");
            return false;
        }
        for(int i=0;i<len_aux;i++){
            if(morada.charAt(0)==' '){
                imprimeString("Morada invalida, insira novamente:");
                return false;
            }
            else if (morada.charAt(i)==' ') {
                if(i+1==len_aux){
                    imprimeString("Morada invalida, insira novamente:");
                    return false;
                }
                else if(morada.charAt(i+1)==' ') {
                    imprimeString("Morada invalida, insira novamente:");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Funcao responsavel pela verificacao de uma certa credencial passada como parametro.Verifica se existe espacos na string recebida como argumento.
     * @param credencial Parametro a verificar.
     * @return Retornara true se o requisito for cumprido, caso contrario retorna false.
     */
    public static boolean verificaCredenciais(String credencial){
        int len_aux=credencial.length();
        for(int i=0;i<len_aux;i++){
            if(credencial.charAt(i)==' ') {
                imprimeString("Credencial invalido, insira novamente:");
                return false;
            }
        }
        return true;
    }

    /**
     * Funcao responsavel pela verificacao de um certo numero recebido como parametro.Verifica se o numero tem 9 digitos.
     * @param numero Numero a verificar.
     * @return Retornara true se o requisito for cumprido, caso contrario retorna false.
     */
    public static boolean verificaNumeros(long numero){
        int n = 0;
        long aux = numero;
        while (aux != 0) {
            aux /= 10;
            n++;
        }
        if (n != 9) {
            imprimeString("Numero invalido, insira novamente:");
            return false;
        }
        return true;
    }

    /**
     * Funcao responsavel pela verificacao de uma string recebida como parametro.Verifica se a string nao e vazia, nao contem numeros, dois espacos seguidos ou ainda se termina num espaco.
     * @param nome Nome a verificar.
     * @param aux String passada como argumento para uma correta impressao da mensagem de erro.
     * @return Retornara true se todos os requisitos forem cumpridos, caso contrario retorna false.
     */
    public static boolean verificaString(String nome,String aux){
        int spaces=0,i;
        int len_aux=nome.length();
        if(nome.equals(" ")){
            imprimeString(aux+" invalido, insira novamente:");
            return false;
        }
        for(i=0;i<len_aux;i++){
            if((nome.charAt(i)>='a' && nome.charAt(i)<='z') || (nome.charAt(i)>='A' && nome.charAt(i)<='Z') || (nome.charAt(i)==' ')){
                if (nome.charAt(i)==' ') {
                    spaces++;
                    if(i+1==len_aux){
                        imprimeString(aux+" invalido, insira novamente:");
                        return false;
                    }
                    else if(nome.charAt(i+1)==' ') {
                        imprimeString(aux+" invalido, insira novamente:");
                        return false;
                    }
                }
            }
            else{
                imprimeString(aux+" invalido, insira novamente:");
                return false;
            }
        }
        if (i==0 || i==spaces){
            imprimeString(aux+" invalido, insira novamente:");
            return false;
        }
        else
            return true;
    }

    /**
     * Funcao responsavel pela verificacao de uma matricula passada como parametro.Verifica se esta no formato das matriculas portuguesas.
     * Exemplos :00-AA-00,AA-00-00,00-00-AA.
     * @param mat Matricula a verificar.
     * @return Retornara true se todos os requisitos forem cumpridos, caso contrario retorna false.
     */
    public static boolean verificaMatricula(String mat){
        if (mat.length()!=8){
            imprimeString("Matricula invalida.");
            return false;
        }
        if (mat.charAt(2)!='-' && mat.charAt(5)!='-'){
            imprimeString("Matricula invalida.");
            return false;
        }
        String[] aux=mat.split("-");
        for(int i=0;i<3;i++) {
            if (aux[i].charAt(0) >= 'A' && aux[i].charAt(0) <= 'Z' && aux[i].charAt(1) >= 'A' && aux[i].charAt(1) <= 'Z') {
                if (aux[(i+1)%3].charAt(0) >= '0' && aux[(i+1)%3].charAt(0) <= '9' && aux[(i+1)%3].charAt(1) >= '0' && aux[(i+1)%3].charAt(1) <= '9') {
                    if (aux[(i+2)%3].charAt(0) >= '0' && aux[(i+2)%3].charAt(0) <= '9' && aux[(i+2)%3].charAt(1) >= '0' && aux[(i+2)%3].charAt(1) <= '9') {
                        return true;
                    }
                }
            }
        }
        imprimeString("Matricula invalida.");
        return false;
    }






}
