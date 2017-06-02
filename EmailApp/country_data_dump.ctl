load data 
infile 'country_data_dump.csv' "str '\r\n'"
append
into table COUNTRY
fields terminated by ','
OPTIONALLY ENCLOSED BY '"' AND '"'
trailing nullcols
           ( COUNTRY_CODE CHAR(4000),
             COUNTRY_NAME CHAR(4000),
             CREATION_DATE DATE "",
             PHONE_CODE CHAR(4000)
           )
