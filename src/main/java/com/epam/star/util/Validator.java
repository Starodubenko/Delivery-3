package com.epam.star.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final String USER_NAME = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private final String USER_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    private final String ADDRESS = "^[a-zA-Z][a-zA-Z0-9-_\\. ]{1,19}$";
    private final String DATE = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d";
    private final String TIME = "^([0-1]\\d|2[0-3])(:[0-5]\\d){2}$";
    private final String TELEPHONE_NUMBER = "^((8|\\+7)[\\- ]?)(\\(?\\d{3}\\)?[\\- ]?)(\\d{3}[\\- ]?)(\\d{2}[\\- ]?)(\\d{2})$";
    private final String IDENTITY_CARD = "(\\d{1,})?";
    private final String RNN = "(\\d{12})?";
    private final String SIK = "[a-zA-Z0-9]{16}$";
    private final String WORK_BOOK = "[a-zA-Z0-9]{16}$";
    private final String E_mail = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

    private Matcher matcher;
    private Pattern pattern;

    private Map<String, String> results = new HashMap<>();
    private List<String> invalidFields = new ArrayList<>();

    public boolean isValide(){

        for (Map.Entry<String, String> stringEntry : results.entrySet()) {
            if (stringEntry.getValue().equals("false")){
                invalidFields.add(stringEntry.getKey());
            }
        }

        if (invalidFields.isEmpty()) return true;
        else return false;
    }

    public List<String> getInvalidFields() {
        return invalidFields;
    }

    public boolean checkUserName(String name){
        pattern = Pattern.compile(USER_NAME);
        matcher = pattern.matcher(name);
        results.put("login", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserPassword(String password){
        pattern = Pattern.compile(USER_PASSWORD);
        matcher = pattern.matcher(password);
        results.put("password", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserFirsName(String firstname){
        pattern = Pattern.compile(USER_NAME);
        matcher = pattern.matcher(firstname);
        results.put("firstname", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserLastName(String lastname){
        pattern = Pattern.compile(USER_NAME);
        matcher = pattern.matcher(lastname);
        results.put("lastname", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserMiddleName(String middlename){
        pattern = Pattern.compile(USER_NAME);
        matcher = pattern.matcher(middlename);
        results.put("middlename", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserAddress(String address){
        pattern = Pattern.compile(ADDRESS);
        matcher = pattern.matcher(address);
        results.put("address", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserTelephone(String telephone){
        pattern = Pattern.compile(TELEPHONE_NUMBER);
        matcher = pattern.matcher(telephone);
        results.put("telephone", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserPMobilephone(String mobilephone){
        pattern = Pattern.compile(TELEPHONE_NUMBER);
        matcher = pattern.matcher(mobilephone);
        results.put("mobilephone", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserIdentityCard(String identitycard){
        pattern = Pattern.compile(IDENTITY_CARD);
        matcher = pattern.matcher(identitycard);
        results.put("identitycard", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserWorkBook(String workbook){
        pattern = Pattern.compile(WORK_BOOK);
        matcher = pattern.matcher(workbook);
        results.put("workbook", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserRNN(String rnn){
        pattern = Pattern.compile(RNN);
        matcher = pattern.matcher(rnn);
        results.put("rnn", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkUserSIK(String sik){
        pattern = Pattern.compile(SIK);
        matcher = pattern.matcher(sik);
        results.put("sik", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkDate(String date){
        pattern = Pattern.compile(DATE);
        matcher = pattern.matcher(date);
        results.put("date", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkTime(String time){
        pattern = Pattern.compile(TIME);
        matcher = pattern.matcher(time);
        results.put("time", String.valueOf(matcher.matches()));
        return matcher.matches();
    }

    public boolean checkEmail(String email){
        pattern = Pattern.compile(E_mail);
        matcher = pattern.matcher(email);
        results.put("email", String.valueOf(matcher.matches()));
        return matcher.matches();
    }
}
