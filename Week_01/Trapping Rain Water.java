import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int trap(int[] height) {

        // 双指针:
        int left = 0, right = height.length - 1;
        int ans = 0;
        int max_left = 0, max_right = 0;
        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= max_left) max_left = height[left];
                else ans += max_left - height[left];
                left++;
            } else {
                if (height[right] >= max_right) max_right = height[right];
                else ans += max_right - height[right];
                right--;
            }
        }
        return ans;
	}
}