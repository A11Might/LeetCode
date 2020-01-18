# 索引

- [算法思想](#算法思想)
    - [双指针](#双指针)
    - [排序](#排序)
    - [贪心思想](贪心思想)
    - [二分查找](#二分查找)
    - [分治](#分治)
    - [搜索](#搜索)
    - [动态规划](#动态规划)
    - [数学](#数学)
    
- [数据结构相关](#数据结构相关)
    - [链表](#链表)
    - [树](#树)
    - [栈和队列](#栈和队列)
    - [哈希表](#哈希表)
    - [字符串](#字符串)
    - [数组和矩阵](#数组与矩阵)
    - [图](#图)
    - [位运算](#位运算)
    
- [参考[cyc2018-LeetCode题解]](https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E7%9B%AE%E5%BD%95.md)

## 算法思想

#### 双指针

双指针主要用于遍历数组, 两个指针指向不同的元素, 从而协同完成任务, 包括指针碰撞, 快慢指针, 双指针

- [1. 有序数组的 Two Sum](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/description/) [[java]](../src/lc167.java)
- [2. 两数平方和](https://leetcode-cn.com/problems/sum-of-square-numbers/description/) [[java]](../src/lc633.java)
- [3. 反转字符串中的元音字符](https://leetcode-cn.com/problems/sum-of-square-numbers/description/) [[java]](../src/lc345.java)
- [4. 回文字符串](https://leetcode-cn.com/problems/valid-palindrome-ii/description/) [[java]](../src/lc680.java)
- [5. 归并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/description/) [[java]](../src/lc080.java)
- [6. 判断链表是否存在环](https://leetcode-cn.com/problems/linked-list-cycle/description/) [[java]](../src/lc141.java)
- [7. 最长子序列](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/description/) [[java]](../src/lc524.java)

#### 排序

- 快速选择

    用于求解 Kth Element 问题, 也就是第 K 个元素的问题. 可以使用快速排序的 partition() 进行实现. 需要先打乱数组, 否则最坏情况下时间复杂度为 O(n ^ 2)
    
    - [Kth Element](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/) [[java]](../src/lc215.java)
    
- 堆

    用于求解 TopK Elements 问题, 也就是 K 个最小元素的问题. 可以维护一个大小为 K 的最小堆, 最小堆中的元素就是最小元素. 最小堆需要使用大顶堆来实现, 大顶堆表示堆顶元素是堆中最大元素. 这是因为我们要得到 k 个最小的元素, 因此当遍历到一个新的元素时, 需要知道这个新元素是否比堆中最大的元素更小, 更小的话就把堆中最大元素去除, 并将新元素添加到堆中. 所以我们需要很容易得到最大元素并移除最大元素, 大顶堆就能很好满足这个要求.
    
    堆也可以用于求解 Kth Element 问题, 得到了大小为 k 的最小堆之后, 因为使用了大顶堆来实现, 因此堆顶元素就是第 k 大的元素.
    
    快速选择也可以求解 TopK Elements 问题, 因为找到 Kth Element 之后, 再遍历一次数组, 所有小于等于 Kth Element 的元素都是 TopK Elements. 
    
    可以看到, 快速选择和堆排序都可以求解 Kth Element 和 TopK Elements 问题
    
    - [出现频率最多的 k 个元素](https://leetcode-cn.com/problems/top-k-frequent-elements/description/) [[java]](../src/lc347.java)

- 桶排序
    
    - [按照字符出现次数对字符串排序](https://leetcode-cn.com/problems/sort-characters-by-frequency/description/) [[java]](../src/lc451.java)
    
- 荷兰国旗问题

    荷兰国旗包含三种颜色: 红, 白, 蓝.
    
    有三种颜色的球, 算法的目标是将这三种球按颜色顺序正确地排列. 它其实是三向切分快速排序的一种变种, 在三向切分快速排序中, 每次切分都将数组分成三个区间: 小于切分元素, 等于切分元素, 大于切分元素, 而该算法是将数组分成三个区间: 等于红色, 等于白色, 等于蓝色.
    
    - [按颜色进行排序](https://leetcode-cn.com/problems/sort-colors/description/) [[java]](../src/lc.java)

#### 贪心思想

#### 二分查找

#### 分治

#### 搜索

#### 动态规划

#### 数学

## 数据结构相关

#### 链表

#### 树

#### 栈和队列

#### 哈希表

#### 字符串

#### 数组与矩阵

#### 图

#### 位运算