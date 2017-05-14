package com.example.juan.foodapp.modelo.serviciosPractica;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.juan.foodapp.R;
import com.example.juan.foodapp.modelo.Estudiante;
import com.example.juan.foodapp.modelo.Practica;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import harmony.java.awt.Color;

/**
 * Clase que se encarga de generar el informe de una practica como un archivo PDF.
 */
public class Informe {

    private Document documento;
    private final String CARPETA_DESTINO = "Practicas";
    private String nombreArchivo;
    private Practica practica;
    private Estudiante estudiante;
    private Context contexto;

    public Informe(String nombre, Practica practica, Estudiante estudiante, Context contexto){
        nombreArchivo = nombre + ".pdf";
        this.practica = practica;
        this.estudiante = estudiante;
        this.contexto = contexto;
    }

    /**
     * Se obtiene un nuevo documento para un informe.
     * @return True si la creacion del documento es exitosa, False de lo contrario.
     */
    public boolean obtenerNuevoDocumento(){
        documento = new Document();
        try{
            File fichero = crearFichero(nombreArchivo);
            FileOutputStream ficheroPdf = new FileOutputStream(fichero.getAbsolutePath());
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPdf);
            HeaderFooter cabecera = new HeaderFooter(new Phrase("Informe de Practica"), false);
            HeaderFooter pieDePagina = new HeaderFooter(new Phrase("Universidad de Antioquia"), false);
            documento.setHeader(cabecera);
            documento.setFooter(pieDePagina);
            documento.open();
            return (true);
        } catch (FileNotFoundException | DocumentException e) {
           return (false);
        }
    }

    public boolean configurarPresentacionDocumento(){
        try {
            // TITULO DEL DOCUMENTO
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 28, Font.BOLD, Color.BLACK);
            Paragraph titulo = new Paragraph("INFORME DE PRACTICA DE LABORATORIO", font);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(new Paragraph(titulo));
            documento.add(new Paragraph("_"));
            documento.add(new LineSeparator());

            insertarImagen("FACULTAD DE CIENCIAS FARMACEUTICAS Y ALIMENTARIAS"
                    ,BitmapFactory.decodeResource(contexto.getResources(), R.drawable.escudo));

            // PRESENTACION
            documento.add(new Paragraph("PRACTICA: " + practica.getNombre()));
            documento.add(new Paragraph("LABORATORIO : " + practica.getNombreLaboratorio()));
            documento.add(new Paragraph("ESTUDIANTE : " + estudiante.getNombre()));
            documento.add(new Paragraph("IDENTIFICACION : " + estudiante.getId()));
            documento.add(new Paragraph("CORREO: " + estudiante.getMail()));
            documento.add(new Paragraph("ASIGNATURA: " + practica.getAsignatura()));
            documento.add(new Paragraph("PROFESOR: " + practica.getProfesor()));
            documento.add(new Paragraph("_"));
            documento.add(new LineSeparator());
            return (true);
        }catch (DocumentException e) {
            return(false);
        }
    }

    public boolean insertarDatos(String subTitulo, String[][] datos){
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, Color.BLACK);
        try {
            documento.add(new Paragraph(subTitulo, font));
            documento.add(new Paragraph(""));
            for (int i=0; i<datos.length;i++){
                documento.add(new Paragraph(datos[i][0]+" : "+datos[i][1]));
            }
            documento.add(new Paragraph("_"));
            documento.add(new LineSeparator());
            return (true);
        } catch (DocumentException e) {
            return (false);
        }
    }

    public void cerrarDocumento(){
        documento.close();
    }

    /**
     * Se inserta una imagen en el documento.
     * @param bitmap mapa de bits correspondiente a la imagen que se va a insertar.
     * @param subTitulo Titulo que va a acompañar la imagen.
     * @return True si el proceso de insercion fue exitoso, False de lo contrario.
     */
    public boolean insertarImagen(String subTitulo, Bitmap bitmap){
        try{
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, Color.BLACK);
            documento.add(new Paragraph(subTitulo, font));
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image imagen = Image.getInstance(stream.toByteArray());
            imagen.setAlignment(Image.ALIGN_CENTER);
            documento.add(imagen);
            documento.add(new Paragraph());
            documento.add(new LineSeparator());
            return (true);
        }catch (DocumentException | IOException e) {
            return (false);
        }
    }

    private File crearFichero(String nombreFichero){
        File ruta = getRuta();
        File fichero = null;
        if(ruta != null)
            fichero = new File(ruta, nombreFichero);
        return (fichero);
    }

    private File getRuta(){
        File ruta = null;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            // Ruta de almacenamiento del PDF
            ruta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), CARPETA_DESTINO);
            if (ruta != null){
                if(!ruta.mkdirs()){
                    if(!ruta.exists()) return(null);
                }
            }
        }
        return ruta;
    }
}
