package com.spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.spring.dto.BookDTO;

@Controller
@RequestMapping("/test")
public class controller {
	String json;
	Gson gs = new Gson();
	
	@GetMapping("/case1")
	public String index() {
		return "index";
	}
	
	//DTO를 JSON으로 바꾸는 것
	@ResponseBody
	@GetMapping("/project01")
	public String project01() {
		BookDTO dto = new BookDTO("자바", 21000,"에이콘", 670);
		System.out.println(dto.toString());

		//dto를 문자열로 바꾼 것.
		json = gs.toJson(dto);
		System.out.println(json);

		return json;
	}
	
	//JSON을 DTO로 바꾸기
//	@ResponseBody
	@GetMapping("/project02")
	public String project02() {
		//            			  (재료 , 어떤 형태로 바꿀 것인가?);
		BookDTO dto = gs.fromJson(json, BookDTO.class);	//BookDTO의 class정보를 담고 있다.
		//System.out.println(dto);
		System.out.println(dto.toString());
		//System.out.println(dto.getTitle()+"\t"+dto.getPrice());
		
		return "index";
	}
	
	// 여러 DTO --> ArrayList로 묶고 --> JSON으로 변환
	@ResponseBody
	@GetMapping("/project03")
	public String project03() {		
		BookDTO dto1 = new BookDTO("자바1",21000, "에이콘1", 570);
		BookDTO dto2 = new BookDTO("자바2",31000, "에이콘1", 670);
		BookDTO dto3 = new BookDTO("자바3",11000, "에이콘1", 370);
		
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
		
		String listJson = gs.toJson(list);
		
		//JSON --> LIST<DTO>
		// 토큰은 정하기 나름. ArrayList가 될 수도 있고, DTO가 될 수도 있다.
		// 객체를 반납하는 것이 아니라 객체의 타입을 반환
		ArrayList<BookDTO> jsontolist = gs.fromJson(listJson,new TypeToken<ArrayList<BookDTO>>() {}.getType());
		
		for(int i=0; i<jsontolist.size(); i++) {
			BookDTO tmp = jsontolist.get(i);
			System.out.println(tmp);
		}
//		
//		for(BookDTO vo : list) {
//			System.out.println(vo);
//		}
		
		return listJson;
	}

	@GetMapping("/project04")
	public String project04(){
		JSONArray students= new JSONArray();
		
		//(1)객체 생성 
		JSONObject student = new JSONObject();
		student.put("name", "홍길동");
		student.put("phone", "010-1111-1111");
		student.put("address", "서울");
		System.out.println(student);
		//생성한 객체를 ArrayList에 넣음
		students.put(student);
		
		//(2)객체 생성
		JSONObject student2 = new JSONObject();
		student2.put("name", "나길동");
		student2.put("phone", "010-2222-2222");
		student2.put("address", "광주");
		System.out.println(student2);
		//생성한 객체를 ArrayList에 넣음
		students.put(student2);
		
		JSONObject object = new JSONObject();
		//마지막으로 생성된 list를 Object에 넣음
		object.put("students", students);
		
		System.out.println(object.toString(2));
		
		return "index";
	}
	
	@GetMapping("/project05")
	public String project05() {
		
		String client_id="";		//본인의 id_key 넣기
		String client_secret = "";	//본인의 secret_key 넣기
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			//Step 1 : 파라미터 확보하기 (검색할 주소정보를 콘솔로 입력)
			System.out.println("주소를 입력하세요: ");
			String address = io.readLine();
			String addr=URLEncoder.encode(address,"UTF-8");
	
			//Step 2 : URL 작성하기
			String reqUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+addr;
			
			//Step 3 : 작성된 URL을 이용하여 커넥션을 생성
			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestProperty("x-ncp-apigw-api-key-id", client_id);
			con.setRequestProperty("x-ncp-apigw-api-key", client_secret);
			BufferedReader br;
			
			//Step 4 : 요청 후 응답데이터 수신하기
			int responseCode = con.getResponseCode();
			//버퍼가 있으면 데이터를 더 잘받을 수 있다. 없다고 못받는 건 아니다.
			br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			
			/*
			if(responseCode==200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));				
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}*/
			
			//Step 5 : 수신한 데이터 문자열 데이터로 변환하기
			String line;
			StringBuffer response = new StringBuffer();
			
			while((line=br.readLine())!=null) {
				response.append(line);				
			}
			br.close();
			System.out.println(response);
			
			//Step 6 : JSON 객체로 변환하기
			// 데이터 단위를 인식시키기위해서 필요
			JSONTokener tokener = new JSONTokener(response.toString());
			// { } 인식
			JSONObject object = new JSONObject(tokener);
			//System.out.println(object.toString());
			System.out.println(object.get("status"));
			
			//다양한 데이터 꺼내보기
			JSONObject meta = object.getJSONObject("meta");
			int ttc = meta.getInt("totalCount");
			
			// 
			JSONArray arr = object.getJSONArray("addresses");
			JSONObject first = (JSONObject)arr.get(0);
			//System.out.println("address : "+ first.get("roadAddress"));
			//System.out.println("jibunAddress : "+first.get("jibunAddress"));
			String x = first.getString("x");
			String y = first.getString("y");
			System.out.println("경도 : "+ x);
			System.out.println("위도 : "+ y);
			
			JSONArray arr2 = first.getJSONArray("addressElements");
			JSONObject obj = (JSONObject)arr2.get(7);
			String sName = obj.getString("shortName");
			System.out.println(sName);
		
			getImage(x,y, addr);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "index";
	}
	
	
	public void getImage(String x, String y, String addr) {
		//Step 1 : URL 작성
		// https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?
		// w=300&
		// h=300&
		// center=127.1054221,37.3591614&
		// level=16' \
		// x-ncp-apigw-api-key-id: {API Key ID}'
		// x-ncp-apigw-api-key: {API Key}'
		String client_id="";
		String client_secret = "";
		
		String url = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
		url += "w=500&h=500&";
		url += "level=16&";
		url += "center="+x+","+y+"&";
		String decode = URLDecoder.decode(addr);
		String pos = null;
		try {
			pos = URLEncoder.encode(x+" "+y,"UTF-8");
        	url += "&markers=type:t|size:mid|pos:"+pos+"|label:"+URLEncoder.encode(decode, "UTF-8");
        	
        	//Step 2 : URL 객체 생성
        	URL ur = new URL(url);
        	HttpURLConnection con = (HttpURLConnection)ur.openConnection();
			con.setRequestProperty("x-ncp-apigw-api-key-id", client_id);
			con.setRequestProperty("x-ncp-apigw-api-key", client_secret);
        	
			//Step 3 : 데이터 수신
			InputStream is = con.getInputStream();
			// 이미지는 바이트단위이기 때문에 바이트 배열을 사용한다.
			byte[] bytes = new byte[1024];
			
			//파일이름 짓기
			/*
				Date dt = new Date();
				Long lt = dt.getTime();
				String imgName = lt.toString();
				아래 imgname을 풀어서 하면 위 코드주석처럼 된다.
			*/
			String imgname = Long.valueOf(new Date().getTime()).toString();
			File f = new File("c://logs//"+imgname+".jpg");
			f.createNewFile();
			int read = 0;
			FileOutputStream os = new FileOutputStream(f);
			while((read = is.read(bytes))!=-1) {			
				os.write(bytes,0,read);
			}
			is.close();
			os.close();
		}catch(Exception e) {}	
		
		
	}
}
