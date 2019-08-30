import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=1146 lang=java
 *
 * [1146] Snapshot Array
 * 
 * 题目：实现快照数组
 * 
 * 思路：https://www.bilibili.com/video/av65922552
 *      
 */
class SnapshotArray {

    public List<TreeMap<Integer, Integer>> array; // treemap找值相当于二分查找
    public int snapId;
    public SnapshotArray(int length) {
        array = new ArrayList<>();
        for (int i = 0; i < length; i++) { // i为快照数组中元素位置
            array.add(new TreeMap<>()); // treemap(版本号，当前版本i位置的值)
            array.get(i).put(0, 0); // 默认快照数组每个位置的值都为0
        }
        snapId = 0; // 版本号从零开始
    }
    
    public void set(int index, int val) {
        array.get(index).put(snapId, val); // 当前版本，快照数组index位置的值设为val
    }
    
    public int snap() {
        return snapId++; // 快照，返回当前版本号，并进入下一个版本
    }
    
    public int get(int index, int snap_id) {
        // floorEntry(snap_id) 版本号不大于snap_id的最大版本号 
        return array.get(index).floorEntry(snap_id).getValue(); // 取出当前版本，快照数组index位置的值
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */

