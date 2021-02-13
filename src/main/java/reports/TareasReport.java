package reports;

import converters.JsoupConverter;
import models.AlumnoTarea;
import org.jsoup.nodes.Document;
import scrappers.TareaScrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TareasReport {

    private List<AlumnoTarea> alumnoTareas;
    private List<Document> documents;
    private List<String> reports;

    public TareasReport() {
    }

    public TareasReport(List<File> directorios) {
        this.documents = JsoupConverter.extractDocsFromDirectories(directorios);
        this.alumnoTareas = new ArrayList<>();
        setAlumnoTareas();
    }

    private void setAlumnoTareas() {
        for (Document doc : documents){
            TareaScrapper ts = new TareaScrapper(doc);
            alumnoTareas.addAll(ts.getAlumnosTareas());
        }
    }

    public List<AlumnoTarea> getAlumnoTareas() {
        return alumnoTareas;
    }

    private void createReports() {

    }
}
