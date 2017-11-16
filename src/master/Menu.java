package master;
import java.util.Scanner;

import static master.Metodos.listEvents;
import static master.Conexao.*;

public class Menu {
    private static Scanner ler = new Scanner(System.in);

    public static void menuPrincipal(){
        int choice = 0;
        do {
            menuPrincipalOpcoes();
            choice = menuPrincipalEscolha(ler.nextInt());
        }while (!(""+choice).equals("9"));
    }

    private static int menuPrincipalEscolha(int choice){
        switch (choice){
            case 1 : listEvents();
                break;
            case 2 : inserirEvento();
                break;
            case 3 :
                break;
            case 9 :
                break;
            default: System.out.println("Opção inválida!");
        }
        return choice;
    }

    private static void menuPrincipalOpcoes(){
        System.out.println("--- Escolha uma opção! ---");
        System.out.println("1. Listar Eventos --------");
        System.out.println("2. Inserir Evento --------");
        System.out.println("3. Editar Evento ---------");
        System.out.println("9. Sair ------------------");
    }


}
