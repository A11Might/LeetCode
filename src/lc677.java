/**
 * @author qhhu
 * @date 2020/2/9 - 9:15
 *
 * [677] 键值映射
 *
 * 题目: 实现MapSum类里的insert和sum方法, 对于方法insert, 你将得到一对(字符串, 整数)的键值对. 字符串表示键, 整数表示值.
 *                                                如果键已经存在, 那么原来的键值对将被替代成新的键值对。
 *                                   对于方法sum, 你将得到一个表示前缀的字符串, 你需要返回所有以该前缀开头的键的值的总和.
 *
 * 难度: medium
 *
 * 思路: 构建前缀树解决当前问题
 */
class MapSum {
    private class TrieNode {
        int val;
        TrieNode[] next;
        boolean end;

        TrieNode() {
            int val = 0;
            next = new TrieNode[26];
            end = false;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    // 重复插入会覆盖原有的值, 所以不能做预处理来方便统计前缀和
    public void insert(String key, int val) {
        TrieNode cur = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (cur.next[index] == null) {
                cur.next[index] = new TrieNode();
            }
            cur = cur.next[index];
        }
        cur.end = true;
        cur.val = val;
    }

    public int sum(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (cur.next[index] == null) {
                return 0;
            }
            cur = cur.next[index];
        }
        return sum(cur);
    }

    // 统计以cur为起始节点的树中的所有的key对应的val之和
    private int sum(TrieNode cur) {
        if (cur == null) return 0;
        int ans = 0;
        if (cur.end) ans += cur.val;
        for (TrieNode node : cur.next) {
            ans += sum(node);
        }

        return ans;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
