package com.digdes.school;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public Commands execute(String bufferString) throws Exception {
        Commands commands;
        String[] massive = bufferString.split("(,|(\\s+(?i)AND\\s+)|(\\s+(?i)OR\\s+))");
//        for (String val : massive) {
//            System.out.print("|" + val + "|" + "\t");
//        }
        Validator validator = new Validator();
        if (massive[0].matches(RegexContainer.insert + RegexContainer.valuesEquals)) {
            //INSERT VALUES ‘lastName’ = ‘Федоров’ , ‘id’=3, ‘age’=40, ‘active’=true
            validator.validation(massive, bufferString, Commands.INSERT);
//            System.out.println("Validation INSERT is OK!");
            commands = Commands.INSERT;

        } else if (massive[0].matches(RegexContainer.update + RegexContainer.valuesEquals + RegexContainer.whereCondition)) {
            validator.validation(massive, bufferString, Commands.UPDATE);
//            System.out.println("Validation UPDATE is OK!");
            commands = Commands.UPDATE;
        } else if (massive[0].matches(RegexContainer.select + RegexContainer.whereCondition)) {
            validator.validation(massive, bufferString, Commands.SELECT);
//            System.out.println("Validation SELECT is OK!");
            commands = Commands.SELECT;
        } else if (massive[0].matches(RegexContainer.delete + RegexContainer.whereCondition)) {
            validator.validation(massive, bufferString, Commands.DELETE);
//            System.out.println("Validation DELETE is OK!");
            commands = Commands.DELETE;
        } else {
            throw new Exception();
        }
        return commands;

    }
    public void validation(String[] bufferArray, String bufferString, Commands command) throws Exception {
        switch (command) {
            case INSERT -> {
                InsertValidation(bufferArray, bufferString);
                break;
            }
            case UPDATE -> {
                UpdateValidation(bufferArray, bufferString);
                break;
            }
            case SELECT -> {
                SelectValidation(bufferArray, bufferString);
                break;
            }
            case DELETE -> {
                DeleteValidation(bufferArray, bufferString);
                break;
            }
        }
    }

    private void InsertValidation(String[] bufferArray, String bufferString) throws Exception {
        boolean idFlag = false;
        boolean costFlag = false;
        boolean lastNameFlag = false;
        boolean activeFlag = false;
        boolean ageFlag = false;
        boolean errors = false;
        for (int i = 0; i < bufferArray.length; i++) {
            if (i == 0) {
                if (bufferArray[i].matches(RegexContainer.insert + RegexContainer.values + RegexContainer.idEqual)) {
                    idFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.insert + RegexContainer.values + RegexContainer.costEqual)) {
                    costFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.insert + RegexContainer.values + RegexContainer.lastNameEqual)) {
                    lastNameFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.insert + RegexContainer.values + RegexContainer.activeEqual)) {
                    activeFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.insert + RegexContainer.values + RegexContainer.ageEqual)) {
                    ageFlag = true;
                } else {
                    throw new Exception();
                }
            } else {
                if (bufferArray[i].matches(RegexContainer.idEqual) && idFlag == false) {
                    idFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.costEqual) && costFlag == false) {
                    costFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.lastNameEqual) && lastNameFlag == false) {
                    lastNameFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.activeEqual) && activeFlag == false) {
                    activeFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.ageEqual) && ageFlag == false) {
                    ageFlag = true;
                } else {
                    throw new Exception();
                }
            }
        }
        Pattern p = Pattern.compile(RegexContainer.orAndWord);
        Matcher m = p.matcher(bufferString);
        if (m.find()){
            throw new Exception();
        }

    }
    private void UpdateValidation(String[] bufferArray, String bufferString) throws Exception {
        boolean idFlag = false;
        boolean costFlag = false;
        boolean lastNameFlag = false;
        boolean activeFlag = false;
        boolean ageFlag = false;
        boolean whereFlag = false;
        for (int i = 0; i < bufferArray.length; i++) {
            if (i == 0) {
                if (bufferArray[i].matches(RegexContainer.update + RegexContainer.values + RegexContainer.idEqual)) {
                    idFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.update + RegexContainer.values + RegexContainer.costEqual)) {
                    costFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.update + RegexContainer.values + RegexContainer.lastNameEqual)) {
                    lastNameFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.update + RegexContainer.values + RegexContainer.activeEqual)) {
                    activeFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.update + RegexContainer.values + RegexContainer.ageEqual)) {
                    ageFlag = true;
                }
                //--------------------------------------
                else if (bufferArray[i].matches(RegexContainer.update + RegexContainer.values + RegexContainer.idEqual + RegexContainer.whereNecessaryCondition)) {
                    idFlag = true;
                    whereFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.update + RegexContainer.values + RegexContainer.costEqual + RegexContainer.whereNecessaryCondition)) {
                    costFlag = true;
                    whereFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.update + RegexContainer.values + RegexContainer.lastNameEqual + RegexContainer.whereNecessaryCondition)) {
                    lastNameFlag = true;
                    whereFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.update + RegexContainer.values + RegexContainer.activeEqual + RegexContainer.whereNecessaryCondition)) {
                    activeFlag = true;
                    whereFlag = true;
                } else if (bufferArray[i].matches(RegexContainer.update + RegexContainer.values + RegexContainer.ageEqual + RegexContainer.whereNecessaryCondition)) {
                    ageFlag = true;
                    whereFlag = true;
                } else {
                    throw new Exception();
                }
            } else {
                if (whereFlag == false){
                    if (bufferArray[i].matches(RegexContainer.idEqual) && idFlag == false) {
                        idFlag = true;
                    } else if (bufferArray[i].matches(RegexContainer.costEqual) && costFlag == false ) {
                        costFlag = true;
                    } else if (bufferArray[i].matches(RegexContainer.lastNameEqual) && lastNameFlag == false ) {
                        lastNameFlag = true;
                    } else if (bufferArray[i].matches(RegexContainer.activeEqual) && activeFlag == false) {
                        activeFlag = true;
                    } else if (bufferArray[i].matches(RegexContainer.ageEqual) && ageFlag == false) {
                        ageFlag = true;
                    }
                    //--------------------
                    else if (bufferArray[i].matches(RegexContainer.idEqual + RegexContainer.whereNecessaryCondition) && idFlag == false) {
                        idFlag = true;
                        whereFlag = true;
                    } else if (bufferArray[i].matches(RegexContainer.costEqual + RegexContainer.whereNecessaryCondition) && costFlag == false) {
                        costFlag = true;
                        whereFlag = true;
                    } else if (bufferArray[i].matches(RegexContainer.lastNameEqual + RegexContainer.whereNecessaryCondition) && lastNameFlag == false) {
                        lastNameFlag = true;
                        whereFlag = true;
                    } else if (bufferArray[i].matches(RegexContainer.activeEqual + RegexContainer.whereNecessaryCondition) && activeFlag == false) {
                        activeFlag = true;
                        whereFlag = true;
                    } else if (bufferArray[i].matches(RegexContainer.ageEqual + RegexContainer.whereNecessaryCondition) && ageFlag == false) {
                        ageFlag = true;
                        whereFlag = true;
                    }
                    else {
                        throw new Exception();
                    }
                } else if (whereFlag == true) {
                    if (bufferArray[i].matches(RegexContainer.groupConditionAndBracket)){
                        //Empty
                    }
                    else {
                        throw new Exception();
                    }

                }
                else {
                    throw new Exception();
                }

            }
        }
        Pattern p = Pattern.compile(RegexContainer.whereWord);
        Matcher m = p.matcher(bufferString);
        String andOrRegex = RegexContainer.orAndWord;
        if (m.find()){
            int medium = m.start();
            String firstHalf = bufferString.substring(0, medium);
            String secondHalf = bufferString.substring(medium, bufferString.length());
            Pattern pattern1 = Pattern.compile(andOrRegex);
            Matcher matcher1 = pattern1.matcher(firstHalf);
            if (matcher1.find()){
                throw new Exception();
            }
            Pattern pattern2 = Pattern.compile("(,)");
            Matcher matcher2 = pattern2.matcher(secondHalf);
            if (matcher2.find()){
                throw new Exception();
            }
        }
        else {
            Pattern pattern = Pattern.compile(andOrRegex);
            Matcher matcher = pattern.matcher(bufferString);
            if (matcher.find()){
                throw new Exception();
            }
        }
        bracketChecker(bufferString);
    }

    private void SelectValidation(String[] bufferArray, String bufferString) throws Exception {
        boolean whereFlag = false;
        boolean errors = false;
        for (int i = 0; i < bufferArray.length; i++) {
            if (i == 0) {
                if (bufferArray[i].matches(RegexContainer.select)) {
                    //empty
                }
                //--------------------------------------
                else if (bufferArray[i].matches(RegexContainer.select + RegexContainer.whereNecessaryCondition)) {
                    whereFlag = true;
                } else {
                    throw new Exception();
                }
            } else {
                if (bufferArray[i].matches(RegexContainer.groupConditionAndBracket) && whereFlag == true) {
                    //Empty
                } else {
                    throw new Exception();
                }

            }
        }
        Pattern pattern = Pattern.compile("(,)");
        Matcher matcher = pattern.matcher(bufferString);
        if (matcher.find()){
            throw new Exception();
        }
        bracketChecker(bufferString);
    }

    private void DeleteValidation(String[] bufferArray, String bufferString) throws Exception {
        boolean whereFlag = false;
        boolean errors = false;
        for (int i = 0; i < bufferArray.length; i++) {
            if (i == 0) {
                if (bufferArray[i].matches(RegexContainer.delete)) {
                    //empty
                }
                //--------------------------------------
                else if (bufferArray[i].matches(RegexContainer.delete + RegexContainer.whereNecessaryCondition)) {
                    whereFlag = true;
                } else {
                    throw new Exception();
                }
            } else {
                if (bufferArray[i].matches(RegexContainer.groupConditionAndBracket) && whereFlag == true) {
                    //Empty
                } else {
                    throw new Exception();
                }

            }
        }
        Pattern pattern = Pattern.compile("(,)");
        Matcher matcher = pattern.matcher(bufferString);
        if (matcher.find()){
            throw new Exception();
        }
        bracketChecker(bufferString);
    }

    public static void bracketChecker(String bufferString) throws Exception {
        Pattern p = Pattern.compile(RegexContainer.whereWord);
        Matcher m = p.matcher(bufferString);
        if (m.find()){
            int medium = m.end();
            String wherePositionString = bufferString.substring(medium, bufferString.length());
            int properlyBracketCounter = 0;
            for (int i = 0; i < wherePositionString.length(); i++){
                char symbol = wherePositionString.charAt(i);
                if (symbol == '('){
                    properlyBracketCounter++;
                }
                else if (symbol == ')'){
                    properlyBracketCounter--;
                }
                if (properlyBracketCounter < 0){
                    throw new Exception();
                }
            }
            if (properlyBracketCounter > 0){
                throw new Exception();
            }
        }
    }
}

