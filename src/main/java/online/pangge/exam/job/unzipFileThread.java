package online.pangge.exam.job;

import online.pangge.exam.util.FileUploadUtil;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * Created by jie34 on 2017/9/2.
 */
public class unzipFileThread implements Callable<String> {
    private String descPath = "D:\\unzipSubject";
    private File subjectFile = null;
    public unzipFileThread(File inputFile){
        subjectFile=inputFile;
    }
    @Override
    public String call() throws Exception {
        try {
            FileUploadUtil.unzipFile(subjectFile, descPath);
        }catch (RuntimeException e){
            return e.getMessage();
        }
        return "unzip "+subjectFile.getName();
    }
}
