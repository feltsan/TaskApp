package test.test.myapplication.supp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BruSD on 9/23/2014.
 */
public class Student {

    private String name, sname, tell, email, age;


    public Student(String name , String sname, String tell, String email, String age){
        this.name = name;
        this.sname = sname;
        this.tell = tell;
        this.email = email;
        this.age = age;
    }


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

    public String getTell(){
        return tell;
    }

    public void setTell (String tell){
        this.tell = tell;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public String getAge(){
        return age;
    }

    public void setAge (String age){
        this.age = age;
    }


}
