# mitrais-coding-test

Tools
  - Eclipse version: 2019-06(4.12.0)

SQL
      DROP TABLE IF EXISTS registration CASCADE;

    CREATE TABLE registration
    (
       id             integer        NOT NULL,
       phone_number   varchar(20)    NOT NULL,
       first_name     varchar(50)    NOT NULL,
       last_name      varchar(50)    NOT NULL,
       date_of_birth  varchar(10),
       gender         varchar(1),
       email          varchar(100)   NOT NULL,
       insert_time    timestamp      NOT NULL,
       updated_time   timestamp      NOT NULL,
    );

    ALTER TABLE registration
       ADD CONSTRAINT registration_pkey
       PRIMARY KEY (id);
  
    CREATE SEQUENCE registration_sequence START 101;

     COMMIT;
