package org.example.tca.response;

public enum Status {

    Pass("Pass"),
    Fail("Fail");

    private String m_status;

    Status(String status) {
        this.m_status = status;
    }

    public String getStatus() {
        return m_status;
    }

    public static Status from(String status) {
        switch (status) {
            case "Pass" :
                return Status.Pass;
            case "Fail" :
                return Status.Fail;
            default:
                return null;
        }
    }
}
