package com.example.starrailnavigatorcompassriddle;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    static ArrayList<Integer> init = new ArrayList<>();//存储初始刻度
    static ArrayList<Integer> mov = new ArrayList<>();//存储移动刻度
    static ArrayList<ArrayList<Integer>> control = new ArrayList<>();//存储控制动作
    static HashMap<String, String> record = new HashMap<>();//存储操作顺序

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        init.clear();
        mov.clear();
        control.clear();
        record.clear();

        init.add(Integer.parseInt(request.getParameter("initBigScale")));
        init.add(Integer.parseInt(request.getParameter("initMidScale")));
        init.add(Integer.parseInt(request.getParameter("initSmallScale")));

        mov.add(Integer.parseInt(request.getParameter("bigMoveScale")));
        mov.add(Integer.parseInt(request.getParameter("midMoveScale")));
        mov.add(Integer.parseInt(request.getParameter("smallMoveScale")));

        HashMap<String, String> InputHashMap = new HashMap<>();
        InputHashMap.put("外", "0");
        InputHashMap.put("中", "1");
        InputHashMap.put("内", "2");
        InputHashMap.put("外中", "01");
        InputHashMap.put("外内", "02");
        InputHashMap.put("中内", "12");

        for (int i = 1; i <= 3; i++) {
            String temp = "controlSelect" + i;
            String strControl = InputHashMap.get(request.getParameter(temp));
            ArrayList<Integer> nums = toArrayList(strControl);
            control.add(nums);
        }

        ArrayList<String> ans = bfs();
        HttpSession session = request.getSession();
        session.setAttribute("ans", ans);
        response.sendRedirect("answer.jsp");
    }


    public static ArrayList<String> bfs() {
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
                    ArrayList<String> res = new ArrayList<>();
                    String outAction = record.get(sNew);
                    for (int k = 0; k < outAction.length(); k++) {
                        ArrayList<Integer> integers = control.get(outAction.charAt(k) - '0');
                        String temp = "第" + k + "步：转动";
                        for (int out : integers) {
                            switch (out) {
                                case 0:
                                    temp = temp + "外";
                                    break;
                                case 1:
                                    temp = temp + "中";
                                    ;
                                    break;
                                case 2:
                                    temp = temp + "内";
                                    ;
                                    break;
                            }
                        }
                        res.add(temp);
                    }
                    return res;
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

    public static ArrayList<Integer> toArrayList(String str) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int j = 0; j < str.length(); j++) {
            nums.add(str.charAt(j) - '0');
        }
        return nums;
    }

}