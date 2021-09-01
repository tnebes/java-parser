package controller;

import model.Page;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

      // Parse the raw data into a 2D array
      String[][] data = new String[rawData.size()][2];
      for (int i = 0; i < rawData.size(); i++) 
      {
         data[i] = rawData.get(i).split(" ");
      }
      rawData = null;
      
      // get the unique webpages from the data
      int uniqueCount = 0;
      for (int i = 0; i < data.length; i++)
      {
         for (int j = 0; j < ...; i++)
         {
            
         }
      }
      
      // create the array of pages
      Page[] parsedData = new Page[uniqueCount];

      // get the views for each page.
      for (int i = 0; i < parsedData.length; i++)
      {
         parsedData[i] = new Page(data[i][0]);
         for (String[] datum : data) {
            if (datum[0].equals(parsedData[i].url)) {
               parsedData[i].views++;
            }
         }
      }
      
      // get the unique views for each page.
      int pushIndex = 0;
      for (Page page : parsedData)
      {
         String[] ipAddresses = new String[data.length];
         for (int i = 0; i < data.length; i++) {
            String dataUrl = data[i][0];
            String dataIp = data[i][1];
            if (page.url == dataUrl && checkInArray(dataIp, ipAddresses)) {
               page.uniqueViews++;
               ipAddresses[pushIndex++] = dataIp;
            }
         }
      }
      return parsedData;
   }

   private boolean checkInArray(String string, String[] array)
   {
      for (String s : array) {
         if (s == null) {
            return true;
         }

         if (s.equals(string)) {
            return false;
         }
      }
      return true;
   }
   
}
