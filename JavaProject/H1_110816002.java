import java.util.Scanner;
public class H1_110816002 {
    public static void main(String args[]){
        //姓名: 張*真
        //學號: 1108*****
        /*程式說操作明: 檢查一個數字是否為質數，利用迴圈詢問使用者是否連續判斷質數(是=1，否=0)，
          且若使用者輸入不符規定之整數，做錯誤判斷要求重新輸入。每執行一次成功判斷是否為質數後，
          則再次詢問是否繼續執行此程式。判斷過程中，若使用者輸入非大於0的整數，則做錯誤判斷，
          讓使用者直接重新輸入新的大於0的整數。假設判斷結果為質數，則輸出為質數。若判斷結果為不是質數，
          則輸出不是質數，且同時輸出因式分解之結果。
          自評: 判斷質數是大一自己在資研社課後練習由Python改為Java邏輯語法，因式分解部分則為第一次寫。
          根據自評項目，程式有意義且可按照所規定者正確執行、程式檔案照規定呈現資訊、由自己寫的程式*/
        int num,choice; //num為使用者所輸入的整數，choice為使用者是否繼續執行此程式的選項

        //最外層迴圈為判斷使用者是否執行此判斷質數程式，是，輸入choice=1，否，輸入choice=0
        //若使用者輸入大於1或小於0，則跳出錯誤提醒訊息，並讓使用者重新輸入。
        while(true){
            System.out.println("是否執行判斷質數程式(是=1,否=0)");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();

            if(choice==1){
                //內層迴圈則判斷使用者是否正確輸入"大於0的正整數"
                //若使用者輸入錯誤，則會跳出錯誤提醒讓使用者輸入符合規定之數字輸入整數
                while(true) {
                    System.out.println("請輸入一個正整數:");
                    Scanner input = new Scanner(System.in);
                    num = input.nextInt();
                    if(num<=0){
                        System.out.println("輸入錯誤,請輸入大於0的整數");
                        continue; //跳出本次迴圈，再次詢問請輸入一個正整數
                    }
                    if(num==1){
                        System.out.println("1不是質數也不是合數");
                        break; //成功判斷1的結果(因為1比較特別，所以另外拿出來做判斷)，故結束本次迴圈
                    }
                    else {
                        int c=2,temp;
                        /*c為計數器，用來判斷是否可整除被除數(使用者所輸入的整數)，
                          temp為暫存被除數(使用者所輸入的整數)以便做因式分解之計算*/

                        /*迴圈執行判斷是否為質數，利用變數c為除數，檢查被除數是否可被整除，
                          若否，則變數c加1直到等於被除數，則輸出為質數。
                          過程中若找到變數c可以整除被除數，則判斷被除數不是質數，進行因式分解*/
                        while(c<num){
                            if(num%c==0){ //找到c可整除num
                                System.out.println(num + "不是質數");
                                System.out.print(num+"=");
                                temp=num;
                                //i紀錄array索引值，j紀錄變數c(除數)值，方便計算
                                int i=0,j=c;
                                int symbol[]=new int[1000];

                                /*因式分解，將找到第一個可整除被除數(使用者所輸入的整數)assign給變數j，
                                 並放到array裡，並且令商為下一個被除數且assign給變數temp。
                                 執行直到j不能整除temp時判斷j若小於temp，則j+1，
                                 並回到第一個if檢查是否整除temp，重複直到temp等於1，輸出因式分解結果。*/
                                while(true){
                                    if(temp%j==0&&temp!=1){
                                        symbol[i]=j;
                                        i+=1;
                                        temp=temp/j;
                                    }
                                    if(temp%j!=0 && j<temp){
                                        j+=1;
                                    }
                                    if(temp==1){ //因式分解完畢，輸出結果
                                        /*避免結尾多輸出一個*符號，先將因式分解的整數存入迴圈
                                          依序輸出整數 *直到倒數第二個整數，在for外面另外輸出最後一個整數，
                                          並離開此因式分解的迴圈 */
                                        for(int x=0;x<i-1;x++){
                                            System.out.print(symbol[x]);
                                            System.out.print("*");
                                        }
                                        System.out.print(symbol[i-1]);
                                        System.out.println("");
                                        break; //離開因式分解迴圈
                                    }
                                }
                                break; //離開要求使用者輸入整數的迴圈
                            }
                            c+=1;
                        }
                        //找不到c可整除num，則輸出num為質數，並離開要求使用者輸入整數的迴圈。
                        if(num==c){
                            System.out.println(num + "是質數");
                            System.out.println(num +"=1*" + num);
                        }
                        break; //離開判斷質數的迴圈，重新回到詢問使用者是否要執行此程式。
                    }
                }
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
