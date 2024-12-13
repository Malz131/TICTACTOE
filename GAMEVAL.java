import java.util.ArrayList;
import java.util.Arrays;


public class GAMEVAL {
    public static Player playerB;public static Player playerA;public static String[][] arr;
    public GAMEVAL(Player playerb,Player playera,String[][] array){
        arr=array;
        playerA=playera;
        playerB=playerb;
    }
    public static String winner(){
  
        for (int i=0;i<arr.length;i++){
            for (int k=0;k<arr.length;k++){
                if (arr[i][k]==playerA.player_piece){
                    if (playerA.playing==true){
                    ArrayList<String> outcome=choices(i, k, playerB.player_piece, playerA);
                    if (outcome.size()==2){
                        return "PLAYER WINS!!";}}}
                if (arr[i][k]==playerB.player_piece){
                
                    if (playerB.playing==true){
                        ArrayList<String> outcome=choices(i, k, playerA.player_piece, playerB);
                        if (outcome.size()==2){
                            return "COM001 WINS!!";}}}
                    

                }
            }
        return "NO WINNER";

    }

    public static ArrayList<String> daigonal(int r_pos,int c_pos,String entity){
        ArrayList<String> dl=new ArrayList<>();
            int col=c_pos;
            int row=r_pos;
            if (row==1 && col==1){
                if (arr[row-1][col-1]==entity){dl.add(Integer.toString(row-1)+" " +Integer.toString(col-1));};
                if (arr[row+1][col-1]==entity){dl.add(Integer.toString(row+1)+" " +Integer.toString(col-1));};
                if (arr[row+1][col+1]==entity){dl.add(Integer.toString(row+1)+" " +Integer.toString(col+1));};
                if (arr[row-1][col+1]==entity){dl.add(Integer.toString(row-1)+" " +Integer.toString(col+1));}}
            else if (row ==2 && col==0){
                if (arr[row-1][col+1]==entity){dl.add(Integer.toString(row-1)+" " +Integer.toString(col+1));};
                if (arr[row-2][col+2]==entity){dl.add(Integer.toString(row-2)+" " +Integer.toString(col+2));};
            }    
            else if (row==0 && col==0){
                if (arr[row+1][col+1]==entity){dl.add(Integer.toString(row+1)+" " +Integer.toString(col+1));};
                if (arr[row+2][col+2]==entity){dl.add(Integer.toString(row+2)+" " +Integer.toString(col+2));};
            }
            else if (row==2 && col==2){
                if (arr[row-1][col-1]==entity){dl.add(Integer.toString(row-1)+" " +Integer.toString(col-1));};
                if (arr[row-2][col-2]==entity){dl.add(Integer.toString(row-2)+" " +Integer.toString(col-2));};
            }
            else if (row==0 && col==2){
                if (arr[row+1][col-1]==entity){dl.add(Integer.toString(row+1)+" " +Integer.toString(col-1));};
                if (arr[row+2][col-2]==entity){dl.add(Integer.toString(row+2)+" " +Integer.toString(col-2));};
            };
            

        return dl;

}
    public static ArrayList<String> choices(int r_pos,int c_pos,String opp_entity,Player player){
        ArrayList<String> vert_options=vertical(r_pos, c_pos, player.player_piece);
        ArrayList<String> hor_options=horizontal(r_pos, c_pos, player.player_piece);
        ArrayList<String> daig_options=daigonal(r_pos, c_pos, player.player_piece);
        //OPPONENTS POSITION:
        ArrayList<String> vert_oppent=vertical(r_pos, c_pos, opp_entity);
        ArrayList<String> hor_oppent=horizontal(r_pos, c_pos, opp_entity);
        ArrayList<String> daig_oppent=daigonal(r_pos, c_pos, opp_entity);
        //VALIDATIONS:
        ArrayList<String> horz=horizontal_val( hor_options, hor_oppent);
        ArrayList<String> vert=vertical_val( vert_options, vert_oppent);
        ArrayList<String> daig=daigonal_val( daig_options, daig_oppent);
        
        //JUST AN EMPTY LIST:
        ArrayList<String> list=new ArrayList<String>();
        


        if(daig.size()!=0){
            return daig;
        }
        else if(horz.size()!=0){
            return horz;
        }
        else if(vert.size()!=0){
            return vert;
        };
    return list;}
public static ArrayList<String> daigonal_val(ArrayList<String> daig_options,ArrayList<String> daig_oppent){
    ArrayList<String> arr0r=new ArrayList<String>();
    ArrayList<String> arr0pr=new ArrayList<String>();
    ArrayList<String> arr0l=new ArrayList<String>();
    ArrayList<String> arr0pl=new ArrayList<String>();
    ArrayList<String> list=new ArrayList<String>();
        for (int i=0;i<daig_options.size();i++) {
           String[] temparr=daig_options.get(i).split(" ");
            int row=Integer.parseInt(temparr[0]); int col=Integer.parseInt(temparr[1]);
            if (row==col){arr0l.add(daig_options.get(i));
            }
            else{
                arr0r.add(daig_options.get(i));
            }
        };
        
        for (int p=0;p<daig_oppent.size();p++) {
            String[] temparr=daig_oppent.get(p).split(" ");
             int row=Integer.parseInt(temparr[0]); int col=Integer.parseInt(temparr[1]);
             if (row==col){arr0pl.add(daig_oppent.get(p));
             }
             else{
                 arr0pr.add(daig_oppent.get(p));
             }
         }

        if (arr0l.size()==2 && arr0pl.size()==0){
            return arr0l;
         }
        else if (arr0r.size()==2 && arr0pr.size()==0 ){
            return arr0r;
         }
        else{
            return list;
        }}
public static ArrayList<String> horizontal_val(ArrayList<String> hor_options,ArrayList<String> hor_oppent){
    ArrayList<String> list=new ArrayList<String>();
    if (hor_oppent.size()==0 &&  hor_options.size()==2){
        return hor_options;
    }
    else{
        return list;
    }
}
public static ArrayList<String> vertical_val(ArrayList<String> vert_options,ArrayList<String> vert_oppent){
    ArrayList<String> list=new ArrayList<String>();
    if (vert_oppent.size()==0 && vert_options.size()==2){
        return vert_options;
    }
    else{
        return list;
    }}
public static ArrayList<String> vertical (int r_pos,int c_pos,String entity){
    ArrayList<String> vert=new ArrayList<>();
        int col=c_pos;
    for (int i=-2;i<3;i++){
            int row=r_pos+i;
            if (row>=3 || row<0){row=r_pos;};
            if (arr[row][col]==entity && row!=r_pos){
            vert.add(Integer.toString(row)+" " +Integer.toString(col));}
            
    };
    return vert;
}
public static ArrayList<String> horizontal (int r_pos,int c_pos,String entity){
    ArrayList<String> horz=new ArrayList<>();
        int row=r_pos;
    for (int i=-2;i<3;i++){
        int col=c_pos+i;
        if (col>=3 || col<0){col=c_pos;};
        if (arr[row][col]==entity && col!=c_pos){
        horz.add(Integer.toString(row)+" " +Integer.toString(col));}
    };
    return horz;
}
public static void main(String[] args){
    /*TODO: MAKE THE CHECKERS WORK.->DONE
    TODO: START WITH SELECTION FUCNTION.
    */
    // Player playerB=new Player(" o ", true);
    // String[][] array=new String[3][3];  
    // for(Integer i=0;i<3;i=i+1){
    //     for(int k=0;k<3;k++){
    //         array[i][k]="   ";
    //     }
    // }
    // array[0][1]=" x ";
    // array[1][1]=" o ";
    // array[1][0]=" x ";
    // array[2][2]=" o ";
    // array[0][0]=" o ";
    // // array[2][1]=" o ";
    // // array[0][1]=" o ";
    // for (int i=0;i<array.length;i++){
    // System.out.println(Arrays.toString(array[i]));
    //     }
    // ArrayList<String> choices=choices(1, 1, " x ",playerB);
    // System.out.println(choices);}

} }