学习笔记

动态规划解题三步骤：

1.分治(子问题)
2.状态数组定义
3.DP方程(状态转移方程)


分治代码模板:

//java
private static int divide_conquer(Problem problem, ){

	if (problem == NULL) {
		int res = process_last_result();
		return res;
	}
	
	subProblem = split_problem(problem)
	
	res0 = divide_conquer(subProblem[0])
	res1 = divide_conquer(subProblem[1])
	
	result = process_result(res0, res1);
	
	return result;
}

//Python
def divide_conquer(problem, param1, param2, ...): 
  # recursion terminator 
  if problem is None: 
	print_result 
	return 

  # prepare data 
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 

  # conquer subproblems 
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …

  # process and generate the final result 
  result = process_result(subresult1, subresult2, subresult3, …)
	
  # revert the current level states
  
// C/C++
int divide_conquer(Problem *problem, int params) {
  // recursion terminator
  if (problem == nullptr) {
    process_result
    return return_result;
  } 

  // process current problem
  subproblems = split_problem(problem, data)
  subresult1 = divide_conquer(subproblem[0], p1)
  subresult2 = divide_conquer(subproblem[1], p1)
  subresult3 = divide_conquer(subproblem[2], p1)
  ...

  // merge
  result = process_result(subresult1, subresult2, subresult3)
  // revert the current level status
 
  return 0;
}

//Javascript
const divide_conquer = (problem, params) => {

  // recursion terminator

  if (problem == null) {

    process_result

    return

  } 

  // process current problem

  subproblems = split_problem(problem, data)

  subresult1 = divide_conquer(subproblem[0], p1)

  subresult2 = divide_conquer(subproblem[1], p1)

  subresult3 = divide_conquer(subproblem[2], p1)

  ...

  // merge

  result = process_result(subresult1, subresult2, subresult3)

  // revert the current level status

}

递归代码模板：
//java
public void recur(int level, int param){

	//terminator
	if (level > MAX_LEVEL) {
		// process result
		return;
	}
	
	// process current logic
	process(level, param);
	
	// drill down
	recur(level: level + 1, param);
	
	// restore current status
}

// C/C++
void recursion(int level, int param) {
	//recursion terminator
	if (level > MAX_LEVEL) {
		// process result
		return;
	}
	
	// process current logic
	process(level, param);
	
	// drill down
	recur(level: level + 1, param);
	
	// restore current status

}

// python
def recursion(level, param1, param2, ...):
	#recursion terminator
	if level > MAX_LEVEL:
		process_result
		return
		
	# process logic in current level
	process(level, data...)
	
	# drill down
	self.recursion(level + 1, p1, ...)
	
	# reverse the current level status if needed
	
// JavaScript
const recursion = (level, params) =>{
	//recursion terminator
	if (level > MAX_LEVEL) {
		// process result
		return;
	}
	
	// process current level
	process(level, param);
	
	// drill down
	recur(level: level + 1, param);
	
	// clean current level status if needed
	
}
	
	