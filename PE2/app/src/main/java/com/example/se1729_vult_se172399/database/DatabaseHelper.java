package com.example.se1729_vult_se172399.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.se1729_vult_se172399.model.Student;
import com.example.se1729_vult_se172399.model.Major;
import com.example.se1729_vult_se172399.model.User;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "StudentManagement.db";
    private static final int DATABASE_VERSION = 1;
    
    private static final String TABLE_STUDENTS = "students";
    private static final String TABLE_MAJORS = "majors";
    private static final String TABLE_USERS = "users";
    
    private static final String STUDENT_ID = "id";
    private static final String STUDENT_NAME = "name";
    private static final String STUDENT_DATE = "date";
    private static final String STUDENT_GENDER = "gender";
    private static final String STUDENT_ADDRESS = "address";
    private static final String STUDENT_ID_NGANH = "idNganh";
    private static final String STUDENT_PHONE = "phone";
    private static final String STUDENT_IMAGE = "imagePath";
    
    private static final String MAJOR_ID = "idNganh";
    private static final String MAJOR_NAME = "nameNganh";
    
    private static final String USER_ID = "id";
    private static final String USER_USERNAME = "username";
    private static final String USER_PASSWORD = "password";
    private static final String USER_EMAIL = "email";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MAJORS_TABLE = "CREATE TABLE " + TABLE_MAJORS + "("
                + MAJOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAJOR_NAME + " TEXT NOT NULL" + ")";
        
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + STUDENT_NAME + " TEXT NOT NULL,"
                + STUDENT_DATE + " TEXT,"
                + STUDENT_GENDER + " TEXT,"
                + STUDENT_ADDRESS + " TEXT,"
                + STUDENT_ID_NGANH + " INTEGER,"
                + STUDENT_PHONE + " TEXT,"
                + STUDENT_IMAGE + " TEXT,"
                + "FOREIGN KEY(" + STUDENT_ID_NGANH + ") REFERENCES " + TABLE_MAJORS + "(" + MAJOR_ID + ")" + ")";
        
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_USERNAME + " TEXT UNIQUE NOT NULL,"
                + USER_PASSWORD + " TEXT NOT NULL,"
                + USER_EMAIL + " TEXT" + ")";
        
        db.execSQL(CREATE_MAJORS_TABLE);
        db.execSQL(CREATE_STUDENTS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        
        insertDefaultMajors(db);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAJORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    
    private void insertDefaultMajors(SQLiteDatabase db) {
        String[] majors = {"Computer Science", "Information Technology", "Software Engineering", "Business Administration", "Mechanical Engineering"};
        for (String major : majors) {
            ContentValues values = new ContentValues();
            values.put(MAJOR_NAME, major);
            db.insert(TABLE_MAJORS, null, values);
        }
    }
    
    public long addMajor(Major major) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MAJOR_NAME, major.getNameNganh());
        
        long result = db.insert(TABLE_MAJORS, null, values);
        db.close();
        return result;
    }
    
    public List<Major> getAllMajors() {
        List<Major> majorList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MAJORS;
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                Major major = new Major();
                major.setIdNganh(cursor.getInt(0));
                major.setNameNganh(cursor.getString(1));
                majorList.add(major);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close();
        return majorList;
    }
    
    public int updateMajor(Major major) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MAJOR_NAME, major.getNameNganh());
        
        int result = db.update(TABLE_MAJORS, values, MAJOR_ID + " = ?", 
                new String[]{String.valueOf(major.getIdNganh())});
        db.close();
        return result;
    }
    
    public void deleteMajor(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MAJORS, MAJOR_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    
    public long addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getName());
        values.put(STUDENT_DATE, student.getDate());
        values.put(STUDENT_GENDER, student.getGender());
        values.put(STUDENT_ADDRESS, student.getAddress());
        values.put(STUDENT_ID_NGANH, student.getIdNganh());
        values.put(STUDENT_PHONE, student.getPhone());
        values.put(STUDENT_IMAGE, student.getImagePath());
        
        long result = db.insert(TABLE_STUDENTS, null, values);
        db.close();
        return result;
    }
    
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setDate(cursor.getString(2));
                student.setGender(cursor.getString(3));
                student.setAddress(cursor.getString(4));
                student.setIdNganh(cursor.getInt(5));
                student.setPhone(cursor.getString(6));
                student.setImagePath(cursor.getString(7));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close();
        return studentList;
    }
    
    public Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENTS, null, STUDENT_ID + "=?", 
                new String[]{String.valueOf(id)}, null, null, null, null);
        
        if (cursor != null && cursor.moveToFirst()) {
            Student student = new Student();
            student.setId(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setDate(cursor.getString(2));
            student.setGender(cursor.getString(3));
            student.setAddress(cursor.getString(4));
            student.setIdNganh(cursor.getInt(5));
            student.setPhone(cursor.getString(6));
            student.setImagePath(cursor.getString(7));
            cursor.close();
            db.close();
            return student;
        }
        
        if (cursor != null) cursor.close();
        db.close();
        return null;
    }
    
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getName());
        values.put(STUDENT_DATE, student.getDate());
        values.put(STUDENT_GENDER, student.getGender());
        values.put(STUDENT_ADDRESS, student.getAddress());
        values.put(STUDENT_ID_NGANH, student.getIdNganh());
        values.put(STUDENT_PHONE, student.getPhone());
        values.put(STUDENT_IMAGE, student.getImagePath());
        
        int result = db.update(TABLE_STUDENTS, values, STUDENT_ID + " = ?", 
                new String[]{String.valueOf(student.getId())});
        db.close();
        return result;
    }
    
    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, STUDENT_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    
    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_USERNAME, user.getUsername());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_EMAIL, user.getEmail());
        
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result;
    }
    
    public User checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {USER_ID, USER_USERNAME, USER_EMAIL};
        String selection = USER_USERNAME + " = ?" + " AND " + USER_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        User user = null;
        
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setEmail(cursor.getString(2));
        }
        
        cursor.close();
        db.close();
        return user;
    }
    
    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {USER_ID};
        String selection = USER_USERNAME + " = ?";
        String[] selectionArgs = {username};
        
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        
        return count > 0;
    }
    
    public void deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, USER_USERNAME + " = ?", new String[]{username});
        db.close();
    }
    
    public String getMajorName(int majorId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String majorName = "";
        Cursor cursor = db.query(TABLE_MAJORS, new String[]{MAJOR_NAME}, 
                MAJOR_ID + "=?", new String[]{String.valueOf(majorId)}, 
                null, null, null);
        
        if (cursor.moveToFirst()) {
            majorName = cursor.getString(0);
        }
        
        cursor.close();
        db.close();
        return majorName;
    }
} 