/**
 * [352] 将数据流变为多个不相交区间
 * 
 * 题目：给定一个非负整数的数据流输入 a1，a2，…，an，…，将到目前为止看到的数字总结为不相交的区间列表。
 * 
 * 难度：hard
 * 
 * 思路：使用有序集合维护当前不相交的区间列表，根据数据流的最新值更新区间列表
 */
class SummaryRanges {

    private int MAX_VALUE = 0x3f3f3f3f;
    private TreeSet<int[]> set;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        set = new TreeSet<>((o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        set.add(new int[] {-MAX_VALUE, -MAX_VALUE});
        set.add(new int[] {MAX_VALUE, MAX_VALUE});
    }

    public void addNum(int val) {
        // 找到左端点 <= val 的最后一个区间 l
        int[] l = set.lower(new int[] {val, MAX_VALUE}); // 小于等于 val 的区间
        int[] r = set.higher(new int[] {val, MAX_VALUE}); // 大于 val 的区间
        
        // x 在 l 区间中
        if (l[1] >= val) return;
        
        if (l[1] == val - 1 && r[0] == val + 1) {
            // [ , x - 1] x [x + 1, ]
            set.add(new int[] {l[0], r[1]});
            set.remove(l);
            set.remove(r);
        } else if (l[1] == val - 1) {
            // [ , x - 1] x
            set.add(new int[] {l[0], val});
            set.remove(l);
        } else if (r[0] == val + 1) {
            // x [x + 1, ]
            set.add(new int[] {val, r[1]});
            set.remove(r);
        } else {
            // [x, x]
            set.add(new int[] {val, val});
        }
    }

    public int[][] getIntervals() {
        int n = set.size();
        int[][] ret = new int[n - 2][2];
        int idx = 0;
        for (int[] arr : set) {
            if (arr[0] == -MAX_VALUE || arr[0] == MAX_VALUE) continue;
            ret[idx][0] = arr[0];
            ret[idx++][1] = arr[1];
        }
        return ret;
    }
}