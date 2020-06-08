package dynamic;

/**
 *
 *
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class DecodeWays {

    public static int numDecodings(String s) {
        int len = s.length();
        if (len <= 0 || s.charAt(0) == '0'){
            return 0;
        }
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2 ; i <= len ; i++) {
            //字母是否合法
            if(s.charAt(i-1) != '0'){
                dp[i] += dp[i-1];
            }
            if ( s.charAt(i-2) == '1' || (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6')  ) {
                dp[i] += dp[i-2];
            }
        }
      return dp[len];
    }


    public static void main(String[] args) {
        System.out.println(numDecodings("27"));
    }
}
