package online.pangge.exam.job;

import online.pangge.exam.util.OSSUtil;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * Created by jie34 on 2017/9/2.
 */
public class uploadFileThread implements Callable<String> {
    private OSSUtil ossUtil=null;
    private File subjectFile = null;
    public uploadFileThread(File inputFile,OSSUtil inputOssUtil){
        ossUtil=inputOssUtil;
        subjectFile=inputFile;
    }
    @Override
    public String call() throws Exception {
        String filename = subjectFile.getName();
        String url = ossUtil.uploadObj(null, filename, subjectFile);
        if(url!=null){
            subjectFile.delete();
        }
        return url;
    }
}
