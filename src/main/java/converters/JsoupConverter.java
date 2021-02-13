package converters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsoupConverter {

    public static List<Document> extractDocsFromDirectories(List<File> directories){

        List<Document> documents = new ArrayList<>();

        for (File dir : directories) {

            for (File input : dir.listFiles()) {

                try {
                    Document doc = Jsoup.parse(input, "UTF-8");
                    documents.add(doc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return documents;
    }
}
