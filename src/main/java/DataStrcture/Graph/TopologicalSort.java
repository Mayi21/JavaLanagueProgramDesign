package DataStrcture.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Author xaohii
 * @Date 2022/3/13 21:12
 */
public class TopologicalSort {
	public static void main(String[] args) {
		ArrayList<Edge> edgeArrayList = readDataFromFile();
		int[][] mat = toMat(edgeArrayList);
		Set<Integer> set = new HashSet<>();
		int start = -1;
		do {
			if(start == -1){
				for (int i=0;i<mat.length;i++){
					if (set.contains(i)){
						continue;
					}
					boolean flag = true;
					for (int j=0;j<mat.length;j++){
						if (mat[j][i] == 1){
							flag = false;
							break;
						}
					}
					if (flag){
						start = i;
						System.out.println(start);
						set.add(start);
						break;
					}
				}
			}else{

				for(int i = 0;i<mat.length;i++){
					if (mat[start][i] == 1){
						mat[start][i] = 0;
					}
				}
				start = -1;
			}
		}while (set.size() != mat.length);
	}
	public static ArrayList<Edge> readDataFromFile() {
		ArrayList<Edge> list = new ArrayList<>();
		File file = new File("src/DataStrcture/Graph/directionGraph.txt");
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = bufferedReader.readLine();

			while (line != null){
				String[] lines = line.split(",");
				Edge edge = new Edge(lines[0],lines[1]);
				list.add(edge);
				line = bufferedReader.readLine();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public static int[][] toMat(ArrayList<Edge> edgeArrayList){
		Set<String> set = new HashSet<>();
		for (Edge edge:edgeArrayList){
			set.add(edge.getStart());
			set.add(edge.getEnd());
		}
		int[][] mat = new int[set.size()][set.size()];
		for (Edge edge:edgeArrayList){
			mat[Integer.parseInt(edge.getStart())][Integer.parseInt(edge.getEnd())] = 1;
		}
		return mat;
	}

}
