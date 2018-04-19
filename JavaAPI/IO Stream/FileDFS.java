package Day23;

import java.io.File;

public class FileDFS {

    public static void main(String[] args){
        File file = new File("/Users/lixingxing/Desktop/PlayGround");
        if(!file.exists()){
            System.out.println("file not exist!");
        }else{
            listFiles(file,1);
            file.getPath();
            file.getAbsolutePath();
        }

    }

    public static void listFiles(File file, int level){
        if(!file.isDirectory()){
            return ;
        }
        // get all the files
        File[] files = file.listFiles();

        for(File temp:files){
            System.out.print("|-");
            for(int i = 0 ; i < level ; i++){
                System.out.print("- ");
            }
            System.out.println(temp.getName());
            listFiles(temp,level+1);
        }

    }


}
