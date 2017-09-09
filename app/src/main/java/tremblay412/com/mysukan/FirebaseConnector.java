package tremblay412.com.mysukan;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akarin on 9/9/2017.
 * <p>This is the class that will connect to the database and retrieve the sport data that we want. This class is a singleton class so only one instance of this class should exist at any runtime.</p>
 */

public class FirebaseConnector {

    private static DatabaseReference mDatabase;
    private static FirebaseConnector instance = null;

    protected FirebaseConnector() {
        // Exists only to defeat instantiation.
    }

    public static FirebaseConnector getInstance() {
        if (instance == null) {
            instance = new FirebaseConnector();
            // [START create_database_reference]
            mDatabase = FirebaseDatabase.getInstance().getReference();
            // [END create_database_reference]
        }
        return instance;
    }

    public ArrayList<String> getAllSports() {
        ArrayList<String> sportList = new ArrayList<String>();

        return sportList;
    }

    public List<Match> getMatches(String sportName) {
        ArrayList<Match> matchList = new ArrayList<Match>();
        mDatabase.child("games").child(sportName).
        return matchList;
    }
}
