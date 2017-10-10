package com.example.user.therailwaylinecheck;

public class MyItem {
      private String mTextLine1;	//ตัวแปรฟิลด์สำหรับพักข้อมูล

      //คอนสตรักเตอร์ เพื่อรับข้อมูลเข้ามาในคลาส
      public MyItem(String textLine1) {
            mTextLine1 = textLine1;
      }
      //เมธอด setter และ setter ทั้งหมดต่อไปนี้ใช้ในการกำหนดและอ่านค่าตัวแปรฟิลด์
      public void setTextLine1(String textLine1) {
            mTextLine1 = textLine1;
      }
      public String getTextLine1() {

            return mTextLine1;
      }



}
