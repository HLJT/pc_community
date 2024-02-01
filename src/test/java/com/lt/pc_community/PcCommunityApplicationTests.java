package com.lt.pc_community;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Stack;
import java.util.regex.Pattern;

@SpringBootTest
class PcCommunityApplicationTests {

    @Test
    void contextLoads() {
        String[] tokens={"2","3","+"};
        Stack<Integer> res=new Stack<Integer>();
        String pattern="^[-+]?\\d+$";
        for(String i:tokens){
            if(Pattern.matches(pattern,i)){
                res.push(Integer.valueOf(i));
            }
            else{
                Integer right=0;
                if(!res.isEmpty()){
                    right=res.pop();
                }
                Integer left=0;
                if(!res.isEmpty()){
                    left=res.pop();
                }
                if(i=="+"){
                    left+=right;
                }
                else if(i.equals("-")){
                    left-=right;
                }
                else if(i.equals("*")){
                    left*=right;
                }
                else{
                    if(right!=0){
                        left/=right;
                    }
                }
                res.push(left);
            }
        }
    }
}


