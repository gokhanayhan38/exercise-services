package com.pluralsight.repository;

import java.util.List;

import com.pluralsight.model.Activity;
// bunu activityrepositorystubı yazdıkdan sonra sagtık > refactor > extract interface ye tıklayıp yaptık 
public interface ActivityRepository {

	List<Activity> findAllActivities();

	Activity findAllActivity(String activityId);

	Activity findActivity(String activityId);

	void create(Activity activity);

	Activity update(Activity activity);

	void delete(String activityId);

	//bu altdaki method search in serverindan kullanılıyor
	List<Activity> findByDescription(List<String> descriptions);

}