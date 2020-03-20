package org.example.tca.response;

public class TCAResponse {

    private Status m_status;
    private String m_message;
    private Level m_level;

    public TCAResponse(Status status, String message) {
        m_status = status;
        m_message = message;
    }

    public TCAResponse(Status status, String message, Level level) {
        m_status = status;
        m_message = message;
        m_level = level;
    }

    public Status getStatus() {
        return m_status;
    }

    public void setStatus(Status status) {
        m_status = status;
    }

    public String getMessage() {
        return m_message;
    }

    public void setMessage(String message) {
        m_message = message;
    }

    public Level getLevel() {
        return m_level;
    }

    public void setLevel(Level level) {
        m_level = level;
    }
}
