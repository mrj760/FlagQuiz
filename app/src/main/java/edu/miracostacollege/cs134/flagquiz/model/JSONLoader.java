package edu.miracostacollege.cs134.flagquiz.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class loads Country data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (Country) with data.
 */
public class JSONLoader {

    /**
     * Loads JSON data from a file in the assets directory.
     *
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<Country> loadJSONFromAsset(Context context) throws IOException {
        List<Country> allCountriesList = new ArrayList<>();
        String json = null;
        InputStream is = context.getAssets().open("Countries.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");

        try {
            JSONObject jsonRootObject = new JSONObject(json);
            JSONArray allCountriesJSON = jsonRootObject.getJSONArray("Countries");

            // Loop through all the countries in the JSON data, create a Country
            int numCountries = allCountriesJSON.length();

            // To be used in loop
            JSONObject countryJSON;
            String name, region;
            Country country = null;

            for (int i = 0; i < numCountries; i++) {
                countryJSON = allCountriesJSON.getJSONObject(i);

                // Extract the name and region
                name = countryJSON.getString("Name");
                region = countryJSON.getString("Region");

                // Add object for each and add the object to the allCountriesList
                country = new Country(name, region);
                allCountriesList.add(country);
            }



        } catch (JSONException e) {
            Log.e("Flag Quiz", e.getMessage());
        }

        return allCountriesList;
    }
}
