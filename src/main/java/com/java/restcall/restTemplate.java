package com.java.restcall;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class restTemplate {

 	  ////////////////////
     ////[GET Method]////
    ////////////////////
	
	private RestTemplate restTemplate;
	private String uri;
	private String result;
	private String jsonData;
	public static final String address = "http://172.17.0.2:7050/";
	
	//Get Method
	public String Get(String input){
		
		this.restTemplate = new RestTemplate(); 
		this.result = restTemplate.getForObject(input, String.class);
		
		return result;
	}
	
	//Get Chain Height, Current/Previous chainHash
	public String getData(){
		//GET
		this.uri = address + "chain";

		//GET 호출, url
		this.result = Get(uri);
		
		return result;
	}
	
	//Get Chain Information(block num)
	public String getChainData(String blockNum){
		this.uri = address + "chain/blocks/" + blockNum;
		
		this.result = Get(uri);
		
		return result;
	}
	
	//Transaction Information(in chainInformation)
	public String getTranData(String txId){
		this.uri = address + "transactions/" + txId;
		this.result = Get(uri);
		
		return result;
	}
	
	//Peer Information
	public String getPeerData(){
		this.uri = address + "network/peers";
		this.result = Get(uri);
		
		return result;
	}
	
	
	
      /////////////////////
     ////[POST Method]////
    /////////////////////
	
	//Post Method
	public String Post(String json, String uri){
		
		this.restTemplate = new RestTemplate(); 
		
		//String을 Json data로 받아오기
	    HttpHeaders headers = new HttpHeaders(); //java에서 Restful Web 서비스 시 요청 헤더에 엑세스 할 수 있게 함
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity param= new HttpEntity(json, headers);
	    System.out.println(param);
	    
	    //Post 요청. uri, parameter(json Data)
	    this.result = restTemplate.postForObject(uri, param, String.class);
	    
		return result;
	}
	
	//EnrollID
	public void enrollId(){
		
		this.uri = address + "registrar";
		this.jsonData = ("{\n" +
		            "    \"enrollId\": \"admin\",\n" +
		            "    \"enrollSecret\" : \"Xurw3yU9zI0l\"\n" +
		            "} ");
		
		this.result = Post(jsonData, uri);
	    System.out.println(result);

	}
	
	//Deploy(현재 초기 값 임의로 a:100, b:200으로 설정해놓았음)
	public void deploy(){
		this.uri = address + "chaincode";
		this.jsonData = ("{\n" +
		            "    \"jsonrpc\": \"2.0\",\n" +
		            "		\"method\": \"deploy\",\n" +
		            "		\"params\": {\n" +
		            "       	\"type\": 1,\n" +
		            "			\"chaincodeID\":{\n" +
		            "           	\"name\": \"ChaincodeLogSmartContract\"\n" +
		            "			},\n"+		
		            "           \"CtorMsg\": {\n" +
		            "           	\"args\": [\"init\", \"a\", \"100\", \"b\", \"200\"]\n" +
		            "           },\n" +
		            "           \"secureContext\": \"admin\"\n" +
		            "       },\n" +
		            "    \"id\": 1\n"+
		            "}");
		
		this.result = Post(jsonData, uri);
	    System.out.println(result);
	}
	
	
	//Invoke
	public void invoke(String from, String to, String amount){
		this.uri = address + "chaincode";
		this.jsonData = ("{\n" +
		            "    \"jsonrpc\": \"2.0\",\n" +
		            "		\"method\": \"invoke\",\n" +
		            "		\"params\": {\n" +
		            "       	\"type\": 1,\n" +
		            "			\"chaincodeID\":{\n" +
		            "           	\"name\": \"ChaincodeLogSmartContract\"\n" +
		            "			},\n"+		
		            "           \"CtorMsg\": {\n" +
		            "           	\"args\": [\"invoke\", \""+ from +"\", \""+ to +"\", \""+ amount +"\"]\n" +
		            "           },\n" +
		            "           \"secureContext\": \"admin\"\n" +
		            "       },\n" +
		            "    \"id\": 3\n"+
		            "}");
		
		this.result = Post(jsonData, uri);
	    System.out.println(result);
	}
	
	//Query
	public String query(String from){
		
		this.uri = address + "chaincode"; 
		this.jsonData = ("{\n" +
		            "    \"jsonrpc\": \"2.0\",\n" +
		            "		\"method\": \"query\",\n" +
		            "		\"params\": {\n" +
		            "       	\"type\": 1,\n" +
		            "			\"chaincodeID\":{\n" +
		            "           	\"name\": \"ChaincodeLogSmartContract\"\n" +
		            "			},\n"+		
		            "           \"CtorMsg\": {\n" +
		            "           	\"args\": [\"query\", \""+ from +"\"]\n" +
		            "           },\n" +  
		            "            \"secureContext\": \"admin\"\n" +
		            "       },\n" +
		            "    \"id\": 5\n"+
		            "}");
		
		this.result = Post(jsonData, uri);
	    System.out.println(result);
	    
	    return result;
	}
	

	
	public static void main(String[] args){

	}
}
