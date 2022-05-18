package Ch5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author xaohii
 * @Date 2022/4/27 10:26
 */
public class P2 {
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3};
		Solution solution = new Solution();
		List<List<Integer>> list = solution.permute(nums);
		System.out.println(list.size());


	}
}
class Solution {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();

		List<Integer> output = new ArrayList<Integer>();
		for (int num : nums) {
			output.add(num);
		}

		int n = nums.length;
		backtrack(n, output, res, 0);
		return res;
	}

	public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
		// 所有数都填完了
		if (first == n) {
			res.add(new ArrayList<Integer>(output));
		}
		for (int i = first; i < n; i++) {
			// 动态维护数组
			Collections.swap(output, first, i);
			// 继续递归填下一个数
			backtrack(n, output, res, first + 1);
			// 撤销操作
			Collections.swap(output, first, i);
		}
	}
}