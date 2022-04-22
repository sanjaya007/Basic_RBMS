package com.assignment;

import java.util.Scanner;

public class Main {

    public static final String STD_ID = "12142647";
    public static final float GUITAR_PRICE = 199.00F;
    public static final float LESSON_PRICE = 29.95F;
    public static final int MAX_Count = getHighestNumber(STD_ID);

    public static void main(String[] args) {
        byte counter = 1;

        String currentBookingName = "";

        String currentLessonCount = "";
        Integer totalLessonCount = 0;
        String currentTotalLessonPrice = "";

        boolean isCurrentGuitarIncluded;
        String currentTotalGuitarPrice = "";

        String currentTotalFinalPrice = "";
        Float allTotalPrice = 0F;

        Integer maxLessonCount = Integer.MIN_VALUE;
        Integer minLessonCount = Integer.MAX_VALUE;
        String maxLessonHolder = "";
        String minLessonHolder = "";
        boolean multipleMaxHolder = false;
        boolean multipleMinHolder = false;

        Scanner scanner = new Scanner(System.in);

//      heading
        System.out.println("");
        System.out.println("Welcome to the Rocky Blues Management System");
        System.out.println("");

        while (counter <= MAX_Count){
            while (true){
                System.out.print("Please enter booking name " + counter + " ==> ");
                String bookingName = scanner.nextLine().trim();
                if (isBlank(bookingName)) {
                    System.out.println("ERROR: Booking name cannot be blank.");
                }else if (isNumeric(bookingName)){
                    System.out.println("Error: Only alphabets are allowed.");
                }else {
                    currentBookingName = bookingName;
                    break;
                }
            }

            while (true) {
                System.out.print("Enter the number of lessons for " + currentBookingName + " ==> ");
                String lessonCount = scanner.nextLine().trim();

                if (isBlank(lessonCount)) {
                    System.out.println("ERROR:  You forget to add number of lessons.");
                }else if (!isNumeric(lessonCount)){
                    System.out.println("Error: Only valid numeric positive digits are allowed.");
                }else if(Integer.parseInt(lessonCount) < 1) {
                    System.out.println("Error: Number of lessons must be greater than or equal to 1.");
                }else {
                    String totalLessonPrice = "";
                    currentLessonCount = lessonCount;
                    totalLessonCount += Integer.parseInt(lessonCount);
                    if (Integer.parseInt(lessonCount) > 10){
                        float result = twentyPercentDiscount(Float.parseFloat(lessonCount));
                        String finalResult = String.format("%.2f", result);
                        totalLessonPrice = finalResult;
                    } else if(Integer.parseInt(lessonCount) > 5 && Integer.parseInt(lessonCount) <= 10){
                        float result = tenPercentDiscount(Float.parseFloat(lessonCount));
                        String finalResult = String.format("%.2f", result);
                        totalLessonPrice = finalResult;
                    }else{
                        float result = noDiscount(Float.parseFloat(lessonCount));
                        String finalResult = String.format("%.2f", result);
                        totalLessonPrice = finalResult;
                    }
                    currentTotalLessonPrice = totalLessonPrice;

//                  find max lesson
                    if (Integer.parseInt(currentLessonCount) >= maxLessonCount){
//                      multiple max
                        if (Integer.parseInt(currentLessonCount) == maxLessonCount){
                            maxLessonHolder += ", " + currentBookingName;
                            multipleMaxHolder = true;
                        }else{
                            maxLessonHolder = currentBookingName;
                            multipleMaxHolder = false;
                        }
                        maxLessonCount = Integer.parseInt(currentLessonCount);
                    }

//                  find min lesson
                    if ((Integer.parseInt(currentLessonCount) <= minLessonCount)){
//                      multiple min
                        if (Integer.parseInt(currentLessonCount) == minLessonCount){
                            minLessonHolder += ", " + currentBookingName;
                            multipleMinHolder = true;
                        }else{
                            minLessonHolder = currentBookingName;
                            multipleMinHolder = false;
                        }
                        minLessonCount = Integer.parseInt(currentLessonCount);
                    }

                    break;
                }
            }

            while (true) {
                System.out.print(currentBookingName + " do you want to purchase a guitar for $199.00 (y/n) ==> ");
                String wantGuitar = scanner.nextLine().trim().toLowerCase();

                if (!isBool(wantGuitar)){
                    System.out.println("ERROR: Enter 'y' or 'n' or 'yes' or 'no'.");
                }else{
                    String guitarPrice = "";
                    if (wantGuitar.equals("y") || wantGuitar.equals("yes")){
                        String finalResult = String.format("%.2f", GUITAR_PRICE);
                        guitarPrice = finalResult;
                        isCurrentGuitarIncluded = true;
                    }else{
                        guitarPrice = "0";
                        isCurrentGuitarIncluded = false;
                    }
                    currentTotalGuitarPrice = guitarPrice;
                    break;
                }
            }

            //      total price
            float totalPrice = Float.parseFloat(currentTotalLessonPrice) + Float.parseFloat(currentTotalGuitarPrice);
            String finalTotalPrice = String.format("%.2f", totalPrice);
            currentTotalFinalPrice = finalTotalPrice;
            allTotalPrice += totalPrice;
            if (!isCurrentGuitarIncluded){
                System.out.println("The charge for " + currentBookingName + " for " + currentLessonCount + " lessons is $" + currentTotalFinalPrice);
            }else{
                System.out.println("The charge for " + currentBookingName + " for " + currentLessonCount + " lessons and a guitar purchase is $" + currentTotalFinalPrice);
            }

            System.out.println("");
            counter++;
        }

//      statistical info
        System.out.println("");
        System.out.println("Statistical information for Rocky Blues");
        System.out.println("");

//      final calculation
        String formattedAllTotalPrice = String.format("%.2f", allTotalPrice);

        String strTotalLessonCount = String.valueOf(totalLessonCount);
        float averageValue = calculateAverage(Float.parseFloat(strTotalLessonCount));
        String averageFormatted = String.format("%.2f", averageValue);

        if (minLessonCount == maxLessonCount){
            System.out.println("All have the same number of " + minLessonCount + " lesson" + grammarInitFirst(minLessonCount) + ".");
        }else{
            System.out.println(minLessonHolder + grammarInitSecond(multipleMinHolder) + " the minimum number of " + minLessonCount + " lesson" + grammarInitFirst(minLessonCount) + ".");
            System.out.println(maxLessonHolder + grammarInitSecond(multipleMaxHolder) + " the maximum number of " + maxLessonCount + " lesson" + grammarInitFirst(maxLessonCount) + ".");
        }
        System.out.println("The average number of lessons per booking is: " + averageFormatted + " lessons.");
        System.out.println("The total charges collected is $" + formattedAllTotalPrice);

//      thank you text
        System.out.println("");
        System.out.println("");
        System.out.println("Thank you for using the Rocky Blues Management System.");
        System.out.println("Program written by " + STD_ID + " .");
        System.out.println("");
        System.out.println("");
    }

