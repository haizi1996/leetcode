package greedy;

import java.util.Stack;

/**
 * 376. 摆动序列
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 *
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 *
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 *
 * 示例 1:
 *
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列均为摆动序列。
 * 示例 2:
 *
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 * 示例 3:
 *
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 */
public class WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {
        if(nums.length <= 2){
            return nums.length;
        }
        return Math.max(wiggleMaxLength( nums , true)  , wiggleMaxLength( nums , false) );
    }

    /**
     * 贪心算法 计算有几个波峰和波谷
     * @param nums
     * @return
     */
    public int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        // 前一次是上升序列还是下降
        int prevdiff = nums[1] - nums[0];
        int count = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                count++;
                prevdiff = diff;
            }
        }
        return count;
    }

    /**
     *  动态规划
     * @param nums
     * @return
     */
    public int wiggleMaxLength3(int[] nums) {
            if (nums.length < 2)
                return nums.length;
            //遍历到当前节点， 最后一次是下降序列的摆动序列长度，
            //遍历到当前节点， 最后一次是上升序列的摆动序列长度，
            int down = 1, up = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1])
                    up = down + 1;
                else if (nums[i] < nums[i - 1])
                    down = up + 1;
            }
            return Math.max(down, up);
        }

    /**
     *
     * @param nums
     * @param flag 是否先降序
     * @return
     */
    public int wiggleMaxLength(int[] nums , boolean flag) {
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 1; i < nums.length ; i++) {
            Integer last_stack = stack.peek();
            // 降序
            if (flag){
                // 如果呈现升序 先出栈
                if (nums[i] >= last_stack ){
                    stack.pop();
                }else {
                    flag = !flag;
                }
                // 升序
            }else {
                // 如果呈现降序 先出栈
                if (nums[i] <= last_stack ){
                    stack.pop();
                }else {
                    flag = !flag;
                }
            }
            stack.push(nums[i]);

        }
        return stack.size();
    }

    public static void main(String[] args) {
        int[] nums = {1,17,5,10,13,15,10,5,16,8};
        System.out.println(new WiggleSubsequence().wiggleMaxLength(nums));

    }
}
