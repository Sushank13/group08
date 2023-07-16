package com.dal.cs.backend.authentication.dataLayer;

import com.dal.cs.backend.baseUtils.dataLayer.BaseDataLayer;
import com.dal.cs.backend.database.IDatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LoginDataLayer extends BaseDataLayer implements ILoginDataLayer {

    private final Logger logger = LogManager.getLogger(LoginDataLayer.class);

    @Autowired
    public LoginDataLayer(IDatabaseConnection databaseConnection) {
        super(databaseConnection);
    }

    public static LoginDataLayer getInstance(IDatabaseConnection databaseConnection) {
        return new LoginDataLayer(databaseConnection);
    }

    @Override
    public boolean createLoginCredential(String emailId, String password) {
        logger.info("[Auth][Data] Create login credential");
        String callProcedure = getProcedureCallString("LoginCreatePassword", 2);
        try {
            CallableStatement callableStatement = connection.prepareCall(callProcedure);
            callableStatement.setString(1, emailId);
            callableStatement.setString(2, password);
            logger.info("[Auth][Data] Executed procedure to set login details ");
            boolean procedureCallStatus = callableStatement.execute();
            logger.info("[Auth][Data] Procedure call status " + procedureCallStatus);

            if (procedureCallStatus) {
                ResultSet resultSet = callableStatement.getResultSet();
                boolean resultStatus = resultSet.next();
                if (resultStatus) {
                    logger.info("[Auth][Data] Password set");
                } else {
                    logger.warn("[Auth][Data] Password not set. Failed data layer call");
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("[Auth][Data] Completed data layer call");
        return true;
    }
}
