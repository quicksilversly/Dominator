CREATE TABLE dominion_user (id bigint GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY NOT NULL, 
						    username varchar(32), 
						    password varchar(32), 
						    description varchar(1024), 
						    user_image varchar(1024));