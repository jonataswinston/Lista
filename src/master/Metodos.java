package master;
import java.sql.*;

import static master.Conexao.*;

public class Metodos {

    public static void listEvents() {
        System.out.printf("ID | Titulo | Custo | Data de criação | Data de modificação | " +
                "Data de execução | Status\n");
        //System.out.println(getmaxLineSQL());
        for (int i = 1; i <= getmaxLineSQL(); i++) {
            getLinesSQL(i);

        }// fim do for
    }//fim do metodo
}
