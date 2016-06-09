package hello.twitchDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class Controllers {

    @Autowired
    private GamesRepository repository;
    private List<Integer> viewerList = new ArrayList<Integer>();
    private List<Date> dateList = new ArrayList<Date>();

    @RequestMapping("/index")
    String index(@RequestParam String name, Model model) {


        for (Twitchbackup tb : repository.findGameByName("League of Legends")) {
            System.out.println(tb.getId());
            System.out.println(tb.getName());
            System.out.println(tb.getGame_id());
            System.out.println("Insgesamt: "+ tb.getStats().size() + " gespeicherte Stats");

            for(int i = 0; i < tb.getStats().size(); i++){
                //System.out.println(tb.getStats().get(i).getViewers());
                viewerList.add(tb.getStats().get(i).getViewers());
                dateList.add(tb.getStats().get(i).getTimestamp());
//				System.out.println(tb.getStats().get(i).getTimestamp());
            }
        }
        System.out.println(viewerList);
        System.out.println(dateList);

        model.addAttribute("name", name);
        model.addAttribute("viewer", viewerList);
        model.addAttribute("date", dateList);

        return "index";
    }
}
