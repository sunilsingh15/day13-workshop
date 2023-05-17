package sg.edu.nus.iss.day13workshop;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Day13WorkshopApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Day13WorkshopApplication.class);

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> opsVal = appArgs.getOptionValues("dataDir");
		System.out.println(opsVal);

		if (opsVal != null) {
			createDir(opsVal.get(0));
		} else {
			System.exit(1);
		}

		app.run(args);
	}

	private static void createDir(String string) {
	}

}
