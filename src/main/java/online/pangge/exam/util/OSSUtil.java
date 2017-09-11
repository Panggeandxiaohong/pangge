package online.pangge.exam.util;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;


public class OSSUtil {
    private Logger logger = Logger.getLogger(OSSUtil.class);
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String ossEndpoint;
    private int timeout;
    private OSSClient ossClient;


    /**;/l
     * OSS初始化
     */
    public void init() {
        logger.info("begin init...");
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(timeout);
        conf.setMaxErrorRetry(10);
        ossClient = new OSSClient(ossEndpoint, accessKeyId, accessKeySecret,conf);
        logger.info("init end,client is "+ossClient.toString());
    }

    public void destroy() {
        logger.info(ossClient.toString() + " is destory");
        ossClient.shutdown();
    }

    /**
     * 指定的key是否存在
     */
    public boolean isExist(String key) {
        key = genKey(key);
        return ossClient.doesObjectExist(bucketName, key);
    }

    /**
     * 从OSS中获取文件输入流
     */
    public InputStream getObjeInputStream(String key) {
        key = genKey(key);
        OSSObject obj = ossClient.getObject(bucketName, key);
        logger.info("get object input strram is "+ obj.toString());
        return obj.getObjectContent();
    }

    /**
     * 将输入流下载存到指定的File文件中
     */
    public void saveIsToFile(InputStream is, File file) {
        logger.info("begin save file ...");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024 * 10];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            logger.info("save file end ...");
        } catch (FileNotFoundException e) {
            logger.error("have error "+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("have error "+e.getMessage());
            e.printStackTrace();
        } finally {
            IOUtils.safeClose(fos);
            logger.error("close io");
        }
    }

    /**
     * get url by key
     *
     * @param fileKey
     * @return
     */
    public URL getURL(String fileKey) {
        System.out.println("get url key = "+fileKey);
        GeneratePresignedUrlRequest g = new GeneratePresignedUrlRequest(bucketName, fileKey);
        return ossClient.generatePresignedUrl(g);
    }

    /**
     * 文件下载,以流的形式
     */
    public void downObj(String key, File file) {
        key = genKey(key);
        InputStream is = getObjeInputStream(key);
        saveIsToFile(is, file);
    }

    /**
     * 简单上传OSS文件
     *
     * @param name        文件名
     * @param file        File对象
     * @param path        存储路径
     * @param contentType 手动设置文件类型：image/png
     * @return OSS文件Key的路径
     */
    public String uploadObj(String path, String name, File file, String contentType) {
        String key = name;
        key = genKey(key);
        ObjectMetadata meta = null;
        logger.info("begin uoload file " + file.getPath());
        if (contentType != null) {
            logger.info("contentType is not null");
            meta = new ObjectMetadata();
            meta.setContentType(contentType);
        }
        System.out.println(" insert key ======="+key);
        PutObjectResult result = ossClient.putObject(bucketName, key, file);
        logger.info("upload result is " + result.getResponse());
        System.out.println("return url = http://" + bucketName + "." + ossEndpoint + "/" + key);
        return "http://" + bucketName + "." + ossEndpoint + "/" + key;
    }

    public String uploadObj(String path, String name, File file) {
        String uploadKey = uploadObj(path, name, file, null);
        return uploadKey;
    }

    /**
     * 删除指定key
     */
    public void delObj(String key) {
        logger.info("delete :"+key);
        ossClient.deleteObject(bucketName, key);
    }

    /**
     * 处理key开头是/,返回开头没有/的key
     */
    private String genKey(String key) {
        System.out.println("key = "+key);
        if (key != null) {
            key = key.replaceAll("\\\\", "/");
        }
        System.out.println("key !=null " + key);
        while (key != null && key.startsWith("/")) {
            key = key.substring(1, key.length());
        }
        System.out.println("replace key = "+key);
        return key;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getOssEndpoint() {
        return ossEndpoint;
    }

    public void setOssEndpoint(String ossEndpoint) {
        this.ossEndpoint = ossEndpoint;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }
}

