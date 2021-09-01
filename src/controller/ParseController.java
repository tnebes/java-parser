package controller;

import model.Page;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ParseController {
   private static ParseController instance = null;

   private ParseController()
   {
      // Exists only to defeat instantiation.
   }
   
   public static ParseController getInstance()
   {
      if (instance == null) {
         instance = new ParseController();
      }
      return instance;
   }
   
   public Page[] parse(String filePath)
   {
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
      for (String line : rawData)
      {
         rawDataArrayList.add(line.split(" "));
      }
      rawData = null;

      // getting unique pages.
      ArrayList<Page> pages = new ArrayList<>();
      for (String[] datum : rawDataArrayList)
      {
         Page singlePage = new Page(datum[0]);
         if (!pages.contains(singlePage))
         {
            pages.add(singlePage);
         }
      }

      // getting views
      for (Page page : pages)
      {
         for (String[] view : rawDataArrayList)
         {
            if (page.url.equals(view[0]))
            {
               page.views++;
            }
         }
      }

      // getting unique views
      for (Page page : pages)
      {
         ArrayList<String> ips = new ArrayList<>();
         for (String[] view : rawDataArrayList)
         {
            if (!ips.contains(view[1]))
            {
               ips.add(view[1]);
               page.uniqueViews++;
            }
         }
      }
      return null;
   }


   private boolean checkInArray(String string, String[] array)
   {
      for (String s : array) {
         if (s == null) {
            return false;
         }

         if (s.equals(string)) {
            return true;
         }
      }
      return false;
   }
   
}
