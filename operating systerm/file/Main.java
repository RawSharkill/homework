import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        MDF mdf=new MDF();
            Init(mdf);

            System.out.println("please input the username");
            String str;
        Scanner scanner=new Scanner(System.in);
        str=scanner.next();

        //find user
        int ans=finduser(mdf,str);
        if(ans==-1)
            System.out.println("no this user!");
        else
        {
            UFD user=mdf.users.get(ans);//get the ans user
            System.out.println("username:  "+user.username);
            for(File file : user.files)
            {
                System.out.println("fielname  "+file.filename);
            }

            AFD afd=new AFD();
            System.out.println("input the order");
            System.out.println("bye--1  "+" create->0"+"  delete->1  "+" open->2  "+" lose->3 "+" read->4 "+" write->5 ");

            Scanner s=new Scanner(System.in);
            int order=s.nextInt();
            RUN(order,user,afd);
            System.out.println("username:  "+user.username);
            for(File file : user.files)
            {
                System.out.println("fielname  "+file.filename);
            }

        }
    }
    static void Init(MDF mdf){
        UFD user1=new UFD("user1");
        UFD user2=new UFD("user2");

        File f1=new File("file1",1);
        File f2=new File("file2",0);
        File f3=new File("file3",0);
        File f4=new File("file4",1);

        user1.files.add(f1);
        user1.files.add(f2);

        user2.files.add(f3);
        user2.files.add(f4);

        mdf.users.add(user1);
        mdf.users.add(user2);
    }
    static int finduser(MDF mdf,String name){
     int count=0;
     for(UFD u : mdf.users)
     {
         if(u.username.equals(name))
             return count;
         else
             count++;
     }
     return -1;
    }
    static void RUN(int order,UFD user,AFD afd){
        switch (order){
            case -1:
                break;
            case 0:
                create(user,afd);
                break;
            case 1:
                delete(user,afd);
                break;
            case 2:
                open(user,afd);
                break;
            case 3:
                lose(user,afd);
                break;
            case 4:
                read(user,afd);
                break;
            case 5:
                write(user,afd);
                break;
                default:
                    System.out.println("no order!!");
                    break;
        }
    }
    static void create(UFD user,AFD afd){
        String name;
        System.out.println("input the name");
        Scanner s=new Scanner(System.in);
        name=s.next();
        System.out.println("RW");
        int rw=s.nextInt();
        File f=new File(name,rw);
        afd.afd.add(f);//runing file
        afd.point++;
        user.files.add(f);
    }
    static void delete(UFD user,AFD afd){
        String name;
        System.out.println("input the name");
        Scanner s=new Scanner(System.in);
        name=s.next();
        int index=user.files.indexOf(name);
        File f=user.files.get(index);
        afd.afd.add(f);//runing file
        afd.point++;
        user.files.remove(name);
    }
    static void open(UFD user,AFD afd){
        String name;
        System.out.println("input the name");
        Scanner s=new Scanner(System.in);
        name=s.next();
        int index=0;
        for(int i=0;i<user.files.size();i++)
        {
            if(user.files.get(i).filename.equals(name))
            {
                index=i;
                break;
            }
        }
        File f =user.files.get(index);
        if(f.RW==1)
        {
            System.out.println("open it!");
        }
        else
        {
            System.out.println("it can not open!");
        }
        afd.afd.add(f);
        afd.point++;
    }
    static void lose(UFD user,AFD afd){
        afd.afd.clear();
        afd.point=0;
    }
    static void read(UFD user,AFD afd){
        String name;
        System.out.println("input the name");
        Scanner s=new Scanner(System.in);
        name=s.next();
        int index=0;
        for(int i=0;i<user.files.size();i++)
        {
            if(user.files.get(i).filename.equals(name))
            {
                index=i;
                break;
            }
        }
        File f =user.files.get(index);
        if(f.RW==1)
        {
            System.out.println(f.content);
        }
        else
        {
            System.out.println("it can not read!");
        }
        afd.afd.add(f);
        afd.point++;
    }
    static void write(UFD user,AFD afd){
        String name;
        System.out.println("input the name");
        Scanner s=new Scanner(System.in);
        name=s.next();
        int index=0;
        for(int i=0;i<user.files.size();i++)
        {
            if(user.files.get(i).filename.equals(name))
            {
                index=i;
                break;
            }
        }
        File f =user.files.get(index);
        if(f.RW==1)
        {
            System.out.println("input the content");
            String str=s.nextLine();
            f.content=str;
            System.out.println("content: "+ f.content);
        }
        else
        {
            System.out.println("it can not write!");
        }
        afd.afd.add(f);
        afd.point++;
    }

}
