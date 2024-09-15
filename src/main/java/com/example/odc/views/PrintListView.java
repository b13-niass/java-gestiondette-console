package com.example.odc.views;

import java.lang.reflect.Field;
import java.util.List;

public class PrintListView {

        public static void printTable(List<?> objects) {
            if (objects.isEmpty()) {
                System.out.println("La liste est vide.");
                return;
            }

            Class<?> clazz = objects.get(0).getClass();

            Field[] fields = clazz.getDeclaredFields();

            int[] columnWidths = new int[fields.length];
            for (int i = 0; i < fields.length; i++) {
                columnWidths[i] = fields[i].getName().length();
            }

            for (Object obj : objects) {
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    try {
                        String value = String.valueOf(fields[i].get(obj));
                        if (value.length() > columnWidths[i]) {
                            columnWidths[i] = value.length();
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            StringBuilder header = new StringBuilder();
            for (int i = 0; i < fields.length; i++) {
                header.append(String.format("%-" + columnWidths[i] + "s ", fields[i].getName()));
            }
            System.out.println(header.toString());

            StringBuilder separator = new StringBuilder();
            for (int width : columnWidths) {
                separator.append("-".repeat(width + 1));
            }
            System.out.println(separator.toString());

            for (Object obj : objects) {
                StringBuilder row = new StringBuilder();
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    try {
                        String value = String.valueOf(fields[i].get(obj));
                        row.append(String.format("%-" + columnWidths[i] + "s ", value));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(row.toString());
            }
        }
}
