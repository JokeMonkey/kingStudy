package king_jcy.king_jcy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @date :2023/1/16
 * @description :
 * @author: moji
 */
public class Test01 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Process pro = Runtime.getRuntime().exec("df -h");
        InputStream in = null;
        try{
            pro.waitFor();
            in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            String line = null;
            List<String> result = new ArrayList<>();
            while((line = read.readLine())!=null){
                //System.out.println(line);
                if(line.startsWith("/dev/disk1s6")){
                    String[] arr = line.split(" ");
                    for(String str : arr){
                        if(!str.equals("")){
                            result.add(str);
                            System.out.println(str);
                        }
                    }
                    String str = result.get(4);
                    String[] arr1 = str.split("%");
                    System.out.println(arr1[0]);
                }else{
                    continue;
                }
            }
        }catch (Exception e){

        }finally {
            pro.destroy();
            if(null != in){
                in.close();
            }
        }

    }

}
