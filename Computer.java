
import java.util.ArrayList;
import java.util.Arrays;
public class Computer{
    public  static Player playerB;public static String[][] arr;


    public Computer(Player pB,String[][] array){
        playerB=pB;this.arr=array;
    }
    // public Boolean start;public Boolean playing;public String player_piece;
    public static void startofgame(){
        if (playerB.playing==true){
            Long r1=Math.round((Math.random()*2));
            Long c1=Math.round((Math.random()*2));
            if (0<=r1 && r1<=2 && 0<=c1 && c1<=2){

            playerB.row=Integer.parseInt(Long.toString(r1));
            playerB.col=Integer.parseInt(Long.toString(c1));}}}
    public static void checker(String opp_entity){
        int stopper=0;   
        for (int i=0;i<arr.length;i++){
            for (int k=0;k<arr.length;k++){
                if (arr[i][k]==playerB.player_piece){
                    String outcome=single_choice(i,k,opp_entity);
                    if (outcome=="updated"){
                        stopper=stopper+1;
                        return;
                    }}
            }};
        for (int p=0;p<arr.length;p++){
            for (int j=0;j<arr.length;j++){
                if (arr[p][j]=="   "){
                    String outcome=single_choice_modthirds(p,j);
                    if (outcome=="updated"){
                        stopper=stopper+1;
                        return;
                    }

                }
        }}} 
    public static ArrayList<String> vertical (int r_pos,int c_pos,String entity,String[][] arr){
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
    public static ArrayList<String> horizontal (int r_pos,int c_pos,String entity,String[][] arr){
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
    
    public static ArrayList<String> daigonal(int r_pos,int c_pos,String entity,String[][] arr){
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
    public static ArrayList<String> choices(int r_pos,int c_pos,String opp_entity,String[][]arr){
        ArrayList<String> vert_options=vertical(r_pos, c_pos, "   ",arr);
        ArrayList<String> hor_options=horizontal(r_pos, c_pos, "   ",arr);
        ArrayList<String> daig_options=daigonal(r_pos, c_pos, "   ",arr);
        //OPPONENTS POSITION:
        ArrayList<String> vert_oppent=vertical(r_pos, c_pos, opp_entity,arr);
        ArrayList<String> hor_oppent=horizontal(r_pos, c_pos, opp_entity,arr);
        ArrayList<String> daig_oppent=daigonal(r_pos, c_pos, opp_entity,arr);
        //VALIDATIONS:
        ArrayList<String> horz=horizontal_val( hor_options, hor_oppent);
        ArrayList<String> vert=vertical_val( vert_options, vert_oppent);
        ArrayList<String> daig=daigonal_val( daig_options, daig_oppent);
        
        //JUST AN EMPTY LIST:
        
        vert.addAll(daig);
        vert.addAll(horz);
        ArrayList<String> list=vert;
        
        if (list.size()!=0){
            return list;
        }
        else if(daig.size()!=0){
            return daig;
        }
        else if(horz.size()!=0){
            return horz;
        }
        else if(vert.size()!=0){
            return vert;
        }
        else{
            return list;
        }
    }
    public static void random_startchoice(String opp_entity){
        ArrayList<String> rand_choices=new ArrayList<String>();
        for (int i=0;i<arr.length;i++){
            for (int k=0;k<arr.length;k++){
                if (arr[i][k]=="   "){
                    String coord=Integer.toString(i)+" "+Integer.toString(k);
                    rand_choices.add(coord);
                }
            };

        };
        int num=Integer.parseInt(Long.toString(Math.round((rand_choices.size()-1)*Math.random())));
        String choice =rand_choices.get(num);
        String[] array_choice=choice.split(" ");
        int row=Integer.parseInt(array_choice[0]);
        int col=Integer.parseInt(array_choice[1]);
        playerB.row=row;playerB.col=col;
    
    }
    public static ArrayList<String> choices_modthirds(int r_pos,int c_pos,String[][]arr){
        ArrayList<String> vert_options=vertical(r_pos, c_pos, "   ",arr);
        ArrayList<String> hor_options=horizontal(r_pos, c_pos, "   ",arr);
        ArrayList<String>   list=new ArrayList<String>();
        //JUST AN EMPTY LIST:
        if (vert_options.size()==3 && hor_options.size()==3){
            vert_options.addAll(hor_options);
            return vert_options;
        }
        else if (vert_options.size()==2){
            return vert_options;
        }
        else if (hor_options.size()==2){
                return hor_options;
        }
        else {return list;}};
        public static String single_choice_modthirds(int r_pos,int c_pos){
        
            ArrayList<String> choices=choices_modthirds(r_pos, c_pos, arr);
            if (choices.size()!=0){
            Integer num=Integer.parseInt(Long.toString(Math.round((choices.size()-1)*Math.random())));
            String choice=choices.get(num);
            String[] array=choice.split(" ");
            int row=Integer.parseInt(array[0]);
            int col=Integer.parseInt(array[1]);
            playerB.row=row;
            playerB.col=col;
            return "updated";
        }
            else{
                return "none";
            }
    
    
        }

    public static String single_choice(int r_pos,int c_pos,String opp_entity){
        
        ArrayList<String> choices=choices(r_pos, c_pos, opp_entity, arr);
        if (choices.size()!=0){
        Integer num=Integer.parseInt(Long.toString(Math.round((choices.size()-1)*Math.random())));
        String choice=choices.get(num);
        String[] array=choice.split(" ");
        int row=Integer.parseInt(array[0]);
        int col=Integer.parseInt(array[1]);
        playerB.row=row;
        playerB.col=col;
        return "updated";
    }
        else{
            return "none";
        }


    }
    public static ArrayList<String> horizontal_val(ArrayList<String> hor_options,ArrayList<String> hor_oppent){
        ArrayList<String> list=new ArrayList<String>();
        if (hor_oppent.size()==0 &&  hor_options.size()!=0){
            return hor_options;
        }
        else{
            return list;
        }
    }
    public static ArrayList<String> vertical_val(ArrayList<String> vert_options,ArrayList<String> vert_oppent){
        ArrayList<String> list=new ArrayList<String>();
        if (vert_oppent.size()==0 ){
            return vert_options;
        }
        else{
            return list;
        }
    }  
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

                if (arr0l.size()!=0 && arr0pl.size()==0 && arr0pr.size()!=0){
                    return arr0l;
                 }
                else if (arr0r.size()!=0 && arr0pr.size()==0 && arr0pl.size()!=0){
                    return arr0r;
                 }   
                else if (daig_oppent.size()==0 && daig_options.size()!=0){
                    return daig_options;
                }    
                else{
                    return list;}
    }
    public static void main(String[] args){
        /*TODO: MAKE THE CHECKERS WORK.->DONE
        TODO: START WITH SELECTION FUCNTION.
        */
        String[][] array=new String[3][3];  
        for(Integer i=0;i<3;i=i+1){
            for(int k=0;k<3;k++){
                array[i][k]="   ";
            }
        }
        array[0][2]=" x ";
        array[1][1]=" o ";
        array[2][0]=" x ";
        array[2][2]=" o ";
        array[0][0]=" o ";
        array[0][1]=" o ";
        ArrayList<String> choices=choices(1, 1, " x ",array);
//WHO AM I?I AM THE GOAT.
        // Player playerB0=new Player(" o ",true);
        // checker(" x ", array, playerB0);
        // System.err.print(Integer.toString(playerB0.row)+" ");
        // System.err.print(Integer.toString(playerB0.col));
        // System.out.println("");
        // array[playerB0.row][playerB0.col]=playerB0.player_piece;
        // for (int i=0;i<array.length;i++){
        //     System.out.println(Arrays.toString(array[i]));
        // }
        //
        // checker(" x ", array, playerB0);
        // System.err.print(Integer.toString(playerB0.row)+" ");
        // System.err.print(Integer.toString(playerB0.col));
        // System.out.println("");
        // array[playerB0.row][playerB0.col]=playerB0.player_piece;
        // // System.out.println(Arrays.deepToString(array));
        // for (int i=0;i<array.length;i++){
        //     System.out.println(Arrays.toString(array[i]));
        // }
        // ArrayList<String> choices=choices(1, 1, " x ", array);
        System.out.println(choices);

        }}