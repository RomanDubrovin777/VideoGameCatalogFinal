package com.example.videogamescatalog.Models;

public class User {
    private String m_email;
    private String m_phone;

    public User(String m_phone, String m_email) {
        this.m_phone = m_phone;
        this.m_email = m_email;
    }
    public User()
    {

    }
    public String getM_email() {
        return m_email;
    }

    public void setM_email(String m_email) {
        this.m_email = m_email;
    }
    public String getM_phone() {
        return m_phone;
    }
    public void setM_phone(String m_phone) {
        this.m_phone = m_phone;
    }
}
