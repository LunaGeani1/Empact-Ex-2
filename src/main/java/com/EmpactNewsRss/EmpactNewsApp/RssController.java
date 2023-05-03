package com.EmpactNewsRss.EmpactNewsApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class RssController {

    @Autowired
    private RssService rssService;

    @GetMapping("/feed")
    public String home(Model model) {
        try {
            List<FeedItem> feedItems = rssService.getFeedItems("https://rss.nytimes.com/services/xml/rss/nyt/World.xml");
            model.addAttribute("feedItems", feedItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "rss";
    }
}


