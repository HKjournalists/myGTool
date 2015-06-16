package myutil.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


/**
 * 使用 map 的自定义工具类
 * 
 * @author Europe
 * @date 2014-4-4
 */
public class OZMapTool {

	/**
	 * <h1>该方法有问题，暂时不用。</h1><br>
	 *  将 map 转为 json 对象。<br>
	 * 传入一个 Map<Integer, String> ，将他转为形如：<br>
	 * {\
	 * "allpattern\":[{\"id\":\"1\",\"totalnum\":\"43258\",\"efficiency\":\"70%\"},{\"id\":\"1\",\"totalnum\":\"4375478\",\"efficiency\":\"60%\"}]
	 * }<br>
	 * 的 json 对象。
	 * 
	 * @param map
	 * @return
	 */
	public String ozMapToJson(Map<Integer, String> map) {

		String str = "{\"allpattern\":[";
		int mapsize = map.size();
		int count = 1;
		System.out.println(mapsize);
		for (Entry<Integer, String> entry : map.entrySet()) {
			String[] data = entry.getValue().split(",");
			if (count == mapsize) {
				str += "{\"id\":\"1\",\"totalnum\":\"" + data[0]
						+ "\",\"efficiency\":\"" + data[1] + "\"}]}";
			} else {
				str += "{\"id\":\"1\",\"totalnum\":\"" + data[0]
						+ "\",\"efficiency\":\"" + data[1] + "\"},";
				count++;
			}
		}
		
		System.out.println("生成的json：" + str);
		
		return str;
	}

	/**
	 * 这个方法的目的不是为了得到返回值，只是作为一个例子，帮助回忆Map的遍历
	 * <pre>
	 * for (Entry<String, String> entry : map.entrySet()) {
	 * 	   keyStr = entry.getKey();
	 * }
	 * </pre>
	 */
	public void ozGetMapMethed() {
		//例子在注释中
		Map<String, String> map = new HashMap<String, String>();
		
		Set<String> key = map.keySet();
	}

	
	
	/**
     * 对map进行排序,倒序排列
     * @param h
     * @param isDesc
     * @return
     */
    public static ArrayList sortMap(Map h,final boolean isDesc){
        ArrayList<Map.Entry<Long,Integer>> l = new ArrayList<Map.Entry<Long,Integer>>(h.entrySet());     
        Collections.sort(l, new Comparator<Map.Entry<Long, Integer>>() {       
			public int compare(Entry<Long, Integer> o1,
					Entry<Long, Integer> o2) {
				if(isDesc)
					return o2.getValue() - o1.getValue();
				else
					return o1.getValue() - o2.getValue();
			}
        });
        return l;
    }
	
	
	
	
	
	// 测试方法
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "aa");
		map.put(2, "aa");
		System.out.println(map.get(1));
		OZMapTool ozmapt = new OZMapTool();
		ozmapt.ozMapToJson(map);
	}

}
