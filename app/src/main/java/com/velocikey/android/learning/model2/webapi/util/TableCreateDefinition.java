package com.velocikey.android.learning.model2.webapi.util;

import android.util.Log;

/**
 * Created by Joseph White on 2015 Jul 17.
 */
public  class TableCreateDefinition {
    // Class fields
    private static final String LOG_TAG = TableCreateDefinition.class.getSimpleName();

    // Keep folks form inadvertantly instantiating this class
    private TableCreateDefinition() {};

    public static String getCreateTableString(String tableName, String[]... columns) {

        StringBuilder result = new StringBuilder();
        result.append("CREATE TABLE " + tableName + "(" + columns[0]);

        for (int i=1; i < columns.length; i++) {
            result.append(", " + columns[i]);
        }
        result.append(")");

        Log.v(LOG_TAG, "SQL Create: " + result.toString());
        return result.toString();
    }
}