/**
	MySQL_1_DDL_Insert_Queries.sql
    
	SQL Script containing the DDL fro creating the Tables required for Dal CLubs project.
    It also includes the Insert queries to insert the sample data into the tables.
	
    Structure:
    1. DDL: Create Tables
    2. Insert: Sample data into tables
*/

-- Use Development database
USE CSCI5308_8_DEVINT;

-- Use Test database
-- USE CSCI5308_8_TEST;

-- Use Production database
-- USE CSCI5308_8_PRODUCTION; 

-- Create Tables for Dal Clubs Database
CREATE TABLE category (
  categoryID VARCHAR(50),
  categoryName VARCHAR(255) NOT NULL,
  PRIMARY KEY (categoryID)
);

CREATE TABLE member (
  emailID VARCHAR(255),
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  userType VARCHAR(255) NOT NULL,
  program VARCHAR(255),
  term INT,
  mobileNumber VARCHAR(255) NOT NULL,
  DOB DATE NOT NULL,
  PRIMARY KEY (emailID)

);

CREATE TABLE login (
  emailID VARCHAR(255),
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (emailID),
  FOREIGN KEY (emailID) REFERENCES member(emailID)
);

CREATE TABLE club (
  clubID VARCHAR(50),
  clubName VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  presidentEmailID VARCHAR(255),
  facebookLink VARCHAR(255),
  instagramLink VARCHAR(255),
  categoryID VARCHAR(50),
  location VARCHAR(255) NOT NULL,
  meetingTime TIME NOT NULL,
  clubImage VARCHAR(255),
  rules VARCHAR(255) NOT NULL,
  PRIMARY KEY (clubID),
  FOREIGN KEY (presidentEmailID) REFERENCES member(emailID),
  FOREIGN KEY (categoryID) REFERENCES category(categoryID)
);

CREATE TABLE joinClubRequest (
  requestID VARCHAR(50),
  requestorEmailID VARCHAR(255),
  clubID VARCHAR(50),
  joiningReason VARCHAR(255) NOT NULL,
  requestStatus VARCHAR(255),
  PRIMARY KEY (requestID),
   FOREIGN KEY (clubID) REFERENCES club(clubID),
  FOREIGN KEY (requestorEmailID) REFERENCES member(emailID)
);

CREATE TABLE events (
  eventID VARCHAR(50),
  clubID VARCHAR(50),
  organizerEmailID VARCHAR(255),
  eventName VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  venue VARCHAR(255) NOT NULL,
  image VARCHAR(255),
  startDate DATE NOT NULL,
  endDate DATE NOT NULL,
  startTime TIME NOT NULL,
  endTime TIME NOT NULL,
  eventTopic VARCHAR(255) NOT NULL,
  PRIMARY KEY (eventID),
  FOREIGN KEY (clubID) REFERENCES club(clubID),
  FOREIGN KEY (organizerEmailID) REFERENCES member(emailID)
);

CREATE TABLE newAndUpdateClubRequest (
  requestID VARCHAR(50),
  clubID VARCHAR(50) NOT NULL,
  requestorEmailID VARCHAR(255),
  categoryID VARCHAR(50),
  clubName VARCHAR(255),
  description VARCHAR(255),
  facebookLink VARCHAR(255),
  instagramLink VARCHAR(255),
  location VARCHAR(255),
  meetingTime VARCHAR(255),
  clubImage VARCHAR(255),
  rules VARCHAR(255),
  requestType VARCHAR(255) NOT NULL,
  requestStatus VARCHAR(255) NOT NULL,
  PRIMARY KEY (requestID),
  FOREIGN KEY (requestorEmailID) REFERENCES member(emailID),
  FOREIGN KEY (categoryID) REFERENCES category(categoryID)
);

-- INSERT DATA INTO TABLES:
-- Insert data into category table
INSERT INTO category (categoryID, categoryName)
VALUES
  ('CAT_1', 'Sports & Atheletics '),
  ('CAT_2', 'Social Justice & Outreach'),
  ('CAT_3', 'Hobbies & Recreation');

-- Insert data into member table
INSERT INTO member (emailID, firstName, lastName, userType, program, term, mobileNumber, DOB)
VALUES
  ('bikectr@dal.ca', 'John', 'Doe', 'president', 'Program 1', 1, 1234567890, '1990-01-01'),
  ('user@dal.ca', 'Jane', 'Smith', 'president', 'Program 2', 2, 2345678901, '1995-02-02'),
  ('daloutdoors@dal.ca', 'Mike', 'Johnson', 'president', 'Program 3', 3, 3456789012, '1985-03-03'),
  ('swit@dal.ca', 'Suman', 'Wit', 'member', 'Program 4', 1, 2345678801, '2002-08-18'),
  ('km421782@dal.ca', 'K', 'M', 'president', 'Program 2', 2, 2345677777, '1992-01-06'),
  ('shrutichaturvedi2707@dal.ca', 'Shruti', 'Chaturvedi', 'president', 'Program 4', 1, 2345678888, '1994-11-22');

