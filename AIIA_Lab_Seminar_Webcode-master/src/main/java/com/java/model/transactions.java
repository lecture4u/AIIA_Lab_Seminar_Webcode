package com.java.model;

import java.util.HashMap;
import java.util.Map;

public class transactions {
	
	public String type;
	public String chaincodeID;
	public String payload;
	public String txid;
	public Map<String, String> timestamp = new HashMap<String, String>();

}
