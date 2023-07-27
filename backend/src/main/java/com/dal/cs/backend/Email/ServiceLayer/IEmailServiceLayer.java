package com.dal.cs.backend.Email.ServiceLayer;

import com.dal.cs.backend.Email.ClassObject.Email;
import com.dal.cs.backend.Email.Exceptions.CustomEmailException;

public interface IEmailServiceLayer
{
    public void sendEmail(Email email);
}
