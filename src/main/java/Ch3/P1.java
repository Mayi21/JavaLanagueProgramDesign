package Ch3;

import javafx.scene.control.TableRow;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xaohii
 * @Date 2022/4/25 19:34
 */
public class P1 {
	public static void main(String[] args) {
		int[] nums = new int[]{2,-1,0,-1,4};
		Solution solution = new Solution();
		List<List<Integer>> list = solution.threeSum(nums);
		for (List<Integer> l:list){
			for (int i:l){
				System.out.print(i+"\t");
			}
			System.out.println("\n");
		}
	}
}
class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		int length = nums.length;
		if(length < 3){
			return new ArrayList<>();
		}else{
			Arrays.sort(nums);
			ArrayList<List<Integer>> list = new ArrayList<>();
			for(int i=0;i<length;i++){
				int start = 0;
				int end = length-1;
				boolean flag = false;
				while(start<end){
					if(nums[start]+nums[end]+nums[i] == 0){
						ArrayList<Integer> l = new ArrayList<>();
						l.add(nums[start]);
						l.add(nums[end]);
						l.add(nums[i]);
						list.add(l);
						break;
					}
					else{
						if (flag){
							end--;
							flag = false;
						}else {
							start++;
							flag = true;
						}
					}
				}
			}
			return list;
		}
	}
}

