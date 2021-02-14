package recolectors;

import models.AlumnoTarea;
import org.jsoup.nodes.Document;
import scrappers.TareaScrapper;

import java.util.ArrayList;
import java.util.List;

public class TareasRecolector {

    private List<Document> documents;
    private List<TareaScrapper> tareaScrappers;
    private List<AlumnoTarea> alumnosTareas;


    public TareasRecolector(List<Document> documents) {
        this.documents = documents;

        recolectar();
    }

    private void recolectar() {
        if (documents != null) {
            tareaScrappers = new ArrayList<>();

            for (Document document : documents) {
                TareaScrapper ts = new TareaScrapper(document);
                tareaScrappers.add(ts);
            }
        }

        if (tareaScrappers != null) {
            alumnosTareas = new ArrayList<>();

            for (TareaScrapper tareaScrapper : tareaScrappers) {
                List<AlumnoTarea> ats = tareaScrapper.getAlumnosTareas();
                if (ats != null) {
                    alumnosTareas.addAll(ats);
                }
            }
        }
    }

    public List<AlumnoTarea> getAlumnosTareas() {
        return alumnosTareas;
    }
}
