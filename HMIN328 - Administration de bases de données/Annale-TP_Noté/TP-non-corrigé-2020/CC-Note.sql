set serveroutput on;

select banner as "oracle version" from v$version

BEGIN dbms_output.put_line('Hello World..'); END;
/

DECLARE
text VARCHAR2(25);
BEGIN
    text:= 'Hello World';
    dbms_output.put_line(text);
END;
/

BEGIN
--single line comment
dbms_output.put_line(' Hello World ');
/*Multi line commenting begins
Multi line commenting ends */
END;
/

/* 
Following are the commonly used naming conventions in PL/SQL.

 The first letter should be used to specify the declared level of the variable. The below point give the different first letters and their declarative level
'P' – Variable is declared at the parameter level
'L' – Variable is declared at the local block
'G' – Variable is declared at the global level
The second letter specifies the type of identifier. Below are the commonly used identifier types and their naming code.
'C' – Cursor Identifier
'V' – Varchar and char datatype
'N' – Number datatype
'R' – Record type
'T' – Table type
Below are some of the examples of proper naming conventions

Lv_name – local level variable of varchar/char datatype
Pc_num – parameter level cursor identifier
Gn_user_id – Global level variable of numerical data type 
*/

DECLARE
lv_name VARCHAR2(50);
lv_name_2 VARCHAR2(50) := 'Amir';
BEGIN 
lv_name := lv_name_2; 
dbms_output.put_line(lv_name);
END;
/

-- default value for a variable

DECLARE
    greetings varchar2(20) DEFAULT 'Have a Good Day';
BEGIN
    dbms_output.put_line(greetings);
END;
/

-- an empty procedure
DECLARE 
   l_product_name VARCHAR2(100) DEFAULT 'Laptop';
BEGIN 
   NULL; 
END;
/

-- PL/SQL constant examples
DECLARE
    co_payment_term   CONSTANT NUMBER   := 45; -- days 
    co_payment_status CONSTANT BOOLEAN  := FALSE; 
BEGIN
    NULL;
END;
/

-- PL/SQL IF THEN statement example
DECLARE n_sales NUMBER := 2000000; 
BEGIN 
   IF n_sales > 100000 THEN 
      DBMS_OUTPUT.PUT_LINE( 'Sales revenue is greater than 100K ' ); 
   END IF; 
END;
/

DECLARE
  b_profitable BOOLEAN :=false;
  BEGIN
    IF b_profitable THEN
        DBMS_OUTPUT.PUT_LINE( 'This sales deal is profitable' );
    ELSE
        DBMS_OUTPUT.PUT_LINE('False');
    END IF;
  END;
/


DECLARE
  i number;
  BEGIN
  FOR i in 1..5 loop
    DBMS_OUTPUT.PUT_LINE(i);
  END loop;
  END;
/

-- reverse count
DECLARE
  i number;
  BEGIN
  FOR i in REVERSE 1..5 loop
    DBMS_OUTPUT.PUT_LINE(i);
  END loop;
  END;
/


-- trigger
CREATE or REPLACE TRIGGER myFirstTrigger
after INSERT ON ami
FOR each row
ENABLE
DECLARE
v_user VARCHAR2(20);
BEGIN
DBMS_OUTPUT.PUT_LINE('You entered ');
END;
