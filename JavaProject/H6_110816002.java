import java.util.Scanner;
class qExist extends Exception{ //繼承，具例外處理功能
}
class qExistfunction{ //函數及拋出例外條件，判斷是否輸入為q
    public void qCheck(String t) throws qExist{
        if(t.equals("q")){
            System.out.println("輸入錯誤，這是英文字母q啦!");
            throw new qExist(); //拋出例外訊息
        }
    }
}
public class H6_110816002 {
    //姓名: 張甄真
    //學號: 110816002
    /*操作說明：先詢問使用者是否執行程式(是=1,否=0)，開始執行後讓使用者重覆輸入數字，並連續累加此5個數字，並將其和印出來。
      當使用者輸入英文字母q時，則會產生例外，且終止輸入結束程式。
      如果輸入的資料不是數字也不是英文字母q時，則會丟出例外，提醒使用者要輸入數字並讓其重新繼續輸入
      自評：根據評分項目，程式有意義且可以執行、允許使用者重覆輸入數字，並連續累加5個數字，
      將其和印出來，輸入字母q時會產生例外終止執行、若輸入的資料不是數字也不是英文字母q時，
      會丟出例外提醒使用者應輸入數字並讓其繼續輸入等標準，故自評分數為100分。 */
    public static void main(String[] args){
        int choice,judgetemp;
        String temp;
        Scanner scn = new Scanner(System.in);
        qExistfunction qExistfunction = new qExistfunction();
        while(true){
            System.out.print("是否執行連加程式(是=1,否=0):");
            choice = scn.nextInt();
            if(choice==1){
                int num[]=new int[5]; //宣告陣列儲存數字
                //依序讓使用者輸入數字，先檢查例外處理，輸入正確後，存值進array
                for(int i=0;i<5;i++){
                    int k = i+1;
                    //例外處理，若正確輸入則離開while迴圈，若否，則進入catch捕捉錯誤，並讓使用者重新輸入正確數值
                    while(true){
                        try{
                            System.out.print("請輸入第"+k+"個數字:");
                            temp = scn.next(); //以字串方式輸入
                            qExistfunction.qCheck(temp); //自訂例外判斷
                            //若不為q或其他字母符號則轉整數(不為整數的例外處理)
                            judgetemp=Integer.parseInt(temp);
                            break; //輸入數字檢查正確，跳出檢查迴圈
                        }catch(qExist e){
                            System.out.println("拋出" + e + "例外");
                            System.exit(0); //輸入q則跳出例外且終止程式
                        }
                        catch(Exception e){
                            System.out.println("拋出" + e + "例外");
                            System.out.println("請重新輸入數字");
                        }
                    }
                    num[i] = judgetemp; //檢查正確，放入儲存陣列中
                }
                //將連續累加的算式及結果列印出來
                int outcome=0;
                for(int i=0;i<4;i++){
                    System.out.print(num[i]+"+");
                    outcome+=num[i];
                }
                outcome+=num[4];
                System.out.print(num[4]+"="+outcome);
                System.out.println("");


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