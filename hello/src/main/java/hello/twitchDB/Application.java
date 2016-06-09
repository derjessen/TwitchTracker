package hello.twitchDB;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private GamesRepository repository;
	private List<Integer> viewerList = new ArrayList<Integer>();
	private List<Date> dateList = new ArrayList<Date>();


	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "twitchbackup");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Programm läuft!");
//
//		for (Twitchbackup tb : repository.findGameByName("League of Legends")) {
//			System.out.println(tb.getId());
//			System.out.println(tb.getName());
//			System.out.println(tb.getGame_id());
//			System.out.println("Insgesamt: "+ tb.getStats().size() + " gespeicherte Stats");
//
//			for(int i = 0; i < tb.getStats().size(); i++){
//				//System.out.println(tb.getStats().get(i).getViewers());
//				viewerList.add(tb.getStats().get(i).getViewers());
//				dateList.add(tb.getStats().get(i).getTimestamp());
////				System.out.println(tb.getStats().get(i).getTimestamp());
//			}
//		}
//		System.out.println(viewerList);
//		System.out.println(dateList);
	}
}
