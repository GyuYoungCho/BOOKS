package com.project.book.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.book.dao.BookDao;
import com.project.book.dto.Book;
import com.project.book.dto.Bookapi;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {
	
	@Autowired
	BookDao boodao;
	Bookapi api;
	String key = new Bookapi().getKey();
	
	@ApiOperation(value = "단어를 포함한 book 검색", response = String.class)
	@GetMapping("/search")
	public Page<Book> getBookSearchList(@RequestParam("keyword") String keyword ,final Pageable pageable) throws NumberFormatException, ParseException{
		String url = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=" + key + "&Query=" + keyword + "&QueryType=Title&MaxResults=10&start=1&SearchTarget=Book&output=xml&Version=20131101";
		JSONArray jsonArray=JSONParsing(url);
		SimpleDateFormat dateparse = new SimpleDateFormat("yyyy-mm-dd");
		List<Book> bookList=new ArrayList<>();
//		Page<Book> bookpage;
		for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject data=(JSONObject) jsonArray.get(i);
            
            Book book=new Book(1,
                    data.get("tile").toString(),
                    data.get("link").toString(),
                    data.get("author").toString(),
                    dateparse.parse(data.get("pubDate").toString()),
                    data.get("description").toString(),
                    Integer.parseInt(data.get("priceSales").toString()),
                    Integer.parseInt(data.get("priceStandard").toString()),
                    Integer.parseInt(data.get("priceStandard").toString()),
                    data.get("malltype").toString(),
                    data.get("cover").toString(),
                    Integer.parseInt(data.get("categoryId").toString()),
                    data.get("publisher").toString(),
                    0
            );
            bookList.add(book);
        }
		
		return new PageImpl<Book>(bookList,pageable,bookList.size());
	}
	
	@ApiOperation(value = "book의 상세 정보", response = String.class)
	@GetMapping("/detail/{bookId}")
	public Page<Book> bookdetail(@PathVariable("bookId") int bookId){
		String url = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?ttbkey=[TTBKey]&itemIdType=ISBN&ItemId=[도서의ISBN]&output=xml&Version=20131101&OptResult=ebookList,usedList,reviewList";
		return null;
	}
	
	@ApiOperation(value = "book의 리뷰 목록", response = String.class)
	@GetMapping("detail/review/{bookId}")
	public Page<Book> getbookreviews(@PathVariable("bookId") int bookId){
		String url = "";
		return null;
	}
	
	public JSONArray JSONParsing(String strurl){
        JSONArray JsonArray=null;
        StringBuffer stringBuffer =new StringBuffer();
        try{
            URL url = new URL(strurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            String stringline;

            while ((stringline = bufferedReader.readLine()) != null) {
                stringBuffer.append(stringline + "\n");
            }

            JSONParser jsonParser=new JSONParser();
            JSONObject jsonObject=(JSONObject) jsonParser.parse(String.valueOf(stringBuffer));
            JsonArray=(JSONArray) jsonObject.get("item");
            httpURLConnection.disconnect();

        }catch (Exception e){
            System.out.println("Parsing error"+e);
        }
        return JsonArray;
    }
}
