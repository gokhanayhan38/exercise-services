package com.pluralsight;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import com.pluralsight.model.Activity;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

@Path("search/activities")
public class ActivitySearchResource {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchForActivities(@QueryParam(value = "description") List<String> descriptions) {
		// we're going to do something a little different than we have before. Since we've used queries doing a GET,
		//but we haven't actually grabbed a query parameter off of our Path. Let's do a @QueryParam, 
		//and grab the value equal to description. Now where this varies from what we've done in the past, 
		//is we're going to grab a List of type String, and those are our descriptions
		System.out.println(descriptions);
		
		List<Activity> activities = activityRepository.findByDescription(descriptions);
		
		if(activities == null || activities.size() <=0 ){
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();
	}
	
	
}
