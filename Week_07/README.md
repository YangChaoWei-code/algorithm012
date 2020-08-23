学习笔记

Trie树代码模板:
//Java
class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}

并查集代码模板：
//java
class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	public int find(int p) { 
		while (p != parent[p]) { 
			parent[p] = parent[parent[p]]; 
			p = parent[p]; 
		}
		return p; 
	}
	public void union(int p, int q) { 
		int rootP = find(p); 
		int rootQ = find(q); 
		if (rootP == rootQ) return; 
		parent[rootP] = rootQ; 
		count--;
	}
}

A*代码模板：
//java
	public class AStar
	{
		public final static int BAR = 1; // 障碍值
		public final static int PATH = 2; // 路径
		public final static int DIRECT_VALUE = 10; // 横竖移动代价
		public final static int OBLIQUE_VALUE = 14; // 斜移动代价
		
		Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
		List<Node> closeList = new ArrayList<Node>();
		
		/**
		 * 开始算法
		 */
		public void start(MapInfo mapInfo)
		{
			if(mapInfo==null) return;
			// clean
			openList.clear();
			closeList.clear();
			// 开始搜索
			openList.add(mapInfo.start);
			moveNodes(mapInfo);
		}
	

		/**
		 * 移动当前结点
		 */
		private void moveNodes(MapInfo mapInfo)
		{
			while (!openList.isEmpty())
			{
				Node current = openList.poll();
				closeList.add(current);
				addNeighborNodeInOpen(mapInfo,current);
				if (isCoordInClose(mapInfo.end.coord))
				{
					drawPath(mapInfo.maps, mapInfo.end);
					break;
				}
			}
		}
		
		/**
		 * 在二维数组中绘制路径
		 */
		private void drawPath(int[][] maps, Node end)
		{
			if(end==null||maps==null) return;
			System.out.println("总代价：" + end.G);
			while (end != null)
			{
				Coord c = end.coord;
				maps[c.y][c.x] = PATH;
				end = end.parent;
			}
		}
	

		/**
		 * 添加所有邻结点到open表
		 */
		private void addNeighborNodeInOpen(MapInfo mapInfo,Node current)
		{
			int x = current.coord.x;
			int y = current.coord.y;
			// 左
			addNeighborNodeInOpen(mapInfo,current, x - 1, y, DIRECT_VALUE);
			// 上
			addNeighborNodeInOpen(mapInfo,current, x, y - 1, DIRECT_VALUE);
			// 右
			addNeighborNodeInOpen(mapInfo,current, x + 1, y, DIRECT_VALUE);
			// 下
			addNeighborNodeInOpen(mapInfo,current, x, y + 1, DIRECT_VALUE);
			// 左上
			addNeighborNodeInOpen(mapInfo,current, x - 1, y - 1, OBLIQUE_VALUE);
			// 右上
			addNeighborNodeInOpen(mapInfo,current, x + 1, y - 1, OBLIQUE_VALUE);
			// 右下
			addNeighborNodeInOpen(mapInfo,current, x + 1, y + 1, OBLIQUE_VALUE);
			// 左下
			addNeighborNodeInOpen(mapInfo,current, x - 1, y + 1, OBLIQUE_VALUE);
		}
	

		/**
		 * 添加一个邻结点到open表
		 */
		private void addNeighborNodeInOpen(MapInfo mapInfo,Node current, int x, int y, int value)
		{
			if (canAddNodeToOpen(mapInfo,x, y))
			{
				Node end=mapInfo.end;
				Coord coord = new Coord(x, y);
				int G = current.G + value; // 计算邻结点的G值
				Node child = findNodeInOpen(coord);
				if (child == null)
				{
					int H=calcH(end.coord,coord); // 计算H值
					if(isEndNode(end.coord,coord))
					{
						child=end;
						child.parent=current;
						child.G=G;
						child.H=H;
					}
					else
					{
						child = new Node(coord, current, G, H);
					}
					openList.add(child);
				}
				else if (child.G > G)
				{
					child.G = G;
					child.parent = current;
					openList.add(child);
				}
			}
		}
	

		/**
		 * 从Open列表中查找结点
		 */
		private Node findNodeInOpen(Coord coord)
		{
			if (coord == null || openList.isEmpty()) return null;
			for (Node node : openList)
			{
				if (node.coord.equals(coord))
				{
					return node;
				}
			}
			return null;
		}
	

	

		/**
		 * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
		 */
		private int calcH(Coord end,Coord coord)
		{
			return Math.abs(end.x - coord.x)
					+ Math.abs(end.y - coord.y);
		}
		
		/**
		 * 判断结点是否是最终结点
		 */
		private boolean isEndNode(Coord end,Coord coord)
		{
			return coord != null && end.equals(coord);
		}
	

		/**
		 * 判断结点能否放入Open列表
		 */
		private boolean canAddNodeToOpen(MapInfo mapInfo,int x, int y)
		{
			// 是否在地图中
			if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.hight) return false;
			// 判断是否是不可通过的结点
			if (mapInfo.maps[y][x] == BAR) return false;
			// 判断结点是否存在close表
			if (isCoordInClose(x, y)) return false;
	

			return true;
		}
	

		/**
		 * 判断坐标是否在close表中
		 */
		private boolean isCoordInClose(Coord coord)
		{
			return coord!=null&&isCoordInClose(coord.x, coord.y);
		}
	

		/**
		 * 判断坐标是否在close表中
		 */
		private boolean isCoordInClose(int x, int y)
		{
			if (closeList.isEmpty()) return false;
			for (Node node : closeList)
			{
				if (node.coord.x == x && node.coord.y == y)
				{
					return true;
				}
			}
			return false;
		}

