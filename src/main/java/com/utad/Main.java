package com.utad;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        introducirRegalos();
    }

    private static void introducirRegalos() {
        Scanner sc = new Scanner(System.in);
        String respuesta = "";
        GiftDAO giftDAO = new GiftDAO();

        while (!respuesta.equalsIgnoreCase("n")) {
            System.out.println("Introduce el nombre de la persona a la que quieres hacer un regalo:");
            String nombre = sc.nextLine();

            System.out.println("Introduce el regalo que quieres hacerle:");
            String regalo = sc.nextLine();

            System.out.println("Introduce el precio del regalo:");
            double precio = sc.nextDouble();
            sc.nextLine();

            Gift gift = new Gift(nombre, regalo, precio);
            giftDAO.insertGift(gift);

            System.out.println("¿Quieres introducir otro regalo? (s/n)");
            respuesta = sc.nextLine();
        }

        String filePath = "./src/main/java/com/utad/regalos.xml";
        XMLExporter.exportDataToXML(filePath);

        sc.close();
    }
}
