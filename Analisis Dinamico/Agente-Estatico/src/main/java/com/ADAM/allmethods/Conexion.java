package com.ADAM.allmethods;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Conexion {
	
private HashMap<String, HashMap<String, InfoArista>> graph;
	
	public Conexion() {
		super();
		this.graph = new HashMap<>();
	}
	
	 public void openBook(File file){
	        try {
	            FileInputStream fileInputStream = new FileInputStream(file);
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
	    }

	    private void getData(List<List<XSSFCell>> cellDataList, int num){

	        switch (num){
	            case 0:
	                for (int i = 1; i < cellDataList.size(); i++){
	                    List<?> cellTempList = cellDataList.get(i);
	                    String id = cellTempList.get(0).toString();
	                    String label = cellTempList.get(1).toString();
	                    String type = cellTempList.get(2).toString();
	                    String subtype = null;
	                    if(cellTempList.size() > 2){
	                        subtype = cellTempList.get(3).toString();
	                    }
	                    this.addNodo(label);
	                }
	                break;
	            case 1:
	                for (int i = 1; i < cellDataList.size(); i++){
	                    List<?> cellTempList = cellDataList.get(i);
	                    String source = cellTempList.get(0).toString();
	                    String target = cellTempList.get(1).toString();
	                    String label = cellTempList.get(2).toString();
	                    String type = cellTempList.get(3).toString();
	                    this.addArista(source, target, type, label);
	                
	                }
	                break;
	            default:
	                // TODO: agregar error
	        }
	    }
	    
	    public void addNodo(String nombreNodo) {
	    	if(!verificarNodo(nombreNodo)) {
	    		this.graph.put(nombreNodo, new HashMap<>());
	    	}
		}
		
		public void addArista(String keyNodo, String nodoDestino, String tipo, String label) {
			if(verificarNodo(keyNodo)) {
				this.graph.get(keyNodo).put(nodoDestino, new InfoArista(tipo, label));
			}else {
				this.addNodo(keyNodo);
				this.graph.get(keyNodo).put(nodoDestino, new InfoArista(tipo, label));
			}
		}
		
		public boolean verificarNodo(String nombreNodo) {
			return this.graph.containsKey(nombreNodo);
		}
	
	
	
	

}
