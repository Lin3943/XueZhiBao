package com.zyl.xuezhibao.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2017/3/9.
 */
public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName();
    /**
     * 将HashMap转换成JSONObject
     *
     * @param hm
     * @return
     */
    public static JSONObject mapToJSONObject(HashMap hm) {
        JSONObject jsonObject = new JSONObject();
        Set set = hm.entrySet();
        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            try {
                jsonObject.put(key, value);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return jsonObject;
    }





    /**
     * Object To json String
     *
     * @param obj
     *
     * @return json String
     */
    public static String objToJsonString(Object obj) {

        // 初始化返回值
        String json = "str_empty";

        if (obj == null) {
            return json;
        }

        StringBuilder buff = new StringBuilder();
        Field[] fields = obj.getClass().getFields();
        try {
            buff.append("[");
            buff.append("{");
            int i = 0;
            for (Field field : fields) {
                if (i != 0) {
                    buff.append(",");
                }
                buff.append(field.getName());
                buff.append(":");
                buff.append("\"");
                buff.append(field.get(obj) == null ? "" : field.get(obj));
                buff.append("\"");
                i++;
            }
            buff.append("}");
            buff.append("]");
            json = buff.toString();
        } catch (Exception e) {
            throw new RuntimeException("cause:" + e.toString());
        }
        return json;
    }





    public static  String listToString(List ss) {
        StringBuffer s = new StringBuffer("");
        if (null != ss) {
            String[] str = new String[ss.size()];
            for (int i=0; i<ss.size(); i++){
                str[i] = ss.get(i).toString();
            }
            arrayToString(str);
            s.append(arrayToString(str));
        }
        return s.toString();
    }

    /**
     * 把数组转换成'',格式的字符串输出
     * @param ss
     * @return
     */
    public static String arrayToString(String[] ss){
        StringBuffer s = new StringBuffer("");
        if(null != ss){
            for(int i=0;i<ss.length-1;i++){
                s.append("'")
                        .append(ss[i])
                        .append("'")
                        .append(",");
            }
            if(ss.length>0){
                s.append("'").append(ss[ss.length-1]).append("'");
            }
        }
        return s.toString();
    }
    /**
     *  Convert an array of strings to one string.
     *  Put the 'separator' string between each element.
     * @param a
     * @param separator
     * @return
     */
    public static String arrayToString(String[] a, String separator) {
        StringBuffer result = new StringBuffer();
        if(a==null){
            return "";
        }
        if (a.length > 0) {
            result.append(a[0]);
            for (int i=1; i<a.length; i++) {
                result.append(separator);
                result.append(a[i]);
            }
        }
        return result.toString();
    }


    /**
     * map转json字符串
     * @param map
     * @return
     */
    public static String simpleMapToJsonStr(HashMap<String,Object > map){
//        if(map==null||map.isEmpty()){
//            return "null";
//        }
//        String jsonStr = "{";
//        Set<?> keySet = map.keySet();
//        for (Object key : keySet) {
//            jsonStr += "\""+key+"\":\""+map.get(key)+"\",";
//        }
//        jsonStr = jsonStr.substring(1,jsonStr.length()-2);
//        jsonStr += "}";
//        return jsonStr;

        String string = "{";
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry e = (Map.Entry) it.next();
            string += "'" + e.getKey() + "':";
            string += "'" + e.getValue() + "',";
        }
        string = string.substring(0, string.lastIndexOf(","));
        string += "}";
        return string;

    }


    /**
     * json字符串转map
     * @param str
     * @return
     */

    //{"pass":"4355","name":"12342","wang":"fsf"}
    public Map  getData(String str){
        String sb = str.substring(1, str.length()-1);
        String[] name =  sb.split("\\\",\\\"");
        String[] nn =null;
        Map map = new HashMap();
        for(int i= 0;i<name.length; i++){
            nn = name[i].split("\\\":\\\"");
            map.put(nn[0], nn[1]);
        }
        return map;
    }







}
