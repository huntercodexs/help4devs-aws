package com.huntercodexs.demo.resource;

import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Properties;

import static com.huntercodexs.demo.resource.Help4DevsPathService.sanitizePath;

@Slf4j
@Service
public class Help4DevsFileHandlerService {

    /** Legacy */
    public static InputStream bytesFileExtractor(String targetPath, String targetFile) {
        String content = fileToString(targetPath, targetFile);
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    /** Legacy */
    public static InputStream fileToByteArray(String targetPath, String targetFile) {
        String content = fileToString(targetPath, targetFile);
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    /** Legacy */
    /*public static ByteArrayDataSource fileToDataSource(String targetPath, String targetFile) throws IOException {
        InputStream fileArray = fileToByteArray(targetPath, targetFile);
        return new ByteArrayDataSource(byteConvert(fileArray), "application/octet-stream");
    }*/

    /** Legacy */
    public static String fileInputStream(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        return IOUtils.toString(fis);
    }

    /** Legacy */
    public static boolean exists(String filepath) {
        try {
            File file = new File(filepath);
            return file.exists();
        } catch (Exception e) {
            //errLog(e.getMessage());
            return false;
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">loadProps</h6>
     *
     * <p style="color: #CDCDCD">Get all data from application properties file passed in the classpath parameter</p>
     *
     * @param classpath (String: The path where the application properties file is placed)
     * @return Properties (Application Properties Details)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Properties loadProps(String classpath) {
        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile(classpath);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }

        return properties;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">byteConvert</h6>
     *
     * <p style="color: #CDCDCD">Convert the InputStream in the bytes</p>
     *
     * @param fileArray (InputStream: File Content in Array Format)
     * @return byte (The file content un bytes)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static byte[] byteConvert(InputStream fileArray) throws IOException {
        ByteArrayOutputStream bytArrayOutputStream = new ByteArrayOutputStream();
        bytArrayOutputStream.write(fileArray.read());
        return bytArrayOutputStream.toByteArray();
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">fileToString</h6>
     *
     * <p style="color: #CDCDCD">Get the content file in the string format</p>
     *
     * @param targetPath (String: Pathname target)
     * @param targetFile (String: Filename target)
     * @return String (File Content)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String fileToString(String targetPath, String targetFile) {

        StringBuilder fileContent = new StringBuilder();

        File path = new File(sanitizePath(targetPath));
        File file = new File(path, targetFile);

        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while ( ( line = bufferedReader.readLine() ) != null) {
                fileContent.append(line).append("\n");
            }

            return fileContent.toString();

        } catch (IOException e) {
            throw new RuntimeException("[EXCEPTION] FILE READER: " + e.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">fileToArray</h6>
     *
     * <p style="color: #CDCDCD">Convert a txt file into an array</p>
     *
     * @param targetPath (String: Pathname target)
     * @param targetFile (String: Filename target)
     * @return ArrayList (File Content in ArrayList)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static ArrayList<String> fileToArray(String targetPath, String targetFile) {

        ArrayList<String> arrayFile = new ArrayList<>();

        File path = new File(sanitizePath(targetPath));
        File file = new File(path, targetFile);

        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while ( ( line = bufferedReader.readLine() ) != null) {
                arrayFile.add(line);
            }

            return arrayFile;

        } catch (IOException e) {
            throw new RuntimeException("[EXCEPTION] FILE READER: " + e.getMessage());
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">byteFile</h6>
     *
     * <p style="color: #CDCDCD">Read a file and get the content in bytes format</p>
     *
     * @param filenamePath (String: The absolute filename path to open and read the file)
     * @return byte[] (File Content in bytes)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static byte[] byteFile(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return IOUtils.toByteArray(fis);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">ioFile</h6>
     *
     * <p style="color: #CDCDCD">Read a file and get the content in string format</p>
     *
     * @param filenamePath (String: The absolute filename path to open and read the file)
     * @return String (File Content)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String ioFile(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return IOUtils.toString(fis);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">binFile</h6>
     *
     * <p style="color: #CDCDCD">Read a file and get the content in binary format</p>
     *
     * @param filenamePath (String: The absolute filename path to open and read the file)
     * @return String (File Binary Content)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String binFile(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return new String(IOUtils.toByteArray(fis), StandardCharsets.UTF_8);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">folderCreate</h6>
     *
     * <p style="color: #CDCDCD">Create a folder according to path parameter</p>
     *
     * @param path (String: The current path to create folder)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean folderCreate(String path) {
        try {

            File file = new File(path);

            if (file.mkdirs()) {
                return true;
            }

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Folder not created: " + ex.getMessage());
        }

        System.out.println("ERROR: Folder not created: " + path);
        return false;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">fileList</h6>
     *
     * <p style="color: #CDCDCD">List files in the specific directory according to filepath parameter</p>
     *
     * @param filePath (String: The current path to list files)
     * @param regExpFilter (String: The regular expression to match files)
     * @return String[]
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String[] fileList(String filePath, String regExpFilter) {
        File file = new File(filePath);

        return file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().matches(regExpFilter);
            }
        });
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">fileDelete</h6>
     *
     * <p style="color: #CDCDCD">Delete on file or directory simply passing the path in the parameters</p>
     *
     * @param path (String: The current file or directory path to delete)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean fileDelete(String path) {
        try {

            File file = new File(path);

            if (file.exists()) {
                if (file.delete()) {
                    return true;
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] File not deleted: " + ex.getMessage());
        }

        System.out.println("ERROR: File not deleted: " + path);
        return false;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">fileMove</h6>
     *
     * <p style="color: #CDCDCD">Rename on file or directory simply passing the old and new path in the parameters</p>
     *
     * @param path (String: The current file or directory path)
     * @param newPath (String: The new path that should be applied in the file or directory target)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean fileMove(String path, String newPath) {
        try {

            File file = new File(path);
            File newFile = new File(newPath);

            if (file.exists()) {
                if (file.renameTo(newFile)) {
                    return true;
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] File not moved: " + ex.getMessage());
        }

        System.out.println("ERROR: File not moved: " + path);
        return false;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">fileWriter</h6>
     *
     * <p style="color: #CDCDCD">Write in some file according data parameters, if the process not end up with success
     *      then one exception will be thrown
     * </p>
     *
     * @param data (byte[]: The content in bytes to write in the target file)
     * @param path (String: The path where the file to write is located)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean fileWriter(byte[] data, String path) {
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(data);
            fileOutputStream.close();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Is not possible to write in the file: " + ex.getMessage());
        }

        return true;
    }

}
