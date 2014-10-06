package test.test.myapplication.supp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by BruSD on 9/23/2014.
 */
public class Student implements Serializable {

    private String name, sname, url;


//    public Student(String name , String sname, String url){
//        this.name = name;
//        this.sname = sname;
////        this.tell = tell;
////        this.email = email;
//        this.url = url;
//    }


    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname(){
        return sname;
    }

    public void setSname (String sname){
        this.sname = sname;
    }
//
//    public String getTell(){
//        return tell;
//    }
//
//    public void setTell (String tell){
//        this.tell = tell;
//    }
//
//    public String getEmail(){
//        return email;
//    }
//
//    public void setEmail (String email){
//        this.email = email;
//    }

    public String getUrl(){
        return url;
    }

    public void setUrl (String url){
        this.url = url;
    }


}
