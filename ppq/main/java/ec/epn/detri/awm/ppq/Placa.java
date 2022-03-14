package ec.epn.detri.awm.ppq;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Placa {
    private String numero;
    private Integer last;

    public Placa(String placa){
        this.numero = placa;
        this.last = Integer.parseInt(placa.substring(placa.length() - 1));
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }
    //Considere dia Domingo =1, Lunes=2, etc.
    public static Boolean puedoCircular(Integer dia, Integer ultimoDigito) {
        if (((dia == 2) && (ultimoDigito == 1 || ultimoDigito == 2)) ||
                        ((dia == 3) && (ultimoDigito == 3 || ultimoDigito == 4)) ||
                        ((dia == 4) && (ultimoDigito == 5 || ultimoDigito == 6)) ||
                        ((dia == 5) && (ultimoDigito == 7 || ultimoDigito == 8)) ||
                        ((dia == 6) && (ultimoDigito == 9 || ultimoDigito == 0))){
            return false;
        }else{
            return true;
        }
    }
    public static Boolean placaEsParticular(String placa){
        char digito;
        Pattern pattern = Pattern.compile("[A-Z]{3}-[0-9]{4}");
        Matcher matcher = pattern.matcher(placa);
        digito = placa.charAt(1);
        if(matcher.matches() == true && (digito != 'A' && digito != 'Z' && digito != 'E' && digito != 'X' && digito != 'S' && digito != 'M')){
            return true;}
        else{
            return false;
        }
    }

    public static String placaEsParticularString(String placa){
        char digito;
        Pattern pattern = Pattern.compile("[A-Z]{3}-[0-9]{4}");
        Matcher matcher = pattern.matcher(placa);
        digito = placa.charAt(1);
        if(matcher.matches() == true && (digito != 'A' && digito != 'Z' && digito != 'E' && digito != 'X' && digito != 'S' && digito != 'M')){
            return "Es Particular";}
        else{
            return "No es Particular";
        }
    }

}
