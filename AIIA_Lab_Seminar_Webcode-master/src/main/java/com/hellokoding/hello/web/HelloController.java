package com.hellokoding.hello.web;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.java.model.Data;
import com.java.model.blockData;
import com.java.model.chain;
import com.java.model.transactions;
import com.java.restcall.restTemplate;

@Controller
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	private restTemplate rest;
	private Map<String, Object> result;
	private Map<String, Object> result2;
	private boolean init = false;
	
	//메인화면(invoke창)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		this.rest = new restTemplate();
		rest.enrollId();
		
		if(init == false){
			rest.deploy();
			init = true;
			System.out.println("Web Application Start!");
		}
		return "home";
	}
	
	//invoke button 클릭 시 발생
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("utf-8");

		this.rest = new restTemplate();
		rest.invoke((String)request.getParameter("To"), (String)request.getParameter("Amount"));
		return "home";
	}
	
	//쿼리화면
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(Model model) {

		return "query";
	}
	
	//쿼리 실행시 발생
	@RequestMapping(value = "/queryUser", method = RequestMethod.POST)
	public String queryUser(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		
		this.result = new HashMap<String, Object>();
		String output;
		
		request.setCharacterEncoding("utf-8");

		this.rest = new restTemplate();
		output = rest.query((String)request.getParameter("User"));
		
		//json -> object
		JsonReader reader = new JsonReader(new StringReader(output));
		reader.setLenient(true);
		Data data = new Gson().fromJson(reader, Data.class);
		
		String amount = data.result.get("message");
		
		result.put("name", (String)request.getParameter("User"));

		result.put("query", amount);
		model.addAttribute("value", result);
		
		return "queryResult";
	}
	
	//블록 정보 보는 창
	@RequestMapping(value = "/blockInfo", method = RequestMethod.GET)
	public String blockInfo(HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		
		
		this.result2 = new HashMap<String, Object>();
		String output2;
		
		request.setCharacterEncoding("utf-8");

		this.rest = new restTemplate();
		output2 = rest.getData();
		
		//chain information
		JsonReader reader2 = new JsonReader(new StringReader(output2));
		reader2.setLenient(true);
		chain chainData = new Gson().fromJson(reader2, chain.class);
		
		int height = Integer.parseInt(chainData.height) - 1;
		
		//체인 정보 넘겨주기
		result2.put("height", height);
		result2.put("currentBlockHash", chainData.currentBlockHash);
		result2.put("previousBlockHash", chainData.previousBlockHash);
		
		model.addAttribute("chain", result2);

		return "blockInfo";
	}
	
	@RequestMapping(value = "transactionResult", method = RequestMethod.POST)
	public String transactionResult(HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		
		this.result = new HashMap<String, Object>();
		String output;
		
		request.setCharacterEncoding("utf-8");
		
		this.rest = new restTemplate();
		output = rest.getTranData((String)request.getParameter("Transaction"));
		
		JsonReader reader = new JsonReader(new StringReader(output));
		reader.setLenient(true);
		transactions Transactions = new Gson().fromJson(reader, transactions.class);
		
		System.out.println(Transactions.chaincodeID);
		System.out.println(Transactions.payload);
		System.out.println(Transactions.txid);
		System.out.println(Transactions.type);
		
		byte[] decoded = Base64.decodeBase64(Transactions.payload);
		String B64decoded = new String(decoded);
		System.out.println(B64decoded);
		
		String b64[] = B64decoded.split("\n");
		int idx = b64[4].indexOf("*");
		String b64_value = b64[4].substring(0, idx);
		String b64_enrollID = b64[4].substring(idx+2);
		
		System.out.println("b64 : " + b64[1]);//chaincodeID
		System.out.println("b64 : " + b64[2]);//Method
		System.out.println("b64 : " + b64[3]);//Key
		System.out.println("b64 : " + b64[4]);//value+enrollID
		System.out.println("b64 : " + b64_value);//Value
		System.out.println("b64 : " + b64_enrollID);//EnrollID
		
		result.put("chaincodeID", Transactions.chaincodeID);
		result.put("payload", Transactions.payload);
		result.put("txid", Transactions.txid);
		result.put("type", Transactions.type);
		
		model.addAttribute("transaction", result);
		
		
		return "transactionResult";
	}
	
	//블록 정보 확인시 발생
	@RequestMapping(value = "/blockResult", method = RequestMethod.POST)
	public String blockResult(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		
		this.result = new HashMap<String, Object>();
		this.result2 = new HashMap<String, Object>();
		String output, output2;
		
		request.setCharacterEncoding("utf-8");

		this.rest = new restTemplate();
		output = rest.getChainData((String)request.getParameter("Block"));
		output2 = rest.getData();
		
		//block information
		JsonReader reader = new JsonReader(new StringReader(output));
		reader.setLenient(true);
		blockData blockdata = new Gson().fromJson(reader, blockData.class);
		
		//chain information
		JsonReader reader2 = new JsonReader(new StringReader(output2));
		reader2.setLenient(true);
		chain chainData = new Gson().fromJson(reader2, chain.class);
		
		//블록 정보 파싱한 결과 콘솔창에 출력해보기(blockdata 중 필요한 정보를 jsp에 넘겨주면 된다.)
		System.out.println(blockdata.previousBlockHash);
		System.out.println(blockdata.stateHash);
		//blockdata.searchNonHashData();
		//blockdata.searchTransaction();
		
		System.out.println(blockdata.transactions.get(0).type);
		System.out.println(blockdata.transactions.get(0).chaincodeID);
		System.out.println(blockdata.transactions.get(0).payload);
		System.out.println(blockdata.transactions.get(0).txid);
		
		//Transaction 시간 정보
		System.out.println(blockdata.transactions.get(0).timestamp.get("seconds"));
		System.out.println(blockdata.transactions.get(0).timestamp.get("nanos"));
		
		//체인 정보 파싱한 결과 콘솔창에 출력해보기
		System.out.println(chainData.height);
		System.out.println(chainData.currentBlockHash);
		System.out.println(chainData.previousBlockHash);
		
		//ledger에 저장된 시간 출력 정보
		System.out.println(blockdata.nonHashData.localLedgerCommitTimestamp.get("seconds"));
		System.out.println(blockdata.nonHashData.localLedgerCommitTimestamp.get("nanos"));

		//블록 정보 넘겨주기
		result.put("previousBlockHash", blockdata.previousBlockHash);
		result.put("stateHash", blockdata.stateHash);
		result.put("type", blockdata.transactions.get(0).type);
		result.put("chaincodeID", blockdata.transactions.get(0).chaincodeID);
		result.put("payload", blockdata.transactions.get(0).payload);
		result.put("txid", blockdata.transactions.get(0).txid);
		result.put("txSeconds", blockdata.transactions.get(0).timestamp.get("seconds"));
		result.put("txNanos", blockdata.transactions.get(0).timestamp.get("nanos"));
		result.put("LgSeconds", blockdata.nonHashData.localLedgerCommitTimestamp.get("seconds"));
		result.put("LgNanos", blockdata.nonHashData.localLedgerCommitTimestamp.get("nanos"));
		
		int height = Integer.parseInt(chainData.height) - 1;
		
		//체인 정보 넘겨주기
		result2.put("height", height);
		result2.put("currentBlockHash", chainData.currentBlockHash);
		result2.put("previousBlockHash", chainData.previousBlockHash);
		
		model.addAttribute("block", result);
		model.addAttribute("chain", result2);
		
		return "blockInfoResult";
	}
	
}