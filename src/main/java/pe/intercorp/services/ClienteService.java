package pe.intercorp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import pe.intercorp.entities.Cliente;

@Service
public class ClienteService {

	public static final String path = "clientes";

	public Cliente create(Cliente object) {

		try {
			Firestore firestore = FirestoreClient.getFirestore();

			DocumentReference reference = firestore.collection(path).document();

			ApiFuture<WriteResult> future = reference.set(object);

			if (future.isCancelled()) {

				System.out.println("cancelled");

			}

			if (future.isDone()) {

				System.out.println("done");

			}

			System.out.println(future.get().toString());

		} catch (InterruptedException e) {

			e.printStackTrace();

		} catch (ExecutionException e) {

			e.printStackTrace();
		}

		return object;

	}

	public List<Cliente> read() {

		List<Cliente> list = new ArrayList<Cliente>();

		Firestore firestore = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> query = firestore.collection(path).get();

		try {

			QuerySnapshot snapshot = query.get();

			List<QueryDocumentSnapshot> documentSnapshots = snapshot.getDocuments();

			for (QueryDocumentSnapshot queryDocumentSnapshot : documentSnapshots) {

				Cliente cliente = queryDocumentSnapshot.toObject(Cliente.class);

				list.add(cliente);

			}

		} catch (InterruptedException e) {

			e.printStackTrace();

		} catch (ExecutionException e) {

			e.printStackTrace();

		}

		return list;

	}

	public void update() {

	}

	public void delete() {

	}

	public Map<String, String> kpi() {

		Map<String, String> map = new HashMap<String, String>();

		List<Cliente> list = this.read();

		int sumAge = 0;

		int average = 0;

		double difference = 0.0;

		double deviation = 0.0;

		for (Cliente e : this.read()) {

			sumAge += e.getEdad();

		}

		average = sumAge / list.size();

		map.put("edadPromedio", String.valueOf(average));

		for (Cliente e : this.read()) {

			difference += Math.pow((e.getEdad() - average), 2);

		}

		deviation = Math.sqrt(difference / list.size());

		map.put("desviacionEstandar", String.format("%.2f", deviation));

		return map;

	}

}
