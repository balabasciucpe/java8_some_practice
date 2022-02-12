package com.modernjava.cap3_MethodReference;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

    public static void main(String[] args)  {

        try {
            checkResult();
        } catch(IOException e) {}
    }

    public static void checkResult() throws IOException
    {
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("First line: ");
        String oneLine = processFile(BufferedReader::readLine);
        System.out.println(oneLine);

        System.out.println("Two lines: ");
        String twoLines = processFile((BufferedReader b) -> b.readLine() + " " + b.readLine());
        System.out.println(twoLines);
    }



    public static String processFileLimited() throws IOException
    {
        try(BufferedReader br = new BufferedReader(new FileReader("./resources//text.txt")))
        {
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader("./resources//text.txt")))
        {
            return p.process(br);
        }

    }

    public interface BufferedReaderProcessor {
        String process(BufferedReader bufferedReader) throws IOException;
    }
}
