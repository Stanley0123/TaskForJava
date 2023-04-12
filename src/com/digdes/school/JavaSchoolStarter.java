package com.digdes.school;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaSchoolStarter {
    public static List<Map<String, Object>> array = new ArrayList<>();
    private Validator validator = new Validator();

    public List<Map<String, Object>> execute(String str) throws Exception {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Commands commands = validator.execute(str);
        switch (commands){
            case INSERT -> {
                //System.out.println("INSERT VALIDATION IS OK");
                mapList = insert(str);
            }
            case UPDATE -> {
                //System.out.println("UPDATE VALIDATION IS OK");
                mapList = update(str);
            }
            case SELECT -> {
                //System.out.println("SELECT VALIDATION IS OK");
                mapList = select(str);
            }
            case DELETE -> {
                //System.out.println("DELETE VALIDATION IS OK");
                mapList = delete(str);

            }
        }
        //--------------------------------------------------------------------------------------------------------------------
        return mapList;
    }

    private List<Map<String, Object>> insert(String str) throws Exception {
        Pattern p = Pattern.compile(RegexContainer.valuesWord);
        Matcher m = p.matcher(str);
        m.find();
        ValueAndWhereScanner valueAndWhereScanner = new ValueAndWhereScanner();
        int valuesPosition = m.end();
        String valuesString = str.substring(valuesPosition, str.length());
        Map<String, Object> map = valueAndWhereScanner.valuesScanner(valuesString);
        Map<String, Object> mapBuf = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        if (!map.containsKey("lastName")){
            map.put("lastName", null);
        }
        if (!map.containsKey("id")){
            map.put("id", null);
        }
        if (!map.containsKey("cost")){
            map.put("cost", null);
        }
        if (!map.containsKey("age")){
            map.put("age", null);
        }
        if (!map.containsKey("active")){
            map.put("active", null);
        }
        boolean allNull = true;
        for(Map.Entry<String, Object> entry: map.entrySet()) {
            if (entry.getValue() != null){
                allNull = false;
                mapBuf.put(entry.getKey(), entry.getValue());
            }
        }
        if (allNull){
            throw new Exception();
        }
        array.add(map);
        list.add(mapBuf);
        return list;
    }

    private List<Map<String, Object>> select(String str) {
        Pattern p = Pattern.compile(RegexContainer.whereWord);
        Matcher m = p.matcher(str);
        if (m.find()){
            ValueAndWhereScanner valueAndWhereScanner = new ValueAndWhereScanner();
            int wherePosition = m.end();
            String whereString = str.substring(wherePosition, str.length());
            return array.stream().filter(valueAndWhereScanner.whereScanner(whereString)).toList();
        }
        else {
            return array;
        }
    }

    private List<Map<String, Object>> update(String str) throws Exception{
        Pattern p = Pattern.compile(RegexContainer.valuesWord);
        Matcher m = p.matcher(str);
        Pattern p1 = Pattern.compile(RegexContainer.whereWord);
        Matcher m1 = p1.matcher(str);
        List<Map<String, Object>> listMapBuf = new ArrayList<>();
        if (!m1.find()){
            m.find();
            int valuesPosition = m.end();
            String valuesString = str.substring(valuesPosition, str.length());
            ValueAndWhereScanner valueAndWhereScanner = new ValueAndWhereScanner();
            Map<String, Object> map = valueAndWhereScanner.valuesScanner(valuesString);
            array.stream().forEach(x -> {
                if (map.containsKey("lastName")){
                    x.replace("lastName", map.get("lastName"));
                }
                if (map.containsKey("cost")){
                    x.replace("cost", map.get("cost"));
                }
                if (map.containsKey("age")){
                    x.replace("age", map.get("age"));
                }
                if (map.containsKey("id")){
                    x.replace("id", map.get("id"));
                }
                if (map.containsKey("active")){
                    x.replace("active", map.get("active"));
                }
                Map<String, Object> mapBuf = new HashMap<>(x);
                boolean allNull = true;
                for(Map.Entry<String, Object> entry: map.entrySet()) {
                    if (entry.getValue() != null){
                        allNull = false;
                        break;
                    }
                }
                if (allNull){
                    //System.out.println("Update");
                    throw new RuntimeException();
                }
                if (mapBuf.containsKey("lastName") && mapBuf.get("lastName") == null){
                    mapBuf.remove("lastName");
                }
                if (mapBuf.containsKey("id") && mapBuf.get("id") == null){
                    mapBuf.remove("id");
                }
                if (mapBuf.containsKey("cost") && mapBuf.get("cost") == null){
                    mapBuf.remove("cost");
                }
                if (mapBuf.containsKey("age") && mapBuf.get("age") == null){
                    mapBuf.remove("age");
                }
                if (mapBuf.containsKey("active") && mapBuf.get("active") == null){
                    mapBuf.remove("active");
                }
                listMapBuf.add(mapBuf);
            });
        }
        else {
            m.find();
            Pattern p2 = Pattern.compile(RegexContainer.whereWord);
            Matcher m2 = p2.matcher(str);
            if (m2.find()){
                int valuesPosition = m.end();
                int whereStartPosition = m2.start();
                int whereFinalePosition = m2.end();

                String valuesString = str.substring(valuesPosition, whereStartPosition);
                String whereString = str.substring(whereFinalePosition, str.length());
                ValueAndWhereScanner valueAndWhereScanner = new ValueAndWhereScanner();
                Map<String, Object> map = valueAndWhereScanner.valuesScanner(valuesString);
                array.stream().filter(valueAndWhereScanner.whereScanner(whereString)).forEach(x -> {
                    if (map.containsKey("lastName")){
                        x.replace("lastName", map.get("lastName"));
                    }
                    if (map.containsKey("cost")){
                        x.replace("cost", map.get("cost"));
                    }
                    if (map.containsKey("age")){
                        x.replace("age", map.get("age"));
                    }
                    if (map.containsKey("id")){
                        x.replace("id", map.get("id"));
                    }
                    if (map.containsKey("active")){
                        x.replace("active", map.get("active"));
                    }
                    Map<String, Object> mapBuf = new HashMap<>(x);
                    boolean allNull = true;
                    for(Map.Entry<String, Object> entry: map.entrySet()) {
                        if (entry.getValue() != null){
                            allNull = false;
                            break;
                        }
                    }
                    if (allNull){
                        //System.out.println("UpdateWhere");
                        throw new RuntimeException();
                    }
                    if (mapBuf.containsKey("lastName") && mapBuf.get("lastName") == null){
                        mapBuf.remove("lastName");
                    }
                    if (mapBuf.containsKey("id") && mapBuf.get("id") == null){
                        mapBuf.remove("id");
                    }
                    if (mapBuf.containsKey("cost") && mapBuf.get("cost") == null){
                        mapBuf.remove("cost");
                    }
                    if (mapBuf.containsKey("age") && mapBuf.get("age") == null){
                        mapBuf.remove("age");
                    }
                    if (mapBuf.containsKey("active") && mapBuf.get("active") == null){
                        mapBuf.remove("active");
                    }
                    listMapBuf.add(mapBuf);
                });
            }
        }

        return listMapBuf;
    }

    private List<Map<String, Object>> delete(String str) {
        Pattern p = Pattern.compile(RegexContainer.whereWord);
        Matcher m = p.matcher(str);
        List<Map<String, Object>> listok;
        if (m.find()){
            ValueAndWhereScanner valueAndWhereScanner = new ValueAndWhereScanner();
            int wherePosition = m.end();
            String whereString = str.substring(wherePosition, str.length());
            listok = array.stream().filter(valueAndWhereScanner.whereScanner(whereString)).toList();
            array.removeIf(valueAndWhereScanner.whereScanner(whereString));
            return listok;
        }
        else {
            List<Map<String, Object>> listDelete = new ArrayList<>(array);
            array.clear();
            return listDelete;
        }
    }

}
