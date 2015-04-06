package com.pluralsight.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pluralsight.model.Activity;


//bu class video 60 da baslatılan client yazma kısmını kapsıyor,son kullanıcının yazılımı kullandıgı taraf burası, junit kullanılacakmış
public class ActivityClient {

	
	private Client client; //jerseyden geliyor bu Client nesnesi, biz kodlamadık 
	
	public ActivityClient(){
		//constructoru override edelim
		client =ClientBuilder.newClient();
		
	}
	
	//almak istedigimiz activity yi parametre olarak alan getirici methodu yazalım
	public Activity get(String id){
		
		//sonunda http://localhost:8282/exercise-services/webapi/activities/1234 olacak
		WebTarget target = client.target("http://localhost:8282/exercise-services/webapi/");
		
		//simdi responsumuzu olusturalım
		
		Response response = target.path("activities/"+id).request().get(Response.class);
		
		if(response.getStatus() != 200){
			throw new RuntimeException(response.getStatus()+": there was an error on the server.");
		}
		
		return response.readEntity(Activity.class);
		
		//buradaki request() : temel request dir , diyorki whatever the default type is you want to hand me back,that's what I'll accept. 
		//yani json , xml ne ise belirtiriz, client ne gonderecekse. ama bu bana default type ver diyor
		//Activity.class ise Activity geri verilecegi anlamında kullanılıyor ve activity clası ile baglanıyor
		
		
		//STRİNGİ NEDEN KULLANIRIZ EGER SADECE NE YAPTIGIMIZI GORMEK İSTİYORSAK KONRTOL İCİN BU SEKİLDE KOD YAZABİLİRİZ
		
		//String response = target.path("activities/"+id).request(MediaType.APPLICATION_JSON).get(String.class);
		
		//string yazınca ve juniti ti run edince fail verir, cunki test clasında assetnotnullin icin string degil o yuzden ama onemli değil.
		//calıstırınca consola xml yazar, yani olusturdugumuz 1234 un bilgileri gelir.(json şeklinde, cunki biz json istemiştik)
		//mediatype. applicatipon_json yada xml e gore verinin tipi değişir, AMA BEN SERVİSİ YAZARKEN {APPLICATOIN_JSON, APPLICATION_XML } YAZMADIGIMDAN XML GORUNMEZ SUAN,BUSEKİLDE CLİENTİN ALACAĞI  VERİ TİPİNİ DEĞİŞTİREBİLİRİZ.
		//BELKİDE CLİENT HEM XML HEMDE JSON KULLANACAK OZAMAN HER İKİSİNİDE USTTEKİ SATIRDAKİ GİBİ YAZARIZ
		
		//System.out.println(response);
		//return null;
		
		
		
		//artık clientimizi yazmıs olduk
		//simdi bunu calıstıracak ortamı kuralım pom.xml i acıp junit ekleyelim
		//juniti poma ekledikten sonra ActivityClienta bir junit test case ekleyelim
		//ActivityClient sag tık new > junit test case , eklerken src/test/java eger yok ise build pathden ilerle source bolumunde bu missing yazan dosyayı remove et sonra elle olustur.
		

	}
	
	
	public List<Activity> get(){
		//burada arguman olarka id almayacaz, hepsini liste olarak alıcaz
	
		WebTarget target = client.target("http://localhost:8282/exercise-services/webapi/");
		
		List<Activity> response = target.path("activities").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>() {});
		
		return response;
		
		//burada da listi ekrana yazdırmak istiyorsak, Activity leri String yapmalıyız  tabiki test deki malum metoduda 
	}

	public Activity create(Activity activity) {
		//http://localhost:8282/exercise-services/webapi/activities/activity
		WebTarget target = client.target("http://localhost:8282/exercise-services/webapi/");
		
		Response response = target.path("activities/activity")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(activity, MediaType.APPLICATION_JSON));
		// activity geciriyoruz o yuzden entity de activity yazdık.
		
		//bu post bize response dondurecek
		
		if(response.getStatus() != 200){
			throw new RuntimeException(response.getStatus()+": there was an error on the server.");
		}
		
		return response.readEntity(Activity.class);
	}

	public Activity update(Activity activity) {
		//http://localhost:8282/exercise-services/webapi/activities/3456
		
		WebTarget target = client.target("http://localhost:8282/exercise-services/webapi/");
		
		Response response = target.path("activities/" + activity.getId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(activity, MediaType.APPLICATION_JSON));
		
		
		if(response.getStatus() != 200){
			throw new RuntimeException(response.getStatus()+": there was an error on the server.");
		}
		
		return response.readEntity(Activity.class);
	}

	public void delete(String activityId) {
		
		WebTarget target = client.target("http://localhost:8282/exercise-services/webapi/");
		
		Response response = target.path("/activities/" + activityId).request(MediaType.APPLICATION_JSON).delete();
		
		if(response.getStatus() != 200){
			throw new RuntimeException(response.getStatus()+": there was an error on the server.");
		}
		
		
	}
	
}
