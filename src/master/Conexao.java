package master;
import java.sql.*;
public class Conexao {

    protected static Connection con;
    private static Statement stmt;
    private static String ip_Serv="35.198.0.84";//ip do sql na google kkk 35.198.0.84
    private static String port="3306";
    private static String url="jdbc:mysql://"+ip_Serv+":"+port+"/";
    private static String db="testes";
    private static String driver="com.mysql.jdbc.Driver";
    private static String user="applet";
    private static String paswd="qwe123";
    private static String ssl="useSSL=false";
    protected static String tabela="cadastro";
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
            System.out.println("Conexão com o banco "+ip_Serv+" encerrada!");
            //stmte.close();
        }catch (Exception e){
            System.out.println(e+ "Classe conexão - stopConexão");
        }
    }

    public static void getLinesSQL(int i){
        startConexao();
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url+db,user,paswd);
            stmt = con.createStatement();
            PreparedStatement stmte = con.prepareStatement("SELECT id, titulo, texto, custo, " +
                    "data_crea, data_mod, data_exec, status FROM "+tabela+"WHERE id LIKE ?");
            stmte.setInt(1,i);
            pswd = stmte.executeQuery();
            pswd.next();
            String title = pswd.getString("Senha_user");
            double cost = pswd.getDouble("Senha_user");
            String dCreate = pswd.getString("Senha_user");
            String dModif = pswd.getString("Senha_user");
            String dExec = pswd.getString("Senha_user");
            String status = pswd.getString("Senha_user");
            System.out.println(i+" | "+title+" | R$"+cost+" | "+dCreate+" | "+dModif+" | "+dExec+" | "+status);
        }catch (Exception e){
            System.out.println(e);
        }
        stopConexao();
    }

    public static int getmaxLineSQL() {
        int maxid = 0;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, user, paswd);
            stmt = con.createStatement();
            PreparedStatement stmte = con.prepareStatement("SELECT max(id) FROM testes.cadastro");
            pswd = stmte.executeQuery();
            pswd.next();

            //maxid = pswd.getInt("id");
        } catch (Exception e) {
            System.out.println(e);
        }
        return maxid;
    }
}
