package master;
import java.sql.*;
import java.util.Scanner;

import static master.Metodos.listEvents;

public class Conexao {

    protected static Connection con;
    private static Statement stmt;
    private static String ip_Serv="35.198.0.84";//ip do sql na google kkk 35.198.0.84
    private static String port="3306";
    private static String url="jdbc:mysql://"+ip_Serv+":"+port+"/";
    private static String db="Winston_hobby";
    private static String driver="com.mysql.jdbc.Driver";
    private static String user="applet";
    private static String paswd="qwe123";
    private static String ssl="?useSSL=false";
    protected static String tabela="roles_list";
    protected static ResultSet pswd=null;

    protected static void startConexao (){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url+db+ssl,user,paswd);
            stmt = con.createStatement();
        }catch (Exception e){
            System.out.println(e+ "Classe conexão - startConexão");
        }
    }

    protected static void stopConexao(){
        try {
            con.close();
            stmt.close();
            //stmte.close();
        }catch (Exception e){
            System.out.println(e+ "Classe conexão - stopConexão");
        }
    }

    public static void getLinesSQL(int i){
        startConexao();
        try{
            Class.forName(driver);
            PreparedStatement stmte = con.prepareStatement("SELECT * FROM "+tabela+" WHERE id LIKE ?");
            stmte.setInt(1,i);
            pswd = stmte.executeQuery();
            pswd.next();
            returnString(pswd, i);
            }catch (Exception e){
            System.out.println(e);
        }
        stopConexao();
    }

    public static int getmaxLineSQL() {
        int maxid = 0;
        startConexao();
        try {
            PreparedStatement stmte = con.prepareStatement("SELECT * FROM "+tabela);
            pswd = stmte.executeQuery();
            pswd.last();
            maxid = pswd.getInt("id");
        } catch (Exception e) {
            System.out.println(e);
        }
        stopConexao();
        return maxid;
    }

    public static void returnString(ResultSet pswd, int i){
        try {
            String title = pswd.getString("titulo");
            double cost = pswd.getDouble("custo");
            String dCreate = pswd.getString("data_crea");
            String dModif = pswd.getString("data_mod");
            String dExec = pswd.getString("data_exec");
            String status = pswd.getString("status");
            System.out.println(i+" | "+title+" | R$"+cost+" | "+dCreate+" | "+dModif+" | "+dExec+" | "+status);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void inserirEvento(){
        Scanner ler = new Scanner(System.in);
        System.out.println("Insira o titulo da atividade ");
        String titulo = ler.nextLine();
        System.out.print("Qual o custo previsto? R$ ");
        double custo = ler.nextDouble();
        System.out.println("Insira a data de execução");
        String data_de_exec = ler.nextLine();
        System.out.println("Status atual: PENDENTE");
        System.out.println("Deseja alterar o status? y | n ");
        String choice = ler.nextLine();
        String status = "PENDENTE";
        if (choice.equalsIgnoreCase("y")){
            int ch = 0;
                System.out.println("Escolha uma opção:\n1.CANCELADO | 2.PROXIMO | 3.FEITO ");
                ch = ler.nextInt();
                if (ch == 1) {
                    status = "CANCELADO";
                } else if (ch == 2) {
                    status = "PROXIMO";
                } else if (ch == 3) {
                    status = "FEITO";
                } else {
                    System.out.println("Opção inválida\nAssumindo padrão: PENDENTE");
                    status = "PENDENTE";
                }
        }
        toStringEscrita(titulo, custo, data_de_exec, status);
        listEvents();
    }
    public static void toStringEscrita(String titulo, double custo, String data_de_exec, String status){
        startConexao();
        try{
            PreparedStatement stmte = con.prepareStatement("INSERT INTO "+tabela+
                    " (titulo,custo,data_exec, status) VALUES(?,?,?,?)");
            stmte.setString(1,titulo);
            stmte.setDouble(2,custo);
            stmte.setString(3,data_de_exec);
            stmte.setString(4, status);
            stmte.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Evento inserido com sucesso!");
        stopConexao();
    }

}
