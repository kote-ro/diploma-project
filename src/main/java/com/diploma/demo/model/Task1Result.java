package com.diploma.demo.model;

import java.util.List;

public class Task1Result {
    List<String> errors;
    List<String> warnings;

    public Task1Result(List<String> errors, List<String> warnings) {
        this.errors = errors;
        this.warnings = warnings;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
