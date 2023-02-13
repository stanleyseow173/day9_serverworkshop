package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {
    public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException{
        String[] arrSplit = args[0].split(":");
        Socket sock = new Socket(arrSplit[0],Integer.parseInt(arrSplit[1]));
        

        InputStream is = sock.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        OutputStream os = sock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        Console cons = System.console();

        String outputString = "";
        String input = "";

        while(!outputString.contains("right")){
            input = cons.readLine("Send command to random number server -->");
            dos.writeUTF(input); //write input as guess XX
            dos.flush();

            outputString = dis.readUTF();
            System.out.println(outputString);
            
           


        }
        dos.close();
        bos.close();
        os.close();

        dis.close();
        bis.close();
        is.close();


    }
}

