/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.entities;
import net.glxn.qrgen.QRCode;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author pc
 */
public class QRCodeGenerator {
    public static void main(String[] args) throws Exception {
    String details = "Tu as reservé une formation c'et votre QR code ";
    ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
    File f = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\3ala3ajltin\\src\\images\\MyChannel.jpg");
    FileOutputStream fos = new FileOutputStream(f);
    fos.write(out.toByteArray());
    fos.flush();
    }
}
