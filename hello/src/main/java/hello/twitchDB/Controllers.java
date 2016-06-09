package hello.twitchDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
}
