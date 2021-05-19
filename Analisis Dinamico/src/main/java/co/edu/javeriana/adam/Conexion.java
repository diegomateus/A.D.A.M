package co.edu.javeriana.adam;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Conexion {
    private HashMap<String, HashMap<String, InfoArista>> graph;

    public Conexion() {
        super();
        this.graph = new HashMap<>();
    }

    public void openBook(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        openSheet(workbook, 0);
        openSheet(workbook, 1);
    }

    private void openSheet(XSSFWorkbook workbook, int sheet) {
        List<List<XSSFCell>> cellData = new ArrayList<>();

        XSSFSheet hssfSheet = workbook.getSheetAt(sheet);

        Iterator<Row> rowIterator = hssfSheet.rowIterator();

        while (rowIterator.hasNext()) {
            XSSFRow hssfRow = (XSSFRow) rowIterator.next();
            Iterator<Cell> iterator = hssfRow.cellIterator();
            List<XSSFCell> cellTemp = new ArrayList<>();

            while (iterator.hasNext()) {
                XSSFCell hssfCell = (XSSFCell) iterator.next();
                cellTemp.add(hssfCell);
            }
            cellData.add(cellTemp);
        }
        getData(cellData, sheet);
    }

    private void getData(List<List<XSSFCell>> cellDataList, int num) {

        switch (num) {
            case 0:
                for (int i = 1; i < cellDataList.size(); i++) {
                    List<?> cellTempList = cellDataList.get(i);
                    String id = cellTempList.get(0).toString();
                    String label = cellTempList.get(1).toString();
                    String type = cellTempList.get(2).toString();
                    String subtype = null;
                    if (cellTempList.size() > 2) {
                        subtype = cellTempList.get(3).toString();
                    }
                    this.addNodo(id);
                }
                break;
            case 1:
                for (int i = 1; i < cellDataList.size(); i++) {
                    List<?> cellTempList = cellDataList.get(i);
                    String source = cellTempList.get(0).toString();
                    String target = cellTempList.get(1).toString();
                    String label = cellTempList.get(2).toString();
                    String type = cellTempList.get(3).toString();
                    this.addArista(source, target, type, label);
                }
                break;
            default:
                System.out.println("¡La hoja no existe!");
        }
    }

    public void addNodo(String nombreNodo) {
        if (!verificarNodo(nombreNodo)) {
            this.graph.put(nombreNodo, new HashMap<>());
        }
    }

    public void addArista(String keyNodo, String nodoDestino, String tipo, String label) {
        if (!verificarNodo(keyNodo)) {
            this.addNodo(keyNodo);
        }
        if (verificarArista(keyNodo, nodoDestino)) {
            this.graph.get(keyNodo).get(nodoDestino).setContador();
        } else {
            this.graph.get(keyNodo).put(nodoDestino, new InfoArista(tipo, label));
        }
    }

    public boolean verificarNodo(String nombreNodo) {
        return this.graph.containsKey(nombreNodo);
    }

    public HashMap<String, HashMap<String, InfoArista>> getGraph() {
        return graph;
    }

    public HashMap<String, InfoArista> getConexiones(String nodo) {
        return graph.get(nodo);
    }

    public int getSize() {
        return this.graph.size();
    }

    public boolean verificarArista(String nodo, String nodoDestino) {
        return this.graph.get(nodo).containsKey(nodoDestino);
    }
}
