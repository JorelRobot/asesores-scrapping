package reports;

import daos.TareasDAO;
import parsers.JsoupFileParser;
import recolectors.TareasRecolector;

import java.io.File;
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

    public TareasDAO getTareasDAO() {
        return tareasDAO;
    }
}
