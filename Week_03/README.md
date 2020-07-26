学习笔记
编写递归代码的几个要点：

1.不要人肉进行递归(最大误区)

2.找到最近最简方法，将其拆解成可重复解决的问题(重复子问题)

3.数学归纳法思维


递归代码模板:

Python:

def recursion(level, param1, param2, ...):
	
	# recursion trminator
	if level > MAX_LEVEL:
		process_result
		return
		
	# process logic in current level
	process(level, data...)
	
	# drill down
	self.recursion(level + 1, p1, ...)
	
	# reverse the current level status if needed
	
	
java:

public void recur(int level, int param) {
	
	// terminator
	if (level > MAX_LEVEL) {
		// process result
		return;
	}
	
	// process current logic
	process(level, param);
	
	// drill down
	recur(level: level + 1, new param);
	
	// restore current status
}


分治代码模板

def divide_conquer(problem, param1, param2, ...):

	# recursion terminator
	if problem is None:
		print_result
		return
		
	# prepare data
	data = prepare_data(problem)
	subproblems = split_problem(problem, data)
	# conquer subproblems
	subresult1 = self.divide_conquer(split_problems[0], p1, ...)
	subresult2 = self.divide_conquer(split_problems[1], p2, ...)
	subresult3 = self.divide_conquer(split_problems[2], p2, ...)
	...
	
	# process and generate the final result
	result = process_result(subresult1, subresult2, subresult3, ...)
	
	# revert the current level states
	
回溯问题的解决方法：

解决一个回溯问题，实际上就是一个决策树的遍历过程。一般来说，我们需要解决三个问题：

路径：也就是已经做出的选择。
选择列表：也就是你当前可以做的选择。
结束条件：也就是到达决策树底层，无法再做选择的条件。

使用的框架基本就是：

LinkedList result = new LinkedList();
public void backtrack(路径，选择列表){
    if(满足结束条件){
        result.add(结果);
    }
    for(选择：选择列表){
        做出选择;
        backtrack(路径，选择列表);
        撤销选择;
    }
}

