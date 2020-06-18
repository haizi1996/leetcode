package dynamic;

/**
 * 面试题 17.06. 2出现的次数
 * 编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。
 *
 * 示例:
 *
 * 输入: 25
 * 输出: 9
 * 解释: (2, 12, 20, 21, 22, 23, 24, 25)(注意 22 应该算作两次)
 * 提示：
 *
 * n <= 10^9
 *
 * 主要思路是数位dp：
 * 以dp[i]表示n的1~i位组成的数字所包含2的个数，关键点在于推导出dp[i]与dp[i-1]的关系
 *
 * 例如：n = 3478
 *
 *
 * dp[1] == numberOf2sInRange(8)
 * dp[2] == numberOf2sInRange(78)
 * dp[3] == numberOf2sInRange(478)
 * dp[4] == numberOf2sInRange(3478)
 *
 * dp[i] = f(dp[i-1]) ?
 * 下面来分析一下dp[i]与dp[i-1]的关系
 * 根据第i位的取值可分为4种情况：
 *
 * 第i位是0
 * 例如：n = 102, 分析dp[2]和dp[1]的关系，即numberOf2sInRange(02)与numberOf2sInRange(2) (02实际是2，写作02便于理解)
 * 第i位是0，该位取值范围只有这一种可能，由此可得
 *
 * dp[2] = dp[1]
 * numberOf2sInRange(02) = numberOf2sInRange(2)
 * 第i位是1
 * 例如：n = 178，分析dp[3]和dp[2]的关系，即numberOf2sInRange(178)与numberOf2sInRange(78)
 * 第3位是1，该位可能取0,1两种情况：
 *
 * dp[3] = 当第3位是0，1-2位取00~99时2的次数 + 当第3位是1, 1-2位取00~78时2的次数
 * dp[3] = numberOf2sInRange(99) + dp[2]
 * numberOf2sInRange(178) = numberOf2sInRange(99) + numberOf2sInRange(78)
 * 第i位是2
 * 例如：n = 233, 分析dp[3]和dp[2]的关系，即numberOf2sInRange(233)与numberOf2sInRange(33)
 *
 * dp[3] = 第3位取0-1,1-2位取00~99时2的次数 + 第3位是2,1-2位取00~33时2在1-2位出现的次数 + 第3位是2,1-2位取00~33时2在第3位出现的次数
 * dp[3] = 2 *numberOf2sInRange(99) + dp[2] + 33 + 1
 * numberOf2sInRange(233) = 2 * numberOf2sInRange(99) + numberOf2sInRange(33) + 33 + 1
 * 第i位大于2
 * 以 n = 478为例，分析dp[3]和dp[2]的关系，即numberOf2sInRange(478)与numberOf2sInRange(78)
 *
 * dp[3] = 第3位取0-3,1-2位取00-99时2出现在1-2位的次数 + 第3位取4,1-2位取00-78时2的次数 + 第3位取2,1-2位取00-99时2出现在第3位的次数
 * dp[3] = 4 * numberOf2sInRange(99) + dp[2] + 100
 * 总结上面4种情况：
 *
 *
 * dp[i]与dp[i-1]的关系，假设n的第i位的值为k
 * dp[i] = k * numberOf2sInRange(99..9){共i-1个9} + dp[i-1] + {n % 10^(i-1) + 1 }{若k == 2}  + { 10^(i-1) } {若k > 2}
 * 根据递推公式可以发现，若计算dp[i]，不仅要知道dp[i-1]还要知道numberOf2sInRange(99..9)，所以要同时计算numberOf2sInRange(99..9)的值
 *
 * 作者：sagacioushugo
 * 链接：https://leetcode-cn.com/problems/number-of-2s-in-range-lcci/solution/javajie-fa-shu-wei-dp-shuang-100-by-sagacioushugo/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class NumberOf2sInRange {


    public int numberOf2sInRange(int n) {

        int len = (int)Math.log10(n) + 1;
        int[] dp_9 = new int[len] , dp_num = new int[len];
        dp_9[0] = 1 ;
        int d = 10;
        dp_num[0] = ( n % d ) >= 2 ? 1 : 0;
        for (int i = 1 ; i < len ; i ++) {
            dp_9[i] = dp_9[i-1] * 10 + d  ;
            int i_value = (n / d) % 10;
            dp_num[i] += i_value * dp_9[i-1] + dp_num[i-1] ;
            if (i_value > 2){
                dp_num[i] += d;
            }else if(i_value == 2) {
                dp_num[i] += (n % d) + 1;
            }
            d *= 10;
        }
        return dp_num[len - 1];

    }


    public static void main(String[] args) {
       String first = "islander" , second = "slander";
        System.out.println(new NumberOf2sInRange().oneEditAway(first , second));
    }

    public boolean oneEditAway(String first, String second) {
        if(first.length() == second.length()){
            return oneEditAway(first , second , 0 , 0, false , 1 , 1);
        }else if(first.length() >= second.length()) {
            return oneEditAway(first , second , 0 , 0, false , 1 , 0);
        }else {
            return oneEditAway(first , second , 0 , 0, false , 0 , 1);
        }
    }

    public boolean oneEditAway(String first, String second , int fi , int si , boolean isOp ,int op_f , int op_s ){
        if(fi >= first.length() && si >= second.length()){
            return true;
        }
        if(((fi == first.length() && si != second.length()) ||(fi != first.length() && si == second.length())) ){
            if (isOp){
                return false;
            }else {
                return oneEditAway(first,second ,fi +op_f , si + op_s , true , op_f , op_s );
            }
        }
        if(isOp){
            if(first.charAt(fi) != second.charAt(si)){
                return false;
            }else{
                return oneEditAway(first , second , fi + 1 , si + 1 , isOp , op_f , op_s);
            }
        }else{
            if(first.charAt(fi ) != second.charAt(si)){
                return oneEditAway(first , second , fi + op_f , si + op_s , true, op_f , op_s) ;
            }else{
                return oneEditAway(first , second , fi + 1 , si + 1 , isOp, op_f , op_s)|| oneEditAway(first , second , fi + op_f , si + op_s , true, op_f , op_s) ;
            }
        }
    }
}
