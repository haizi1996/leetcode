package dynamic;

/**
 *
 *  96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 */
public class UniqueBinarySearchTrees {


    public static int numTrees(int n) {
        if(n <= 2){
            return n;
        }
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 2 ;
        for (int i = 2 ; i <= n; i++) {
            // 新加入的节点放在第几层 一共i层
            for (int j = 1 ; j <= i; j++) {
                dp[i] = dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
//        System.out.println(numTrees(3));

        String str1 = new String("123");
        String str2 = new String("123");
        System.out.println(str1 == str2);
        System.out.println(str1.intern() == str2.intern());
    }
}
