package reports;

import daos.TareasDAO;
import models.Alumno;
import models.Tarea;
import parsers.JsoupFileParser;
import recolectors.TareasRecolector;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * @param input Directorio del cual se extraeran los archivos
     */
    public TareasReport(File input) {
        this.input = input;

        tareasRecolector = new TareasRecolector(JsoupFileParser.parseDirectoryFiles(this.input));
        tareasDAO = new TareasDAO(tareasRecolector.getAlumnosTareas());
    }

    public List<String> createReportForEachAlumnos() {

        List<String> templates = new ArrayList<>();

        // Por cada alumno
        for (Alumno al : tareasDAO.getAllAlumnos()) {
            String template = " Alumno: " + al.getNombre() + "\n Email: " + al.getEmail();

            //*/
            // Por cada una de sus materias
            for (String mat : tareasDAO.getMateriasByNombreAlumno(al.getNombre())) {
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
        generateCSVReport();

        return templates;
    }

    public String generateCSVReport() {
        try {
            String currentPath = Paths.get("").toAbsolutePath().normalize().toString();
            String downloadFolder = "/reports";
            String downloadPath = currentPath + downloadFolder;
            File newFolder = new File(downloadPath);
            boolean dirCreated = newFolder.mkdir();

            // get current time
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-dd_HH-mm-ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            String fileName = "Reporte_de_tareas_" + dtf.format(now) + ".csv";

            // Whatever the file path is.
            File statText = new File(downloadPath + "/" + fileName);
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);

            w.write("Alumno, Materia, Tarea, URL-Tarea, Status\n");

            // ESCRITURA ===============================================================================================

            // Por cada alumno
            for (Alumno al : tareasDAO.getAllAlumnos()) {
                String template = al.getNombre();

                for (String mat : tareasDAO.getMateriasByNombreAlumno(al.getNombre())) {
                    List<Tarea> materia_tareas = tareasDAO.getTareasByAlumnoYMateria(al.getNombre(), mat);

                    template += ", " + mat;

                    for (Tarea t : materia_tareas) {

                        String tarea_status = tareasDAO.getStatusTareaByAlumnoAndTarea(al.getNombre(), t);

                        template += ", " + t.getNombre() + ", " + t.getUrlTarea() + ", " + tarea_status;
                        template += "\n, ";

                    }
                    template += "\n";
                }
                //*/
                template += "\n";
                w.write(template);
            }

            w.close();
            return downloadPath + "/" + fileName;
        } catch (IOException e) {
            System.err.println("Problem writing to the file " + e);
        }

        return "error";
    }

    public TareasDAO getTareasDAO() {
        return tareasDAO;
    }
}
