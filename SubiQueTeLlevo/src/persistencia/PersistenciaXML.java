package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

<<<<<<< HEAD

public class PersistenciaXML implements Persistencia{
=======
/**
 * Clase encargada de serializar el Sistema
 */
public class PersistenciaXML {
>>>>>>> refs/remotes/main/main
	private FileOutputStream fileOutput;
	private XMLEncoder encoder;
	private FileInputStream fileInput;
	private XMLDecoder decoder;

	public void abrirOutput(String nombre) throws IOException {
		this.fileOutput = new FileOutputStream(nombre);
		this.encoder = new XMLEncoder(this.fileOutput);
	}

	public void abrirInput(String nombre) throws IOException {
		this.fileInput = new FileInputStream(nombre);
		this.decoder = new XMLDecoder(this.fileInput);
	}

	public void cerrarOutput() throws IOException {
		if (this.encoder != null)
			this.encoder.close();
	}

	public void cerrarInput() throws IOException {
		if (this.decoder != null)
			this.decoder.close();
	}

	public void escribir(Object objeto) throws IOException {
		if (this.encoder != null)
			this.encoder.writeObject(objeto);
	}

	public Object leer() throws IOException, ClassNotFoundException {
		Object objeto = null;
		if (this.decoder != null)
			objeto = (Serializable) this.decoder.readObject();
		return objeto;
	}
	
	/*
	 PARA PODER PERSISTIR LOS DATOS
	PersistenciaXML persistencia = new PersistenciaXML();
	persistencia.abrirOutput("Subi_que_te_llevo.xml");
	try {
		persistencia.abrirOutput("Subi_que_te_llevo.xml");
		System.out.println("Crea el archivo de escritura.");
		SistemaDTO sDTO = Util.SistemaDTOFromSistema(aca paso el sistema);
		persistencia.escribir(sDTO);
		System.out.println("Archivo grabado exitosamente.");
		persistencia.cerrarOutput();
		System.out.println("Archivo cerrado.");
	}
	catch (IOException e) {
		System.out.println(e.getLocalizedMessage());
	}*/
	
	/*
	PARA DESPERSISITIR
	PersistenciaXML persistencia = new PersistenciaXML();
	Sistema sistema = null;
	try {
		persistencia.abrirInput("Subi_que_te_llevo.xml");
		System.out.println("Archivo abierto.");
		SistemaDTO sDTO = (SistemaDTO) persistencia.leer();
		sistema = Util.SistemaFromSistemaDTO(sDTO);
		System.out.println("Archivo recuperado.");
		persistencia.cerrarInput();
		System.out.println("Archivo cerrado.");
	}
	catch (IOException e){
		System.out.println(e.getLocalizedMessage());
	}
	catch (ClassNotFoundException e) {
		System.out.println(e.getLocalizedMessage());
	}*/
	
}
