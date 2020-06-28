package pe.intercorp.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseService {

	@PostConstruct
	public void initialize() {

		try {

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			File file = new File(
					classLoader.getResource("lmv-irtech-challenge-firebase-adminsdk-v13e7-52d031ebda.json").getPath());

			InputStream credentialsStream = new FileInputStream(file);

			GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);

			FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials)
					.setDatabaseUrl("https://lmv-irtech-challenge.firebaseio.com").build();

			FirebaseApp.initializeApp(options);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
