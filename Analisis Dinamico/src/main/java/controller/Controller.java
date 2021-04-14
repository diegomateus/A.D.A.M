package controller;

import model.Edge;
import model.Node;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Controller {

    public List<Edge> edges;
    public List<Node> nodes;
    private final File file;

    public Controller(File file) {
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.file = file;
    }

    public void openBook(){
        try {
            FileInputStream fileInputStream = new FileInputStream(this.file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            openSheet(workbook,0);
            openSheet(workbook,1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openSheet(XSSFWorkbook workbook, int sheet){
        List<List<XSSFCell>> cellData = new ArrayList<>();

        try{
            XSSFSheet hssfSheet = workbook.getSheetAt(sheet);

            Iterator<Row> rowIterator = hssfSheet.rowIterator();

            while(rowIterator.hasNext()){
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator<Cell> iterator = hssfRow.cellIterator();
                List<XSSFCell> cellTemp = new ArrayList<>();

                while (iterator.hasNext()){
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTemp.add(hssfCell);
                }
                cellData.add(cellTemp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        getData(cellData,sheet);
        print();
    }

    private void getData(List<List<XSSFCell>> cellDataList, int num){

        switch (num){
            case 0:
                for (int i = 1; i < cellDataList.size(); i++){
                    List<?> cellTempList = cellDataList.get(i);
                    String id = cellTempList.get(0).toString();
                    String label = cellTempList.get(1).toString();
                    String type = cellTempList.get(2).toString();
                    String subtype = "null";
                    if(cellTempList.size() > 2){
                        subtype = cellTempList.get(3).toString();
                    }
                    nodes.add(new Node(id,label,type,subtype));
                }
                break;
            case 1:
                for (int i = 1; i < cellDataList.size(); i++){
                    List<?> cellTempList = cellDataList.get(i);
                    String source = cellTempList.get(0).toString();
                    String target = cellTempList.get(1).toString();
                    String label = cellTempList.get(2).toString();
                    String type = cellTempList.get(3).toString();
                    edges.add(new Edge(source,target,label,type));
                }
                break;
            default:
                // TODO: agregar error
        }
    }

    public void print(){

        for (Node node : nodes) {
            System.out.println(node.id + " " + node.label + " " + node.type + " " + node.subtype);
        }
        for (Edge edge : edges) {
            System.out.println(edge.source + " " + edge.label + " " + edge.type + " " + edge.target);
        }
    }
}
