/**
 * @author qhhu
 * @date 2019/9/24 - 23:28
 *
 * [341] 扁平化嵌套列表迭代器
 *
 * 题目：设计一个迭代器，使其能够遍历给定的嵌套的整型列表中的所有整数
 *      (列表中的项或者为一个整数，或者是另一个列表)
 *
 * 难度：medium
 *
 * 思路：实现把所有结果遍历出来，
 *      递归遍历列表中的数据，是整数就放入List，不是则再递归遍历
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    public List<Integer> list;
    public int index;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = dfs(nestedList);
        index = 0;
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    // 将嵌套的整型列表中的所有整数遍历出来，存入列表
    private List<Integer> dfs(List<NestedInteger> nestedList) {
        ArrayList<Integer> list = new ArrayList<>();
        for (NestedInteger temp : nestedList) {
            if (temp.isInteger()) {
                list.add(temp.getInteger());
            } else {
                list.addAll(dfs(temp.getList()));
            }
        }

        return list;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
