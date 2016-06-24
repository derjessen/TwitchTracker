package hello.twitchDB;

import com.mb3364.http.RequestParams;
import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.handlers.ChannelResponseHandler;
import com.mb3364.twitch.api.handlers.TopGamesResponseHandler;
import com.mb3364.twitch.api.handlers.UserResponseHandler;
import com.mb3364.twitch.api.models.Channel;
import com.mb3364.twitch.api.models.TopGame;
import com.mb3364.twitch.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class Controllers {

    @Autowired
    private GamesRepository repository;

    @RequestMapping("/")
    String index(Model model) {

    Twitchbackup tb = repository.findGameByName("League of Legends");
            System.out.println(tb.getId());
            System.out.println(tb.getName());
            System.out.println(tb.getGame_id());
            System.out.println("Insgesamt: "+ tb.getStats().size() + " gespeicherte Stats");

            Object[] chartsData = new Object[tb.getStats().size()];

            for(int i = 0; i < tb.getStats().size(); i++){
                int viewers = tb.getStats().get(i).getViewers();
                Date timestamp = tb.getStats().get(i).getTimestamp();
                LocalDateTime localDateTime = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                chartsData[i] = new Object[] {localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")), viewers};
            }
        model.addAttribute("chartData", chartsData);
        return "index";
    }

    @RequestMapping("/twitch")
    public String twitch() {

        Twitch twitch = new Twitch();
        twitch.setClientId("pf07c0exdacmspfs21hro97tfpq0u6y"); // This is your registered application's client ID

        twitch.channels().get("TV_raGe", new ChannelResponseHandler() {
            @Override
            public void onSuccess(Channel channel) {
                /* Successful response from the Twitch API */
                System.out.println(channel.getGame());
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                /* Twitch API responded with an error message */
            }

            @Override
            public void onFailure(Throwable e) {
                /* Unable to access Twitch, or error parsing the response */
            }
        });

        twitch.users().get("TV_raGe", new UserResponseHandler() {
            @Override
            public void onSuccess(User user) {

                System.out.println(user.getName());
            }

            @Override
            public void onFailure(int i, String s, String s1) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
        RequestParams params = new RequestParams();
        params.put("limit", 50);

        twitch.games().getTop(params ,new TopGamesResponseHandler() {
            @Override
            public void onSuccess(int i, List<TopGame> list) {
                System.out.println(list.size());
                for(TopGame game : list) {
                    System.out.println(game.getGame().getName() + " hat " + game.getViewers() + " Zuschauer");
                }
            }

            @Override
            public void onFailure(int i, String s, String s1) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        return "twitch";
    }
}
