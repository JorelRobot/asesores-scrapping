package parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsoupFileParser {

    private File file;

    public static Document parseFile(File file){
        Document document = null;

        if (!file.isDirectory()) {
            try {
                document = Jsoup.parse(file, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return document;
    }

    public static List<Document> parseDirectoryFiles(File directory) {
        List<Document> documents = null;

        if (directory.isDirectory()) {
            documents = new ArrayList<>();

            for (File file : directory.listFiles()){
                Document doc = parseFile(file);

                if (doc != null){
                    documents.add(parseFile(file));
                }
            }

        }

        return documents;
    }

}
