package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zuowp291 on 2015/11/16.
 */
public class FileUtils {
    private static final int BUFFER_SIZE = 4096;
    public static final long ONE_KB = 1024L;
    public static final long ONE_MB = 1048576L;
    public static final long ONE_GB = 1073741824L;
    public static final long ONE_TB = 1099511627776L;
    public static final long ONE_PB = 1125899906842624L;
    public static final long ONE_EB = 1152921504606846976L;
    public static final BigInteger ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
    private static final String TAG = "FileUtils";

    /**
     * Writes a file to Disk.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform this operation using another thread.
     *
     * @param file The file to write to Disk.
     */
    public static void writeToFile(File file, String fileContent) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(fileContent);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    public static void writeToFile(File file, byte[] data) {
        OutputStream writer = null;
        try {
            writer = new FileOutputStream(file);
            writer.write(data);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    public static void writeFileToSD(String pathName, String fileName, StringBuilder content) {
        try {
            File path = new File(pathName);
            File file = new File(pathName + fileName);
            if (!path.exists()) {
                path.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(file.length());
            raf.write(content.toString().getBytes());
            raf.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a file to Disk.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform this operation using another thread.
     *
     * @param file The file to write to Disk.
     */
    public static String readFile(String file) {
        return readFile(new File(file));
    }

    private static String readFile(File file) {
        BufferedReader reader = null;
        final char[] buffer = new char[BUFFER_SIZE];
        StringBuilder sb = new StringBuilder((int) file.length());
        try {
            reader = new BufferedReader(new FileReader(file));
            int num;
            while ((num = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, num);
            }
            return sb.toString();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    /***
     * @brief: do not read a big file
     */
    public static byte[] readFileData(File file) {
        InputStream reader = null;
        try {
            if (!file.exists()) return null;
            int len = (int) file.length();
            if (len <= 0 || 1048576 < len) return new byte[0];
            byte[] data = new byte[len];

            reader = new FileInputStream(file);
            if (len == reader.read(data)) {
                return data;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return null;
    }

    public static boolean isFileExists(String fileName) {
        if (fileName == null || (fileName = fileName.trim()).equals("")) {
            return false;
        }
        File file = new File(fileName);
        return isFileExists(file);
    }

    private static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static boolean isDirectory(File file) {
        return file != null && file.isDirectory();
    }

    public static boolean delFile(File file) {
        if (file == null)
            return false;
        if (!isFileExists(file)) {
            return true;
        }
        // 如果是文件夹，返回false
        return !file.isDirectory() && file.delete();
    }

    public static boolean delFileOrDir(File f) {
        if (f.isDirectory()) {
            return delDir(f);
        } else if (f.isFile()) {
            return delFile(f);
        } else {
            return false;
        }
    }

    public static boolean delDir(File dir) {
        if (dir == null)
            return false;
        if (!isFileExists(dir)) {
            return true;
        }
        if (!isDirectory(dir)) {
            return false;
        }
        File[] fl = dir.listFiles();
        if (fl == null)
            return false;

        for (File file : fl) {
            if (file.isFile()) {
                if (!delFile(file)) {
                    return false;
                }
            } else if (file.isDirectory()) {
                if (!delDir(file)) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static byte[] readFileToByte(String fileName) throws IOException {
        File file = new File(fileName);
        InputStream input = new FileInputStream(file);
        byte[] byt = new byte[input.available()];
        input.read(byt);
        input.close();
        return byt;
    }

    /**
     * 确认一个目录存在，如果不存在，则尝试创建此目录。
     *
     * @param path 目录的全路径名
     * @return 如果目录存在，则返回 true，如果无法创建此目录，则返回 false.
     */
    private static boolean makeSurePathExists(String path) {
        File file = new File(path);
        return makeSurePathExists(file);
    }

    /**
     * @see FileUtils#makeSurePathExists(String)
     */
    private static boolean makeSurePathExists(File path) {
        return path != null && (path.isDirectory() || !path.exists() && path.mkdirs());
    }

    public static String readStream(InputStream inputStream) {
        final byte[] buffer = new byte[BUFFER_SIZE];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int count;
        try {
            while ((count = inputStream.read(buffer)) != -1) {
                if (count > 0) {
                    bos.write(buffer, 0, count);
                }
            }
            byte[] data = bos.toByteArray();
            return new String(data, "UTF-8");
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(bos);
        }
    }

    public static void copyFile(File srcFile, File destFile) throws IOException {
        copyFile(srcFile, destFile, true);
    }

    private static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        } else if (destFile == null) {
            throw new NullPointerException("Destination must not be null");
        } else if (!srcFile.exists()) {
            throw new FileNotFoundException("Source \'" + srcFile + "\' does not exist");
        } else if (srcFile.isDirectory()) {
            throw new IOException("Source \'" + srcFile + "\' exists but is a directory");
        } else if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
            throw new IOException("Source \'" + srcFile + "\' and destination \'" + destFile + "\' are the same");
        } else {
            File parentFile = destFile.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Destination \'" + parentFile + "\' directory cannot be created");
            } else if (destFile.exists() && !destFile.canWrite()) {
                throw new IOException("Destination \'" + destFile + "\' exists but is read-only");
            } else {
                doCopyFile(srcFile, destFile, preserveFileDate);
            }
        }
    }

    private static void doCopyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        if (destFile.exists() && destFile.isDirectory()) {
            throw new IOException("Destination \'" + destFile + "\' exists but is a directory");
        } else {
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel input = null;
            FileChannel output = null;

            long size;
            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                input = fis.getChannel();
                output = fos.getChannel();
                size = input.size();
                long pos = 0L;

                for (long count = 0L; pos < size; pos += output.transferFrom(input, pos, count)) {
                    count = size - pos > 31457280L ? 31457280L : size - pos;
                }
            } finally {
                IOUtils.closeQuietly(output);
                IOUtils.closeQuietly(fos);
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(fis);
            }

            if (srcFile.length() != size) {
                throw new IOException("Failed to copy full contents from \'" + srcFile + "\' to \'" + destFile + "\'");
            } else {
                if (preserveFileDate) {
                    destFile.setLastModified(srcFile.lastModified());
                }

            }
        }
    }

    public static File[] fileNameSort(File[] files) {
        if (null == files) {
            return null;
        }
        List<File> fileList = Arrays.asList(files);
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o1.getName().compareTo(o2.getName());
            }
        });
        return files;
    }

    @SuppressWarnings("unchecked")
    public static List<File> listFiles(File directory) {
        if (directory == null || !directory.isDirectory()) {
            return Collections.EMPTY_LIST;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return Collections.EMPTY_LIST;
        }
        List<File> list = new ArrayList<>();
        for (File f : files) {
            if (f.isFile()) {
                list.add(f);
            } else if (f.isDirectory()) {
                list.addAll(listFiles(f));
            }
        }
        return list;
    }

    public static String readAssetsFile(Context c, String filename) {
        InputStream is = null;
        try {
            is = c.getAssets().open(filename);
            return FileUtils.readStream(is);
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            IOUtils.closeQuietly(is);
        }
        return null;
    }

    public static boolean copyAssetsFile(Context c, String assetName, File destFile) {
        InputStream is = null;
        try {
            is = c.getAssets().open(assetName);
            writeStream(is, destFile);
            return true;
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            IOUtils.closeQuietly(is);
        }
        return false;
    }

    public static void writeStream(InputStream stream, File dst) throws IOException {
        File parentDir = dst.getParentFile();
        if (!parentDir.isDirectory() && !parentDir.mkdirs()) {
            throw new IOException("can not make dir:" + parentDir);
        }
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(dst));
            writeStream(stream, bos);
        } finally {
            IOUtils.closeQuietly(bos);
        }
    }

    public static void writeStream(InputStream stream, OutputStream dst) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int size;
        while ((size = stream.read(buffer)) != -1) {
            dst.write(buffer, 0, size);
        }
    }
}
