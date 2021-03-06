package com.ceiba.cita.utils;

import java.util.ArrayList;
import java.util.Calendar;

public class HolidayUtil {

    private int year;
    private int easterMonth;
    private int easterDay;
    private ArrayList<String> holidays;

    public HolidayUtil(int year) {
        this.year = year;
        this.holidays = new ArrayList<>();
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int g = (8 * b + 13) / 25;
        int h = (19 * a + b - d - g + 15) % 30;
        int j = c / 4;
        int k = c % 4;
        int m = (a + 11 * h) / 319;
        int r = (2 * e + 2 * j - k - h + m + 32) % 7;
        this.easterMonth = (h - m + r + 90) / 25;
        this.easterDay = (h - m + r + this.easterMonth + 19) % 32;
        this.easterMonth--;
        this.holidays.add("1:1");               // Primero de Enero
        this.holidays.add("5:1");               // Dia del trabajo 1 de mayo
        this.holidays.add("7:20");              //Independencia 20 de Julio
        this.holidays.add("8:7");               //Batalla de boyaca 7 de agosto
        this.holidays.add("12:8");              //Maria inmaculada 8 de diciembre
        this.holidays.add("12:25");             //Navidad 25 de diciembre
        this.calculateEmiliani(1, 6);           // Reyes magos 6 de enero
        this.calculateEmiliani(3, 19);          //San jose 19 de marzo
        this.calculateEmiliani(6, 29);          //San pedro y san pablo 29 de junio
        this.calculateEmiliani(8, 15);          //Asuncion 15 de agosto
        this.calculateEmiliani(10, 12);          //Descubrimiento de america 12 de octubre
        this.calculateEmiliani(11, 1);          //Todos los santos 1 de noviembre
        this.calculateEmiliani(11, 11);         //Independencia de cartagena 11 de noviembre
        this.calculateOtherHoliday(-3, false);  //jueves santos
        this.calculateOtherHoliday(-2, false);  //viernes santo
        this.calculateOtherHoliday(40, true);   //Asención del señor de pascua
        this.calculateOtherHoliday(60, true);   //Corpus cristi
        this.calculateOtherHoliday(68, true);   //Sagrado corazon
    }

    private void calculateEmiliani(int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(this.year, month, day);
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                date.add(Calendar.DATE, 1);
                break;
            case 3:
                date.add(Calendar.DATE, 6);
                break;
            case 4:
                date.add(Calendar.DATE, 5);
                break;
            case 5:
                date.add(Calendar.DATE, 4);
                break;
            case 6:
                date.add(Calendar.DATE, 3);
                break;
            case 7:
                date.add(Calendar.DATE, 2);
                break;
            default:
                break;
        }
        this.holidays.add(date.get(Calendar.MONTH) + ":" + date.get(Calendar.DATE));
    }

    private void calculateOtherHoliday(int days, boolean emiliani) {
        Calendar date = Calendar.getInstance();
        date.set(this.year, this.easterMonth, this.easterDay);
        date.add(Calendar.DATE, days);
        if (emiliani) {
            this.calculateEmiliani(date.get(Calendar.MONTH), date.get(Calendar.DATE));
        } else {
            this.holidays.add(date.get(Calendar.MONTH) + ":" + date.get(Calendar.DATE));
        }
    }

    public boolean isHoliday(int month, int day) {
        return this.holidays.contains(month + ":" +day);
    }
}
