import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.parseInt;

/*姓名: 張*真
  學號: 110816***
  操作說明: 刻出MS Window小算盤介面，並模擬window小算盤的顏色及按鈕擺放位置等，提供使用者基本的加、
          減、乘、除運算功能並顯示運算結果在label上，且根據此小算盤沒有四則運算原則，故操作為兩兩數值直接運算。
          也提供C、CE、Del的按鍵功能(C清除全部運算、CE清除當次輸入、Del清除最後一個輸入的數字)，
          且在尚未輸入數字前按下以上三個按鈕會提醒使用者輸入錯誤。此程式可以執行小數點運算，
          包括直接輸入包含小數點的數值做運算及運算結果可為小數等，能正常顯示以及搭配功能鍵使用。
          另外特殊運算(%、倒數、根號、平方)，平方可與上述做混合運算，並直接顯示結果於Label上，
          若要結束小算盤程式可直接按右上角的叉叉關閉視窗。
  自評：
      1.程式有意義且可以執行 (+20%)
      2.僅支援整數運算 (+30%)
      3.支援小數運算 (+25%)
      4.包含特殊計算功能 (+25%) */

public class H7_110816002 extends Frame implements ActionListener{
    //Panel、Label
    static H7_110816002 frm=new H7_110816002();
    static Panel pnl_num=new Panel(new GridLayout(3,3));
    static Panel pnl_funct=new Panel(new GridLayout(2,3));
    static Panel pnl_op=new Panel(new GridLayout(6,1));
    static Panel pnl_else=new Panel(new GridLayout(1,3));
    static Label lab=new Label("0",Label.RIGHT);
    //按鈕
    static Button btn1=new Button("1");
    static Button btn2=new Button("2");
    static Button btn3=new Button("3");
    static Button btn4=new Button("4");
    static Button btn5=new Button("5");
    static Button btn6=new Button("6");
    static Button btn7=new Button("7");
    static Button btn8=new Button("8");
    static Button btn9=new Button("9");
    static Button btn10=new Button("0");
    static Button btn11=new Button("+");
    static Button btn12=new Button("-");
    static Button btn13=new Button("×");
    static Button btn14=new Button("÷");
    static Button btn15=new Button("%");
    static Button btn16=new Button("√");
    static Button btn17=new Button("1/x");
    static Button btn18=new Button("x^2");
    static Button btn19=new Button("C");
    static Button btn20=new Button("CE");
    static Button btn21=new Button("=");
    static Button btn22=new Button(".");
    static Button btn23=new Button("Del");
    static Button btn24=new Button("");
    //主程式
    public static void main(String arg[]){
        frm.setTitle("MS Window"); //設定小算盤名稱
        frm.setLayout(null);
        frm.setSize(360,550);
        frm.setLocation(45,35);
        frm.setResizable(false); //固定視窗大小

        //設定大小、位置
        lab.setBounds(20,30,320,80);
        pnl_num.setBounds(20,260,240,210);
        pnl_funct.setBounds(20,120,240,140);
        pnl_op.setBounds(260,120,80,420);
        pnl_else.setBounds(20,470,240,70);

        //設定顏色
        lab.setBackground(new Color(245,245,245));
        btn21.setBackground(new Color(122,184,204));
        btn1.setBackground(new Color(255,252,250));
        btn2.setBackground(new Color(255,252,250));
        btn3.setBackground(new Color(255,252,250));
        btn4.setBackground(new Color(255,252,250));
        btn5.setBackground(new Color(255,252,250));
        btn6.setBackground(new Color(255,252,250));
        btn7.setBackground(new Color(255,252,250));
        btn8.setBackground(new Color(255,252,250));
        btn9.setBackground(new Color(255,252,250));
        btn10.setBackground(new Color(255,252,250));
        btn22.setBackground(new Color(255,252,250));
        btn24.setBackground(new Color(255,252,250));
        btn11.setBackground(new Color(245,245,245));
        btn12.setBackground(new Color(245,245,245));
        btn13.setBackground(new Color(245,245,245));
        btn14.setBackground(new Color(245,245,245));
        btn15.setBackground(new Color(245,245,245));
        btn16.setBackground(new Color(245,245,245));
        btn17.setBackground(new Color(245,245,245));
        btn18.setBackground(new Color(245,245,245));
        btn19.setBackground(new Color(245,245,245));
        btn20.setBackground(new Color(245,245,245));
        btn23.setBackground(new Color(245,245,245));

        // 設定字型大小
        Font fnt_l=new Font("Serief",Font.BOLD,32);
        lab.setFont(fnt_l);
        Font fnt_p=new Font("Serief",Font.BOLD,16);
        pnl_num.setFont(fnt_p);
        pnl_funct.setFont(fnt_p);
        pnl_op.setFont(fnt_p);
        pnl_else.setFont(fnt_p);

        // 設定Button
        btn1.addActionListener(frm);btn2.addActionListener(frm);btn3.addActionListener(frm);
        btn4.addActionListener(frm);btn5.addActionListener(frm);btn6.addActionListener(frm);
        btn7.addActionListener(frm);btn8.addActionListener(frm);btn9.addActionListener(frm);
        pnl_num.add(btn9);pnl_num.add(btn8);pnl_num.add(btn7);pnl_num.add(btn6);
        pnl_num.add(btn5);pnl_num.add(btn4);pnl_num.add(btn3);pnl_num.add(btn2);
        pnl_num.add(btn1);

        btn15.addActionListener(frm);btn20.addActionListener(frm);
        btn19.addActionListener(frm);btn17.addActionListener(frm);
        btn18.addActionListener(frm);btn16.addActionListener(frm);
        pnl_funct.add(btn15);pnl_funct.add(btn20);pnl_funct.add(btn19);
        pnl_funct.add(btn17);pnl_funct.add(btn18);pnl_funct.add(btn16);

        btn23.addActionListener(frm);btn14.addActionListener(frm);
        btn13.addActionListener(frm);btn12.addActionListener(frm);
        btn11.addActionListener(frm);btn21.addActionListener(frm);
        pnl_op.add(btn23);pnl_op.add(btn14);pnl_op.add(btn13);pnl_op.add(btn12);
        pnl_op.add(btn11);pnl_op.add(btn21);

        btn24.addActionListener(frm);btn10.addActionListener(frm);
        btn22.addActionListener(frm);
        pnl_else.add(btn24);pnl_else.add(btn10);pnl_else.add(btn22);

        //加至frm
        frm.add(lab);
        frm.add(pnl_num);
        frm.add(pnl_funct);
        frm.add(pnl_op);
        frm.add(pnl_else);
        frm.setVisible(true);

        //關閉視窗(匿名內部類別)
        frm.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

    }
    int counter=0;//計算輸入幾次數字
    int counterop=0; //計算輸入幾次opertor
    boolean state=true; //做除法運算時，分母是否為0的判斷紀錄
    String op; //紀錄上一次輸入的operator

