package com.github.superryhma.miniprojekti.dbc;

import org.javalite.activejdbc.Base;

public class Dbc {
    public static void open() {
        if (!Base.hasConnection()) {
            Base.open("jdbc/DataSource");
        }
    }

    public static void close() {
        if (Base.hasConnection()) {
            Base.close();
        }
    }
    
    public static void openTransaction() {
        Base.openTransaction();
    }
    
    public static void commitTransaction() {
        Base.commitTransaction();
    }
    
    public static void rollbackTransaction() {
        Base.rollbackTransaction();
    }
}
