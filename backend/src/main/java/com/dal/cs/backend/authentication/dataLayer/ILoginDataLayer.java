package com.dal.cs.backend.authentication.dataLayer;

public interface ILoginDataLayer {
    boolean createLoginCredential(String emailId, String password);
}
