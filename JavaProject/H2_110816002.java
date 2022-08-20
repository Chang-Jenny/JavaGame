import java.util.Scanner;
public class H2_110816002 {
    public static void main(String args[]){
        //姓名: 張*真
        //學號: 1108*****
        /*操作說明：詢問使用者是否執行此程式(是=1,否=0)，若輸入錯誤則跳錯誤訊息並重新輸入，
          開始執行時，先問使用者要輸入排序數字的數量，再依序輸入排序數字。
          輸入過程中執行try-catch的例外處理判斷(Java_CH.13例外處理)，直到輸入正確型別，
          輸入完畢後執行排序(由小到大輸出)，依題目要求，利用上課講義例子 app6_3 的找陣列中最小值改寫來進行排序，
          但一次輸入多個以空白鍵相隔的整數並判斷輸入型別錯誤，至作業截止時間仍無法想到解法，
          故以老師上課提到可以讓使用者先輸入要排序的數字數量，再繼續執行本程式為本次作業解法。
          自評：
          依據自評項目，程式有意義且可正常執行、可正確顯示答案、可偵測輸入不為整數的錯誤並可讓使用者重新輸入等*/
        while(true){ //最外層迴圈，判斷是否執行程式
            int choice,count,min;
            int temp;
            //String str;
            System.out.println("是否執行排序程式(是=1,否=0)");
            Scanner scn = new Scanner(System.in);
            choice = scn.nextInt();

            if(choice==1){
                System.out.println("請輸入排序數字的數量:");
                count = scn.nextInt();
                int num[]=new int[count]; //宣告陣列儲存數字
                //依序讓使用者輸入數字，先檢查例外處理，輸入正確後，存值進array
                for(int i=0;i<count;i++){
                    int k = i+1;
                    //例外處理，若正確輸入則離開while迴圈，若否，則進入catch捕捉錯誤，並讓使用者重新輸入正確數值
                    while(true){
                        try{
                            System.out.println("請輸入第" + k + "個數字:");
                            temp = scn.nextInt();
                            break; //輸入數字檢查正確，跳出檢查迴圈
                        }catch(Exception e){
                            System.out.println("拋出" + e + "例外");
                            String str_clear = scn.next(); //清除錯誤輸入
                            System.out.println("請重新輸入數字");
                        }
                    }
                    num[i] = temp; //檢查正確，放入儲存陣列中
                }

                /*數字排序由小到大，先設min變數的值為num[0](確保值在陣列裡)
                  比較min與陣列其餘值的大小，若陣列值<min，則與min交換，以此類推到for迴圈結束。
                  (外層for是將min設為陣列[0]、[1]、......，內層for是與min比較大小值)*/
                for(int j=0;j<num.length;j++){
                    min = num[j];
                    for(int k=j+1;k<num.length;k++){
                        if(num[k] <= min){
                            min = num[k];
                            int t;
                            t = num[j];
                            num[j] = min;
                            num[k] = t;
                        }
                    }
                    System.out.print(min + " "); //輸出最小值，即可得由小到大排序
                } System.out.println("");
            }

            if(choice==0){
                System.out.println("謝謝使用,離開程式中");
                break; //離開本程式
            }
            if(choice>1 ||choice<0){
                System.out.println("輸入錯誤,請重新輸入(是=1,否=0)");
                continue; //跳出本次迴圈，並詢問請輸入一個正整數
            }
        }
    }
}
