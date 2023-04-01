package file_sort;

import java.io.*;
import java.util.*;

public class Sorter {

    public File sortFile(File source) throws IOException {
        long chunksAmount = calculateChunksAmount(source);
        long chunkSize = calculateChunkSize(source, chunksAmount);
        List<File> chunksList = getListOfSortedChunks(source, chunkSize);
        return mergeChunks(chunksList);
    }

    private static List<File> getListOfSortedChunks(File source, long chunkSize) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(source));
        List<File> chunksList = new ArrayList<>();
        String line = "";
        List<String> chunk = new ArrayList<>();
        while (line != null) {
            long currentChunkSize = 0;
            while (currentChunkSize < chunkSize && (line = br.readLine()) != null) {
                chunk.add(line);
                currentChunkSize += line.length();
            }
            chunk.sort(Comparator.comparingLong(Long::parseLong));
            File file = new File("src/file_sort/sorting_bits/chunk" + chunksList.size() + ".txt");
            if (source.length() > chunkSize) {
                file.deleteOnExit();
            }
            try (PrintWriter pw = new PrintWriter(file)) {
                for (String s : chunk) {
                    pw.println(s);
                }
                pw.flush();
            }
            chunksList.add(file);
            chunk.clear();
        }
        br.close();
        return chunksList;
    }


    private static File mergeChunks(List<File> chunkList) throws IOException {
        while (chunkList.size() > 1) {
            File file = new File("src/file_sort/sorting_bits/merged" + chunkList.size() + ".txt");
            if (chunkList.size() > 2) {
                file.deleteOnExit();
            }
            chunkList.add(file);
            try (BufferedReader bf1 = new BufferedReader(new FileReader(chunkList.get(0)));
                 BufferedReader bf2 = new BufferedReader(new FileReader(chunkList.get(1)));
                 PrintWriter pw = new PrintWriter(file)) {

                String num1 = bf1.readLine(), num2 = bf2.readLine();

                while (num1 != null && num2 != null) {
                    if (Long.parseLong(num1) < Long.parseLong(num2)) {
                        pw.println(num1);
                        num1 = bf1.readLine();
                    } else {
                        pw.println(num2);
                        num2 = bf2.readLine();
                    }
                }
                while (num1 == null && num2 != null) {
                    pw.println(num2);
                    num2 = bf2.readLine();
                }
                while (num1 != null && num2 == null) {
                    pw.println(num1);
                    num1 = bf1.readLine();
                }
                pw.flush();
                chunkList.remove(0);
                chunkList.remove(1);
            }
        }
        return chunkList.get(0);
    }


    private static long calculateChunksAmount(File source) {
        long fileSize = source.length();
        double availableMemory = Runtime.getRuntime().freeMemory();
        return (long) Math.ceil(fileSize / availableMemory / 2);
    }


    private static long calculateChunkSize(File source, long chunkAmount) {
        return source.length() / chunkAmount;
    }

}
