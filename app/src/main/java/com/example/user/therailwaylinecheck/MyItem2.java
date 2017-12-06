package com.example.user.therailwaylinecheck;

public class MyItem2 {
      private String mTextLine1;
      private String mTextLine2;//ตัวแปรฟิลด์สำหรับพักข้อมูล
      private String mTextLine3;
      private String mStation2;
      private String mNumTrain2;
      private String mTrainType2;
      private String mtime2;


      //คอนสตรักเตอร์ เพื่อรับข้อมูลเข้ามาในคลาส
      public MyItem2(String Station, String NumTrain, String TrainType, String time) {
         mStation2 = Station;
         mNumTrain2 = NumTrain;
         mTrainType2 = TrainType;
         mtime2 = time;

      }

    public String getmStation() {
        return mStation2;
    }

    public void setmStation(String Station) {
        this.mStation2 = Station;
    }

    public String getmNumTrain() {
        return mNumTrain2;
    }

    public void setmNumTrain(String mNumTrain) {
        this.mNumTrain2 = mNumTrain;
    }

    public String getmTrainType() {
        return mTrainType2;
    }

    public void setmTrainType(String mTrainType) {
        this.mTrainType2 = mTrainType;
    }

    public String getMtime() {
        return mtime2;
    }

    public void setMtime(String mtime) {
        this.mtime2 = mtime;
    }
}
