package com.example.user.therailwaylinecheck;

public class TableMyItem {
      private String mTextLine1;
      private String mTextLine2;//ตัวแปรฟิลด์สำหรับพักข้อมูล
      private String mTextLine3;
      private String mOrder;
      private String mSt;
      private String mArrived;
      private String mDeparted;


      //คอนสตรักเตอร์ เพื่อรับข้อมูลเข้ามาในคลาส
      public TableMyItem(String order, String st, String arrived, String departed) {
         mOrder = order;
         mSt = st;
         mArrived = arrived;
         mDeparted = departed;
      }

    public String getmOrder() {
        return mOrder;
    }

    public void setmOrder(String mOrder) {
        this.mOrder = mOrder;
    }

    public String getmSt() {
        return mSt;
    }

    public void setmSt(String mSt) {
        this.mSt = mSt;
    }

    public String getmArrived() {
        return mArrived;
    }

    public void setmArrived(String mArrived) {
        this.mArrived = mArrived;
    }

    public String getmDeparted() {
        return mDeparted;
    }

    public void setmDeparted(String mDeparted) {
        this.mDeparted = mDeparted;
    }
}
