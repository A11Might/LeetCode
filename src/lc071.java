import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author qhhu
 * @date 2019/9/23 - 15:56
 *
 * 题目：简化unix风格的绝对路径，"."代表当前目录本身，".."表示切换到上一级
 *      (返回的规范路径必须始终以斜杠"/"开头，并且两个目录名之间只有一个斜杠"/"，不能以"/"结尾)
 *
 * 难度：medium
 *
 * 思路：使用双端队列，栈结构简化绝对目录(依次压入目录，遇到".."则弹出一级目录)，队列结构输出简化后的目录
 */
class Solution {
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        String[] strs = path.split("/");
        for (String str : strs) {
            // 遇到"."，当前目录不变
            if (str.equals(".")) {
                continue;
            // 遇到".."，弹出一级目录
            } else if (str.equals("..")) {
                deque.pollLast();
            // 若给定目录中有连续的两个"//"时，split后会出现""，跳过即可
            } else if (str.equals("")) {
                continue;
            // 遇到目录，压入栈中
            } else {
                deque.addLast(str);
            }
        }

        // 打印输出
        if (deque.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/");
            sb.append(deque.pollFirst());
        }

        return sb.toString();
    }
}
