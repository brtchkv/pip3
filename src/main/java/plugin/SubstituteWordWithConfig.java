package plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Mojo(name="scan")
public class SubstituteWordWithConfig extends AbstractMojo{

    @Parameter(property = "old.word")
    private String oldWord;
    @Parameter(property = "old.word")
    private String newWord;
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            File resources = new File("src/main/resources/someText");
            for (File file : resources.listFiles()) {
                FileReader fileReader = new FileReader(file);
                Scanner scanner = new Scanner(fileReader);
                FileWriter fileWriter = new FileWriter(file, true);
                List<String> answer = new ArrayList<String>();
                while (scanner.hasNextLine()) {
                    answer.add(scanner.nextLine());
                }
                for (int i = 0; i < answer.size(); i++) {
                    if (answer.get(i).trim().equals(oldWord))
                        fileWriter.append(newWord);
                    else
                        fileWriter.append(answer.get(i));
                }
                fileWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