-- Insert data into login table
INSERT INTO login (emailID, password)
VALUES
  ('bikectr@dal.ca', 'password1'),
  ('user@dal.ca', 'password'),
  ('daloutdoors@dal.ca', 'password2'),
  ('swit@dal.ca', 'password3'),
  ('km421782@dal.ca', 'password4'),
  ('shrutichaturvedi2707@dal.ca', 'password5');

-- Insert data into club table
INSERT INTO club (clubID, clubName, description, presidentEmailID, facebookLink, instagramLink, categoryID, location, meetingTime, clubImage, rules)
VALUES
  ('CLB_1', 'Dal/Kings Bike Society ', 'Description 1', 'bikectr@dal.ca', 'https://www.facebook.com/dalbikecentre/', 'https://www.instagram.com/bikeatdal/ ', 'CAT_1', 'Location 1', '17:00:00', NULL , 'Rules 1'),
  ('CLB_2', 'UNICEF DAL', 'Description 2', 'user@dal.ca', NULL , 'https://www.instagram.com/dalkinsociety/', 'CAT_2', 'Location 2', '18:00:00', 'image2.jpg', 'Rules 2'),
  ('CLB_3', 'Dalhousie Outdoors Society', 'Description 3', 'daloutdoors@dal.ca',  'https://www.facebook.com/DalOutdoors',' https://instagram.com/daloutdoors?igshid=NTc4MTIwNjQ2YQ==', 'CAT_3', 'Location 3', '19:00:00', 'image3.jpg', 'Rules 3');

-- Insert data into events table
INSERT INTO events (eventID, eventName, description, venue, clubID, organizerEmailID, image, startDate, endDate, startTime, endTime, eventTopic)
VALUES
  ('EVNT_1', 'Bike Society AGM', 'Bike Ride followed by AGM, Pizza, door prize, elections', 'SUB, room 307', 'CLB_1', 'km421782@dal.ca', NULL , '2023-03-19', '2023-03-19', '16:00:00', '18:00:00', 'Cycling'),
  ('EVNT_2', 'UNICEF GALA', 'Charitable event for improving life around the world', 'SMU, Layola 290', 'CLB_2', 'shrutichaturvedi2707@dal.ca', NULL , '2023-03-20', '2023-03-20', '19:00:00', '22:00:00', 'Topic 2'),
  ('EVNT_3', 'Dalhousie Outdoors Society Spring AGM', 'Join DOS for their spring AGM! You can expect a DOS year summary, games, prizes, exec elections, the societys plans for the future and a good time! ', 'SUB Room 22', 'CLB_3', 'daloutdoors@dal.ca', NULL , '2023-03-21', '2023-03-21', '18:00:00', '20:00:00', 'Outdoor');

-- Insert data into newAndUpdateClubRequest table
INSERT INTO newAndUpdateClubRequest (requestID, clubID, requestorEmailID, categoryID, clubName, description, facebookLink, instagramLink, location, meetingTime, clubImage, rules, requestType, requestStatus)
VALUES
('REQ_1', 'CLB_1', 'bikectr@dal.ca', 'CAT_1', 'Dal/Kings Bike Society', 'Description 1', 'https://www.facebook.com/dalbikecentre/', 'https://www.instagram.com/bikeatdal/', 'Location 1', '17:00:00', NULL, 'Rules 1', 'NEW_REQUEST', 'APPROVED'),
('REQ_2', 'CLB_2', 'user@dal.ca', 'CAT_2', 'UNICEF DAL', 'Description 2', NULL, 'https://www.instagram.com/dalkinsociety/', 'Location 2', '18:00:00', 'image2.jpg', 'Rules 2', 'NEW_REQUEST', 'APPROVED'),
('REQ_3', 'CLB_3', 'daloutdoors@dal.ca', 'CAT_3', 'Dalhousie Outdoors Society', 'Description 3', 'https://www.facebook.com/DalOutdoors', 'https://instagram.com/daloutdoors?igshid=NTc4MTIwNjQ2YQ==', 'Location 3', '19:00:00', 'image3.jpg', 'Rules 3', 'NEW_REQUEST', 'APPROVED');

 
 
-- Drop all tables:
-- DROP table events;
-- DROP table joinClubRequest;
-- DROP table newAndUpdateClubRequest;
-- DROP table club;
-- DROP table login;
-- DROP table member;
-- DROP table category;
