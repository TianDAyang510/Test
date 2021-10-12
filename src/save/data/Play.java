package save.data;

import sun.security.util.Password;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * @author MXHstrat
 * @create 2021 - 10 - 10  14:35
 */
public class Play {


    public String logname;

    public String picturName[];

    public String showImage;
    public String webDir[], tomcatDir;
    int index = 0;

    public Play() {
        File f = new File("");
        String path = f.getAbsolutePath();
        int index1 = path.indexOf("bin");
        tomcatDir = path.substring(0, index1);
    }

    public void setLogname(String logname) {

        showImage = "";
        this.logname = logname;
    }


    public void setWebDir(String[] webDir) {

        this.webDir = webDir;
        File dirImage = new File(tomcatDir + "/web"+webDir+"/image");
        picturName = dirImage.list(new FileStartName(logname));

    }

    public String getShowImage() {
        try {
            showImage = picturName[index];
            return showImage;
        }catch (Exception ee){
            return "space.gif";
        }
    }

    public void setIndex(int index) {
        this.index = index;
        if(this.index>=picturName.length){
            this.index = 0;
        }
        if (this.index<0)
            this.index = picturName.length-1;

    }

    public int getIndex() {
        return index;
    }

    class FileStartName implements FilenameFilter {

        String logname =null;
        Pattern pattern;
        Matcher matcher;


         FileStartName(String logname) {
             this.logname=logname;
             pattern = Pattern.compile(logname);
        }

        public boolean accept(File dir,String name){
             matcher = pattern.matcher(name);
             if(matcher.find())
                 return true;
             else
                 return false;
        }
    }
}
