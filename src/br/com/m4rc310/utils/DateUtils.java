/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.utils;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Esta classe apresenta metodos que facilitam o uso de datas na aplicação
 *
 * @author Marcelo Lopes da Silva - 647497 UTFPR
 */
public class DateUtils {

    private static final String ESPACADOR = "/";

//    ResourceBundle rb = ResourceBundle.getBundle("sistemaestagio/utils/Bundle");
    /**
     * Retorna a data atual do sistema
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * Recebe por parametro a data no formato String e retorna uma data no
     * formato Date
     *
     * @param data é a data no formato string que será convertida para Date()
     * @return um valor Date()
     */
    
    public static void main(String[] args) throws ParseException, Exception {
        System.out.println(
        getDate("29/02/1979"));
    }
    
    
    public static Date getDate(String data) throws ParseException, Exception {
        if (!validaData(data)) {
            Calendar calendar = GregorianCalendar.getInstance();
            int mes = calendar.get(Calendar.MONTH);
            int ano = calendar.get(Calendar.YEAR);

            StringBuilder sb = new StringBuilder();

            //      só um falor entre 1 e 31 referente ao dia do mês      
            String redex1 = "([1-9]|0[1-9]|1[0-9]|2[0-9]|3[01])";
            String redex2 = "([1-9]|[0-2][0-9]|3[01])([/.-|])([1-9]|[0][1-9]|1[012])";
            String redex3 = "([0-2][0-9]|3[01])([/.-]|)([0][0-9]|1[012])\\2(\\d{1,4})";
            
            data = data.replace("/", "");
            data = data.replace("-", "");
            data = data.replace(".", "");

            if (data.matches(redex1)) {
                sb.append(String.format("%02d", Integer.parseInt(data)));

                sb.append(ESPACADOR);

                sb.append(String.format("%02d", mes));

                sb.append(ESPACADOR);

                sb.append(ano);
            } 
            
            if (data.matches(redex2)) {

                if (data.length() == 3) {
                    sb.append(String.format("%02d", Integer.parseInt(data.substring(0, 2))));

                    sb.append(ESPACADOR);

                    sb.append(String.format("%02d", Integer.parseInt(data.substring(2, 3))));

                    sb.append(ESPACADOR);

                    sb.append(ano);
                }
                
                if (data.length() == 4) {
                    sb.append(String.format("%02d", Integer.parseInt(data.substring(0, 2))));

                    sb.append(ESPACADOR);

                    sb.append(String.format("%02d", Integer.parseInt(data.substring(2, 4))));

                    sb.append(ESPACADOR);

                    sb.append(ano);
                }
            }
            if (data.matches(redex3)) {
                sb.append(String.format("%02d", Integer.parseInt(data.substring(0, 2))));

                sb.append(ESPACADOR);

                sb.append(String.format("%02d", Integer.parseInt(data.substring(2, 4))));

                sb.append(ESPACADOR);
                sb.append(String.format("%02d", Integer.parseInt(data.substring(4, data.length()))));
                
            }

            if(validaData(sb.toString())){
                return new SimpleDateFormat("dd/MM/yyyy").parse(sb.toString());
            }

            throw new Exception(ResourceBundle.getBundle("resource/Bundle").getString("data.invalida"));
        }
        return new SimpleDateFormat("dd/MM/yyyy").parse(data);
    }

    /**
     * Recebe por parametro a data no formato String e retorna uma data no
     * formato Date
     *
     * @param formato é a formato em que os dados estão sendo infomados
     * Exemplo:<br /> "dd/MM/yyyy"
     * @param data é a data no formato string que será convertida para Date()
     * @return um valor Date()
     */
    public static Date getDate(String formato, String data) throws ParseException {
        formato = formato == null ? "dd/MM/yyyy" : formato;
        data = data == null ? new Date().toString() : data;

        return new SimpleDateFormat(formato).parse(data);
    }

    /**
     *
     * @param formato
     * @param data
     * @return
     * @throws Exception
     */
    public static String getDateString(String formato, String data) throws Exception {
        formato = formato == null ? "dd/MM/yyyy" : formato;
        data = data == null ? new Date().toString() : data;

        return new SimpleDateFormat(formato).parse(data).toString();
    }
    final static long MSEC_PER_HOUR = 1000L * 60L * 60L; // Numero de milisegundos numa hora
    public final static long MSEC_PER_DAY = MSEC_PER_HOUR * 24L; // Numero de milisegundos ao dia 

    public static Long getIdade(Date dataNascimento) throws Exception {

        long difDay = ((new Date().getTime() - dataNascimento.getTime()) / (24 * 60 * 60 * 1000));

//        Calendar c = new GregorianCalendar();
//        c.setTime(d1);
//        int days = c.get(Calendar.DAY_OF_YEAR);

        difDay = (long) (difDay / 365.25);

        return difDay;
    }

    public static String getDiaDaSemana(Date data) throws Exception, ParseException {
        Date d = data;
        String[] dias = new String[]{"Sabado", "Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"};

        Calendar calendario = new GregorianCalendar();
        calendario.setTime(d);



        return dias[calendario.get(Calendar.DAY_OF_WEEK)];


    }

    public static boolean validaData(String data) throws ParseException {
        Boolean ret = false;
        String DatePattern = "^"
                + "(?:(31)(\\D)(0?[13578]|1[02])\\2|(29|30)(\\D)(0?[13-9]|1[0-2])\\5|(0?[1-9]|1\\d|2[0-8])(\\D)(0?[1-9]|1[0-2])\\8)"
                + "((?:1[6-9]|[2-9]\\d)?\\d{2})$|"
                + "^"
                + "(29)(\\D)(0?2)\\12((?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$";

        if (data.matches(DatePattern)) {
            ret = true;


        } else {
            ret = false;


        }

        return ret;


    }

    public static boolean validaData(Date data) throws Exception {
        return validaData(dateToString(data));


    }

    public Calendar getCalendar(Date date) {
        GregorianCalendar ret = new GregorianCalendar();
        ret.setTime(date);
        return ret;
    }

    public static String dateToString(Date data, String format) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        StringBuilder dateString = new StringBuilder(dateFormat.format(data));

        return dateString.toString();
    }

    public static String dateToString(Date data) throws Exception {
        return dateToString(data, "dd/mm/yyyy");
    }

}
