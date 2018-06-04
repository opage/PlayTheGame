package ecom.jonas.managed;

import com.icesoft.faces.component.inputfile.FileInfo;

import java.io.File;

/**
 * <p>The InputFileData Class is a simple wrapper/storage for object that are
 * returned by the inputFile component.  The FileInfo Class contains file
 * attributes that are associated during the file upload process.  The File
 * Object is a standard java.io File object which contains the uploaded
 * file data. </p>
 *
 * @since 1.0
 */
public class InputFileData {

    // file info attributes
    private FileInfo fileInfo;
    // file that was uplaoded
    private File file;
    private String fileName;
   

    /**
     * Create a new InputFileDat object.
     *
     * @param fileInfo fileInfo object created by the inputFile component for
     *                 a given File object.
     */
    public InputFileData(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
        this.file = fileInfo.getFile();
    
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    public boolean delete(){
    	return file.delete();
    }

	
}