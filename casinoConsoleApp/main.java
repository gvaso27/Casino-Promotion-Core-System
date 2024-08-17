import service.appServices;

import java.io.*;

public static void main(String[] args) {
    String inputPath = "data/transactions.txt";
    String outputPath = "data/results.txt";
    fileModifier(inputPath, outputPath);
}

//reading from input file and modifying output file accordingly
private static void fileModifier(String inputPath, String outputPath){
    appServices service = new appServices();
    try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
        createFile(outputPath);
        String line;
        String[] words;
        while ((line = br.readLine()) != null) {
            words = line.split("\\s+");
            if(words[0].equals("register")){
                service.register(words[1]);
            }
            if(words[0].equals("addscenario")){
                service.addScenario(Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]));
            }
            if(words[0].equals("deposit")){
                service.deposit(words[1], Integer.parseInt(words[2]));
            }
            if(words[0].equals("bet")){
                service.bet(words[1], Integer.parseInt(words[3]), words[2].equalsIgnoreCase("SLOTS"));
            }
            if(words[0].equals("balance")){
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath, true))) {
                    writer.write(String.valueOf(service.balance(words[1])));
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } catch (IOException e) {
        System.out.println("No file with name:\"transactions.txt\" was found.");
    }
}

//creating file and if it already exists deleting its content
private static void createFile(String outputPath){
    File file = new File(outputPath);
    if (!file.exists()) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }else{
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}