    public static boolean isBlank(String value){
        if (value.equals("")){
            return true;
        }
        return false;
    }

    public static boolean isAlphabet(String value) {
        String strRegex = "[a-zA-Z]+";
        if(value.matches(strRegex)){
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String value) {
        String intRegex = "[0-9]+";
        if(value.matches(intRegex)){
            return true;
        }
        return false;
    }

    public static float twentyPercentDiscount(Float value){
        float totalLessonPrice = value * LESSON_PRICE;
        float discountValue = (20F / 100F) * totalLessonPrice;
        float result = totalLessonPrice - discountValue;
        return result;
    }

    public static float tenPercentDiscount(Float value){
        float totalLessonPrice = value * LESSON_PRICE;
        float discountValue = (10F / 100F) * totalLessonPrice;
        float result = totalLessonPrice - discountValue;
        return result;
    }

    public static float noDiscount(Float value){
        float totalLessonPrice = value * LESSON_PRICE;
        float discountValue = 0F;
        float result = totalLessonPrice - discountValue;
        return result;
    }

    public static boolean isBool(String value){
        if (value.equals("y") || value.equals("n") || value.equals("yes") || value.equals("no")){
            return true;
        }
        return false;
    }

    public static float calculateAverage(Float value){
        float result = (value) / MAX_Count;
        return  result;
    }

    public static String grammarInitFirst(Integer value){
        if (value > 1){
            return "s";
        }else{
            return "";
        }
    }

    public static String grammarInitSecond(boolean value){
        if (value){
            return " have";
        }else{
            return " has";
        }
    }

    public static Integer getHighestNumber(String value){
        Integer currentNumber = Integer.MIN_VALUE;
        for (int i = 0; i < value.length(); i++) {
            Integer j = Integer.parseInt(String.valueOf(value.charAt(i)));
            if ( j > currentNumber){
                currentNumber = j;
            }
        }
        return  currentNumber;
    }
}