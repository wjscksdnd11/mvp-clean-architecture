package com.example.mvpclean.utils;

import java.util.Stack;

public class HistoryUtil {
    private int previousId;
    private final int initId;
    private Stack<Integer> stack =new Stack<>();

    public HistoryUtil(int initId) {
        this.previousId = initId;
        this.initId = initId;
    }
    public int getInitId(){
        return initId;
    }

    public boolean isNoHistory(){
        return stack.isEmpty();
    }
    public int getPreviousId(){
        if (stack.isEmpty())
            return initId;
        else
            return stack.peek();
    }
    public void addCurrentHistory(int id){
        //empty check
        if (stack.isEmpty())
            stack.push(initId);
        //backpress 이후 이전 화면이 다시 set 될 경우,
        else if (stack.peek() == id && stack.size()>1){
            //stack이 중복일 경우
           if (stack.contains(id))
                stack.pop();
        }else{
            //case 체크 후 이전 id push
            stack.push(previousId);
        }
        previousId = id;
    }


}
