import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MLE {
    public static void main(String[] args) {
        List<String> ss = new LinkedList<>();
        ss.add("C:\\Users\\于田\\信息检索\\src\\DOC1.TXT");
        ss.add("C:\\Users\\于田\\信息检索\\src\\DOC2.TXT");
        ss.add("C:\\Users\\于田\\信息检索\\src\\DOC3.TXT");
        ss.add("C:\\Users\\于田\\信息检索\\src\\DOC4.TXT");
        ss.add("C:\\Users\\于田\\信息检索\\src\\DOC5.TXT");

        Scanner s = new Scanner(System.in);
        System.out.println("please input search");
        String search = s.nextLine();

        String[] searchword = search.split(" ");
        List<Integer> sumwo = new LinkedList<>();
        List<List<Integer>> wd = new LinkedList<>();
        List<Integer> dsize = new LinkedList<>();
        for (int i = 0; i < searchword.length; i++) {
//            if (searchword[i].equals("and") || searchword[i].equals("on") || searchword[i].equals("in") || searchword[i].equals("of")) {
//                continue;
//            }
            List<Integer> w = new LinkedList<>();
            int sum = 0;
            for (String adress : ss) {
                FileReader fileReader;
                {
                    try {
                        fileReader = new FileReader(adress);
                        int k;
                        String str = "";
                        while ((k = fileReader.read()) > -1) {
                            str = str + (char) k;
                        }

                        String[] wo = str.split(" ");
                        dsize.add(wo.length);//记录文档长度
                        int count = 0;
                        for (String ww : wo) {
                            if (ww.equals(searchword[i]))
                                count++;
                        }
                        w.add(count);
                        // System.out.println(searchword[i]+"  "+adress+"  "+count);
                        sum += count;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            sumwo.add(sum);//放入总的链表中
            wd.add(w);
        }
        double l = 0;
        for (int k = 0; k < 5; k++)
            l += dsize.get(k);

        HashMap<Integer, Double> docgoal = new HashMap<>();
        for (int i = 0; i < 5; i++)//对于五个文档
        {
            double goal = 1;
            for (int j = 0; j < searchword.length; j++) {
                List<Integer> x = wd.get(j);
                double d = dsize.get(i);
                double xi = x.get(i);
                double y = xi / d;//文档频率
                double z = sumwo.get(j) / l;
                goal = goal * (y + z) / 2.0;
                try {
                    String sd="C:\\Users\\于田\\信息检索\\src\\Run.TXT";
                    FileWriter fw = new FileWriter(sd, true);
                    String f = "doc" + i + " " + searchword[j] + "  " + "w/c  " + y + " w/c " + z + "  goal " + 10000 * goal;
                    fw.write(f);
                    fw.write("\n");
                    fw.close();
                } catch (Exception e) {
                    e.printStackTrace();

               }
            }
                docgoal.put(i, goal);
                // System.out.println(i+"  "+goal);

            }
//            Comparator<Map.Entry<Integer, Double>> valuecompare = Comparator.comparing(Map.Entry::getValue);
//
//            List<Map.Entry<Integer, Double>> doclist = new ArrayList<>(docgoal.entrySet());
//            Collections.sort(doclist, valuecompare);

            List<Map.Entry<Integer, Double>> wordMap = new ArrayList<Map.Entry<Integer, Double>>(docgoal.entrySet());
            Collections.sort(wordMap, new Comparator<Map.Entry<Integer, Double>>() {// 根据value排序
                public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            for (Map.Entry<Integer, Double> set : wordMap) {
                int a = set.getKey().intValue();
                FileReader fileReader;
                {
                    try {
                        fileReader = new FileReader(ss.get(a));
                        int k;
                        String str = "";
                        while ((k = fileReader.read()) > -1) {
                            str = str + (char) k;
                        }
                        System.out.println(str + "    goal   " + set.getValue());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }