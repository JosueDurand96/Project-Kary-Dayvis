package com.example.boosters.ui.Soporte;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

public class SoporteDAO {
    private DbHelper _dbHelper;

    public SoporteDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String codigo, String correo, String alias, String descripcion) throws DAOException {
        Log.i("SoporteDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{codigo, correo, alias, descripcion};
            db.execSQL("INSERT INTO soporte(codigo,correo,alias,descripcion)VALUES(?,?,?,?)", args);
            Log.i("SoporteDAO", "Se inserto");
        } catch (Exception e) {
            throw new DAOException("SoporteDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    public SoporteViewModel obtener() throws DAOException {
        Log.i("SoporteDAO", "obtener()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        SoporteViewModel modelo = new SoporteViewModel();
        try {
            Cursor c = db.rawQuery("select id, codigo, correo, alias, descripcion", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int id = c.getInt(c.getColumnIndex("id"));
                    String codigo = c.getString(c.getColumnIndex("codigo"));
                    String correo = c.getString(c.getColumnIndex("correo"));
                    String alias = c.getString(c.getColumnIndex("alias"));
                    String descripcion = c.getString(c.getColumnIndex("descripcion"));
                    modelo.setId(id);
                    modelo.setCodigo(codigo);
                    modelo.setCorreo(correo);
                    modelo.setAlias(alias);
                    modelo.setDescripcion(descripcion);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("SoporteDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return modelo;
    }

    public ArrayList<SoporteViewModel> buscar(String criterio)
            throws DAOException {
        Log.i("SoporteDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<SoporteViewModel> lista = new ArrayList<SoporteViewModel>();
        try {
            Cursor c = db.rawQuery("select id,codigo,correo,alias,descripcion from soporte where codigo like '%" + criterio + "%' or descripcion like '%" + criterio + "%'", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int id = c.getInt(c.getColumnIndex("id"));
                    String codigo = c.getString(c.getColumnIndex("codigo"));
                    String correo = c.getString(c.getColumnIndex("correo"));
                    String alias = c.getString(c.getColumnIndex("alias"));
                    String descripcion = c.getString(c.getColumnIndex("descripcion"));
                    SoporteViewModel modelo = new SoporteViewModel();
                    modelo.setId(id);
                    modelo.setCodigo(codigo);
                    modelo.setCorreo(correo);
                    modelo.setAlias(alias);
                    modelo.setDescripcion(descripcion);
                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("SoporteDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }

        }
        return lista;
    }
}