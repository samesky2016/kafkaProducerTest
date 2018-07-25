package com.jieshun.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	public static String mapToJson(Map<String, Object> map) {
		if ((map == null) || (map.size() == 0)) {
			return null;
		}
		return JSON.toJSONString(map);
	}

	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj);
	}

	public static <T> T toObject(String text, Class<T> clazz) {
		if (text == null) {
			return null;
		}
		return JSON.parseObject(text, clazz);
	}

	public static Map<String, Object> jsonToMap(String jsonStr) throws Exception {
		if ((jsonStr == null) || (StringUtil.empty(jsonStr))) {
			return null;
		}
		return (Map) JSON.parseObject(jsonStr, Map.class);
	}

	public static String formaterAsJSON(String content) {
	   
	    //求优雅输出
	    ObjectMapper mapper = new ObjectMapper();
	    Object obj;
		try {
			obj = mapper.readValue(content, Object.class);
			content=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			content="json格式不正确，请检查："+e.getMessage();
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			content="json格式不正确，请检查："+e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			content="json格式不正确，请检查："+e.getMessage();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	 

	}

	public static void main(String[] args) {
		
		   //已知一个json 字符串
	    String json = "{\"name\":\"sojson\",\"age\":4,\"domain\":\"http://www.sojson.com\"}";

	   System.out.println(formaterAsJSON(json));
		
	}
}
