package reports;

import daos.TareasDAO;
import models.Alumno;
import models.Tarea;
import parsers.JsoupFileParser;
import recolectors.TareasRecolector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TareasReport {

    private List<File> inputs; // Ya sean directorios o archivos
    private File input; // Ya sea directorio o archivo
    private TareasRecolector tareasRecolector;
    private TareasDAO tareasDAO;

    public TareasReport() {
    }

    public TareasReport(List<File> inputs) {
        this.inputs = inputs;
    }

    public TareasReport(File input) {
        this.input = input;

        tareasRecolector = new TareasRecolector(JsoupFileParser.parseDirectoryFiles(this.input));
        tareasDAO = new TareasDAO(tareasRecolector.getAlumnosTareas());
    }

    public List<String> createReportForEachAlumnos(){

        List<String> templates = new ArrayList<>();

        // Por cada alumno
        for (Alumno al : tareasDAO.getAllAlumnos()){
            String template = "Alumno: " + al.getNombre() + "\nEmail: " + al.getEmail();

            //*/
            // Por cada una de sus materias
            for (String mat : tareasDAO.getMateriasByNombreAlumno(al.getNombre())){
                List<Tarea> materia_tareas = tareasDAO.getTareasByAlumnoYMateria(al.getNombre(), mat);

                template += "\n\n\tMateria: " + mat;

                for (Tarea t : materia_tareas) {

                    String tarea_status = tareasDAO.getStatusTareaByAlumnoAndTarea(al.getNombre(), t);
                    template += "\n\n\t\tTarea: " + t.getNombre() + "\n\t\tSeccion: " + t.getSeccion() +
                                "\n\t\tURL Tarea: " + t.getUrlTarea() + "\n\t\tStatus Tarea: " + tarea_status;
                }
            }
            //*/

            templates.add(template);
        }

        return templates;
    }

    public TareasDAO getTareasDAO() {
        return tareasDAO;
    }
}
