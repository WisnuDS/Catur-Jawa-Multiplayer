package com.company;

public class TampilanPapan {
    public static void printBoard(Papan board){
        clearConsole();
        Ubin[][] b = board.getUbins();

        System.out.println();
        System.out.println("      [A][B][C] \n");
        for(int i = 0; i < 3; i++) {
            System.out.print("[" + (3 - i) + "]   ");

            for (int j = 0; j < 3; j++){
                System.out.print(b[i][j].getValue());
            }

            System.out.println("   [" + (3 - i) + "]");
        }

        System.out.println("\n      [A][B][C]\n");
    }

    /**
     * Universal console clear for both Windows and Unix machines.
     */
    public static void clearConsole(){
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                //ASCII escape code
                System.out.print("\033[H\033[2J");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e){
            System.out.println("Error while trying to clear console");
        }
    }
}
