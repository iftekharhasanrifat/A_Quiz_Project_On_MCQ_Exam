package Quiz_Project;

// Create a quiz program that will take questions,
// option and answer from admin user and save it to the question bank ,Then if any user want to give the quiz,
// random 5 questions will be shown to the user from the question bank.
//        Program output:
//        1. Add Quiz
//        2. Start Quiz

//        if user select option 1,
//        then system will tell user to input a question,
//        4 options and correct answer to save data in a quiz bank.
//        The quiz bank will be a json file.

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Scanner;

public class Quiz {
    public static void main(String[] args) {
        System.out.println("------------------A Quiz project on MCQ exam---------------------");
        System.out.println("Please Choose from the options below");
        System.out.println("1. Add Quiz\n" + "2. Start Quiz");
        Utils utils = new Utils();
        String fileLocation="./src/main/resources/QuestionBank.json";
        char ch='y';
        try {
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            switch (option) {
                case 1:
                    JSONArray jsonArray = new JSONArray();
                    JSONArray questionArray=new JSONArray();
                    do {

                        JSONParser jsonParser = new JSONParser();

                        Scanner scanner = new Scanner(System.in);
                        try{
                            questionArray = (JSONArray) jsonParser.parse(new FileReader(fileLocation));
                            System.out.println("Please add a ques here:");
                            String question = scanner.nextLine();
                            System.out.println("Input options.");
                            System.out.println("Option a:");
                            String optionA = scanner.nextLine();
                            System.out.println("Option b:");
                            String optionB = scanner.nextLine();
                            System.out.println("Option c:");
                            String optionC = scanner.nextLine();
                            System.out.println("Option d:");
                            String optionD = scanner.nextLine();
                            System.out.println("Please input the correct ans");
                            String correctAnswer = scanner.next();

                            JSONObject questionObj = new JSONObject();
                            questionObj.put("Question", question);
                            questionObj.put("option a", optionA);
                            questionObj.put("option b", optionB);
                            questionObj.put("option c", optionC);
                            questionObj.put("option d", optionD);
                            questionObj.put("answer", correctAnswer);
                            questionArray.add(questionObj);
                            utils.createQuestionList(fileLocation,questionArray);
                            System.out.println("Quiz saved at the database. Do you want to add more? (y/n)");
                            ch=scanner.next().charAt(0);
                        }
                        catch (Exception e){
                            System.out.println("Please add a ques here:");
                            String question = scanner.nextLine();
                            System.out.println("Input options.");
                            System.out.println("Option a:");
                            String optionA = scanner.nextLine();
                            System.out.println("Option b:");
                            String optionB = scanner.nextLine();
                            System.out.println("Option c:");
                            String optionC = scanner.nextLine();
                            System.out.println("Option d:");
                            String optionD = scanner.nextLine();
                            System.out.println("Please input the correct ans");
                            String correctAnswer = scanner.next();

                            JSONObject questionObj = new JSONObject();
                            questionObj.put("Question", question);
                            questionObj.put("option a", optionA);
                            questionObj.put("option b", optionB);
                            questionObj.put("option c", optionC);
                            questionObj.put("option d", optionD);
                            questionObj.put("answer", correctAnswer);
                            jsonArray.add(questionObj);
                            utils.createQuestionList(fileLocation,jsonArray);
                            System.out.println("Quiz saved at the database. Do you want to add more? (y/n)");
                            ch=scanner.next().charAt(0);
                        }
                    }while (ch!='n');
                    break;
                case 2:
                    int count = 0;
                    JSONParser jsonParser = new JSONParser();
                    try {
                        JSONArray questionBank = (JSONArray) jsonParser.parse(new FileReader(fileLocation));
                        System.out.println("You will be asked 5 questions, each questions has 1 marks");
                        JSONArray randomFiveQuestion = utils.randomFiveQuestion(questionBank);
                        for (int i = 0; i < randomFiveQuestion.size(); i++) {
                            JSONObject jsonObject = (JSONObject) randomFiveQuestion.get(i);
                            String Question = jsonObject.get("Question").toString();
                            String optionA = jsonObject.get("option a").toString();
                            String optionB = jsonObject.get("option b").toString();
                            String optionC = jsonObject.get("option c").toString();
                            String optionD = jsonObject.get("option d").toString();
                            String correctAnswer = jsonObject.get("answer").toString();
                            System.out.println(i+1+". "+Question);
                            System.out.println("a. "+optionA);
                            System.out.println("b. "+optionB);
                            System.out.println("c. "+optionC);
                            System.out.println("d. "+optionD);
                            System.out.println();
                            System.out.println("Please submit your answer: ");
                            String answer = input.next();
                            if(answer.equals(correctAnswer)){
                                System.out.println("Correct!");
                                count++;
                            }
                            else {
                                System.out.println("Not correct");
                            }
                            System.out.println("---------------------------------------");
                        }
                        System.out.println("You got "+count+" out of 5");
                    }
                    catch (Exception e){
                        System.out.println("Please ask your teacher to add some questions");
                    }
                    break;
                default:
                    System.out.println("Please choose from the given options");
                    break;
            }
            input.close();

        } catch (Exception e) {
            System.out.println("Please choose from the given options ");
        }
    }
}
