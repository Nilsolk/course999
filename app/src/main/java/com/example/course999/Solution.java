package com.example.course999;

public class Solution {
    public static void main(String[] args) {
        try {
            Example example = (Example) Class.forName("example").newInstance();
            example.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


class Example {
    void show() {
        System.out.println("Hello im here");
    }

}
