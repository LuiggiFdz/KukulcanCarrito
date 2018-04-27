import java.util.*;
import java.io.*;
/**
 * @author LuiggiFernandez
 */
public class KukulcanCarrito {
    public static void main(String[] args) throws IOException {
        Kukulcan_Requirements METHODS = new Kukulcan_Requirements();
        METHODS.BIENVENIDA();
    }
}
public class Kukulcan_Requirements {
    public static void BIENVENIDA() throws IOException {
        Scanner K = new Scanner(System.in);
        System.out.println("██████╗ ██╗███████╗███╗   ██╗██╗   ██╗███████╗███╗   ██╗██╗██████╗  ██████╗ \n" +
                           "██╔══██╗██║██╔════╝████╗  ██║██║   ██║██╔════╝████╗  ██║██║██╔══██╗██╔═══██╗\n" +
                           "██████╔╝██║█████╗  ██╔██╗ ██║██║   ██║█████╗  ██╔██╗ ██║██║██║  ██║██║   ██║\n" +
                           "██╔══██╗██║██╔══╝  ██║╚██╗██║╚██╗ ██╔╝██╔══╝  ██║╚██╗██║██║██║  ██║██║   ██║\n" +
                           "██████╔╝██║███████╗██║ ╚████║ ╚████╔╝ ███████╗██║ ╚████║██║██████╔╝╚██████╔╝\n" +
                           "╚═════╝ ╚═╝╚══════╝╚═╝  ╚═══╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝╚═╝╚═════╝  ╚═════╝ \n");
        System.out.println("\n¿Usted ya es Usuario?\nS / N");
        char ISUSER;
        ISUSER = K.nextLine().charAt(0);
        if (ISUSER == 's' || ISUSER == 'S') {
            System.out.println("Usuario: ");
            String USER = K.nextLine();
            while (!USER_EXISTS(USER)) {
                System.out.println("Usuario: ");
                USER = K.nextLine();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("Contraseña: ");
                String PASSWORD = K.nextLine();
                ACCESS(USER, PASSWORD);
                if (ACCESS(USER, PASSWORD)) {
                    i = 3;
                    System.out.println("Bienvenido "+USER);
                    MENU();
                }
                else {
                    i++;
                    if (i == 3) {
                        System.out.println("Ha excedido el número de intentos para el usuario.");
                    }
                }
            }
        } else {
            System.out.println("Ingrese un nombre de usuario: ");
            String USER = K.nextLine();
            while (USER_EXISTS(USER)){
                System.out.println("El nombre de usuario ya existe, seleccione otro\nIngrese un nombre de usuario:");
                USER = K.nextLine();
            }
            System.out.println("Ingrese una contraseña: ");
            String PASSWORD = K.nextLine();
            System.out.println("Ingrese su mail: ");
            String MAIL = K.nextLine();
            NEW_USER(USER, PASSWORD, MAIL);
            MENU();
        }
        System.out.println("¡La sesión ha expirado!\n¡Vuelva pronto!");
        System.exit(0);
    }
    public static String ENCRYPT(String PASSWORD) {
        int ENCRYPTED = PASSWORD.hashCode()*242;
        PASSWORD = Integer.toString(ENCRYPTED);
        ENCRYPTED = PASSWORD.hashCode()+48;
        PASSWORD = Integer.toString(ENCRYPTED);
        ENCRYPTED = PASSWORD.hashCode()-767;
        PASSWORD = Integer.toString(ENCRYPTED);
        ENCRYPTED = PASSWORD.hashCode()+34;
        PASSWORD = Integer.toString(ENCRYPTED);
        ENCRYPTED = PASSWORD.hashCode()/2;
        PASSWORD = Integer.toString(ENCRYPTED);
        ENCRYPTED = PASSWORD.hashCode()*2;
        PASSWORD = Integer.toString(ENCRYPTED);
        ENCRYPTED = PASSWORD.hashCode()+5678;
        PASSWORD = Integer.toString(ENCRYPTED);
        ENCRYPTED = PASSWORD.hashCode()+12;
        PASSWORD = Integer.toString(ENCRYPTED);
        ENCRYPTED = PASSWORD.hashCode()-1234;
        PASSWORD = Integer.toString(ENCRYPTED);
        ENCRYPTED = PASSWORD.hashCode()+666;
        PASSWORD = Integer.toString(ENCRYPTED);
        return PASSWORD;
    }
    public static boolean ACCESS(String USERS, String PASSWORD) throws IOException {
        boolean ACCESS = false;
        String PASSWORD2 = ENCRYPT(PASSWORD);
        File DB = new File("/Users/LuiggiFernandez/Desktop/Progs/KukulcanCarrito/Kukulcan_Users.txt");
        try (FileReader GET_DB = new FileReader(DB); BufferedReader SET_DB = new BufferedReader(GET_DB)) {
            String LINE;
            String LINE_COLUMN[];
            LINE = SET_DB.readLine();
            while (LINE != null && ACCESS != true) {
                LINE_COLUMN= LINE.split("\\|");
                if (USERS.equals(LINE_COLUMN[0]) && PASSWORD2.equals(LINE_COLUMN[1])) {
                    ACCESS = true;
                } else {
                    LINE = SET_DB.readLine();
                }
            }
        }
        return ACCESS;
    }
    public static boolean USER_EXISTS(String USER) throws IOException{
        boolean EXISTS = false;
        File DB = new File("/Users/LuiggiFernandez/Desktop/Progs/KukulcanCarrito/Kukulcan_Users.txt");
        try (FileReader GET_DB = new FileReader(DB); BufferedReader SET_DB = new BufferedReader(GET_DB)) {
            String LINE;
            String LINE_COLUMN[];
            LINE = SET_DB.readLine();
            while (LINE != null) {
                LINE_COLUMN= LINE.split("\\|");
                if (USER.equals(LINE_COLUMN[0])) {
                    EXISTS = true;
                }
                LINE = SET_DB.readLine();
            }
            GET_DB.close();
            SET_DB.close();
        }
        return EXISTS;
    }
    public static void NEW_USER(String USER, String PASSWORD, String MAIL) throws IOException{
        String PASSWORD2 = ENCRYPT(PASSWORD);
        File DB = new File("/Users/LuiggiFernandez/Desktop/Progs/KukulcanCarrito/Kukulcan_Users.txt");
        try (FileReader GET_DB = new FileReader(DB); BufferedReader SET_DB = new BufferedReader(GET_DB); FileWriter FW_DB = new FileWriter(DB, true); BufferedWriter BW_DB = new BufferedWriter(FW_DB)) {
            String LINE;
            LINE = SET_DB.readLine();
            while (LINE != null) {
                LINE = SET_DB.readLine();
            }
            if (LINE == null) {
                BW_DB.write("\n"+USER+"|"+PASSWORD2+"|"+MAIL);
            }
            GET_DB.close();
            SET_DB.close();
            BW_DB.close();
            FW_DB.close();
        }
    }
    public static String FORMAT(String WORD, int SIZE){
        while(WORD.length() < SIZE){
            WORD+= " ";
        }
        return WORD;
    }
    public static void E_CAR(int x) throws IOException {
        Scanner K = new Scanner(System.in);
        int SUM = 0;
        if (x == 0) {
            System.out.println("Su carrito está vacío\nRegrese al menu");
            MENU();
        } else {
            System.out.println("1)Add\n2)Delete\n3)Pay");
            int OP = K.nextInt();
            switch (OP) {
                case 1:
                    ADD();
                    break;
                case 2:
                    DELETE();
                    break;
                case 3:
                    for (int i = 0; i < x; i++) {
                        SUM += PROD[i];
                    }
                    System.out.println("Total a pagar: $"+SUM+".00");
                    File DB = new File("/Users/LuiggiFernandez/Desktop/Progs/KukulcanCarrito/Kukulcan_Ticket.txt");
                    FileWriter FW_DB = new FileWriter(DB);
                    try (BufferedWriter BW_DB = new BufferedWriter(FW_DB)) {
                        for (int i = 0; i < x; i++) {
                            BW_DB.write("Producto: "+PROD[i]+"\n");
                        }
                        BW_DB.write("Total a pagar: $"+SUM+".00");
                        BW_DB.close();
                        FW_DB.close();
                    }
                    break;
                default:
                    E_CAR(x);
            }
        }
    }
    public static void ADD() throws IOException{
        Scanner k = new Scanner(System.in);
        int x = 0;
        for (int i = 0; i < 10; i++) {
            if (PROD[i] == 0 && x < 1) {
                System.out.print("Ingrese el valor que desea: ");
                PROD[i] = k.nextInt();
                x++;
            }
            else {
                System.out.println("No puede seleccionar más productos");
            }
        }
        int z = 0;
        for (int y : PROD) {
            z++;
        }
        E_CAR(z);
    }
    public static void DELETE() throws IOException {
        Scanner k = new Scanner(System.in);
        int i = 1;
        if (PROD[0] == 0) {
            System.out.println("No hay productos que eliminar");
        }
        else {
            for (int n : PROD) {
                if (i < 10) {
                    PROD[n] = PROD[n+1];
                    i++;
                }
            }
            PROD[10] = 0;
        }
        int z = 0;
        for (int y : PROD) {
            z++;
        }
        E_CAR(z);
    }
    static int PROD[] = new int [10];
    public static void MENU() throws IOException {
        Scanner K = new Scanner(System.in);
        int MENU;
        System.out.println("Menu:\n1)Productos\n2)Carrito\n3)Salir");
        MENU = K.nextInt();
        switch (MENU) {
            case 1:
                File P_DB = new File("/Users/LuiggiFernandez/Desktop/Progs/KukulcanCarrito/Kukulcan_Products.txt");
                FileReader GET_DB = new FileReader(P_DB);
                BufferedReader SET_DB = new BufferedReader(GET_DB);
                String LINE;
                String LINE_COLUMN[];
                LINE = SET_DB.readLine();
                System.out.println("===============PRODUCTOS===============");
                while (LINE != null) {
                    LINE_COLUMN = LINE.split("\\|");
                    System.out.println(FORMAT(LINE_COLUMN[0], 3)+" | "+FORMAT(LINE_COLUMN[1], 20)+" | "+FORMAT(LINE_COLUMN[3], 7)+" | "+FORMAT(LINE_COLUMN[4], 6));
                    LINE = SET_DB.readLine();
                }
                System.out.println("=======================================");
                System.out.println("¿Qué productos desea?\n*NOTA: Actualmente solo hay disponibilidad de 1 por producto");
                boolean NEXT = true;
                int x = 0;
                while (NEXT) {
                    PROD[x] = K.nextInt();
                    x++;
                    System.out.println("¿Desea agregar otro producto?\nEscriba (true/false)");
                    NEXT = K.nextBoolean();
                }
                GET_DB.close();
                SET_DB.close();
                E_CAR(x);
                break;
            case 2:
                E_CAR(0);
                break;
            case 3:
                System.out.println("Hasta luego, ¡Vuelva pronto!");
                System.exit(0);
                break;
            default:
                MENU();
                break;
        }
    }
}
