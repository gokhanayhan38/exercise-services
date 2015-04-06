package com.pluralsight.client;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pluralsight.model.Activity;

public class ActivityClientTest {

	//TEST (JUNİT) in önemi sudur, sadece kontrol icin bu yapı kullanılıyor, yazdıgımız test ile bir veri akısı yada baglantı gercekleştirilmiyor, ayrıca bu clientde değildir
	//bu sadece serverdan gelen verinin dogru gelip gelmediğini kontrol ediyor.ve otomatik olarak yazılımcı testi yaparken elle veri girmeden burda yazılan parametreler ile test yapıyor
	
	
	//search get i icin ornek
	@Test
	public void testSearch(){
		ActivitySearchClient client = new ActivitySearchClient();
		
		String param = "description";
		List<String> searchValues = new ArrayList<String>();
		
		searchValues.add("swimming");
		searchValues.add("running");
		
		List<Activity> activities = client.search(param, searchValues);
		System.out.println(activities);
		
		assertNotNull(activities);
	}
	
	
	
	
	//delete icin örnek
	@Test
	public void testDelete(){
		ActivityClient client = new ActivityClient();
		
		client.delete("1234");
		
	}
	
	
	
	
	
	
	//put icin ornek - tum put methodlarını ıd ile kullanırız.
	@Test
	public void testPut(){
		//biseyleri store etmek icin activity objesi alalım
		Activity activity = new Activity();
		
		activity.setId("3456");
		activity.setDescription("bikram yoga");
		activity.setDuration(90);
		
		ActivityClient client = new ActivityClient();
		
		activity = client.update(activity);
		
		assertNotNull(activity);
		
		
	}
	
	
	
	
	@Test
	public void testCreate(){
		ActivityClient client = new ActivityClient();
		
		Activity activity= new Activity();
		activity.setDescription("Swimming");
		activity.setDuration(90);
		//burada ıd belirtmek sacma olur cunki post yapıyoruz, id yi server olusturacek
		
		activity=client.create(activity);
		
		assertNotNull(activity);
		
	}
	
	@Test
	public void testGet() {
		ActivityClient client = new ActivityClient(); //ActivityClientin icinde jerseyden gelen bvir client nesnesi vardı onu kullanıyor, nesnelerin isimleri aynı olsada biri jerseyin clienti , diğeri activityClientin nesnesi (icindeki jersey clienti da dahil olmak üzere)
		
		Activity activity =client.get("1234");
		
		System.out.println(activity);
		
		//and then we have to, for our test to succeed, we have to do an assertion(iddia,sav,hak arama) at the end of it
		assertNotNull(activity);
		//static importları kendimiz eklemeliyiz. import static org.junit.Assert.*;
	//serveri calıstır ve ,  And now we can right click on this Test. So if I right click on this Test method, I can say Run As, JUnit Test.
		
	
	}
	
	@Test
	public void testGetList(){
		ActivityClient client = new ActivityClient();
		
		List<Activity> activities = client.get();
		
		System.out.println(activities);
		assertNotNull(activities);
		//burada bir hata(get(list.class) calısmaz) verecek , junit calıstırınca , video 63 bunun cozumunu veriyor.
		
 		
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetWithBadRequest(){
		ActivityClient client = new ActivityClient();
		client.get("123"); //z4 den o yuzden hata gelecek
	}
	
	
	@Test(expected=RuntimeException.class)
	public void testGetWithNotFound(){
		ActivityClient client = new ActivityClient();
		client.get("7777"); //z4 den o yuzden hata gelecek
	}
	

}
