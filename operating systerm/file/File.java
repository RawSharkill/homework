public class File {//文件
    String filename;
    int RW;//允许读写 0/1
    String content;
    int length;//文件长度
    File(String str,int rw){
        filename=str;
        RW=rw;
        length=10;
        content="i am an opened file";
    }
}
