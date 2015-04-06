package com.pluralsight;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

//burası bizim servisimiz olacak (server tarafı)
@Path("activities") //localhost:8080/exercise-services/webapi/activities
public class ActivityRecource {

	private ActivityRepository activityRepository =new ActivityRepositoryStub();
	
	
	//delete
	@DELETE
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam ("activityId") String activityId){
		
		System.out.println(activityId);
		
		activityRepository.delete(activityId);
		
		return Response.ok().build();
		
	}
	
	
	
	//put metodu olmayan kaydı olusturur olanı update eder, idempotentdir, updatelerde kullanılmalıdır.
	@PUT
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Activity activity){
		
		//debug icin ifade yazalım
		System.out.println(activity.getId());
		
		//simdi stubu cagıralım zaten clasın ustunde nesne olusturmustuk onu kullanıcaz
		activity = activityRepository.update(activity); // update methodu yokyu activityrepositorystubda bunu oluşturucaz
		//activitye esitledin sadece client gormek isterse diye.
		//bu kullanıcıya donecek olan response
		return Response.ok().entity(activity).build();
	}
	
	
	@POST
	@Path("activity")    //http://localhost:8282/exercise-services/webapi/activities/activity
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	public Activity createActivity(Activity activity){
		// burada video 55 , We got our object now bound to the parameters that we're sending in.
		//artık kısaltma yaptıgımız desc i kullanmalıyız ama ilk post örneginde descriptionu kullanmıstık
		
		
		//sadece kontrol icin syso yazalım , sadece calısıp calısmadıgını anlamak icin syso ların hic bir hukmu yok
		System.out.println(activity.getDescription());
		System.out.println(activity.getDuration());
		
		activityRepository.create(activity);
		
		return activity;
	}
	
	
	
	@POST
	@Path("activity")   //localhost:8080/exercise-services/webapi/activities/activity   bunu kullanarak post kullanıcaz
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //bu bunu temsil eder application/x-www-form-urlencoded bu temel bir html formudur
	@Produces(MediaType.APPLICATION_JSON)
	public Activity createActivityParams(MultivaluedMap<String, String> formParams){
		//buraya kadar tum temel elemanlara sahip olduk artık postun icini kodlayabiliriz.
		
		System.out.println(formParams.getFirst("description"));
		System.out.println(formParams.getFirst("duration"));
		
		//chromedaki postmande video 53 u uygula ordan send yapacagız cunki
		
		Activity activity=new Activity();
		activity.setDescription(formParams.getFirst("description"));
		activity.setDuration(Integer.parseInt(formParams.getFirst("duration"))); //burası parse edildi cunki duration int
		
		activityRepository.create(activity); 
		
		return activity;
	}
	
	//http://localhost:8282/exercise-services/webapi/activities   calısır tum activities leri alır
	@GET
	@Produces(MediaType.APPLICATION_JSON) //json pom.xml de bir jar olmasını ister onu unutmamalıyız(jersey-media-moxy jarı lazım)pomdaki uncomment kısmını sil //@Produces(MediaType.APPLICATION_XML)
	public List<Activity> getAllActivities(){
		return activityRepository.findAllActivities();
	}
	
	
	
	//jersey jaxb ile pojomuzu  xml e donusturur sonrada xml den json a donusturulur.xml annotationlarımız json outputunu etkiler.
	//ornegin xmlelement ile desc yazmıstık o jsondada desc olarak kalır ,artık json nesnesi desc kısaltması ile olusturulur. 
	
	//bu alttaki ornegin http error kodları olmayan hali untitled2.txt nin icinde var.
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	@Path("{activityId}")//localhost:8080/exercise-services/webapi/activities/1234
	public Response getActivity(@PathParam ("activityId") String activityId){
		
		if(activityId==null || activityId.length()<4){
			//eger bana valid ıd yada uzunlugu 4 den buyuk olan bisey gelmezse return olarak bad_request statursu gidecek , 300 301
			return Response.status(Status.BAD_REQUEST).build(); // bu bize eger clientdan badrequest gelirse bize bir error message build edecek
		}
		
		//yukarı takılmazsa buraya gecer ve alt satırdaki kod ile activity yi buluruz 
		Activity activity = activityRepository.findAllActivity(activityId);
		if(activity == null){
			//eger activity miz null ise (yani elimizde olmayan bir zıkımı istiyor ise bizden kullanıcı) 404 error gondeririz.
			return Response.status(Status.NOT_FOUND).build(); // 404 mesajı donrerir
		}
		
		return Response.ok().entity(activity).build();
		
		//burdan sonra clienta clasına gecip degişiklikler yapıcaz.
	}
	//@PathParam("almak istedigimiz seyi yazıyoruz icine") String activityId    bu sayede url den parametremizim yakalayabiliriz
	//1234 u alır String activityId de saklar.
	//bu getactivity metodunu kullanıcı dostu yapmalıyız ondelikle ,video 66
	//"Activity" geri donus tipi yerine "Response" yazalım, tum methodda bu degişikliği yapmalıyız.
	
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	@Path("{activityId}/user")//localhost:8080/exercise-services/webapi/activities/1234/user
	public User getActivityUser(@PathParam ("activityId") String activityId){
		
		Activity activity = activityRepository.findActivity(activityId);
		User user = activity.getUser();
		return user;
		
		//return activityRepository.findActivity(activityId).getUser();  //buda dogrudur nested retrieve diye gecer
		
		// burada tum {"desc":"swimming","duration":55,"id":"1234","user":{"id":"5678","name":"bryan"}} bu veriyi gondermek isteseydin user degilde activity gondermek zorundaydık, yani ustteki method gibi
		
		
	}
	
}
