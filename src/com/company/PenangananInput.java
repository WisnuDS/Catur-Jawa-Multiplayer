package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PenangananInput {
    private final static Pattern validMove = Pattern.compile("([a-cA-C][1-3])([-])([a-cA-C][1-3])", Pattern.CASE_INSENSITIVE);
    private final static Pattern validPut = Pattern.compile("([a-cA-C][1-3])");
    private final BoardMapper mapper;

    public PenangananInput(){
        mapper = new BoardMapper();
    }

    public Tuple parse(String val){
        int x = BoardMapper.map(val.charAt(0));
        int y = BoardMapper.map(Integer.parseInt(String.valueOf(val.charAt(1))));

        return new Tuple(y, x);
    }

    public Tuple getFrom(String val){
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords = matcher.group(1);

        return parse(coords);
    }

    public Tuple getTo(String val){
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords =  matcher.group(3);

        return parse(coords);
    }

    public boolean isValid(String val){
        Matcher matcher = validMove.matcher(val);
        System.out.println(matcher.matches());
        return matcher.matches();
    }

    public boolean isValidPut(String val){
        Matcher matcher = validPut.matcher(val);

        return matcher.matches();
    }
}
