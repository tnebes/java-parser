package controller;

import model.Page;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParseController {
    private static ParseController instance = null;

    private ParseController() {
        // Exists only to defeat instantiation.
    }

    public static ParseController getInstance() {
        if (instance == null) {
            instance = new ParseController();
        }
        return instance;
    }

    public Page[] parse(String filePath) {
        ArrayList<String> rawData = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rawData.add(line);
            }
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println("Error reading file '" + filePath + "'");
            return null;
        }

        ArrayList<String[]> rawDataArrayList = new ArrayList<>();
        for (String line : rawData) {
            rawDataArrayList.add(line.split(" "));
        }
        rawData = null;

        // getting unique pages.
        ArrayList<Page> pages = new ArrayList<>();
        for (String[] datum : rawDataArrayList) {
            Page singlePage = new Page(datum[0]);
            if (!pages.contains(singlePage)) {
                pages.add(singlePage);
            }
        }

        // getting views
        for (Page page : pages) {
            for (String[] view : rawDataArrayList) {
                if (page.url.equals(view[0])) {
                    page.views++;
                }
            }
        }

        // getting unique views
        for (Page page : pages) {
            ArrayList<String> ips = new ArrayList<>();
            for (String[] view : rawDataArrayList) {
                if (!ips.contains(view[1]) && page.url.equals(view[0])) {
                    ips.add(view[1]);
                    page.uniqueViews++;
                }
            }
        }

        // sort by views
        pages.sort((Page p1, Page p2) -> p2.views - p1.views);  
        return pages.toArray(new Page[pages.size()]);
    }
}
