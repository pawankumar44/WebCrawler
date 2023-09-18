package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
//this project or class acts as crawler bot.
public class Crawler {
    HashSet<String> urlSet;//to keep the records of visited url.
    int MAX_DEPTH = 2;//because we are applying the dfs algo with limited depth.
    Crawler(){
        urlSet = new HashSet<String>();
    }
    public void getPageTextAndLinks(String url, int depth){
        if(urlSet.contains(url)){
            return;
        }
        if(depth>MAX_DEPTH){
            return;
        }
        depth++;
        try {
            //parse html page to java object
            //document from jsoup
            //timeout-> if fails to connect url then proceed to next url
            Document document = Jsoup.connect(url).timeout(5000).get();
            //Indexer work start here
            System.out.println(document.title());//checking we are getting the links or not
//          jsoup select element
            //selecting all the link that are present in the document
            Elements availableLinksOnPage = document.select("a[href]");//selecting all of the anchor tags href
            for (Element currentLink : availableLinksOnPage) {
                //convert element object to string object and pass to function recursively
                getPageTextAndLinks(currentLink.attr("abs:href"), depth);
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.getPageTextAndLinks("https://www.javatpoint.com/",1);
    }
}