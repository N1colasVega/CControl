package com.example.nicolas.ccontrol;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

//Вспомогательный класс для удобнйо работы с БД
public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
    // имя базы данных
    private static final String DATABASE_NAME = "database6.db";
    // версия базы данных
    private static final int DATABASE_VERSION = 1;
    //ПРИМЕР СОЗДАНИЕ ===================================================================
// Таблица транзикций
// имя таблицы
    public static final String TABLE_TRANS = "transaction_table";
    // названия столбцов
    public static final String TRANS_ID = "transaction_id";
    public static final String CAT_ID = "category_id";
    public static final String SOURCE_ID = "source_id";
    public static final String TITLE_COLUM = "title";
    public static final String DESC_COLUM = "description";
    public static final String SUM_COLUM = "sum";
    public static final String ADRESS_COLUM = "adress";
    public static final String DATE_COLUM = "date";

    // Таблица категорий
// имя таблицы
    public static final String TABLE_CAT = "category";
    // названия столбцов
    public static final String CAT_ID2 = "category_id";
    public static final String TITLE_COLUM2 = "title";
    public static final String DESC_COLUM2 = "description";
    public static final String IMG_COLUM = "image";
    public static final String STATUS_COLUM = "status";
    public static final String DATE_ADD__COLUM = "date_added";

    // Таблица пунктов
// имя таблицы
    public static final String TABLE_SOURCE = "source";
    // названия столбцов
    public static final String SOURCE_ID2 = "source_id";
    public static final String TITLE_COLUM3 = "title";
    public static final String BALANCE_COLUM = "balance";
    public static final String IMG_COLUM2 = "image";
    public static final String DATE_ADD__COLUM2 = "date_added";

    // Таблица изображений
// имя таблицы
    public static final String TABLE_IMAGE = "transaction_image";
    // названия столбцов
    public static final String IMAGE_ID = "image_id";
    public static final String TRANS_ID2 = "transaction_id";
    public static final String IMG_COLUM3 = "image";
    public static final String DESC_COLUM3 = "description";
    public static final String DATE_ADD__COLUM3 = "date_added";

    private static final String DATABASE_CREATE_TARNS_TABLE = "create table " + TABLE_TRANS + " ("
            + TRANS_ID + " integer primary key autoincrement,"
            + CAT_ID + " integer,"
            + SOURCE_ID + " integer,"
            + TITLE_COLUM + " text,"
            + DESC_COLUM + " text,"
            + SUM_COLUM + " real,"
            + ADRESS_COLUM + " text,"
            + DATE_COLUM + " integer,"
            + "foreign key (" + SOURCE_ID + ") references " + TABLE_SOURCE + "(" + SOURCE_ID2 + "),"
            + "foreign key (" + CAT_ID + ") references " + TABLE_CAT + "(" + CAT_ID2 + "));";

    private static final String DATABASE_CREATE_CAT_TABEL = "create table " + TABLE_CAT + " ("
            + CAT_ID2 + " integer primary key autoincrement, "
            + TITLE_COLUM2 + " text,"
            + DESC_COLUM2 + " text,"
            + IMG_COLUM + " text,"
            + STATUS_COLUM + " integer,"
            + DATE_ADD__COLUM + " integer" + ")";

    private static final String DATABASE_CREATE_SOURSE_TABEL = "create table " + TABLE_SOURCE + " ("
            + SOURCE_ID2 + " integer primary key autoincrement, "
            + TITLE_COLUM3 + " text,"
            + BALANCE_COLUM + " real,"
            + IMG_COLUM2 + " text,"
            + DATE_ADD__COLUM2 + " integer" + ")";

    private static final String DATABASE_CREATE_IMAGE_TABEL = "create table " + TABLE_IMAGE + " ("
            + IMAGE_ID + " integer primary key autoincrement,"
            + TRANS_ID2 + " integer,"
            + IMG_COLUM3 + " text,"
            + DESC_COLUM3 + " text,"
            + DATE_ADD__COLUM3 + " integer,"
            + " foreign key (" + TRANS_ID2 + ") references " + TABLE_TRANS + "(" + TRANS_ID + "))";

    //==================================================================================================
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);//Простой конструктор
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);//Трудный конструктор
    }

    DatabaseHelper(Context context) {//Конструктор для даунов
        //Таким образом, можно перенести логику создания базы в отдельный класс.
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KAY_NAME = "name";
    public static final String KAY_MAIL = "mail";

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key," + KAY_NAME + " text," + KAY_MAIL + " text" + ")");*/

        db.execSQL(DATABASE_CREATE_CAT_TABEL);// <- Запускаем наш скрип при создании базы
        db.execSQL(DATABASE_CREATE_SOURSE_TABEL);
        db.execSQL(DATABASE_CREATE_TARNS_TABLE);
        db.execSQL(DATABASE_CREATE_IMAGE_TABEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Это при обнове базы, елси к примеру мы захотим добавить новые колонки в таблицы
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);//Это для логов

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_CREATE_TARNS_TABLE);
        // Создаём новую таблицу
        onCreate(db);
    }
}
