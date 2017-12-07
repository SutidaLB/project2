package com.example.user.therailwaylinecheck;

public class MyItem2 {
    private String mTextLine1;
    private String mTextLine2;//ตัวแปรฟิลด์สำหรับพักข้อมูล
    private String mTextLine3;
    private String mStation2;
    private String mNumTrain2;
    private String mTrainType2;
    private String mtime2;

    private String mStation3;
    private String mNumTrain3;
    private String mTrainType3;
    private String mtime3;


    //คอนสตรักเตอร์ เพื่อรับข้อมูลเข้ามาในคลาส
    public MyItem2(String Station2, String NumTrain2, String TrainType2, String time2,String Station3, String NumTrain3, String TrainType3, String time3) {
         mStation2 = Station2;
         mNumTrain2 = NumTrain2;
         mTrainType2 = TrainType2;
         mtime2 = time2;

         mStation3 = Station3;
         mNumTrain3 = NumTrain3;
         mTrainType3 = TrainType3;
         mtime3 = time3;
    }

    public String getmStation2() {

          return mStation2;
    }

    public void setmStation2(String Station2) {

          this.mStation2 = Station2;
    }

    public String getmNumTrain2() {

          return mNumTrain2;
    }

    public void setmNumTrain2(String mNumTrain2) {

          this.mNumTrain2 = mNumTrain2;
    }

    public String getmTrainType2() {

          return mTrainType2;
    }

    public void setmTrainType2(String mTrainType2) {

          this.mTrainType2 = mTrainType2;
    }

    public String getMtime2() {

          return mtime2;
    }

    public void setMtime2(String mtime2) {

          this.mtime2 = mtime2;
    }

    public String getmStation3() {
        return mStation3;
    }

    public void setmStation3(String mStation3) {
        this.mStation3 = mStation3;
    }

    public String getmNumTrain3() {
        return mNumTrain3;
    }

    public void setmNumTrain3(String mNumTrain3) {
        this.mNumTrain3 = mNumTrain3;
    }

    public String getmTrainType3() {
        return mTrainType3;
    }

    public void setmTrainType3(String mTrainType3) {
        this.mTrainType3 = mTrainType3;
    }

    public String getMtime3() {
        return mtime3;
    }

    public void setMtime3(String mtime3) {
        this.mtime3 = mtime3;
    }


}
