import java.util.Scanner;
public class H4_110816002{
    //姓名: 張甄真
    //學號: 110816002
    /*操作說明：先詢問使用者是否執行萬年曆程式(是=1,否=0)，開始執行後先依序讓使用者輸入欲查詢年、月、日，
      過程中均包含錯誤提醒(年不小於1、月不小於1且不大於12、日期則以1582年的曆法更動為分界，進行獨立判斷輸出等)，
      最後輸出查詢年、月、日為星期幾的資訊外，也可以輸出為月曆形式。
      曆法:小於1582年:每四年一閏，但西元四年二月不是閏年，
      若為1582年:曆法更動下，十月少十天(該月4日的後一天為15日)，
      大於1582年:每四年一閏，逢百不閏，但四百則要閏年。
      自評：輸入年、月、日並顯示當天為星期幾以及當月之月曆，
      且本程式設計為可從西元一年一月一日開始判斷，不因曆法更動而無法進行查詢，
      依評分項目及標準，程式有意義且可以執行、完成全部功能等，故自評分數為100分*/
    public static void main(String args[]){
        int choice,inputyear,inputmonth,inputday,tempweek,week,year,month,day;
        Scanner scn = new Scanner(System.in);
        while(true){
            System.out.print("是否執行萬年曆程式(是=1,否=0):");
            choice = scn.nextInt();
            if(choice==1) {
                System.out.print("請輸入查詢年份:");
                inputyear = scn.nextInt();
                while(inputyear<1){ //若輸入年份錯誤，則提醒使用者錯誤並重新輸入
                    System.out.print("輸入錯誤，請重新輸入查詢年份:");
                    inputyear = scn.nextInt();
                }
                year = totalYear(inputyear); //計算查詢年份前一年開始的所有天數總和
                System.out.print("請輸入查詢月份:");
                inputmonth = scn.nextInt();
                while(inputmonth>12||inputmonth<1){ //若輸入月份錯誤，則提醒使用者錯誤並重新輸入
                    System.out.print("輸入錯誤，請重新輸入查詢月份:");
                    inputmonth = scn.nextInt();
                }
                month = totalMonth(inputmonth, inputyear); //同年該月份天數總和
                System.out.print("請輸入查詢日期:");
                inputday = scn.nextInt();
                int judgemonth;
                //1582/10月曆法更動，故直接提出做判斷
                if(inputyear==1582&&inputmonth==10){
                    while(inputday==5||inputday==6||inputday==7||inputday==8||inputday==9
                            ||inputday==10||inputday==11||inputday==12||inputday==13||inputday==14
                            ||inputday<1){
                        System.out.print("輸入超過當月天數，請重新輸入查詢日期:");
                        inputday = scn.nextInt();
                    }
                }
                else{ //判斷輸入日期是否超過該月最大日期或輸入小於1，若錯誤則跳出錯誤訊息，並提供使用者再次輸入
                    judgemonth =totalinmonth(inputmonth, inputyear);
                    while(inputday>judgemonth||inputday<1){
                        System.out.print("輸入超過當月天數，請重新輸入查詢日期:");
                        inputday = scn.nextInt();
                    }
                }
                tempweek = weekday(year, month, inputday); //輸入當天為星期幾(的數字代號)
                int counter,outputday=1;//counter為該月總天數，outputday為輸出日期
                System.out.print(inputyear + "年" + inputmonth + "月" + inputday + "日");
                if(inputyear==1582&&inputmonth==10){ //單獨判斷1582/10月情形
                    if(inputday==15||inputday==22||inputday==29)
                        System.out.print("星期五");
                    if(inputday==16||inputday==23||inputday==30)
                        System.out.print("星期六");
                    if(inputday==17||inputday==24||inputday==31)
                        System.out.print("星期日");
                    if(inputday==18||inputday==25)
                        System.out.print("星期一");
                    if(inputday==19||inputday==26)
                        System.out.print("星期二");
                    if(inputday==20||inputday==27)
                        System.out.print("星期三");
                    if(inputday==21||inputday==28)
                        System.out.print("星期四");
                    System.out.println("");
                }
                else
                    week(tempweek); //輸出為星期幾(數字代號轉成星期表示)
                day = (year+month+1)%7; //當月第一天是星期幾(的數字代號)
                week = transnum(day); //將日一二三四五六[1234560]轉成0123456表示
                counter = totalinmonth(inputmonth,inputyear); //當月總共有幾天
                System.out.println("********" + inputyear + "年" + inputmonth + "月月曆**********");
                System.out.println("SUN MON TUE WED THU FRI SAT");
                //1582/10月曆法更動較大，故直接設定輸出月曆樣式
                if (inputyear==1582&&inputmonth==10){
                    System.out.print("\t"+1+"\t"+2+"\t"+3+"\t"+4+"\t"+15+"\t"+16+"\t");
                    System.out.println("");
                    for (int i=17;i<=31;i++){
                        System.out.print(i + "\t");
                        if(i==23||i==30||i==31)
                            System.out.println("");
                    }
                }
                else {
                    //當月第一週印製
                    for(int i=0;i<week;i++) { //當月一號前的空格輸出
                        System.out.print(("\t"));
                    }
                    for(int i=week;i<7;i++){ //當月一日開始列印數值
                        System.out.print((outputday + "\t"));
                        outputday += 1;
                    }
                    System.out.println("");
                    //當月剩下月曆日期輸出
                    int j=0;
                    for (int i=outputday;i<=counter;i++) {
                        System.out.print(outputday + "\t");
                        outputday += 1;
                        j+=1;
                        if(j%7==0) //輸出完七天要換行
                            System.out.println("");
                    }
                    System.out.println("");
                }
            }//choice==1
            if(choice==0){
                System.out.println("謝謝使用,離開程式中");
                break; //離開本程式
            }
            if(choice>1||choice<0){
                System.out.println("輸入錯誤,請重新輸入(是=1,否=0)");
                continue; //跳出本次迴圈，並詢問請輸入一個正整數
            }
        }//while(true)
    }
    public static int totalYear(int y) {
        int tempYear = 0;
        for(int i=1;i<y;i++) {
            if (i<1582){ //以1582年為分界，小於1582年每四年一閏
                if(i==4){ //西元四年二月只有28天!
                    tempYear+=365;
                }
                else{
                    if(i%4==0){
                        tempYear+=366;
                    }
                    else
                        tempYear+=365;
                }
            }
            if(i==1582) //該年十月少十天(曆法更動)
                tempYear += 355;
            if(i>1582){ //每四年閏，逢百不閏，但四百要閏
                if(i%400==0)
                    tempYear+=366;
                else{
                    if(i%100==0)
                        tempYear+=365;
                    else{
                        if(i%4==0)
                            tempYear+=366;
                        else
                            tempYear+=365;
                    }
                }
            }
        }
        return tempYear;
    } //totalYear()
    public static int totalMonth(int m, int y) {
        int tempMonth=0; //與totalYear函數計算思考一樣，以1582年為分界
        if(y<1582){
            for(int i=1;i<m;i++){
                if(i==2){
                    if(y==4){
                        tempMonth+=28;
                    }
                    else{
                        if(y%4==0){
                            tempMonth+=29;
                        }
                        else{
                            tempMonth+=28;
                        }
                    }
                }
                if(i==1||i==3||i==5||i==7||i==8||i==10||i==12)
                    tempMonth+=31;
                if(i==4||i==6||i==9||i==11)
                    tempMonth+=30;
            }
        }
        if(y==1582){
            for (int i=1;i<m;i++){
                if(i==2)
                    tempMonth+=28;
                if(i==1||i==3||i==5||i==7||i==8||i==12)
                    tempMonth+=31;
                if(i==4||i==6||i==9||i==11)
                    tempMonth+=30;
                if(i==10)
                    tempMonth+=21;
            }
        }
        if(y>1582){
            for(int i=1;i<m;i++){
                if(i==2){
                    if(y%4==0&&y%100!=0||y%400==0){
                        tempMonth+=29;
                    }
                    else{
                        tempMonth+=28;
                    }
                }
                if(i==1||i==3||i==5||i==7||i==8||i==10||i==12)
                    tempMonth+=31;
                if(i==4||i==6||i==9||i==11)
                    tempMonth+=30;
            }
        }
        return tempMonth;
    } //totalMonth()
    public static int weekday(int y,int m,int d){
        int w;
        w=(y+m+d)%7;
        return w;
    } //計算天數轉換星期幾
    public static void week(int w){
        if(w==0) System.out.println("星期六");
        if(w==1) System.out.println("星期日");
        if(w==2) System.out.println("星期一");
        if(w==3) System.out.println("星期二");
        if(w==4) System.out.println("星期三");
        if(w==5) System.out.println("星期四");
        if(w==6) System.out.println("星期五");
    } //列印輸出星期
    public static int transnum(int num){
        int number=0;
        if(num==1) number=0;
        if(num==2) number=1;
        if(num==3) number=2;
        if(num==4) number=3;
        if(num==5) number=4;
        if(num==6) number=5;
        if(num==0) number=6;
        return number;
    } //將日一二三四五六[1234560]轉成[0123456]表示
    public static int totalinmonth(int t,int y){
        int total=0;
        if(t==2){ //計算若為二月(天數依閏年有影響，故另外討論)
            if(y%4==0&&y%100!=0||y%400==0){
                total=29;
            }
            else{
                total=28;
            }
        }
        if(t==1||t==3||t==5||t==7||t==8||t==10||t==12)
            total=31;
        if(t==4||t==6||t==9||t==11)
            total=30;
        return total;
    } //當月有幾天
}
