package software.joe.photoSync;

import software.joe.photoSync.data.DatabaseSetup;
import software.joe.photoSync.provider.BaseProvider;
import software.joe.photoSync.provider.FileSystemProvider;
import software.joe.photoSync.worker.EliminateDupplicationWorker;
import software.joe.photoSync.worker.IWorker;

import java.util.ArrayList;
import java.util.stream.Collector;

public class Main {
    public static void main(String[] args) {
        //iniate database
        DatabaseSetup.Initialize();

        //define workers
        ArrayList<BaseProvider> providers = new ArrayList<>();
        providers.add(
                new FileSystemProvider("C:\\publish\\fotos\\origem")
        );

        ArrayList<IWorker> workers = new ArrayList<>();
        workers.add(
                new EliminateDupplicationWorker(
                        "C:\\publish\\fotos\\destino",
                        providers
                )
        );


        //run
        for(IWorker w : workers) {
            w.ExecuteWork();
        }

    }
}