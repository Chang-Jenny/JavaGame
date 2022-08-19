import java.util.Scanner;
class numException extends Exception{ //繼承，具例外處理功能
}
class playnum{ //函數及拋出例外條件
    public void numlength(int t) throws numException{
        if(t>4){
            System.out.println("輸入錯誤，超過四個數字啦!");
            throw new numException();
        }
    }
}
public class H3_110816002{
    //姓名: 張甄真
    //學號: 110816002
    /*操作說明：先詢問使用者是否執行猜數字程式(是=1,否=0)，開始執行後則先亂數產生一組四位不重複的數字，
      再讓使用者輸入輸入一組四位不重複的數字，若重複則跳出錯誤提醒並重新輸入，輸入正確則存入陣列。
      在使用者猜測次數小於等於十次的情況下，判斷使用者輸入的數字與亂數產生的數字為_A_B，並輸出顯示每次猜測結果。
      若猜測次數大於十，則結束猜數字迴圈(遊戲)並輸出正解，再回到最外層迴圈詢問是否執行猜數字程式。
      自評：根據自評項目，程式有意義且可以執行、可以完成遊戲、特殊功能(可以讓使用者重複玩不知道算不算、
      及判斷使用者輸入是否為正確數量的數字或正確整數)，故先自評分數100*/
    public static void main(String args[]){
        int number,choice,counterA=0,counterB=0,counter=0;
        int num[] = new int[4]; //亂數產生的一組四位數字
        int temp[] = new int[10]; //判斷數字不重複的暫存陣列
        int ans[] = new int [4]; //使用者猜的一組四位數字
        while(true) { //外層 while迴圈判斷是否執行此程式
            System.out.print("是否執行猜數字程式(是=1,否=0):");
            Scanner scnn = new Scanner(System.in);
            choice = scnn.nextInt();
            if (choice == 1) {
                /*亂數產生一組數字，temp陣列先將index0~9的數值設為1，
                  若產生一個數字，則設該index的數值為0，代表用過。
                  每次產生數字會先判斷該index存放的數值是否為1(未使用)，
                  若為0(表示已使用)，則重新亂數產生數字。*/
                for(int j=0;j<temp.length;j++)
                    temp[j]=1;
                for(int i=0;i<num.length;i++){
                    number = (int)(Math.random()*10);
                    while(temp[number]==0){
                        number = (int)(Math.random()*10);
                    }
                    if(temp[number]==1) {
                        num[i] = number;
                        temp[number] = 0;
                    }
                }
                /*for(int k=0;k<4;k++){
                    System.out.print(num[k]);
                }//可直接得知正解*/
                counter=0;counterA=0;counterB=0;
                while(counter<=10){ //猜的次數不超過十次
                    System.out.print("請猜一組四位數字:");
                    counter+=1; //計數器
                    while(true){ //輸入一組四位數字並直接存入ans陣列，再判斷四位數字是否重複
                        Scanner scn = new Scanner(System.in);
                        playnum play = new playnum(); //函數產生的物件
                        while(true){
                            try{
                                String intToStr = scn.next();
                                play.numlength(intToStr.length()); //判斷是否輸入正確數量的數字
                                String input[] = intToStr.split("");
                                for(int k=0;k<4;k++){ //以字串形式存入input陣列，再將字串轉整數存入ans陣列
                                    ans[k] = Integer.parseInt(input[k]);
                                }
                                break; //輸入數字檢查正確，跳出檢查迴圈
                            }catch(numException e){ //自訂例外處理
                                System.out.println("拋出" + e + "例外");
                                System.out.print("請重新輸入數字:");
                            }
                            catch(Exception e){
                                System.out.println("拋出" + e + "例外");
                                System.out.print("請重新輸入數字:");
                            }
                        }

                        int judge = 1;
                        //比較輸入數字是否重複，若重複則設變數judge為0，用if判斷直接跳出檢查迴圈，重新輸入數字。
                        for(int i=0;i<4;i++){
                            for(int j=i+1;j<4;j++){
                                if (ans[i] == ans[j]){
                                    System.out.print("輸入錯誤(重複)，請重新輸入一個四位數字:");
                                    judge = 0;
                                    break; //離開內層迴圈
                                }
                            }
                            if(judge==0)
                                break; //離開外層迴圈，重新讓使用者輸入四個整數
                        }
                        if(judge==1) //若變數judge為1，則表示輸入正確(數字無重複)，輸出此數字，並接續判斷_A_B
                            break; //離開判斷輸入是否符合規定之迴圈
                    } //while判斷是否重複的 "}"

                    int judgeForAns = 0;
                    //判斷_A，若數字對且位置對，則變數counterA加1
                    //若counterA等於4，表示使用者猜對全部數字及位置，將變數judgeForAns設為1
                    for(int i=0;i<4;i++){
                        if(ans[i]==num[i]){
                            counterA+=1;
                        }
                        if(counterA==4){
                            System.out.println("恭喜答對!");
                            judgeForAns = 1;
                            break; //離開判斷_A的迴圈
                        }
                    }
                    //判斷_B，若數字隊但位置錯，則變數counterB加1
                    //若判斷到相同位置則continue，避免重複計算到counterA的值
                    for(int i=0;i<4;i++)
                        for(int j=0;j<4;j++){
                            if(ans[i]==num[j]){
                                if(i==j)
                                    continue; //略過數字對且位置對的情形
                                counterB+=1;
                            }
                        } //判斷B
                    if(judgeForAns==1){
                        System.out.println("總共猜了"+counter+"次!");
                        break; //完全猜中，離開判斷_A_B的迴圈
                    }
                    if(counter<10){ //猜次數小於10次，輸出_A_B和猜的次數
                        System.out.println(counterA+"A"+counterB+"B");
                        System.out.println("已猜"+counter+"次!");
                        counterA=counterB=0;
                    }
                    //猜到第10次尚未猜中，則直接公布正解，結束猜數字回合，重新詢問使用者是否執行猜數字程式
                    if(counter==10){
                        System.out.println(counterA+"A"+counterB+"B");
                        System.out.println("猜太多次了啦~下次再來玩吧!");
                        System.out.print("公布正解:");
                        for(int i=0;i<num.length;i++){
                            System.out.print(num[i]);
                        }
                        System.out.println("");
                        break; //猜完第十次則離開判斷_A_B的迴圈
                    }
                }//猜不超過十次的 "}"
            }
            if (choice == 0) {
                System.out.println("謝謝使用,離開程式中");
                break; //離開本程式
            }
            if (choice > 1 || choice < 0) {
                System.out.println("輸入錯誤,請重新輸入(是=1,否=0)");
                continue; //跳出本次迴圈，並詢問請輸入一個正整數
            }
        }
    }
}