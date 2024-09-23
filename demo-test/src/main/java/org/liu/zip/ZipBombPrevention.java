package org.liu.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author lzs
 * @Date 2024/8/20 15:36
 **/
public class ZipBombPrevention {
    // 限制解压缩的总大小（例如：100MB）
    private static final long MAX_UNCOMPRESSED_SIZE = 100 * 1024 * 1024;
    // 限制单个文件的大小（例如：10MB）
    private static final long MAX_SINGLE_FILE_SIZE = 10 * 1024 * 1024;
    // 限制文件数量（例如：1000个文件）
    private static final int MAX_FILES = 1000;

    public static void main(String[] args) {
        File zipFile = new File("example.zip");
        File outputDir = new File("output");
        try {
            unzip(zipFile, outputDir);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void unzip(File zipFile, File outputDir) throws IOException {
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            long totalUncompressedSize = 0;
            int fileCount = 0;
            while ((entry = zis.getNextEntry()) != null) {
                fileCount++;
                if (fileCount > MAX_FILES) {
                    throw new IOException("Too many files in the ZIP archive.");
                }
                File outFile = new File(outputDir, entry.getName());
                // 检查目录穿越漏洞
                if (!outFile.toPath().normalize().startsWith(outputDir.toPath())) {
                    throw new IOException("ZIP entry is outside of the target dir: " + entry.getName());
                }
                if (entry.isDirectory()) {
                    if (!outFile.isDirectory() && !outFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + outFile);
                    }
                    continue;
                }
                long uncompressedSize = extractFile(zis, outFile);
                totalUncompressedSize += uncompressedSize;
                if (uncompressedSize > MAX_SINGLE_FILE_SIZE) {
                    throw new IOException("File too large: " + entry.getName());
                }
                if (totalUncompressedSize > MAX_UNCOMPRESSED_SIZE) {
                    throw new IOException("Total uncompressed size exceeds limit.");
                }
            }
        }
    }

    private static long extractFile(ZipInputStream zis, File outFile) throws IOException {
        long size = 0;
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile))) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = zis.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
                size += len;
                // 实时监控单个文件大小，避免过大
                if (size > MAX_SINGLE_FILE_SIZE) {
                    throw new IOException("Extracted file size exceeds limit: " + outFile.getName());
                }
            }
        }
        return size;
    }
}
