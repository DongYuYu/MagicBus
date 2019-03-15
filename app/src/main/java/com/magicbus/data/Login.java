package com.magicbus.data;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("msg")
    private String message;
    @SerializedName("userid")
    private String userid;
    @SerializedName("userfirstname")
    private String userfirstname;
    @SerializedName("userlastname")
    private String userlastname;
    @SerializedName("useremail")
    private String useremail;
    @SerializedName("appapikey")
    private String appapikey;

    public Login(String message, String userid, String userfirstname, String userlastname, String useremail, String appapikey) {
        this.message = message;
        this.userid = userid;
        this.userfirstname = userfirstname;
        this.userlastname = userlastname;
        this.useremail = useremail;
        this.appapikey = appapikey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }

    public String getUserlastname() {
        return userlastname;
    }

    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getAppapikey() {
        return appapikey;
    }

    public void setAppapikey(String appapikey) {
        this.appapikey = appapikey;
    }
}
