package com.jcy.zookeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.I0Itec.zkclient.ZkClient;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        int port = 18081;
        SimpleServer server = new SimpleServer(port);
        server.init();
    }
 
    private int port;
 
    public SimpleServer(int port) {
        this.port = port;
    }
    
    private void regServer() {
        //向ZooKeeper注册当前服务器
        ZkClient client = new ZkClient("127.0.0.1:2181", 60000, 1000); 
        String path = "/test/server" + port; 
        //加上这句就好了 
        if(!client.exists("/test")){
            client.createPersistent("/test"); 
        } 
        if(client.exists(path)){
            client.delete(path); 
            client.createEphemeral(path, "127.0.0.1:" + port);
        }else{
            client.createEphemeral(path, "127.0.0.1:" + port);
        }
    }
    
    
    public void init() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            while (true) {
                regServer();
                
                Socket socket = server.accept();
                
                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                
                String message = bf.readLine();
                if(null != message){
                    System.out.println("Server recive:" + message);
                }else{
                    System.out.println("Server recive:no message");
                }
                
                PrintStream ps = new PrintStream(socket.getOutputStream());
                if(null != message){
                    ps.print("Server:" + message);
                }else{
                    ps.print("Server: no message");
                    break;
                }             
                
                bf.close();
                ps.close();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {}
            }
        }
    }


    
    class SimpleServerHandler {
        private Socket socket;
        
        public SimpleServerHandler(Socket socket){
            this.socket = socket;
        }
        
        public void test() {
            BufferedReader bf = null;
            PrintWriter pw = null;
            
            try {
                bf = new BufferedReader(new InputStreamReader(
                        this.socket.getInputStream()));
                pw = new PrintWriter(this.socket.getOutputStream());
                String body = null;
                while(true){
                    body = bf.readLine();
                    if(null == body){
                        break;
                    }
                    System.out.println("Server Receive--->" + body);
                    pw.println("server:" + body);
                }
            } catch (IOException e) {
                e.printStackTrace();
                if(pw != null){
                    pw.close();
                }
                if(bf != null){
                    try {
                        bf.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(this.socket != null){
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }finally{
                        this.socket = null;
                    }
                }
            }
        }
        
    }

}
