package recolectors;

import models.AlumnoTarea;
import org.jsoup.nodes.Document;
import scrappers.TareaScrapper;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TareasRecolector {

    private List<Document> documents;
    private List<TareaScrapper> tareaScrappers;
    private List<AlumnoTarea> alumnosTareas;

    public TareasRecolector(List<Document> documents) {
        this.documents = documents;

        recolectar();
    }

    private void recolectar() {
        if (documents != null || !documents.isEmpty()) {
            tareaScrappers = new ArrayList<>();

            for (Document document : documents) {
                TareaScrapper ts = new TareaScrapper(document);

                if (ts.isAnalizable()) {
                    tareaScrappers.add(ts);
                }
            }

            if (tareaScrappers != null || !tareaScrappers.isEmpty()) {
                alumnosTareas = new ArrayList<>();

                for (TareaScrapper tareaScrapper : tareaScrappers) {
                    List<AlumnoTarea> ats = tareaScrapper.getAlumnosTareas();
                    if (ats != null) {
                        alumnosTareas.addAll(ats);
                    }
                }
            } else if (tareaScrappers.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ha ocurrio un error en el metodo de recoleccion", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (documents.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay documentos que analizar para el metodo de recoleccion", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public List<AlumnoTarea> getAlumnosTareas() {
        return alumnosTareas;
    }
}
