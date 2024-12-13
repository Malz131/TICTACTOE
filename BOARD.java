


import java.util.Arrays;
import java.util.Scanner;


public class BOARD{

    private Boolean isplay;

    public static void player_input(Player playerA,Player playerB,String[][] arr){
        System.out.print("PLAYER INPUT: ");
        Scanner StdIn= new Scanner(System.in);
        String[] input=StdIn.nextLine().split(" ");
        //TRY AND CATCH(EXCEPT):
        try{
        Integer row=Integer.parseInt(input[0]);
        Integer col=Integer.parseInt(input[1]);
    
        if ((0<=row && row<=2) && (0<=col && col<=2)){
            playerA.row=row;
            playerA.col=col;
            arr[playerA.row][playerA.col]=playerA.player_piece;
            // playerB.playing=true;
            // playerA.playing=false;
        }
            else{
                System.out.println("ERROR: OUT OF BOUNDS");
                System.exit(1);};}
        catch(Exception e){
            //"ERROR:INVALID POSITION"
            System.out.println("ERROR:INVALID POSITION");
            System.exit(1);}}
    //RANDOM LETTER:
    public static void random_letter(BOARD main0,Player playerA,Player playerB){
        Integer r_option=Integer.parseInt(Long.toString(Math.round(1*Math.random())));
        switch(r_option){
            case 0:
                playerA.player_piece=" x ";
                playerB.player_piece=" o ";
            break;
            case 1:
            playerA.player_piece=" o ";
            playerB.player_piece=" x ";
            break;

        };
    }
    //PRINTING THE BOARD:
    public static void starter(BOARD main0){
        Integer r_option=Integer.parseInt(Long.toString(Math.round(1*Math.random())));
        switch(r_option){
            case 0:
            main0.isplay=false;
            break;
            case 1:
            main0.isplay=true;
            break;

        };

    }
    //INITIAL GAME:
    public static void initial_game(Player playerA,Player playerB,Computer computer,String[][] arr){
    if (playerB.playing==true){
        computer.startofgame();
        arr[playerB.row][playerB.col]=playerB.player_piece;
        playerB.playing=false;
        playerA.playing=true;
        System.out.println("COM001 TURN");

    }
    else{
        player_input(playerA,playerB,arr);
        playerB.playing=true;playerA.playing=false;
        System.out.println("PLAYER TURN ");

    }
    print_board(arr);}
    //PRINTING THE BOARD:
    public static void print_board(String[][] arr){
        Integer size=arr.length;
    System.out.print("    ");

        for (Integer i=0;i<size;i++){
            System.out.print(Integer.toString(i)+"   ");
    };
    System.out.println(" ");
    System.out.print("  ");
        for (Integer i=0;i<size;i++){
                System.out.print("+---");
        }
        System.out.println("+");
        for (Integer k=0;k<size;k++){
            System.out.print(Integer.toString(k)+" |");
            for (Integer j=0;j<size;j++){
                System.out.print(arr[k][j]+"|");
        };
        System.out.println("");
        System.out.print("  ");
        for (Integer i=0;i<size;i++){
            System.out.print("+---");
    }
    System.out.println("+");
    }
    
        
    }
    public static void reset_board(String[][] arr){
        for(Integer i=0;i<3;i++){
            for(Integer k=0;k<3;k++){
                arr[i][k]="   ";
            };
    
            };
    }
    //BOARD CLASS
    public BOARD(Boolean isplay0){
        this.isplay=isplay0;


    }
    // THE GAMELOOP:
    public static void during_game(Player playerA,Player playerB,Computer computer,String[][] arr){
        Boolean winner=false;

        while (winner==false){

            if (winner==true){
                break;
            };
            if (playerB.playing==true){
            computer.checker(playerA.player_piece);
            arr[playerB.row][playerB.col]=playerB.player_piece;
            //GAMEVALIDATION OBJECT:
            GAMEVAL gameval=new GAMEVAL(playerB,playerA,arr);
            String results=gameval.winner();
            System.out.println("COM001 TURN");
            print_board(arr);
            playerB.playing=false;playerA.playing=true;
            if (results!="NO WINNER"){
                System.out.println(results);
                return;
            }
            

        }
            if (playerA.playing==true){
                player_input(playerA, playerB, arr);

                //GAMEVALIDATION OBJECT:
                GAMEVAL gameval0=new GAMEVAL(playerB,playerA,arr);
                System.out.println("PLAYER TURN ");
                String resultsp=gameval0.winner();
                print_board(arr);
                playerB.playing=true;playerA.playing=false;
                if (resultsp!="NO WINNER"){
                    System.out.println(resultsp);
                    return;
                }
            };

        };
    }
    public static void main(String[] args){
        String[][] arr= new String[3][3];
        //CLEAN THE BOARD AND PRINT THE EMPTY BOARD:
        reset_board(arr);
        print_board(arr);
        //DETERMINE WHO IS STARTING, PLAYER1 OR PLAYER2 (RANDOMLY):
        BOARD main0=new BOARD(true);
        starter(main0);
        //THE PLAYER
        Player playerA=new Player("   ",main0.isplay);
        //THE COMPUTER
        Player playerB=new Player("   ", !main0.isplay);
        //RANDOM ALLOCATION OF X AND O:
        random_letter(main0,playerA,playerB);
        //COMPUTER OBJECT:
        Computer computer=new Computer(playerB,arr);

        //INITIAL GAME:
        System.out.println("################");
        System.out.println("PLAYER IS:"+playerA.player_piece);
        System.out.println("COMPUTER IS:"+playerB.player_piece);
        System.out.println("################");
        System.out.println("");
        System.out.println("GAME BEGINS");
        initial_game(playerA,playerB,computer,arr);
        //DURING GAME:
        if (playerB.playing==true){
            computer.random_startchoice(playerA.player_piece);
            arr[playerB.row][playerB.col]=playerB.player_piece;
            playerB.playing=false;playerA.playing=true;
            System.out.println("COM001 TURN");
            print_board(arr);
        };

        during_game(playerA, playerB, computer, arr);
        

        

    //TODO:END-TODAY:
    //CREATE A SYSTEM THAT CHECKS IF THERES THREE OOO OR XXX TO WIN THE GAME.
    //->CREATE A METHOD THAT CHECK IF THERES A POSSIBILITY OF WINNING THE GAME.->DONE(somehow)
        }
    
    }
