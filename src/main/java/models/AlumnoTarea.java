package models;

public class AlumnoTarea {

    private Alumno alumno;
    private Tarea tarea;
    private String status;

    public AlumnoTarea() {
    }

    public AlumnoTarea(Alumno alumno, Tarea tarea, String status) {
        this.alumno = alumno;
        this.tarea = tarea;
        this.status = status;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
