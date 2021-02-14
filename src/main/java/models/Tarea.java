package models;

public class Tarea {

    private String nombre;
    private String seccion;
    private String urlTarea;
    private String materia;

    public Tarea () {

    }

    public Tarea(String nombre, String seccion, String urlTarea, String materia) {
        this.nombre = nombre;
        this.seccion = seccion;
        this.urlTarea = urlTarea;
        this.materia = materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getUrlTarea() {
        return urlTarea;
    }

    public void setUrlTarea(String urlTarea) {
        this.urlTarea = urlTarea;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
