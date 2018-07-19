package com.rufus.bumblebee.Main.Columns;

import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.TypeTestData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Column implements Columns<TypeTestData, String> {
    private String COLUMN_NAME;
    private List<TypeTestData> values = Collections.synchronizedList(new ArrayList<TypeTestData>());
    private List<String> report = Collections.synchronizedList(new ArrayList());

    public Column(String COLUMN_NAME) {
        this.COLUMN_NAME = COLUMN_NAME;
    }

    public void setReport(List<String> report) {
        this.report.addAll(report);
    }

    @Override
    public List<TypeTestData> getValues() {
        if (checkRule()) {
            try {
                throw new Exception("Test data not be null or COLUMN_NAME not be null");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return values;
        }
    }

    @Override
    public void setValues(List<TypeTestData> values) {
        this.values.addAll(values);
    }

    public String getCOLUMN() {
        return COLUMN_NAME;
    }

    public void clear() {
        values.clear();
        report.clear();
    }

    public String getTestValue(int id) {
        if (id > report.size() - 1) {
            return null;
        }

        return report.get(id);
    }

    public int getSizeValue() {
        return this.report.size();
    }

    private boolean checkRule() {
        if (COLUMN_NAME == null || COLUMN_NAME == "") {
            return true;
        } else {
            return false;
        }
    }
}