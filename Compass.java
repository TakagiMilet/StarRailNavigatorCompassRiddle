package BFS;

import java.util.*;

public class 罗盘 {
    static ArrayList<Integer> init = new ArrayList<>();//存储初始刻度
    static ArrayList<Integer> mov = new ArrayList<>();//存储移动刻度
    static ArrayList<ArrayList<Integer>> control = new ArrayList<>();//存储控制动作
    static HashMap<String, String> record = new HashMap<>();//存储操作顺序

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入初始刻度：（从外到内）0-2");
        for (int i = 0; i < 3; i++) {
            init.add(scanner.nextInt());
        }
        System.out.println("输入移动刻度数：（从外到内）");
        for (int i = 0; i < 3; i++) {
            mov.add(scanner.nextInt());
        }
        System.out.println("输入控制：0-2（0表示外圈，2表示圈）");
        for (int i = 1; i <= 3; i++) {
            String temp = scanner.next();
            ArrayList<Integer> nums = new ArrayList<>();
            for (int j = 0; j < temp.length(); j++) {
                nums.add(temp.charAt(j) - '0');
            }
            control.add(nums);
        }
        ArrayList<Integer> bfs = bfs();
        for (int i = 0; i < bfs.size(); i++) {
            System.out.print(control.get(i) + ":");
            System.out.println(bfs.get(i));
        }
    }

    public static ArrayList<Integer> bfs() {
        ArrayDeque<ArrayList<Integer>> arrayDeque = new ArrayDeque<>();//队列
        HashSet<String> hashSet = new HashSet<>();//记录是否重复
        HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>();//建立状态和action的映射

        //初始化
        ArrayList<Integer> action = new ArrayList<>();
        action.add(0);
        action.add(0);
        action.add(0);
        arrayDeque.add(init);
        hashSet.add(toString(init));
        hashMap.put(toString(init), action);
        record.put(toString(init), "");

        //开始bfs
        while (!arrayDeque.isEmpty()) {
            ArrayList<Integer> first = arrayDeque.pop();
            String strFirst = toString(first);
            ArrayList<Integer> actionFirst = new ArrayList<>(hashMap.get(strFirst));

            //3种控制，依次遍历
            for (int i = 0; i < 3; i++) {
                ArrayList<Integer> controlList = control.get(i);
                //模拟移动
                for (int d : controlList) {
                    first.set(d, (first.get(d) + mov.get(d)) % 360);
                }
                actionFirst.set(i, actionFirst.get(i) + 1);
                String sNew = toString(first);


                if (!hashSet.contains(sNew)) {
                    arrayDeque.add(new ArrayList<>(first));
                    hashSet.add(sNew);
                    hashMap.put(sNew, new ArrayList<>(actionFirst));
                    record.put(sNew, record.get(strFirst) + i);
                }

                //判断是否结束
                if (sNew.equals("0,0,0,")) {
                    String outAction = record.get(sNew);
                    for (int k = 0; k < outAction.length(); k++) {
                        ArrayList<Integer> integers = control.get(outAction.charAt(k) - '0');
                        System.out.print("第" + k + "步：转动" );
                        for(int out:integers){
                            switch (out){
                                case 0:
                                    System.out.print("外");
                                    break;
                                case 1:
                                    System.out.print("中");
                                    break;
                                case 2:
                                    System.out.print("内");
                                    break;
                            }
                        }
                        System.out.println();
                    }
                    return actionFirst;
                }


                for (int d : controlList) {
                    first.set(d, (first.get(d) + 360 - mov.get(d)) % 360);
                }
                actionFirst.set(i, actionFirst.get(i) - 1);
            }
        }
        return new ArrayList<>();
    }

    public static String toString(ArrayList<Integer> arrayList) {
        String res = "";
        for (int i = 0; i < arrayList.size(); i++) {
            res += arrayList.get(i);
            res += ",";
        }
        return res;
    }
}
