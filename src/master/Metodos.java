package master;
import java.sql.*;

import static master.Conexao.*;

public class Metodos {

    public static void listEvents(){
        System.out.printf("ID | Titulo | Custo | Data de criação | Data de modificação | " +
                "Data de execução | Status");
        System.out.println(getmaxLineSQL());
       // for (int i  = 0; i<(getmaxLineSQL()+1); i++){
            //getLinesSQL(1);
        // fim do for
    }


}
