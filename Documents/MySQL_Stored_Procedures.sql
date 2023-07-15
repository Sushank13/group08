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
      SELECT CONCAT('CLB_',(SELECT CAST(SUBSTRING_INDEX(clubID , '_', -1) AS UNSIGNED) AS clubID FROM newAndUpdateClubRequest ORDER BY clubID DESC LIMIT 1)) AS clubID FROM newAndUpdateClubRequest LIMIT 1;
END //
DELIMITER ;

-- Procedure for getting the requeest ID of the last row in the table
DELIMITER //
CREATE PROCEDURE getLatestRequestId()
BEGIN 
      SELECT CONCAT('REQ_',(SELECT CAST(SUBSTRING_INDEX(requestID, '_', -1) AS UNSIGNED) AS requestID FROM newAndUpdateClubRequest ORDER BY requestID DESC LIMIT 1)) AS requestID FROM newAndUpdateClubRequest LIMIT 1;
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

-- Procedure to get the club request information from newAndUpdateClubRequest table based on the reqid
DELIMITER //
CREATE PROCEDURE getClubRequestInfoByRequestId(IN requestId varchar(50))
BEGIN
  SELECT * FROM newAndUpdateClubRequest as naucr WHERE naucr.requestID=requestID;
END //
DELIMITER ;


-- Procedure to create a club by inserting record in Club table
DELIMITER //
CREATE PROCEDURE createClub(IN clubID VARCHAR(50),IN clubName VARCHAR(255),IN clubDescription VARCHAR(255),IN presidentEmailID VARCHAR(255),IN facebookLink VARCHAR(255),IN instagramLink VARCHAR(255),IN categoryID VARCHAR(50),IN location VARCHAR(255), IN meetingTime VARCHAR(255),IN clubImage VARCHAR(255),IN rules VARCHAR(255))
BEGIN
  INSERT INTO club values(clubID,clubName,clubDescription, presidentEmailID,facebookLink,instagramLink,categoryID,location,meetingTime,clubImage,rules);
END //
DELIMITER ;

-- Procedure to deleting a club record in Club table based on clubID
DELIMITER //
CREATE PROCEDURE deleteClub(IN deleteClubID VARCHAR(50))
BEGIN
  DELETE FROM eventRegistrationDetails WHERE eventID IN ( 
			SELECT ERD.eventID 
            FROM eventRegistrationDetails ERD 
            INNER JOIN events EVNT 
            ON ERD.eventID = EVNT.eventID 
            WHERE EVNT.clubID = deleteClubID
		);
  DELETE FROM events WHERE clubID=deleteClubID;
  DELETE FROM club WHERE clubID=deleteClubID;
END //
DELIMITER ;

-- Procedure to update club request status to approved 
DELIMITER //
CREATE PROCEDURE updateClubRequestStatusToApproved(IN requestId VARCHAR(50))
BEGIN
   UPDATE newAndUpdateClubRequest as naucr SET requestStatus="APPROVED" WHERE naucr.requestID=requestId;
END //
DELIMITER ;


-- Procedure to update club request status to rejected
DELIMITER //
CREATE PROCEDURE updateClubRequestStatusToRejected(IN requestId VARCHAR(50))
BEGIN
   UPDATE newAndUpdateClubRequest as naucr SET requestStatus="REJECTED" WHERE naucr.requestID=requestId;
END //
DELIMITER ;
