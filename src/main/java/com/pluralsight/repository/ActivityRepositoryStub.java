package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
//bunun icinde veriler var veritabanaı baglantısı vb burada hep 
public class ActivityRepositoryStub implements ActivityRepository {

	
	@Override
	public List<Activity> findByDescription(List<String> descriptions) {
		// select * from activities where description in(?,?,?)
		
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity = new Activity();
		activity.setId("2345");
		activity.setDescription("swimming");
		activity.setDuration(55);
		
		activities.add(activity);
		
		return activities;
	}
	
	
	@Override
	public void delete(String activityId) {
		//delete from activity where activity =?
		
		
	}
	
	
	@Override
	public Activity update(Activity activity) {
		// search the db to see if we have an activity with that id already
		//select * from Activity where id = ?
		// if resultset == 0 
		//insert into Activity table
		//else 
		//update the Activity table
		
		//return koyduk cunki clienta değişiklikleri gostermek isteyebiliriz.
		return activity;
		
	}
	
	
	@Override
	public void create(Activity activity) {
		//ctrl-space ile olusturuldu bu method
		//should issue a insert statement to the db
		//post ile gelen seyi db ye kaydettirmeliyim
	}
	
	
	
	
	
	public List<Activity> findAllActivities(){
		
		List<Activity> activities = new ArrayList<Activity>();
		
		//burada ornek calıssın diye birkac tane activity olusturuyoruz ama veritabanından alacak bunları
		Activity activity1=new Activity();
		
		activity1.setDescription("swimming");
		activity1.setDuration(55);
		activities.add(activity1);
		
		Activity activity2=new Activity();
		
		activity2.setDescription("cycling");
		activity2.setDuration(120);
		activities.add(activity2);
		
		return activities;
	}
	
	
	
	//bu altdaki metodu crtl+scapeye basıp ordan olusturduk
	//normalde burada database query leri olması gerekiyor sadece ornek olsun diye byle yazıldı içleri
	//bu method tum activities ler icinden id ye gore işlem yapıyor
	@Override
	public Activity findAllActivity(String activityId) {
		//normalde burada database query leri olması gerekiyor sadece ornek olsun diye byle yazıldı içleri
		
		if(activityId.equals("7777")){
			return null;
		}
		
		
		Activity activity1=new Activity();
		
		activity1.setId("1234");  //kullanıcıya genelde ıd donderilmez ama sadece ornek bu
		activity1.setDescription("swimming");
		activity1.setDuration(55);
		
		
	
		return activity1;
	}
	
	@Override
	public Activity findActivity(String activityId) {
		//normalde burada database query leri olması gerekiyor sadece ornek olsun diye byle yazıldı içleri
		
		Activity activity1=new Activity();
		
		activity1.setId("1234");  //kullanıcıya genelde ıd donderilmez ama sadece ornek bu
		activity1.setDescription("swimming");
		activity1.setDuration(55);
		
		User user =new User();
		user.setId("5678");
		user.setName("bryan");
		
		activity1.setUser(user);
	
		return activity1;
	}





	
}
