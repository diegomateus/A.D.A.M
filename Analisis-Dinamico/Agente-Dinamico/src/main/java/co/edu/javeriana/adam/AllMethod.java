package co.edu.javeriana.adam;

import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.io.FileOutputStream;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AllMethod {

    public static Stack<String> trace = new Stack<>();
    public static Conexion graph = new Conexion();
    public static Boolean bandera = false;
    public static String nodoAnterior = null;
    public static LocalDateTime time = LocalDateTime.now();
    public static Boolean excelStatic = true;
    public static int enter = 0;
    public static HashMap<String, Integer> trazaCompleta = new HashMap<>();
    public static boolean isTrace = false;

    @Advice.OnMethodEnter
    static void enterMethods(@Advice.Origin String method) {
        if (excelStatic) {
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            File file = new File(path.substring(0, path.length() - 1) + "analisisEstatico.xlsx");
            try {
                graph.openBook(file);
                excelStatic = false;
                System.out.println("Se leyó exitosamente el archivo de análisis estático...");
            } catch (Exception e) {
                System.out.println("¡No existe el directorio solicitado!");
                System.out.println("Exception: " + e.toString());
            }
        }

        String firma = "";
        StringTokenizer st1 = new StringTokenizer(method, " ");
        while (st1.hasMoreTokens()) {
            String aux = st1.nextToken();
            if (aux.contains("(")) {
                StringTokenizer st2 = new StringTokenizer(aux, "(");
                firma = st2.nextToken();
            }
        }

        trace.add(firma);
        System.out.println(firma);
        isTrace = true;
    }

    @Advice.OnMethodExit
    static void getAllMethods() {

        if (trace.size() > 2 && isTrace) {
            String traza = "";
            String origen = trace.firstElement();
            for (String node : trace) {
                traza += node + "\n";
                if (!graph.verificarNodo(node)) graph.addNodo(node);
                if (node != trace.firstElement()) graph.addArista(origen, node, "", "");
                origen = node;
            }

            if (trazaCompleta.containsKey(traza)) {
                int aux = trazaCompleta.get(traza);
                trazaCompleta.replace(traza, aux + 1);
            } else {
                trazaCompleta.put(traza, 1);
            }

            isTrace = false;
            trace.clear();
            System.out.println("%" + (java.time.Duration.between(time, LocalDateTime.now()).toMillis() * 100 / 60000));
        }


        try {

            if (java.time.Duration.between(time, LocalDateTime.now()).toMinutes() > 0) {
                System.out.println("Documento Creado.");

                Workbook workbook = new XSSFWorkbook();

                Sheet sheet = workbook.createSheet("Node");
                Sheet sheet2 = workbook.createSheet("Arista");
                Sheet sheet3 = workbook.createSheet("Traza");

                sheet.setColumnWidth(0, 10000);
                sheet.setColumnWidth(1, 10000);

                sheet2.setColumnWidth(0, 10000);
                sheet2.setColumnWidth(1, 10000);
                sheet2.setColumnWidth(2, 10000);
                sheet2.setColumnWidth(3, 10000);

                sheet3.setColumnWidth(0, 10000);
                sheet3.setColumnWidth(1, 10000);

                Row header = sheet.createRow(0);
                Row header2 = sheet2.createRow(0);
                Row header3 = sheet3.createRow(0);

                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                XSSFFont font = ((XSSFWorkbook) workbook).createFont();
                font.setFontName("Arial");
                font.setFontHeightInPoints((short) 14);
                font.setBold(true);
                headerStyle.setFont(font);

                Cell headerCell = header.createCell(0);
                headerCell.setCellValue("Nodes");
                headerCell.setCellStyle(headerStyle);

                headerCell = header2.createCell(0);
                headerCell.setCellValue("source");
                headerCell.setCellStyle(headerStyle);

                headerCell = header2.createCell(1);
                headerCell.setCellValue("Target");
                headerCell.setCellStyle(headerStyle);

                headerCell = header2.createCell(2);
                headerCell.setCellValue("#Call");
                headerCell.setCellStyle(headerStyle);

                headerCell = header2.createCell(3);
                headerCell.setCellValue("label");
                headerCell.setCellStyle(headerStyle);

                headerCell = header2.createCell(4);
                headerCell.setCellValue("Type");
                headerCell.setCellStyle(headerStyle);

                headerCell = header3.createCell(0);
                headerCell.setCellValue("Traza");
                headerCell.setCellStyle(headerStyle);

                headerCell = header3.createCell(1);
                headerCell.setCellValue("Calls");
                headerCell.setCellStyle(headerStyle);

                CellStyle style = workbook.createCellStyle();
                style.setWrapText(true);

                int i = 2, j = 2;
                for (Entry<String, HashMap<String, InfoArista>> entry : graph.getGraph().entrySet()) {
                    Row row = sheet.createRow(i);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(entry.getKey());
                    cell.setCellStyle(style);
                    for (Map.Entry<String, InfoArista> entries : graph.getConexiones(entry.getKey()).entrySet()) {
                        Row row2 = sheet2.createRow(j);
                        Cell cell2 = row2.createCell(0);
                        cell2.setCellValue(entry.getKey());
                        cell2.setCellStyle(style);

                        cell2 = row2.createCell(1);
                        cell2.setCellValue(entries.getKey());
                        cell2.setCellStyle(style);

                        cell2 = row2.createCell(2);
                        cell2.setCellValue(String.valueOf(entries.getValue().getContador()));
                        cell2.setCellStyle(style);

                        cell2 = row2.createCell(3);
                        cell2.setCellValue(entries.getValue().getLabel());
                        cell2.setCellStyle(style);

                        cell2 = row2.createCell(4);
                        cell2.setCellValue(entries.getValue().getTipo());
                        cell2.setCellStyle(style);
                        j++;
                    }
                    i++;
                }
                int k = 2;
                for (Entry<String, Integer> entry : trazaCompleta.entrySet()) {
                    Row row3 = sheet3.createRow(k);

                    Cell cell3 = row3.createCell(0);
                    cell3.setCellValue(entry.getKey());
                    cell3.setCellStyle(style);

                    cell3 = row3.createCell(1);
                    cell3.setCellValue(entry.getValue());
                    cell3.setCellStyle(style);
                    k++;
                }

                File currDir = new File(".");
                String path = currDir.getAbsolutePath();
                String fileLocation = path.substring(0, path.length() - 1) + "Analisis_Dinamico.xlsx";

                FileOutputStream outputStream = new FileOutputStream(fileLocation);
                workbook.write(outputStream);
                workbook.close();

                time = LocalDateTime.now();

            }
            trace.clear();

        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
    }
}

