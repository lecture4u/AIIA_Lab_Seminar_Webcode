package com.java.model;

import java.util.HashMap;
import java.util.Map;

//쿼리 결과 파싱하기(json data -> class data)
public class Data {

	public String jsonrpc;
	public Map<String, String> result = new HashMap<String, String>();
	public int id;
}
