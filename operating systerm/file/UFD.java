import java.util.LinkedList;
import java.util.List;

public class UFD {//用户文件目录
    String username;
    List<File> files= new LinkedList<>();//文件目录
    int length;//文件个数
    int pro;//保护码
    UFD(String  s){
        username=s;
        length=0;
    }
}
