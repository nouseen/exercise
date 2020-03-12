package com.nouseen.exercise.core_java;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

/**
 * 不支持同一对象并发执行
 *
 * @auth nouseen
 * @since 2020/2/20
 */
public class FileSizeReader {

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 当前主线程
     */
    private Thread mainThread;

    /**
     * 目录数量
     */
    private AtomicLong dirNumber = new AtomicLong(0);

    /**
     * 文件大小合计
     */
    private AtomicLong fileSizeTotal = new AtomicLong(0);

    /**
     * 不需要后停止线程池
     *
     * @auth nouseen
     * @since 2020/2/20
     */
    public static void stopExecutor(){
        executorService.shutdown();
    }


    /**
     * 获取目录中所有文件的字节数合计
     * 单个对象不支持并发执行
     *
     * @auth nouseen
     * @since 2020/2/20
     */
    public long getFileSize(String path) {

        // 处理一下文件目录兼容
        if (path == null) {
            return 0;
        }

        path.replace("\\", File.pathSeparator);
        path.replace("/", File.pathSeparator);

        File file = new File(path);
        if (file == null) {
            return 0;
        }
        mainThread = Thread.currentThread();

        // 如果不是目录直接返回大小
        if (file.isFile()) {
            return file.length();
        }

        // 目录数量加1
        dirNumber.incrementAndGet();

        // 主业务
        creaseNowPathSize(file);

        // 等待线程池完成所有目录
        LockSupport.park();

        long result = fileSizeTotal.get();

        dirNumber.set(0);
        fileSizeTotal.set(0);

        return result;
    }

    private void creaseNowPathSize(File targetFile) {

        executorService.execute(new Runnable() {

            @Override
            public void run() {

                long totalSpace = 0;

                // 处理当前目录下的所有文件，如果是文件，累计大小，如果是目录，递归调用。
                File[] files = targetFile.listFiles();
                if (files != null) {
                    for (File nowFile : files) {
                        if (nowFile.isFile()) {
                            totalSpace += nowFile.length();
                        } else {
                            // 目录数量加1
                            dirNumber.incrementAndGet();

                            // 递归
                            creaseNowPathSize(nowFile);
                        }
                    }
                }

                fileSizeTotal.getAndAdd(totalSpace);

                // 当前目录处理完减1
                long nowDirNumber = dirNumber.decrementAndGet();
                // 所有目录处理完了，唤醒主线程
                if (nowDirNumber == 0) {
                    LockSupport.unpark(mainThread);
                }
            }
        });
    }

    public static void main(String[] args) {
        FileSizeReader fileSizeReader = new FileSizeReader();
        // long fileSize = fileSizeReader.getFileSize("D:\\");
        long fileSize = fileSizeReader.getFileSize("c:/");
        System.out.println(fileSize);
        stopExecutor();
    }
}
