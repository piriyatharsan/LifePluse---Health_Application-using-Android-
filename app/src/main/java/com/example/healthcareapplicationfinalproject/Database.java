package com.example.healthcareapplicationfinalproject;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static  final String DbName = "HealthCare.db";

    private static final String TABLE_DOCTORS = "doctors";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_EXPERIENCE = "experience";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_FEE = "fee";

    public Database( Context context) {
        super(context, DbName, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table Users( ID INTEGER Primary Key AUTOINCREMENT ,username text, email text,password text)";
        db.execSQL(qry1);

        String qry2 = "create table Carts(ID INTEGER Primary Key AUTOINCREMENT,username text, product text,price float,otype text)";
        db.execSQL(qry2);

        String qry3 = "create table BloodRequest(ID INTEGER Primary Key AUTOINCREMENT,username text, bloodtype text,gender text,nearHospital text,number text)";
        db.execSQL(qry3);

        String qry4 = "create table DocterAppoinment(ID INTEGER Primary Key AUTOINCREMENT,type text, name text, address text,number text,amount text,date text,time text)";
        db.execSQL(qry4);

        String qry5 = "create table orderPlace(username text,fullname text,address text,contactno text,pincode int,date text, time text, amount float,otype text)";
        db.execSQL(qry5);

        String CREATE_DOCTORS_TABLE = "CREATE TABLE " + TABLE_DOCTORS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_EXPERIENCE + " TEXT,"
                + COLUMN_PHONE + " TEXT,"
                + COLUMN_FEE + " TEXT"+ ")";
        db.execSQL(CREATE_DOCTORS_TABLE);




    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Carts");
        db.execSQL("DROP TABLE IF EXISTS BloodRequest");
        db.execSQL("DROP TABLE IF EXISTS DocterAppoinment");
        db.execSQL("DROP TABLE IF EXISTS orderPlace");
    }

    public boolean register(String username,String email,String password){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
         long result = db.insert("Users",null,cv);

        if (result==-1){
            return false;
        }
        else {
            return  true;
        }
    }


    public  boolean checkUserName(String username){
    SQLiteDatabase mydb = this.getWritableDatabase();
    Cursor cursor = mydb.rawQuery("SELECT * FROM Users WHERE username = ?",new String[]{username});
        if (cursor.getCount()>0){
        return true;
    }
        else {
        return false;
    }
}

    public  boolean checkUserNamePassword(String username,String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("SELECT * FROM Users WHERE username = ? AND password =?",new String[]{username, password});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updatePassword(String username,String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",password);

        long result = mydb.update("users",contentValues,"username = ?",new String[]{username});

        if (result==-1){
            return false;
        }
        else {
            return  true;
        }
    }


    public ArrayList<String> getAllUsersData() {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try (Cursor c = db.rawQuery("SELECT * FROM Users", null)) {
            if (c.moveToFirst()) {
                do {
                    arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3));
                } while (c.moveToNext());
            }
        } finally {
            db.close();
        }

        return arr;
    }

    // lab Activity

    public boolean addCart(String username,String product,float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        long result  = db.insert("Carts",null,cv);
        if (result==-1){
            return false;
        }
        else {
            return  true;
        }
    }

    public int checkCart(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Carts WHERE username = ? AND product = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        return result;
    }

    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("SELECT * FROM Carts WHERE username = ? AND otype = ?", str);
        if (c.moveToFirst()) {
            do {
                String product =c.getString(2);
                String price = c.getString(3);
                arr.add(product+"$"+price);
            }while (c.moveToNext());
        }
        return arr;

    }


    public void addOrder(String username,String fullname,String address,String contactno,int pincode,String date,String time,float amount,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contactno",contactno);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",amount);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderPlace",null,cv);
    }

    public ArrayList getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE username = ?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while (c.moveToNext());
        }
        db.close();
        return arr;

    }
    public void removeCart(String username,String otype){
        int result =0;
        String str[] = new String[2];
        str[0] =username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Carts","username=? and otype=?",str);
        db.close();
    }


    // Blood Request

    public boolean bloodRequest(String username,String bloodtype,String gender,String nearHospital,String number){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("bloodtype",bloodtype);
        cv.put("gender",gender);
        cv.put("nearHospital",nearHospital);
        cv.put("number",number);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert("BloodRequest",null,cv);

        if (result==-1){
            return false;
        }
        else {
            return  true;
        }
    }

    public ArrayList<String> getRequestBloodData() {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor c = db.rawQuery("SELECT * FROM BloodRequest", null)) {
            if (c.moveToFirst()) {
                do {
                    arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3)+"$"+c.getString(4) + "$" + c.getString(5));
                } while (c.moveToNext());
            }
        } finally {
            db.close();
        }

        return arr;
    }

    /// Docter Appoiment

    public boolean BookAppoinment(String type,String name,String Address, String Number,String Amount,String Date,String Time){
        ContentValues cv = new ContentValues();
        cv.put("type",type);
        cv.put("name",name);
        cv.put("address",Address);
        cv.put("number",Number);
        cv.put("amount",Amount);
        cv.put("date",Date);
        cv.put("time",Time);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert("DocterAppoinment",null,cv);

        if (result==-1){
            return false;
        }
        else {
            return  true;
        }

    }

    public void addDoctor(String category, String name, String location, String experience, String phone, String fee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_EXPERIENCE, experience);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_FEE, fee);
        db.insert(TABLE_DOCTORS, null, values);
    }

    public Cursor getDoctorsByCategory(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_DOCTORS, null, COLUMN_CATEGORY + "=?", new String[]{category}, null, null, null);
    }


    public ArrayList<String> getAppoinmentData() {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor c = db.rawQuery("SELECT * FROM DocterAppoinment", null)) {
            if (c.moveToFirst()) {
                do {
                    arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3)+"$"+c.getString(4) + "$" + c.getString(5) + "$" + c.getString(6)+"$"+c.getString(7));
                } while (c.moveToNext());
            }
        } finally {
            db.close();
        }

        return arr;
    }







}
