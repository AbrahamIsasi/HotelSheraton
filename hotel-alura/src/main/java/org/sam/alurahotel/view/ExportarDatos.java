package org.sam.alurahotel.view;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sam.alurahotel.modelo.Huesped;
import org.sam.alurahotel.modelo.Reserva;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportarDatos {

    public void exportarExcelReservas(List<Reserva> reservas) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reservas");

        // Crear la fila de encabezado para Reservas
        Row headerRow = sheet.createRow(0);
        String[] encabezados = {"ID Reserva", "Fecha Entrada", "Fecha Salida", "Valor", "Tipo Habitacion", "Forma de Pago"};
        for (int i = 0; i < encabezados.length; i++) {
            headerRow.createCell(i).setCellValue(encabezados[i]);
        }

        // Llenar los datos de reservas
        int rowNum = 1;
        for (Reserva reserva : reservas) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(reserva.getId());
            row.createCell(1).setCellValue(reserva.getFechaEntrada().toString());
            row.createCell(2).setCellValue(reserva.getFechaSalida().toString());
            row.createCell(3).setCellValue(reserva.getValor());
            row.createCell(4).setCellValue(reserva.getTipoHabitacion());
            row.createCell(5).setCellValue(reserva.getFormaPago());
        }

        // Guardar el archivo
        try (FileOutputStream fileOut = new FileOutputStream("Reservas.xlsx")) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }

    public void exportarExcelHuespedes(List<Huesped> huespedes) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Huespedes");

        // Crear la fila de encabezado para Huespedes
        Row headerRow = sheet.createRow(0);
        String[] encabezados = {"ID Huesped", "Nombre", "Apellido", "Fecha Nacimiento", "Nacionalidad", "Telefono", "ID Reserva"};
        for (int i = 0; i < encabezados.length; i++) {
            headerRow.createCell(i).setCellValue(encabezados[i]);
        }

        // Llenar los datos de huespedes
        int rowNum = 1;
        for (Huesped huesped : huespedes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(huesped.getId());
            row.createCell(1).setCellValue(huesped.getNombre());
            row.createCell(2).setCellValue(huesped.getApellido());
            row.createCell(3).setCellValue(huesped.getFechaNacimiento().toString());
            row.createCell(4).setCellValue(huesped.getNacionalidad());
            row.createCell(5).setCellValue(huesped.getTelefono());
            row.createCell(6).setCellValue(huesped.getIdReserva());
        }

        // Guardar el archivo
        try (FileOutputStream fileOut = new FileOutputStream("Huespedes.xlsx")) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }
}
