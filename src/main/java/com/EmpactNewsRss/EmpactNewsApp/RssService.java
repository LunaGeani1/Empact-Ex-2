package com.EmpactNewsRss.EmpactNewsApp;

import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class RssService {

    public List<FeedItem> getFeedItems(String feedUrl ) throws Exception{
        URL url = new URL(feedUrl);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(url));

        List<FeedItem> feedItems = new ArrayList<>();

        for(SyndEntry entry : feed.getEntries()){
            FeedItem item = new FeedItem();
            item.setTitle(entry.getTitle());
            item.setDescription(entry.getDescription().getValue());
            item.setPubDate(entry.getPublishedDate());
            feedItems.add(item);
        }
        Collections.sort(feedItems, Comparator.comparing(FeedItem::getTitle));
        return feedItems;
    }
}
