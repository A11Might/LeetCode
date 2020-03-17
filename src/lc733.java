/**
 * @author qhhu
 * @date 2020/3/17 - 15:16
 *
 * [733] 图像渲染
 *
 * 题目：给定一个坐标表示图像渲染开始的像素点和一个新的颜色值，让你重新上色这幅图像。
 *
 * 难度：easy
 *
 * 思路：Flood Fill（深度优先搜索）：从起始像素点开始，深度优先搜索遍历所有与之相连的像素点，并对其重新上色。
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度即递归栈的深度)
     */
    private int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // 需要一个特判，否则会死循环。
        // 因为我们是根据当前像素点颜色是否与初始像素点颜色相同来决定是否继续 dfs 的。
        // 若初始像素点颜色与新颜色相同，则初始像素颜色不变，会一直递归调用 dfs。
        if (image[sr][sc] == newColor) return image;
        int oldColor = image[sr][sc];
        image[sr][sc] = newColor;
        for (int[] dir : direction) {
            int x = sr + dir[0], y = sc + dir[1];
            if (x < 0 || x >= image.length || y < 0 || y >= image[0].length
                    || image[x][y] != oldColor) continue;
            floodFill(image, x, y, newColor);
        }

        return image;
    }
}