package br.com.streaming.util;

public class FormatadorTempo {

    public static String formatarSegundos(int segundos) {
        int mm = segundos / 60;
        int ss = segundos % 60;
        return String.format("%02d:%02d", mm, ss);
    }

    public static String formatarDuracaoLonga(int segundos) {
        int horas   = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs    = segundos % 60;
        if (horas > 0) {
            return String.format("%dh %02dmin %02ds", horas, minutos, segs);
        }
        return String.format("%dmin %02ds", minutos, segs);
    }
}