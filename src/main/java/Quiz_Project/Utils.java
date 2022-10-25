package Quiz_Project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Utils {
    public void createQuestionList(String fileLocation, JSONArray jsonArray) throws IOException {
        FileWriter fileWriter = new FileWriter(fileLocation);
        fileWriter.write(jsonArray.toString());
        fileWriter.flush();
    }

    public JSONArray randomFiveQuestion(JSONArray jsonArray){
        Random rn = new Random();
        JSONArray randomQuestions = new JSONArray();
        while (randomQuestions.size()<5){
            int randomIndex = rn.nextInt(15);
            JSONObject jsonObject= (JSONObject) jsonArray.get(randomIndex);
            if(!randomQuestions.contains(jsonObject)){
                randomQuestions.add(jsonObject);
            }
        }
        return randomQuestions;
    }

}
