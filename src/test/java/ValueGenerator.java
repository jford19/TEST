import java.util.Random;

public class ValueGenerator {

    public String generateRandomValue(String type) {
        String randomStringValue = "";
        Random rand = new Random();

        // Generate random integers in range 0 to 999
        int rand_int1 = rand.nextInt(1000);
        String randNum = String.valueOf(rand_int1);

        if (type.equals("user")) {
            randomStringValue = "jfuser" + randNum;
        } else if (type.equals("password")) {
            randomStringValue = "jfpassword" + randNum;
        }else if (type.equals("email")) {
            randomStringValue = "jfemail" + randNum + "@email.com";
        }else if (type.equals("id")) {
            randomStringValue = randNum+"123";
        }

        return  randomStringValue;
    }
}
