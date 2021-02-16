package daos;

import models.Alumno;
import models.AlumnoTarea;
import models.Tarea;

import java.util.ArrayList;
import java.util.List;

public class TareasDAO {

    List<AlumnoTarea> data; // Todos los datos recolectados de los archivos

    public TareasDAO(List<AlumnoTarea> data) {
        this.data = data;
    }

    public List<Alumno> getAllAlumnos () {

        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(data.get(0).getAlumno());

        for (AlumnoTarea at : data){
            if (!existeAlumno(at.getAlumno(), alumnos)){
                alumnos.add(at.getAlumno());
            }
        }

        return alumnos;
    }

    public List<Tarea> getTareasByNombreAlumno(String nombre) {
        List<Tarea> tareas = new ArrayList<>();
        List<AlumnoTarea> data_alumno = getDataByNombreAlumno(nombre);

        for (AlumnoTarea at : data_alumno){
            tareas.add(at.getTarea());
        }

        return tareas;
    }
    
    public String getStatusTareaByAlumnoAndTarea(String nombre, Tarea tarea) {
        List<AlumnoTarea> data_alumno = getDataByNombreAlumno(nombre);
        String status_tarea = "";

        for (AlumnoTarea at : data_alumno){
            if (at.getTarea().getUrlTarea().equals(tarea.getUrlTarea())){
                status_tarea = at.getStatus();
            }
        }

        return status_tarea;
    }

    public List<Tarea> getTareasByAlumnoYMateria(String nombre, String materia) {
        List<Tarea> tareas = new ArrayList<>();
        List<AlumnoTarea> data_alumno = getDataByNombreAlumno(nombre);

        for (AlumnoTarea at : data_alumno){
            if (at.getTarea().getMateria().equals(materia)) {
                tareas.add(at.getTarea());
            }
        }

        return tareas;
    }

    public List<String> getMateriasByNombreAlumno(String nombre) {
        List<String> materias = new ArrayList<>();
        List<AlumnoTarea> data_alumno = getDataByNombreAlumno(nombre);
        materias.add(data_alumno.get(0).getTarea().getMateria());

        for (AlumnoTarea at : data_alumno){
            if (!existeMatria(at.getTarea().getMateria(), materias)){
                materias.add(at.getTarea().getMateria());
            }
        }

        return materias;
    }

    public List<AlumnoTarea> getDataByNombreAlumno (String nombre) {
        List<AlumnoTarea> alumno_data = new ArrayList<>();

        for (AlumnoTarea at : data){

            if (at.getAlumno().getNombre().equals(nombre)){
                alumno_data.add(at);
            }

        }

        return alumno_data;
    }

    public boolean existeAlumno(Alumno alumno, List<Alumno> alumnos) {

        for (Alumno al : alumnos) {
            if (al.getNombre().equals(alumno.getNombre())){
                return true;
            }
        }

        return false;
    }

    public boolean existeMatria(String materia, List<String> materias) {

        for (String m : materias) {
            if (m.equals(materia)){
                return true;
            }
        }

        return false;
    }

}
