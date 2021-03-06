package com.polito.did2017.lampapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by asus on 06/12/2017.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class LampManager {


    private static LampManager ourInstance = null;
    private List<Lamp> lista = Collections.synchronizedList(new ArrayList());
    private Context ctx;
    //SharedPreferences mPrefs;
    //Map<String, ?> allEntries;
    //SharedPreferences.Editor editor;

    private LampManager() {
        //this.ctx = ctx.getApplicationContext();
        //mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        //allEntries = mPrefs.getAll();
        //editor = mPrefs.edit();
    }


    public static synchronized LampManager getInstance() {
        if (ourInstance == null) ourInstance = new LampManager();
        return ourInstance;
    }


    /*public void setLamps() {
        boolean b = true;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String url = null;
            Log.d("map_values", entry.getKey() + ": " + entry.getValue().toString());
            if (entry.getKey().contains("URL")) {
                url = (String) entry.getValue().toString();
                int i = entry.getKey().indexOf(":", 2);
                Log.d("map_values", entry.getKey() + i + ": " + entry.getValue().toString());
                String name = entry.getKey().substring(0, i);
                System.out.println(name);
                Lamp l = new Lamp(url, name, ctx);
                synchronized (lista) {
                    for (int pos = 0; pos < lista.size(); pos++) {
                        if (getLamp(pos).getName().equals(name)) {
                            b = false;
                            break;
                        } else
                            b = true;
                    }
                    if (b)
                        lista.add(l);
                    Log.d("Stringa add", lista.toString());
                }
            }
        }
    }*/

    public List<Lamp> getLamps() {
        Collections.sort(lista);
        return lista;
    }

    public int getPosLamp(String name){
        int i;
        synchronized (lista) {
            for (i = 0; i < lista.size(); i++) {
                if (name.equals(lista.get(i).getName())){
                    lista.remove(lista.get(i));
                    break;
                }
            }
        }
        return i;
    }

    public Lamp getLamp(int i) {
        synchronized (lista) {
            Collections.sort(lista);
            return lista.get(i);
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public int posLamp(String name){
        int i;
        synchronized (lista) {
            for (i = 0; i < lista.size(); i++) {
                if (name.equals(lista.get(i).getName())){
                    //if (pos==i){
                    break;
                }
            }
        }
        return i;
    }



    public void removeLamp(String name) {
        //Log.d("REMOVELAMP", lista.get(i).getName());
        /*
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getValue().toString().contains(name) || entry.getKey().contains(name)) {
                editor.remove(entry.getKey());
            }
        }
        */
        int i;
        synchronized (lista) {
            for (i = 0; i < lista.size(); i++) {
                if (name.equals(lista.get(i).getName())){
                //if (pos==i){
                    Log.d("Stringa Swipe", lista.get(i).getName());
                    lista.remove(lista.get(i));
                    break;
                }
            }
        }
        Collections.sort(lista);
        //editor.commit();
    }

    public boolean addLamp(Boolean s, String URL, int lum, int col, int win, String name, String img) throws InterruptedException {
        boolean b = true;
        synchronized (lista) {
            try {
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println(lista.get(i).getName() + ".equals" + name);
                    if (lista.get(i).getName().equals(name)) {
                        b = false;
                        if (!lista.get(i).getURL().equals(URL)) {
                            lista.get(i).setURL(URL);
                        }
                        break;
                    } else
                        b = true;
                }
            } catch (Exception e) {
                Log.d("ERR", e.getMessage());
            }
            if (b) {
                Lamp l = new Lamp(URL, name);
                lista.add(l);
                l.setState(s);
                l.setColor(col);
                l.setIntensity(lum);
                l.setWing(win);
                if(img == null || img.compareTo("")==0)l.setPicture("https://firebasestorage.googleapis.com/v0/b/lampapp-6688e.appspot.com/"+
                        "o/Flower_Lamp.png?alt=media&token=50681145-8f46-4cfe-8ea0-e2bebb78785a");
                else l.setPicture(img);
                new TcpClient(l).execute();

            }
        }
        Collections.sort(lista);
        return b;
    };
}


