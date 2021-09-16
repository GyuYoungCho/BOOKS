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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.book.dao.BookDao;
import com.project.book.dao.UserDao;
import com.project.book.dao.UserLogDao;
import com.project.book.dto.Book;
import com.project.book.dto.BookDetail;
import com.project.book.dto.Bookapi;
import com.project.book.dto.User;
import com.project.book.dto.UserLog;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserLogDao userlogDao;
	
	Bookapi api;
	String key = new Bookapi().getKey();
	
	@ApiOperation(value = "단어를 포함한 book 검색", response = String.class)
	@GetMapping("/search")
	public Page<Book> getBookSearchList(@RequestParam("keyword") String keyword ,final Pageable pageable) throws NumberFormatException, ParseException{
		keyword = "%" + keyword + "%";
		Page<Book> bookpage = bookDao.findByTitleKeyword(keyword, pageable);
		List<Book> booklist = bookpage.toList();
		return new PageImpl<Book>(booklist, pageable, bookpage.getTotalElements());
	}
	
	@ApiOperation(value = "book의 상세 정보", response = String.class)
	@GetMapping("/detail/{bookId}/{userId}")
	public ResponseEntity<BookDetail> bookdetail(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId) throws NumberFormatException, ParseException{
		
		Book book = bookDao.getByBookId(bookId);
		
		String itemurl = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?ttbkey="+ key + 
				"&itemIdType=ISBN&ItemId="+ book.getIsbn() + "&output=js&Version=20131101";
		JSONArray jsonArray=JSONParsing(itemurl);
		
		if (jsonArray.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
		JSONObject data=(JSONObject) jsonArray.get(0);
		SimpleDateFormat dateparse = new SimpleDateFormat("yyyy-mm-dd");
		
		JSONObject subinfodata=(JSONObject) data.get("subInfo");
		
		BookDetail bookdetail =new BookDetail(1,
                data.get("title").toString(),
                data.get("link").toString(),
                data.get("author").toString(),
                dateparse.parse(data.get("pubDate").toString()),
                data.get("description").toString(),
                Integer.parseInt(data.get("priceSales").toString()),
                Integer.parseInt(data.get("priceStandard").toString()),
                Integer.parseInt(data.get("mileage").toString()),
                data.get("mallType").toString(),
                data.get("cover").toString(),
                Integer.parseInt(data.get("categoryId").toString()),
                data.get("publisher").toString(),
                Integer.parseInt(subinfodata.get("itemPage").toString())
        );
		
		if(bookdetail!=null && userId!=0) {
			User user = userDao.getByUserId(userId);
			UserLog log =  new UserLog(0,new Date(),user,book);
			userlogDao.save(log);
		}
		
		
		return new ResponseEntity<>(bookdetail, HttpStatus.OK);
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
