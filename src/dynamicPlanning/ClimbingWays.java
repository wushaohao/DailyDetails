package dynamicPlanning;

/**
 *
 * @author wuhao
 * @date 17/6/29
 * 动态规划: 10层阶梯 可以走一步 也可以走2步 求这有多少种方式？
 */
public class ClimbingWays {

    public static int counts(int i){
        if(i<1){
            return 0;
        }else if (i==1){
            return 1;
        }else if (i==2){
            return 2;
        }

        int a=1,b=2,temp=0;

        for (int j = 3; j < i; j++) {
            temp =a+b;
            a=b;
            b=temp;
        }

        return temp;
    }

    public static void main(String[] args) {
        System.out.println("The count is "+counts(10));
    }

}
