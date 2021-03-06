package myutil.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * json 的几种格式
 * {"DevInfo":[{"AgentName":"Python_Agent","Screen_Size":"480*800","Device_Model":"Lenovo+S760","AgentChannel":"Channel_ID","Network":"WIFI%E6%A8%A1%E5%BC%8F","System_Model":"2.3.4","Location":"","SDKVersion":"150204","UserId":"860157010327119-null-b42e03d9cc43ff4c","AgentID":"","DeviceSDKVersion":10}]}
 * 
 * 
 */


/**
 * json 的自定义工具类
 * @author Europe
 * @date 2014-4-25
 *
 */
public class OZJson {

	/**
	 * 将 json 格式的字符串，转变成 json 对象<br>
	 * 例如：{"aa":{"k1":"v1","k2","v2"},"bb":{"k1":"v1","k2","v2"}}
	 */
	public JSONObject ozStringToJson(String str){
		
		JSONObject a = JSON.parseObject(str);
		
		return a;
	}
	
	/**
	 * 
	 * 将包含多个键-值对的 json 格式的字符串，转变成 json 对象<br>
	 * 并且取其中一组键-值对作为json对象返回<br>
	 * 例如：{"aa":{"k1":"v1","k2","v2"},"bb":{"k1":"v1","k2","v2"}}
	 * @param str
	 * @return
	 */
	public JSONObject ozSTJGetKey(String str){
		JSONObject a = JSON.parseObject(str);
		JSONObject b  = a.getJSONObject("patternAnalysisInfo");
		return b;
	}
	
	/**
	 * 遍历json对象，获取value值(该方法未完成)
	 * @param jsonObject 传入的json对象
	 * @param keyStr 所要放入的 key 关键字 （只对字符串中有多个key有效）
	 */
	public String ozParseJSON(JSONObject jsonObject,String keyStr){
		
		for( String id : jsonObject.keySet()) {
			System.out.println(id+":"+jsonObject.getJSONObject(id).getString(keyStr));
			System.out.println(jsonObject.getJSONObject(id).toString());
		}
		
		
		return  null;
	}
	
	
	/**
	 * 构建json数组
	 */
	public void ozBuildJsonArray(){
		JSONObject js = new JSONObject();
		js.put("a", "asdfas");
		js.put("a2", "asdfas");
		js.put("a3", "asdfas");
		
		JSONArray jsary = new JSONArray();
		jsary.add(js);
		jsary.add(js);
	}
	
	/**
	 * 遍历json数组
	 */
	public void ozWatchJsonArray(){
//		String json1 = "{data:[{name:'Wallace'},{name:'Grommit'}]}";
//		jsonObjSplit = new JSONObject(json1);
//		JSONArray ja = jsonObjSplit.getJSONArray("data");
//		for (int i = 0; i < ja.length(); i++) {
//		JSONObject jo = (JSONObject) ja.get(i);
//		System.out.println(jo.get("name"));
//		}
	}
	
	
	/**例子*/
	public static void main(String[] args) {
//		String patterStr = "{\"patternAnalysisInfo\":{\"4\":{\"countNum\":108989,\"successNum\":108953,\"successPer\":9},\"5\":{\"countNum\":108989,\"successNum\":108953,\"successPer\":9}}}";
//		JSONObject a = JSON.parseObject(patterStr);
//		JSONObject b  = a.getJSONObject("patternAnalysisInfo");
//		
//		for( String id : b.keySet()) {
//			System.out.println(id+":"+b.getJSONObject(id).getString("countNum"));
//			System.out.println(b.getJSONObject(id).toString());
//		}
		
		JSONObject js = new JSONObject();
		js.put("a", "s");
		System.out.println(js.toJSONString());
		
	}
	
}
