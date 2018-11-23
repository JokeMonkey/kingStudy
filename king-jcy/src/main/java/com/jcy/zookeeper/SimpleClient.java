package com.jcy.zookeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

public class SimpleClient {
    private static List<String> servers = new ArrayList<>();
    
    public static void main(String[] args) {
        initServerList();
        int count = 0;
        SimpleClient client = new SimpleClient();
        while(true){
            System.out.println("计数器：" + count++);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            try{
                String message = console.readLine();
                
                if("exit".equals(message)) {
                    System.exit(0);
                }
                client.send(message);
            }catch(Exception e){
                
            }
        }
    }
    
    
    public SimpleClient() {
        
    }

    public void send(String message) {
        String server = SimpleClient.getServer();
        String[] cfg = server.split(":");
        
        Socket socket = null;
        BufferedReader bf = null;
        PrintWriter pw = null;
        
        try {
            socket = new Socket(cfg[0], Integer.parseInt(cfg[1]));
            
            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(message);
            System.out.println("client sendMsg:" + message);
            
            
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String responseMsg = bf.readLine();
                if(null != responseMsg && !"".equals(responseMsg)){
                    System.out.println("Client recive:" + responseMsg);
                }else{
                    break;
                }
            }
            
            pw.close();
            bf.close();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    private static void initServerList() {
        //启动时从ZooKeeper读取可用服务器
        final String path = "/test";
        final ZkClient zkClient = new ZkClient("127.0.0.1:2181", 60000, 30000);
        List<String> childs = zkClient.getChildren(path);
        servers.clear();
        for(String p : childs) {
            servers.add(zkClient.readData(path + "/" + p).toString());
        }
        //订阅节点变化事件
        zkClient.subscribeChildChanges("/test", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds)
                    throws Exception {
                System.out.println(String.format("[ZookeeperRegistry] service list change: path=%s, currentChilds=%s", parentPath, currentChilds.toString()));
                servers.clear();
                for(String p : currentChilds) {
                    servers.add(zkClient.readData(path + "/" + p).toString());
                }
                System.out.println("Servers: " + servers.toString());
            }
        });
        
    }
    
    public static String getServer() {
        return servers.get(new Random().nextInt(servers.size()));
    }

}
