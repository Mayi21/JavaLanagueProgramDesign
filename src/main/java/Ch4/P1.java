package Ch4;

/**
 * @Author xaohii
 * @Date 2022/4/27 9:29
 */
public class P1 {
	public static void main(String[] args) {
		int[] a = new int[]{1,1,0,0,1,1,1,0,0,1};
		Solution solution = new Solution();
		System.out.println(solution.maxSubArray(a));
	}
}
class Solution {
	public int maxSubArray(int[] nums) {
		int len = nums.length;
		int sum = 0;
		int res = Integer.MIN_VALUE;
		for (int x : nums) {
			sum += x;
			res = Math.max(res, sum);
			if (sum<0){
				sum=0;
			}
		}
		return res;
	}
}

