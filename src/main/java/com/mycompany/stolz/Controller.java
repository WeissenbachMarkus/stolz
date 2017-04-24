/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stolz;

import javax.swing.JFrame;

/**
 *
 * @author markus
 */
public class Controller
{

    String path;
    JFrame display;

    public void setPath(String path)
    {
        this.path = path;
        this.display=new Display();
    }

    public Controller()
    {
        this.path = "";
    }

    public static void main(String[] args)
    {
        new Controller();
    }

}
