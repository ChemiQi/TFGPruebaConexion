package com.example.tfg2.utilidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class ImagenesBlobBitmap {
    //método que convierte un blob a un Bitmap
    public static Bitmap blob_to_bitmap(Blob b, int width, int height) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        try {
            int blobLength = (int) b.length();
            byte[] blobAsBytes = b.getBytes(1, blobLength);
            bitmap = BitmapFactory.decodeByteArray(blobAsBytes, 0, blobAsBytes.length);
        } catch (Exception e) {
            return null;
        }
        return bitmap;
    }

    //método que convierte un blob a byte[]
    public static byte[] blob_to_bytes(Blob b) {
        int blobLength = 0;
        byte[] blobAsBytes = new byte[0];
        try {
            blobLength = (int) b.length();
            blobAsBytes = b.getBytes(1, blobLength);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blobAsBytes;
    }

    //método que convierte byte[] a bitmap
    public static Bitmap bytes_to_bitmap(byte[] b) {

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(200, 200, config);
        try {
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        } catch (Exception e) {
        }

        return bitmap;
    }

    public static byte[] bitmap_to_bytes(Bitmap bm) {
        if(bm != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            b = imagemTratada(b);
            return b;
        }else {
            return null;
        }
    }
    public static byte[] bitmap_to_bytesPequeño(Bitmap bm) {
        if(bm != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            b = imagemTratadaSuperPequeño(b);
            return b;
        }else {
            return null;
        }
    }

    private static byte[] imagemTratada(byte[] imagem_img){

        while (imagem_img.length > 500000){
            System.out.println("-------------------- TAMAÑO " + imagem_img.length);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem_img, 0, imagem_img.length);
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.PNG, 80, stream);
            imagem_img = stream.toByteArray();
        }
        return imagem_img;
    }
    private static byte[] imagemTratadaSuperPequeño(byte[] imagem_img){

        while (imagem_img.length > 38000){
            System.out.println("-------------------- TAMAÑO " + imagem_img.length);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem_img, 0, imagem_img.length);
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.PNG, 80, stream);
            imagem_img = stream.toByteArray();
        }
        return imagem_img;
    }
}
