import java.util.*;
import java.util.Scanner;
public class H5_110816002{
    //姓名: 張*真
    //學號: 1108******
    /*操作說明：先詢問使用者是否執行中序轉前序程式(是=1,否=0)，開始執行後讓使用者輸入一個中序運算式，
      同時運算子(operator)包含加減乘除及括號，最終結果輸出為一個前序運算式。
      自評：根據評分項目，程式有意義且可以執行、可轉成後序式(不支援括號)、支援括號等評分標準*/
    public static void main(String[] args){
        int choice;
        String inputinfix,temp;
        Stack outputstack = new Stack(); //最終輸出的stack
        Stack tempstack = new Stack(); //operator存放並判斷優先權的stack
        Scanner scn = new Scanner(System.in);
        while(true){
            System.out.print("是否執行中序轉前序程式(是=1,否=0):");
            choice = scn.nextInt();
            if(choice==1){
                System.out.print("請輸入一個中序式:");
                inputinfix = scn.next();
                //System.out.println(inputinfix.length());
                String input[] = new String[inputinfix.length()];
                int j=inputinfix.length()-1; //由右而左輸入(存進陣列)
                for(int i=0;i<inputinfix.length();i++){
                    input[j] = String.valueOf(inputinfix.charAt(i));
                    j-=1;
                }
                for(int i=0;i<input.length;i++){
                    //如果是operand則直接push到outputstack
                    if(!(input[i].equals("+")
                            ||input[i].equals("-")
                            ||input[i].equals("*")
                            ||input[i].equals("/")
                            ||input[i].equals(")")
                            ||input[i].equals("("))){
                        outputstack.push(input[i]);
                    }
                    //如果讀取右括號，則直接push進tempstack
                    if(input[i].equals(")")){
                        tempstack.push(input[i]);
                    }
                    //如果讀取到左括號，則無條件將tempstack的operator push到outputstack，直到遇到右括號
                    if(input[i].equals("(")){
                        while(!(tempstack.peek().equals(")"))){
                            outputstack.push(tempstack.pop());
                        }
                        tempstack.pop(); //把多push進去的右括號pop掉
                    }

                    //如果是operator(+、-、*、/)則開始做判斷
                    if(input[i].equals("+")
                            ||input[i].equals("-")
                            ||input[i].equals("*")||input[i].equals("/")){
                        outputstack.push(" "); //保持輸出數字前空格，方便判斷數字
                        //如果第一個讀取為右括號，則直接push到tempstack
                        if(tempstack.empty()||tempstack.peek().equals(")")){
                            tempstack.push(input[i]);
                        } else{
                            if(!(tempstack.empty())){
                                while(!(tempstack.empty())){
                                    //優先權判斷，+-小於*/，需先將stack的頂端值pop到outputstack
                                    if((input[i].equals("+") || input[i].equals("-")) &&
                                            (tempstack.peek().equals("*") || tempstack.peek().equals("/"))) {
                                        outputstack.push(tempstack.peek());
                                        tempstack.pop();
                                        if(tempstack.empty()){
                                            tempstack.push(input[i]);
                                            break; //push到tempstack則結束該operator的判斷
                                        }
                                    }
                                    //優先權判斷，*/大於+-，則直接push到tempstack
                                    if((input[i].equals("*") || input[i].equals("/"))&&
                                            (tempstack.peek().equals("+") || tempstack.peek().equals("-"))){
                                        tempstack.push(input[i]);
                                        break; //push到tempstack則結束該operator的判斷
                                    }
                                    //如果遇到輸入+-，堆疊頂端值也是+或-，則優先權相同，但結合性由左到右，故直接push到tempstack
                                    if((input[i].equals("+") || input[i].equals("-")) &&
                                            (tempstack.peek().equals("+") || tempstack.peek().equals("-"))){
                                        tempstack.push(input[i]);
                                        break; //push到tempstack則結束該operator的判斷
                                    }
                                    //如果遇到輸入*/，堆疊頂端值也是*或/，則優先權相同，但結合性由左到右，故直接push到tempstack
                                    if((input[i].equals("*") || input[i].equals("/"))&&
                                            (tempstack.peek().equals("*") || tempstack.peek().equals("/"))){
                                        tempstack.push(input[i]);
                                        break; //push到tempstack則結束該operator的判斷
                                    }
                                    //如果輸入的operator等於tempstack頂端值，則直接push到tempstack裡
                                    if(input[i].equals(tempstack.peek())){
                                        tempstack.push(input[i]);
                                        break; //push到tempstack則結束該operator的判斷
                                    }
                                }
                            }
                        } //else
                        outputstack.push(" "); //保持輸出數字前空格，方便判斷數字
                    }
                }
                //結束讀取中序式後，若tempstack不為空，則將堆疊內的operator push到outputstack
                while(!(tempstack.empty())){
                    outputstack.push(tempstack.peek());
                    tempstack.pop();
                }
                //最終輸出前序式
                System.out.print("前序式: ");
                while(!(outputstack.empty())){
                    System.out.print(outputstack.peek());
                    outputstack.pop();
                }
                System.out.println(" ");

            } //choice==1
            if(choice==0){
                System.out.println("謝謝使用,離開程式中");
                break; //離開本程式
            }
            if(choice>1||choice<0){
                System.out.println("輸入錯誤,請重新輸入(是=1,否=0)");
                continue; //跳出本次迴圈，並詢問請輸入一個正整數
            }
        } //while(true)
    } //public static void main()
}
