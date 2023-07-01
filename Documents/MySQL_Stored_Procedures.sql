use CSCI5308_8_DEVINT
DELIMITER //
CREATE PROCEDURE `AuthenticationSaveEmailAndPassword`(IN userEmailId varchar(255),IN userPassword varchar(255) )
BEGIN
	INSERT INTO login (emailId, password) 
    values (userEmailId, userPassword); 
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `AuthenticationGetPassword` (IN userEmailId varchar(255))
BEGIN
	SELECT password FROM login WHERE emailID=userEmaiId;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE `MemberSaveNewMember` (IN emailId VARCHAR(255), IN firstName VARCHAR(255), IN lastName VARCHAR(255), IN userType VARCHAR(255), IN program VARCHAR(255), IN term INT(11), IN mobileNumber VARCHAR(255), IN DOB DATE)
BEGIN
	INSERT INTO member Values (emailID, firstName, lastName, userType, program, term, mobileNumber, DOB);
END //
DELIMITER ;

-- CALL MemberSaveNewMember('xyz@dal.ca', 'Jinay', 'Shah', 'member', 'Program 4', 1, 2345678888, '2000-08-15');