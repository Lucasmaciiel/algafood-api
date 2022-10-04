package com.lmg.lmgfood.infra.repository.service.report;

public class ReportException extends RuntimeException {

    private static final long serialVersionUID = -3492887870551309621L;

    public ReportException(String message) {
        super(message);
    }

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
