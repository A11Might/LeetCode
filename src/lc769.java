/**
 * @author qhhu
 * @date 2020/2/14 - 8:54
 *
 * [769] 最多能完成排序的块
 *
 * 题目: 数组arr是[0, 1, ..., arr.length - 1]的一种排列, 将这个数组分割成几个块, 并将这些块分别进行排序. 之后再连接起来,
 *      使得连接的结果和按升序排序后的原数组相同. 返回最多能将数组分成的块数.
 *
 * 难度: medium
 *
 * 思路: 不同块排序后连接在一起和按升序排序后的原数组相同, 说明当前块的最大值小于之后块的最小值.
 *      所以遍历数组, 当遍历到第i个位置时, 如果可以切分为块, 那前i个位置的最大值一定等于i;
 *      否则, 前i个元素中有大于i的数即有比i小的数划分到后面的块(因为数组arr为[0, arr.length - 1]的一种排列), 那块排序后, 一定不满足升序.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int maxChunksToSorted(int[] arr) {
        int cnt = 0, max = -1;
        for (int i = 0; i < arr.length; i++) {
            //统计前i个位置的最大元素
            max = Math.max(max, arr[i]);
            if (max == i) cnt++;
        }

        return cnt;
    }
}
