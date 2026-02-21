package com.blackrock.autosave.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponse {

    private List<Transaction> valid = new ArrayList<>();
    private List<Transaction> invalid = new ArrayList<>();
    private List<Transaction> duplicate = new ArrayList<>();

    public ValidationResponse() {}

    public List<Transaction> getValid() {
        return valid;
    }

    public void setValid(List<Transaction> valid) {
        this.valid = valid;
    }

    public List<Transaction> getInvalid() {
        return invalid;
    }

    public void setInvalid(List<Transaction> invalid) {
        this.invalid = invalid;
    }

    public List<Transaction> getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(List<Transaction> duplicate) {
        this.duplicate = duplicate;
    }
}