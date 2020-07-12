学习笔记
本周学习心得：
    在没有加入算法训练营之前，自己对数组、链表、队列和栈也是有一定的了解，但是也只是
停留在了解层面，如果做一些这方面的算法题，大部分情况会摸不着头脑，尤其是链表的算法
题，更容易被绕晕，原因还是在于自己平时只是看一些数据结构与算法方面的理论知识，很少
去动手写一些这方面的代码，曾经也尝试过去刷一些题，但是因为方法不对，总是死磕在一道
题里面，有时因为一道题浪费了很多时间，最终还是不得其解，几次尝试过后就放弃了。加入了
训练营之后，听了超哥讲得切碎知识点、刻意练习以及五毒神掌，觉得这种方法应该适合我，
于是就报了本期的课程，学习了一周感觉收获不小，尤其是使用五毒神掌刷题，之前自己刷题刷了一遍
就不再看了，过了一段时间就往了，现在按照超哥的讲的方式来刷题，多刷了几次之后就连链表
的算法题也不再感到害怕了，每过一遍理解就会更深一些，自己的积极性也得到了提高，不会像
以前那样尝试了几次就放弃了，虽然目前训练营只学习了一周的时间，但是通过这种方式让我有
信心拿下算法，加油！

课后作业：
用 add first 或 add last 这套新的 API 改写 Deque 的代码：
        Deque<String> deque = new LinkedList<>();
        Queue q = new PriorityQueue(3);
        q.element();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque); //[c, b, a]

        String str = deque.peekFirst();
        System.out.println(str);//c
        System.out.println(deque);//[c, b, a]

        while (deque.size() > 0) {
            System.out.println(deque.removeFirst()); //c,b,a
        }
        System.out.println(deque);

分析 Queue 和 Priority Queue 的源码：
	
	Queue是一个队列，是一种先入先出的数据结构，本质上是一个接口，继承于Collection接口。
	基本操作有：
	    add(E e)和offer(E e)都是在队列中添加一个元素，区别是如果队列已满，前者会抛出异常，后者则返回false。
	    element()和peek()的语义完全相同，都是返回队首元素，区别是如果队列已满，前者会抛出异常，后者则返回null。
	    remove()和poll()方法的语义也完全相同，都是获取并删除队首元素，区别是当方法失败时前者抛出异常，后者返回null。
	
	PriorityQueue是优先队列，它是Queue接口的一种实现，可以对元素进行排序可以放基本数据类型的包装类或自定义的类
	对于基本数据类型的包装器类，优先队列中元素默认排列顺序是升序排列但对于自己定义的类来说，需要自己定义比较器，
	由于优先队列是通过使用小顶堆实现的，所以每次添加或删除元素都要进行相应的调整，复杂度为log(n)
	基本操作：
	    add(E e)和offer(E e)语义相同，都是向优先队列中插入元素，这两个方法在优先队列中没有什么差别,插入一个元素之后会调用
	       siftUp(int k, E x)方法进行小顶堆的调整
	    peek()方法获取但不删除队首元素，既是小顶堆堆顶元素，如果失败则返回null
	    remove()和poll()方法的语义也完全相同，都是获取并删除队首元素，区别是当方法失败时前者抛出异常，后者返回null。
	       由于删除操作会改变队列的结构，为维护小顶堆的性质，会调用siftDown(int k, E x)方法进行调整。
	    remove(Object o)方法用于删除队列中跟o相等的某一个元素（如果有多个相等，只删除一个），可以分为2种情况：
		    1. 删除的是最后一个元素。直接删除即可，不需要调整。
			2. 删除的不是最后一个元素，从删除点开始以最后一个元素为参照调用一次siftDown()。
		indexOf(Object o) 确定元素的位置
		grow(int minCapacity) 扩容，PriorityQueue采用自动扩容机制，当size>=queue.lenth的时候，会调用该方法进行数组扩容，
		minCapacity为满足要求的最小容量。