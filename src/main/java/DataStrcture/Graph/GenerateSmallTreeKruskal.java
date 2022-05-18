package DataStrcture.Graph;

import java.io.*;
import java.util.*;

/**
 * @Author xaohii
 * @Date 2022/3/13 17:51
 */
public class GenerateSmallTreeKruskal {
	public static void main(String[] args) throws Exception {
		File file = new File("src/DataStrcture/Graph/value-graph.txt");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = bufferedReader.readLine();
		ArrayList<Edge> list = new ArrayList<>();
		while (line != null){
			String[] lines = line.split(",");
			Edge edge = new Edge(lines[0],lines[1],Integer.valueOf(lines[2]));
			list.add(edge);
			line = bufferedReader.readLine();
		}
		list.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if (o1.getValue() > o2.getValue()){
					return 1;
				}else if(o1.getValue() < o2.getValue()){
					return -1;
				}else{
					return 0;
				}
			}
		});
		for(Edge edge:list){
			System.out.println("start:"+edge.getStart()+",end:"+edge.getEnd()+",value:"+edge.getValue());
		}
		ArrayList<Set<String>> setArrayList = new ArrayList<>();
		for(Edge edge:list){
			if(!judgeInOneSet(edge,setArrayList)){
				addToArraylistSet(edge,setArrayList);
			}
		}
	}
	public static boolean judgeInOneSet(Edge edge,ArrayList<Set<String>> list){
		for (Set<String> set:list){
			if (set.contains(edge.getStart()) && set.contains(edge.getEnd())){
				return true;
			}
		}
		return false;
	}
	public static void addToArraylistSet(Edge edge,ArrayList<Set<String>> setArrayList){
		if (setArrayList.size() == 0){
			Set<String> set = new HashSet<>();
			set.add(edge.getStart());
			set.add(edge.getEnd());
		}else{
			for (Set<String> set:setArrayList){
				if (set.contains(edge.getStart())){
					set.add(edge.getEnd());
					break;
				} else if (set.contains(edge.getEnd())){
					set.add(edge.getStart());
					break;
				} else{
					Set<String> s = setArrayList.get(0);
					s.add(edge.getStart());
					s.add(edge.getEnd());
				}
			}
		}

	}
	public static boolean isCricle(Edge edge, ArrayList<Edge> edgeArrayList){
		Set<String> vertexSet = new HashSet<>();
		for (Edge e:edgeArrayList){
			vertexSet.add(e.getStart());
			vertexSet.add(e.getEnd());
		}
		if(vertexSet.contains(edge.getStart()) && vertexSet.contains(edge.getEnd())){
			String start = edge.getStart();
			String end = edge.getEnd();
			Stack<Edge> edgeStack = new Stack<>();
			boolean flag = false;
			do {
				for (Edge e:edgeArrayList){
					if (e.getStart().equals(start)){
						if (e.getEnd().equals(end)){
							flag = true;
							break;
						}else{
							start = e.getEnd();
							edgeStack.push(edge);
						}
					}else if(e.getEnd().equals(start)){
						if (e.getStart().equals(end)){
							flag = true;
							break;
						}else {
							start = e.getStart();
							edgeStack.push(edge);
						}
					}
				}
				// TODO:
			}while (edgeStack.empty());
			return true;
		}else{
			return false;
		}
	}
}
class Edge{
	private String start;
	private String end;
	private int value;
	Edge(String start,String end){
		this.start = start;
		this.end = end;
	}
	Edge(String start,String end,int value){
		this.start = start;
		this.end = end;
		this.value = value;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