    float Cnum[]=new float[12];//紀錄輸入每一次輸入的數字，根據位置乘10000...1000、100、10、1
    float firstnum,secondnum=0; //紀錄運算的數字
    float outcome=0; //運算結果
    public void actionPerformed(ActionEvent e){
        if(counter==0){
            lab.setText("0"); //尚未輸入數字
        }
        Button btn=(Button)e.getSource();
        try{
            Cnum[counter]= parseInt(btn.getLabel());
            counter+=1; //如果是數字才加1，且Cnum陣列從索引值0開始存放
        }catch(Exception E){ }
        if(lab.getText()!="0"){
            if(btn==btn1)// 如果是按下btn1按鈕
                lab.setText(lab.getText()+"1");
            if(btn==btn2)
                lab.setText(lab.getText()+"2");
            if(btn==btn3)
                lab.setText(lab.getText()+"3");
            if(btn==btn4)
                lab.setText(lab.getText()+"4");
            if(btn==btn5)
                lab.setText(lab.getText()+"5");
            if(btn==btn6)
                lab.setText(lab.getText()+"6");
            if(btn==btn7)
                lab.setText(lab.getText()+"7");
            if(btn==btn8)
                lab.setText(lab.getText()+"8");
            if(btn==btn9)
                lab.setText(lab.getText()+"9");
            if(btn==btn10)
                lab.setText(lab.getText()+"0");
        }
        else{
            if(btn==btn1)// 如果是按下btn1按鈕
                lab.setText("1");
            if(btn==btn2)
                lab.setText("2");
            if(btn==btn3)
                lab.setText("3");
            if(btn==btn4)
                lab.setText("4");
            if(btn==btn5)
                lab.setText("5");
            if(btn==btn6)
                lab.setText("6");
            if(btn==btn7)
                lab.setText("7");
            if(btn==btn8)
                lab.setText("8");
            if(btn==btn9)
                lab.setText("9");
            if(btn==btn10)
                lab.setText("0");
        }
        if(btn==btn19){ //C
            //清除所有計算結果
            lab.setText("");
            counter=0;
            counterop=0;
            lab.setText("0");
            for(int i=0;i<12;i++){
                Cnum[i]=0;
            }
            firstnum=secondnum=0;
            op="";
        }

        if(btn==btn23){ //去掉一個Del
            if(counter==0){ //如果未做任何輸入，表示不需要Del
                System.out.println("error!");
//                lab.setText("error!renew it!");
                lab.setText("0");
            }

            else{ //顯示刪掉最後輸入的數字，並讓Cnum[刪除值的索引值]存的值為0
                lab.setText("");
                for(int i=0;i<counter-1;i++){
                    System.out.println(Cnum[i]);
                    if(Cnum[i]==11){ //判斷是否為小數點的情況
                        lab.setText(lab.getText()+".");
                    }
                    else{
                        lab.setText(lab.getText()+(int)Cnum[i]);
                    }
                }
                Cnum[counter-1]=0;
                counter-=1;
            }
        }

        if(btn==btn20){ //去掉當前輸入CE
            lab.setText("0");
            counter=0;
        }

        if(btn==btn11){ //+
            if(counter==0 && counterop==0){ // 第一個輸入為+
                counterop+=1;
                out(0);
                op="+";

            }
            else{
                lab.setText("+");
                counterop+=1;
                float temp=tonumber(Cnum,counter);
                System.out.println(temp);
                out(temp);
                op="+";
            }
        }

        if(btn==btn12){ //-
            if(counter==0 && counterop==0){ // 第一個輸入為-
                counterop+=1;
                out(0);
                op="-";
            }
            else{
                lab.setText("-");
                counterop+=1;
                float temp=tonumber(Cnum,counter);
                System.out.println(temp);
                out(temp);
                op="-";
            }

        }
        if(btn==btn13){ //*
            if(counter==0 && counterop==0){ // 第一個輸入為*
                counterop+=1;
                out(0);
                op="*";
            }
            else{
                lab.setText("*");
                counterop+=1;
                float temp=tonumber(Cnum,counter);
                System.out.println(temp);
                out(temp);
                op="*";
            }

        }
        if(btn==btn14){ //÷
            if(counter==0 && counterop==0){ // 第一個輸入為÷
                counterop+=1;
                out(0);
                op="/";
            }
            else{
                lab.setText("÷");
                counterop+=1;
                float temp=tonumber(Cnum,counter);
                System.out.println(temp);
                out(temp);
                op="/";
            }

        }
        if(btn==btn21){ //=
            if(counterop==0){
                lab.setText("0");
            }
            else{
                counterop+=1;
                float temp=tonumber(Cnum,counter);

                out(temp);
                if(state==true){ //考慮是否除法分母為0
                    String judge = Float.toString(outcome);
                    if(judge.contains(".")){
                        int index;
                        index = judge.indexOf(".");
                        System.out.println(index);
                        if(judge.substring(judge.indexOf(".")+1, judge.length()).equals("0")){
                            lab.setText(Integer.toString((int) outcome));
                        }
                        else{
                            lab.setText(Float.toString(outcome));
                        }
                    }
                    else{
                        lab.setText(Float.toString(outcome));
                    }
                }
                else{
                    state=true;
                }
                counterop=0; //清除紀錄，重新開始小算盤
                op="=";
            }
        }

        if(btn==btn22){ //小數點.
            if(counter==0){ //錯誤判斷，第一個輸入不為小數點
//                System.out.println("輸入錯誤!");
                lab.setText("0.");
                Cnum[counter]=0;
                counter+=1;
                Cnum[counter]=11;
                counter+=1;

            }
            else{
                lab.setText(lab.getText()+".");
                Cnum[counter]=11; //若輸入小數點，則存一個標示在陣列裡讓函數判斷
                counter+=1;
            }
        }

        if(btn==btn18){ //平方
            if(counter==0){ //第一個輸入為^2
                lab.setText("0");
                out(0);
            }
            else{
                lab.setText("");
                float temp=tonumber(Cnum,counter);
                float realSquare=square(temp);
                int s=Integer.toString((int)realSquare).length();
                int squarenum[]=new int[12];
                //將平方後的數字拆開存進陣列
                //例: 123拆成 1、2、3分別放進陣列讓下一個operator做運算
                for(int i=s-1;i>=0;i--){
                    int y=(int)realSquare;
                    y=y%10;
                    realSquare=realSquare/10;
                    squarenum[i]=y;
                }
                for(int i=0;i<s;i++){ //顯示在lab上
                    Cnum[i]=squarenum[i];
                    lab.setText(lab.getText()+squarenum[i]);
                }
                counter=s;
            }
        }
        //僅做單次運算
        if(btn==btn15){ //100分之x
            if(counter==0){
                lab.setText("0");
                counterop+=1;
                out(0);
            }
            else{
                lab.setText("");
                float temp=tonumber(Cnum,counter);
                float re=temp/100;
                lab.setText(Float.toString(re));

                counterop+=1;
                out(re);
                counter=0;
            }

        }
        if(btn==btn17){ //x的倒數
            if(counter==0){
                lab.setText("Cannot Divide by 0");
            }
            else{
                lab.setText("");
                float temp=tonumber(Cnum,counter);
                float re=1/temp;
                lab.setText(Float.toString(re));

                counterop+=1;
                out(re);
                counter=0;
            }

        }
        if(btn==btn16){ //根號
            if(counter==0){
                lab.setText("0");
                counterop+=1;
                out(0);
            }
            else{
                lab.setText("");
                double temp=tonumber(Cnum,counter);
                double re=Math.pow(temp,0.5);

                String judge = Double.toString(re);
                if(judge.contains(".")){
                    int index;
                    index = judge.indexOf(".");

                    if(judge.substring(judge.indexOf(".")+1, judge.length()).equals("0")){
                        lab.setText(Integer.toString((int) re));
                    }
                    else{
                        lab.setText(Float.toString(outcome));
                    }
                }

//                lab.setText(Float.toString((float)re));

                counterop+=1;
                out((float)re);
                counter=0;
            }

        }

    }
    //把一個數字一個數字組合起來成一個operand
    public float tonumber(float Cnum[],int counter){
        float temp=0;
        boolean smallnum=false;
        int val=0;
        for(int i=0;i<counter;i++){ //判斷是否有小數點
            if(Cnum[i]==11){
                val=i;
                smallnum=true; //若有，設為true
            }
        }
        //有小數點則分開加總數字，先算小數點前，再加總小數點後
        //設val為陣列存小數點(數值為11)的索引值
        if(smallnum==true){
            int Ctemp=0;
            for(int i=0;i<val;i++){
                Cnum[i]=Cnum[i]*(int)Math.pow(10,val-1-i);
                System.out.println(Cnum[i]);
                temp+=Cnum[i];
            }
            for(int i=val+1;i<counter;i++){
                Ctemp+=1;
                Cnum[i]=Cnum[i]/(float)Math.pow(10,Ctemp);
                System.out.println(Cnum[i]);
                temp+=Cnum[i];
            }
        }
        //沒有小數點則做一般加總運算
        if(smallnum==false){
            for(int i=0;i<counter;i++){
                //System.out.println(counter-1-i);
                //System.out.println(Cnum[i]);
                Cnum[i]=Cnum[i]*(int)Math.pow(10,counter-1-i);
                System.out.println(Cnum[i]);
                temp+=Cnum[i];
            }
        }
        return temp;
    }
    //將處理過後的數值兩兩做適當的運算
    public float out(float x){
        float temp=x;
        if(counterop==1){
            firstnum=temp;
        }
        else{
            secondnum=temp;

            //運算
            if(op=="+"){
                outcome=firstnum+secondnum;
                System.out.println(outcome);
                firstnum=outcome;
            }
            if(op=="-"){
                outcome=firstnum-secondnum;
                System.out.println(outcome);
                firstnum=outcome;
            }
            if(op=="*"){
                outcome=firstnum*secondnum;
                System.out.println(outcome);
                firstnum=outcome;
            }
            if(op=="/"){
                if(secondnum==0){ //分母不得為0的判斷
                    state=false; //設狀態為false表示在除法時，分母為0
                    outcome=0;
                    counterop=0;
                    lab.setText("Cannot Divide by 0");
                    System.out.println("分母不為0");

                }else{
                    outcome=firstnum/secondnum;
                    System.out.println(outcome);
                    firstnum=outcome;
                }
            }

        }
        for(int i=0;i<11;i++)
            Cnum[i]=0;
        //要清除Cnum的值，因為要存下一次輸入的數字
        counter=0;

        return outcome;
    }
    //平方函數
    public float square(float x){
        float temp=x*x;
        return temp;
    }
}
