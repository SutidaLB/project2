package com.example.user.therailwaylinecheck;

public class MyItem {
      private String mTextLine1;
      private String mTextLine2;//ตัวแปรฟิลด์สำหรับพักข้อมูล
      private String mTextLine3;
      private String mStation;
      private String mNumTrain;
      private String mTrainType;
      private String mtime;


      //คอนสตรักเตอร์ เพื่อรับข้อมูลเข้ามาในคลาส
      public MyItem(String Station,String NumTrain,String TrainType,String time) {
         mStation = Station;
         mNumTrain = NumTrain;
         mTrainType = TrainType;
         mtime = time;
      }

    public String getmStation() {
        return mStation;
    }

    public void setmStation(String Station) {
        this.mStation = Station;
    }

    public String getmNumTrain() {
        return mNumTrain;
    }

    public void setmNumTrain(String mNumTrain) {
        this.mNumTrain = mNumTrain;
    }

    public String getmTrainType() {
        return mTrainType;
    }

    public void setmTrainType(String mTrainType) {
        this.mTrainType = mTrainType;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }
}
