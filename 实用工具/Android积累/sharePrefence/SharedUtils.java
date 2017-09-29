import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedUtils {
	/**
	 * 该方法用于SharedPreferences数据的存储
	 * @param context	为了得到SharedPreferences对象的环境
	 * @param name 		文件名
	 * @param mode 		访问模式
	 * @param map		存储的数据
	 * @return
	 * 			该返回值用于确定是否存储成功,true表示成功,false表示失败
	 */
	public static boolean putData(Context context,String name,int mode,Map<String,Object> map){
		SharedPreferences preferences = context.getSharedPreferences(name, mode);
		Editor edit = preferences.edit();
		Set<Entry<String,Object>> set = map.entrySet();
		for (Entry<String, Object> entry:set) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String) {
				edit.putString(key, (String)value);
			}else if(value instanceof Boolean){
				edit.putBoolean(key, (Boolean)value);
			}else if(value instanceof Float){
				edit.putFloat(key, (Float)value);
			}else if(value instanceof Integer){
				edit.putInt(key, (Integer)value);
			}else if(value instanceof Long){
				edit.putLong(key, (Long)value);
			}
		}
		return edit.commit();
	}
	/**
	 * 该方法用于在SharedPreferences中取对应key的value
	 * @param context	环境
	 * @param name		文件名
	 * @param mode		访问模式
	 * @param key		要取值的key
	 * @return
	 * 		取出的值
	 */
	public static Object getData(Context context,String name,int mode,String key){
		SharedPreferences preferences = context.getSharedPreferences(name, mode);
		Map<String, ?> all = preferences.getAll();
		Object object = all.get(key);
		return object;
	}
}
