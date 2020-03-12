package com.nouseen.exercise.core_java;

import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

public class FileUtilTest {

    private static Thread mainThread;


    private static AtomicLong dirNumber = new AtomicLong(0);
    private static AtomicLong fileSizeTotal = new AtomicLong(0);


    @Test
    public void testGetFileSize() throws ExecutionException, InterruptedException {
        final long start = System.nanoTime();

        String path = "D:\\weather";
        File file = new File(path);
        mainThread = Thread.currentThread();

        // 如果不是目录直接返回大小
        if (file.isFile()) {
            System.out.println(file.length());
            return;
        }
        // 目录数量加1
        dirNumber.incrementAndGet();

        fileSize(file);
        LockSupport.park();

        final long end = System.nanoTime();
        System.out.println("Total Size: " + fileSizeTotal);
        System.out.println("Time taken: " + (end - start) / 1.0e9);
    }

    @Test
    public void testGetFileSize1() {
        String path = "D:\\weather\\nmc-1周温度预报-2020-02-14-12-29-25.xls";
        fileSize(new File(path));

    }
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void fileSize(File targetFile) {

        executorService.execute(new Runnable() {

            @Override
            public void run()  {

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
                            fileSize(nowFile);
                        }
                    }
                }

                fileSizeTotal.getAndAdd(totalSpace);

                // 当前目录处理完减1
                long dirNumber = FileUtilTest.dirNumber.decrementAndGet();
                if (dirNumber == 0) {
                    LockSupport.unpark(mainThread);
                }
            }
        });

    }

}