1.单词搜索II的时间复杂度为O(N(4*3^L-1)),其中N代表单元格数，4代表
当前位置可以探索4个方向，3代表最多有3个相邻的单元格要探索，L代表
单词的最大长度

双向BFS代码模板(以单词接龙为例):
//Java
	//1.使用queue
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord) || !wordList.contains(endWord)) return 0;
        wordList.add(beginWord);
        Set<String> newSet = new HashSet<>(wordList);

        Queue<String> bqueue = new LinkedList<>();
        Set<String> bvisited = new HashSet<>();
        bqueue.offer(beginWord);
        bvisited.add(beginWord);

        Queue<String> equeue = new LinkedList<>();
        Set<String> evisited = new HashSet<>();
        equeue.offer(endWord);
        evisited.add(endWord);

        int level = 0;
        while (!bqueue.isEmpty() && !equeue.isEmpty()) {
            if (bqueue.size() > equeue.size()) {
                Queue<String> temp = bqueue;
                bqueue = equeue;
                equeue = temp;

                Set<String> set = bvisited;
                bvisited = evisited;
                evisited = set;
            }

            level++;
            int size = bqueue.size();
            while (size-- > 0) {
                String currWord = bqueue.poll();
                char[] charArray = currWord.toCharArray();
                for (int i = 0; i < charArray.length; ++i) {
                    char oldChar = charArray[i];
                    for(char j = 'a'; j <= 'z'; ++j) {
                        if (charArray[i] == j) continue;
                        charArray[i] = j;
                        String nextWord = new String(charArray);
                        if (bvisited.contains(nextWord)) continue;

                        if (evisited.contains(nextWord)) return level + 1;

                        if (newSet.contains(nextWord)) {
                            bqueue.offer(nextWord);
                            bvisited.add(nextWord);
                        }
                    }
                    charArray[i] = oldChar;
                }
            }
        }
        return 0;
		
	}
	//2.仅使用set
        if(beginWord.equals(endWord) || !wordList.contains(endWord)) return 0;
        wordList.add(beginWord);
        Set<String> newWord = new HashSet<>(wordList);

        Set<String> bvisited = new HashSet<>();
        bvisited.add(beginWord);

        Set<String> evisited = new HashSet<>();
        evisited.add(endWord);

        Set<String> visited = new HashSet<>();
        int count = 1;
        while (!bvisited.isEmpty() && !evisited.isEmpty()) {
            if (bvisited.size() > evisited.size()) {
                Set<String> set = bvisited;
                bvisited = evisited;
                evisited = set;
            }
            Set<String> temp = new HashSet<>();
            for (String currentWord: bvisited
                 ) {
                char[] charArray = currentWord.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    char old = charArray[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (charArray[i] == j)continue;
                        charArray[i] = j;
                        String next = new String(charArray);
                        if (evisited.contains(next)) return count + 1;

                        if (!visited.contains(next) && newWord.contains(next)){
                            temp.add(next);
                            visited.add(next);
                        }
                    }
                    charArray[i] = old;
                }
            }
            count++;
            bvisited = temp;
        }
        return 0;
		
	}
	