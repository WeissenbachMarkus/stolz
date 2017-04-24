/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stolz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author markus
 */
public class ReadCSVFile
{

    String csvPath;
    BufferedReader br;
    String csvSplitBy;

    public ReadCSVFile()
    {
        this.csvSplitBy = "\t";

    }

    public void setCSVpath(String path)
    {
        this.csvPath=path;
    }
    
    public ArrayList<String[]> read()
    {

        ArrayList<String[]> csvFile = new ArrayList<>();

        try
        {
            this.br = new BufferedReader(new FileReader(this.csvPath));

            String line = "";
            while ((line = this.br.readLine()) != null)
            {

                String[] itemsAndStock = line.split(this.csvSplitBy);

                csvFile.add(itemsAndStock);
            }

        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return csvFile;
    }

}
