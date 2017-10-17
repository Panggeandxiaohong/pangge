package online.pangge.exam.util;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Enumeration;
import java.util.UUID;

public class FileUploadUtil {
    private FileUploadUtil() {
    }

    public static String fileUpload(MultipartFile file) {
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        String fileName = null;
        try {
            //获取文件原始名称
            String fileOriginalFilename = file.getOriginalFilename();
            //截取文件类型
            String fileType = fileOriginalFilename.substring(fileOriginalFilename.lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + fileType;
            //获取文件名(随机数)
            HttpServletRequest req = UserContext.getLocal();
            String realPath = req.getSession().getServletContext().getRealPath("/upload");
            File target = new File(realPath, fileOriginalFilename);
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(target);
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "/upload/" + fileName;
    }

    /**
     * 删除文件
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        String path = UserContext.getLocal().getSession().getServletContext().getRealPath("/") + filePath;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    public static File getFile(MultipartFile myfiles){
        CommonsMultipartFile cf= (CommonsMultipartFile)myfiles;
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        return fi.getStoreLocation();
    }

    public static void unzipFile(File srcFile,String descPath){
        try {
            if(!srcFile.exists()&&(srcFile.length()<=0)){
                throw new RuntimeException("the file is not exists");
            }
            ZipFile zipFile = new ZipFile(srcFile,"gbk");
            String strPath,gbkPath,strTemp;
            strPath=descPath;
            Enumeration<ZipEntry> enumeration = zipFile.getEntries();
            while(enumeration.hasMoreElements()){
                ZipEntry zipEnt = enumeration.nextElement();
                gbkPath = zipEnt.getName();
                strTemp = strPath+ File.separator+gbkPath;
                if(zipEnt.isDirectory()){
                    File dir = new File(strTemp);
                    if(!dir.exists()){
                        dir.mkdirs();
                    }
                    continue;
                }else{
                    InputStream in = zipFile.getInputStream(zipEnt);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
                    String strSubdir = gbkPath;
                    for(int i = 0 ; i <strSubdir.length();i++){
                        if("/".equalsIgnoreCase(strSubdir.substring(i,i+1))){
                            String temp = strPath+File.separator+strSubdir.substring(0,i);
                            File subDir = new File(temp);
                            if(!subDir.exists()){
                                subDir.mkdir();
                            }
                        }
                    }
                    FileOutputStream out = new FileOutputStream(strTemp);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
                    int len;
                    byte[] buff = new byte[2014];
                    while((len=bufferedInputStream.read(buff))!=-1){
                        bufferedOutputStream.write(buff,0,len);
                    }
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                }
            }
            zipFile.close();
            System.out.println(srcFile.delete());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
