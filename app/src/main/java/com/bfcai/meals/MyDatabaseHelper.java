package com.bfcai.meals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    // هنا قمت بانشاء الداتابيز بتاعتي و الحدول لي جواها و الاعمده بتاعت البيانات لي هحتاجها جواه
    private Context context;
    private static final String DATABASE_NAME = "orders.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_orders";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_ADDRESS = "address";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // هنا بعد معرفنا البيانات بتاعتنا فوق بنحطها هنا في string ويعدين نعمل
        // ليها execute عشان تتحول لجدول في الداتبيز وينشئه
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE   + " TEXT, " +
                COLUMN_NAME    + " TEXT, " +
                COLUMN_PHONE   + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_PRICE   + " TEXT );";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // ده لو عملنا تعديلات علي schema بتاع الداتابيز فبدل ما البيانات كلها تتمسح
        // وتتكريت من الاول قولناله لان ضيف الجديد علي طول مع الاحتفاظ بالقديم
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    // الفانكشن المسئولة عن اضافة الوصفات
    void addWasfa(String title, String price , String name , String phone , String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ADDRESS, address);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "فشلت", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "اضيفت بنجاح", Toast.LENGTH_SHORT).show();
        }
    }
    // الفانكشن المسئولة عن قراءة البيانات كلها واظهارها
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    // الفانكشن المسئولة عن تحديث البيانات
    void updateData(String row_id ,String title, String price , String name , String phone , String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ADDRESS, address);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    // فانكشن مسئوله عن حذف صف
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


}