package com.guis.asistencia.customviews;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.guis.asistencia.models.AsistenciaModel;

public class AsistenciaExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// Estableciendo las cabeceras para negociacion de contenido
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader( "Content-disposition", "attachment; filename=" + (String) model.get("sheetname") ); 
		
		// Obteniendo la lista de las asistencia envia desde el controlador
		@SuppressWarnings("unchecked")
		List<AsistenciaModel> asistencias = (List<AsistenciaModel>) model.get("asistentes");
		
		// Construyendo el excel
		Sheet sheet = workbook.createSheet("Asistentes");
		
		Font fuente = workbook.createFont();
		fuente.setColor(IndexedColors.WHITE.getIndex());
		fuente.setBold(true);
		
		// Estilo de la cabecera
		CellStyle estiloCelda = workbook.createCellStyle();
		estiloCelda.setFont(fuente);
		estiloCelda.setAlignment(HorizontalAlignment.CENTER);
		estiloCelda.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		estiloCelda.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Row header = sheet.createRow(0);
		
		
		Cell codigo = header.createCell(0);
		codigo.setCellValue("Código");
		codigo.setCellStyle(estiloCelda);
		sheet.autoSizeColumn(0);
		
		Cell apPaterno = header.createCell(1);
		apPaterno.setCellValue("Apellido Paterno");
		apPaterno.setCellStyle(estiloCelda);
		sheet.autoSizeColumn(1);
		
		Cell apMaterno = header.createCell(2);
		apMaterno.setCellValue("Apellido Materno");
		apMaterno.setCellStyle(estiloCelda);
		sheet.autoSizeColumn(2);
		
		Cell nombres = header.createCell(3);
		nombres.setCellValue("Nombres");
		nombres.setCellStyle(estiloCelda);
		sheet.autoSizeColumn(3);
		
		Cell horaLlegado = header.createCell(4);
		horaLlegado.setCellValue("Hora de llegada");
		horaLlegado.setCellStyle(estiloCelda);
		sheet.autoSizeColumn(4);
		
		
		int rowNum = 1;
		
		// Iterando la lista
		for(AsistenciaModel asistencia : asistencias) {
			
			Row row = sheet.createRow(rowNum);
			
			row.createCell(0).setCellValue(asistencia.getAlumno().getVCodigoA());
			row.createCell(1).setCellValue(asistencia.getAlumno().getVApPaternoA());
			row.createCell(2).setCellValue(asistencia.getAlumno().getVApMaternoA());
			row.createCell(3).setCellValue(asistencia.getAlumno().getVNombres());
			LocalTime horaLlegada = asistencia.getHoraDeLlegada();
			row.createCell(4).setCellValue( (horaLlegada != null) ? horaLlegada.toString() : "Inasistencia");
			
			rowNum++;
		}
	}
}
