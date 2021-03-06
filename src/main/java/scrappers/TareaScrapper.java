package scrappers;

import models.Alumno;
import models.AlumnoTarea;
import models.Tarea;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TareaScrapper {

    private Document document;

    private Tarea tarea; // Guarda los datos de la tarea analiazda
    private List<Alumno> alumnos; // Almacena los alumnos que tienen asignada la tarea analizada
    private List<AlumnoTarea> alumnosTareas; // Almacena la relacion entre el alumno y sus tareas asignadas

    public TareaScrapper(Document document) {
        this.document = document;

        scrapping();
    }

    private void scrapping() {

        tarea = new Tarea();
        alumnos = new ArrayList<>();
        alumnosTareas = new ArrayList<>();

        try {
            setNombreTarea();
            setSeccionTarea();
            setUrlTarea();
            setMateriaTarea();
            setAlumnos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El archivo no se puede analizar", "Error",  JOptionPane.ERROR_MESSAGE);
        }

    }

    private void setNombreTarea() throws Exception {
        Elements lis_elems = document.select("li.breadcrumb-item");

        for (Element li : lis_elems) {
            Elements a_tag = li.select("a[href*=/assign/]");
            if (a_tag.size() > 0) {
                tarea.setNombre(a_tag.first().text());
                break;
            }
        }
    }

    private void setSeccionTarea() throws Exception {
        Elements lis_elems = document.select("li.breadcrumb-item");

        for (Element li : lis_elems) {

            Elements a_tag = li.select("a[href*=#section]");
            if (a_tag.size() > 0) {
                tarea.setSeccion(a_tag.first().text());
                break;
            }
        }
    }

    private void setUrlTarea() throws Exception {
        Elements lis_elems = document.select("li.breadcrumb-item");

        for (Element li : lis_elems) {
            Elements a_tag = li.select("a[href*=/assign/]");
            if (a_tag.size() > 0) {
                tarea.setUrlTarea(a_tag.first().attr("href"));
                break;
            }
        }
    }

    private void setMateriaTarea() throws Exception {
        Elements h1s = document.getElementsByTag("h1");
        tarea.setMateria(h1s.first().text());
    }

    private void setAlumnos() throws Exception {
        Elements filas_alumnos = document.select("tr[class*=user]");

        int i = 0;
        for (Element tr : filas_alumnos) {

            Element nombre_elem = tr.selectFirst("td.c2");
            Element email_elem = tr.selectFirst("td.email");
            Element status_elem = tr.selectFirst("td.c4");

            alumnos.add(new Alumno(nombre_elem.text(), email_elem.text()));
            alumnosTareas.add(new AlumnoTarea(alumnos.get(i), tarea, status_elem.text()));

            i++;
        }
    }

    public Tarea getTarea() {
        return tarea;
    }

    public List<AlumnoTarea> getAlumnosTareas() {
        return alumnosTareas;
    }

    public boolean isAnalizable() {

        if (alumnos.isEmpty() || alumnosTareas.isEmpty()) {
            return false;
        }

        return true;
    }
}
