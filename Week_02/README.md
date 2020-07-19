学习笔记
HashMap总结：
HashMap 是一个散列表，它存储的内容是键值对 (key-value) 映射。
它根据键的hashCode值存储数据，一般情况下可以直接定位到它的值，因而具有很快的访问速度，但是遍历的顺序是不确定的。
HashMap最多只允许一条记录的键为null，可以允许多条记录的值为null。
HashMap非线程安全，即任一时刻可以有多个线程同时写 HashMap，可能会导致数据的不一致。如果需要满足线程安全，可以用
 Collections 的 synchronizedMap 方法使 HashMap 具有线程安全的能力，或者使用 ConcurrentHashMap。

put()方法大致的思路为：

如果 table 没有使用过的情况(tab=table)==null || (n=tab.length) == 0，则以默认大小进行一次 resize
计算 key 的 hash 值，然后获取底层 table 数组的第 (n-1)&hash 的位置的数组索引 tab[i] 处的数据，即 hash 对 n 取模
的位置，依赖的是 n 为 2 的次方这一条件
先检查该 bucket 第一个元素是否是和插入的 key 相等 (如果是同一个对象则肯定 equals)
如果不相等并且是 TreeNode 的情况，调用 TreeNode 的 putTreeVal 方法
否则循环遍历链表，如果找到相等的 key 跳出循环否则达到最后一个节点时将新的节点添加到链表最后, 当前面找到了相同的 
key 的情况下替换这个节点的 value 为新的 value。
最后如果新增了 key-value 对，则增加 size 并且判断是否超过了 threshold, 如果超过则需要进行 resize 扩容

get()方法大致的思路为：
根据哈希表元素个数与哈希值求模（使用的公式是 (n - 1) &hash）得到 key 所在的桶的头结点，如果头节点恰好是红黑树节点
，就调用红黑树节点的 getTreeNode() 方法，否则就遍历链表节点，找到相同的 key 值返回对应的 Value 值即可。