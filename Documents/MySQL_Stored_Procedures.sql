-- Procedure for selecting all the CategoryID and CategoryName from category table
DELIMITER //
CREATE PROCEDURE selectAllFromCategory ()
BEGIN
	SELECT categoryID, categoryName FROM category;	
END //
DELIMITER ;

-- Procedure for getting the club ID of the last row in the table
DELIMITER //
CREATE PROCEDURE getLatestClubId()
BEGIN 
      SELECT clubID from newAndUpdateClubRequest ORDER BY clubID DESC LIMIT 1;
END //
DELIMITER ;

-- Procedure for getting the requeest ID of the last row in the table
DELIMITER //
CREATE PROCEDURE getLatestRequestId()
BEGIN 
      SELECT requestID from newAndUpdateClubRequest ORDER BY requestID DESC LIMIT 1;
END //
DELIMITER ;

-- Procedure for getting all the clubs from Club table
DELIMITER //
CREATE PROCEDURE getAllClubs()
BEGIN 
      SELECT * from club;
END //
DELIMITER ;

-- Procedure for inserting new club create request details into NewAndUpdateClubRequest
DELIMITER //
CREATE PROCEDURE insertIntoNewAndUpdateClubRequest (IN requestID VARCHAR(50),IN clubID VARCHAR(50),IN requestorEmailID VARCHAR(50),IN categoryID VARCHAR(50),IN clubName VARCHAR(50), IN clubDescription VARCHAR(50),IN facebookLink VARCHAR(50),IN instagramLink VARCHAR(50), IN location VARCHAR(50), IN meetingTime VARCHAR(50),IN clubImage VARCHAR(50),IN rules VARCHAR(50),IN requestType VARCHAR(50), IN requestStatus VARCHAR(50))
BEGIN 
      INSERT INTO newAndUpdateClubRequest values (requestID, clubID,requestorEmailID,categoryID,clubName,clubDescription,facebookLink,instagramLink,location,meetingTime,clubImage,rules,requestType,requestStatus);
END //
DELIMITER ;

-- Procedure for inserting data of new member request
DELIMITER //
CREATE PROCEDURE `MemberSaveNewMember` (IN emailId VARCHAR(255), IN firstName VARCHAR(255), IN lastName VARCHAR(255), IN userType VARCHAR(255), IN program VARCHAR(255), IN term INT(11), IN mobileNumber VARCHAR(255), IN DOB DATE)
BEGIN
	INSERT INTO member Values (emailID, firstName, lastName, userType, program, term, mobileNumber, DOB);
END //
DELIMITER ;
