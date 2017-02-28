package Stack;

import java.util.Stack;

/**
 * Created by 65401 on 2017/2/28.
 * 迪杰斯特拉的双栈算法
 * 计算((2+(6/3))-4)
 */
public class doubleStack {
    private static Stack<Double> vals=new Stack<>();//     运算数stack
    private static Stack<Character> op=new Stack<>();//    运算符stack

    public static void main(String[] args) {
        String str="((2+(6/3))-4)";
        System.out.println(evl(str));
    }
    /**
     *
     * @param express:四则表达式
     * @return：最终结果
     */
    public static double evl(String express){
        char [] cs=express.toCharArray();
        for(int i=0;i<cs.length;i++){
            if(cs[i]=='+'){
                op.push(cs[i]);
            }else if(cs[i]=='-'){
                op.push(cs[i]);
            }else if(cs[i]=='*'){
                op.push(cs[i]);
            }else if(cs[i]=='/'){
                op.push(cs[i]);
            }else if(cs[i]=='('){//     不动

            }else if(cs[i]==')'){//     当检测到 ) 计算
                double d1=vals.pop();
                double d2=vals.pop();
                char c1=op.pop();
                if(c1=='+'){
                    vals.push(d2+d1);
                }else if(c1=='-'){
                    vals.push(d2-d1);
                }else if(c1=='*'){
                    vals.push(d2*d1);
                }else if(c1=='/'){
                    vals.push(d2/d1);
                }

            }else{//    数字
                vals.push(Double.parseDouble(new Character(cs[i]).toString()));
            }
        }
        return vals.pop();
    }
}
