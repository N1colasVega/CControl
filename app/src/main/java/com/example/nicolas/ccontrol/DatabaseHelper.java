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
    private static final String DATABASE_NAME = "mydatabase.db";
    // версия базы данных
    private static final int DATABASE_VERSION = 1;
//ПРИМЕР СОЗДАНИЕ ===================================================================
    // имя таблицы
    public static final String DATABASE_TABLE = "nameTabel";

    // названия столбцов
    public static final String NAME1_COLUMN = "____";
    public static final String NAME2_COLUMN = "____";
    public static final String NAME3_COLUMN = "____";
    //И так далее.................................

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + NAME1_COLUMN
            + " text not null, " + NAME2_COLUMN + " integer, " + NAME3_COLUMN
            + " integer);";
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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT); // <- Запускаем наш скрип при создании базы
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //Это при обнове базы, елси к примеру мы захотим добавить новые колонки в таблицы
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);//Это для логов

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        // Создаём новую таблицу
        onCreate(db);
    }
}
