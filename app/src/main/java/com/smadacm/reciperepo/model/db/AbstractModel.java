package com.smadacm.reciperepo.model.db;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.smadacm.reciperepo.db.DbHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractModel {
    DbHelper dbHelper;
    Context context;
    List<String> columns;

    String tableName;
    int _id = -1;

    public AbstractModel(Context context){
        this.context = context;
        this.dbHelper = new DbHelper(context);
        this.columns = new ArrayList<>();
    }

    public AbstractModel(Context context, HashMap<String, String> record){
        this.context = context;
        this.dbHelper = new DbHelper(context);
        this.columns = new ArrayList<>();

        this.loadByRecord(record);
    }

    protected abstract void setupColumns();

    public int getId(){ return _id; }

    public boolean load(int id){
        HashMap<String, String> wheres = new HashMap<String, String>();
        wheres.put("_id", Integer.valueOf(id).toString());
        return this.load(wheres);
    }
    public boolean load(HashMap<String, String> wheres){
        Cursor cur = this.dbHelper.selectItem(this.tableName, this.columns, wheres);
        if(cur.getCount() != 1){
            return false;
        }
        for(int i = 0; i < cur.getColumnCount(); i++){
            String colName = cur.getColumnName(i);
            GetFieldReturn fieldReturn = this.getFieldByName(colName);
            if(fieldReturn.isInt()){
                this.set(fieldReturn.field, cur.getInt(i));
            } else if(fieldReturn.isLong()){
                this.set(fieldReturn.field, cur.getLong(i));
            } else if(fieldReturn.isString()){
                this.set(fieldReturn.field, cur.getString(i));
            }
        }
        return true;
    }
    protected boolean loadByRecord(HashMap<String, String> values){
        for (String key : values.keySet()) {
            GetFieldReturn fieldReturn = this.getFieldByName(key);
            String val = values.get(key);

            if(fieldReturn.isInt()){
                this.set(fieldReturn.field, Integer.valueOf(val));
            } else if(fieldReturn.isLong()){
                this.set(fieldReturn.field, Long.valueOf(val));
            } else {
                this.set(fieldReturn.field, val);
            }
        }
        return true;
    }

    public int save(){
        HashMap<String, String> values = this.prepColumnsForUpsert();
        if(this._id > 0){
            this._id = this.dbHelper.insertItem(this.tableName, values);
        } else {
            this.dbHelper.updateItem(this.tableName, values);
        }
        return this._id;
    }

    protected HashMap<String, String> prepColumnsForUpsert(){
        HashMap<String, String> ret = new HashMap<>();
        if(this._id > 0){
            ret.put("_id", Integer.valueOf(this._id).toString());
        }

        GetFieldReturn fieldRet;
        for(int i=0; i<this.columns.size(); i++){
            String columnName = this.columns.get(i);
            fieldRet = this.getFieldByName(columnName);
            if(fieldRet == null) continue;
            String val;

            try {
                if(fieldRet.isAbstractModel()){
                    AbstractModel prop = (AbstractModel) fieldRet.field.get(this);
                    prop.save();
                    val = Integer.valueOf(prop.getId()).toString();
                } else if(fieldRet.isInt()){
                    val = Integer.valueOf((int) fieldRet.field.get(this)).toString();
                } else {
                    val = (String) fieldRet.field.get(this);
                }
            } catch (IllegalAccessException e){
                continue;
            }

            ret.put(columnName, val);
        }

        return ret;
    }

    protected String camelCaseToUnderscore(String toConvert){
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return toConvert
                .replaceAll(regex, replacement)
                .toLowerCase();
    }
    protected String underscoreToCamelCase(String toConvert){
        String[] parts = toConvert.split("_");
        StringBuilder partsFixed = new StringBuilder();
        for(int i=0; i<parts.length; i++){
            String part = parts[i];
            if(part.length() <= 0) continue;
            if(i != 0){
                part = part.substring(0, 1).toUpperCase() + part.substring(1);
            }
            partsFixed.append(part);
        }
        return partsFixed.toString();
    }

    protected GetFieldReturn getFieldByName(String name){
        if(name.contains("_") || name.toLowerCase().equals(name)){
            name = this.underscoreToCamelCase(name);
        }

        Class<?> cls = this.getClass();
        Field field;
        try {
            field = cls.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return null;
        }
        field.setAccessible(true);
        return new GetFieldReturn(field);
    }

    public boolean set(String fieldName, Object value){
        GetFieldReturn fieldReturn = this.getFieldByName(fieldName);
        if(fieldReturn.isInt()){
            int ival = (int) value;
            return this.set(fieldReturn, ival);
        } else if(fieldReturn.isLong()){
            long lval = (long) value;
            return this.set(fieldReturn, lval);
        }
        return this.set(fieldReturn, value);
    }
    protected boolean set(GetFieldReturn field, int value){
        return this.set(field.field, value);
    }
    protected boolean set(GetFieldReturn field, long value){
        return this.set(field.field, value);
    }
    protected boolean set(GetFieldReturn field, Object value){
        return this.set(field.field, value);
    }
    protected boolean set(Field field, Object value){
        try {
            field.set(this, value);
        } catch(IllegalAccessException e){
            return false;
        }
        return true;
    }
    protected boolean set(Field field, int value){
        try {
            field.setInt(this, value);
        } catch(IllegalAccessException e){
            return false;
        }
        return true;
    }
    protected boolean set(Field field, long value){
        try {
            field.setLong(this, value);
        } catch(IllegalAccessException e){
            return false;
        }
        return true;
    }

    private class GetFieldReturn {
        Field field;
        String fieldName;
        Class<?> fieldClass;

        String dbColumnName;

        public GetFieldReturn(Field field){
            this.field = field;
            this.fieldName = field.getName();
            this.fieldClass = field.getType();
            this.dbColumnName = camelCaseToUnderscore(field.getName());
        }

        public GetFieldReturn(Field field, String dbColumnName){
            this.field = field;
            this.fieldName = field.getName();
            this.fieldClass = field.getType();
            this.dbColumnName = dbColumnName;
        }

        public boolean isLong(){
            return this.fieldClass.equals(Long.TYPE);
        }
        public boolean isInt(){
            return this.fieldClass.equals(Integer.TYPE);
        }
        public boolean isString(){
            return this.fieldClass.equals(String.class);
        }
        public boolean isAbstractModel(){
            return this.fieldClass.isAssignableFrom(AbstractModel.class);
        }
    }
}
