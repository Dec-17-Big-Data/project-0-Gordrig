-- table creation
create table person (
    userid          number(20)      primary key,
    username        varchar2(255)    unique not null,
    pw              varchar2(255)    not null,
    super           number(1)       default 0 check(super >=0) check (super <= 1),
    activated       number(1)       default 1 check(activated >=0) check (activated <= 1)
);

create table bankaccount (
    accountid       number(20)      primary key,
    owner           number(20)      ,
    balance         number(20,2)    not null check (balance >= 0.00),
    activated       number(1)       default 1 check(activated >=0) check (activated <= 1),
    
    constraint ownerConstraint foreign key (owner) references person(userid)
);

create table transaction (
    transactionid   number(20)      primary key,
    person          number(20)      ,
    account1        number(20)      not null,
    account2        number(20)      ,
    transactiontime timestamp       default systimestamp,
    action          number(1)       not null check (action >= 0) check (action < 3),
    amount          number(20,2)    not null check (amount >= 0),
    
    constraint userConstraint foreign key (person) references person(userid),
    constraint account1Constraint foreign key (account1) references bankaccount(accountid)
);

-- sequences

DROP SEQUENCE user_seq;
CREATE SEQUENCE user_seq
    start with 1
    increment by 1;

DROP SEQUENCE account_seq;
CREATE SEQUENCE account_seq
    start with 1
    increment by 1;
    
DROP SEQUENCE transaction_seq;
CREATE SEQUENCE transaction_seq
    start with 1
    increment by 1;

-- triggers

CREATE OR REPLACE TRIGGER user_seq_trigger 
BEFORE INSERT ON person
FOR EACH ROW
BEGIN 
    --IF :new.userid IS NULL THEN
        SELECT user_seq.nextval INTO :new.userid from dual;
    --END IF;
END;  
/
CREATE OR REPLACE TRIGGER account_seq_trigger 
BEFORE INSERT ON bankaccount
FOR EACH ROW
BEGIN 
    --IF :new.accountid IS NULL THEN
        SELECT account_seq.nextval INTO :new.accountid from dual;
    --END IF;
END;
/
CREATE OR REPLACE TRIGGER transaction_seq_trigger 
BEFORE INSERT ON crushadmin.transaction
FOR EACH ROW
BEGIN 
    --IF :new.transactionid IS NULL THEN
        SELECT transaction_seq.nextval INTO :new.transactionid from dual;
    --END IF;
END;  
/
create or replace trigger transaction_logic
after insert on crushadmin.transaction
for each row
begin
    if :new.action = 0 then
        update bankaccount set balance = (balance + :new.amount) where accountid = :new.account1;
    elsif :new.action = 1 then
        update bankaccount set balance = (balance - :new.amount) where accountid = :new.account1;
    elsif :new.action = 2 then
        update bankaccount set balance = (balance - :new.amount) where accountid = :new.account1;
        update bankaccount set balance = (balance + :new.amount) where accountid = :new.account2;
    end if;
end;

-- stored procedures
/*
    CREATE [OR REPLACE] proc_name
    IS
        This section is where you can DECLARE variables
    BEGIN
        This section is where you can write the execution, or utilize other transactions
    [EXCEPTION]
        Perform exception handling here
    
    END;
    */
    /*  list of all stored procedures
    
        verify_login(uname in varchar2,provpw in varchar2,accepted out number(1))
        verify_super(uname in varchar2,provpw in varchar2,super out number(1))
        get_users(uname in varchar2, provpw in varchar2, rs out sys_refcursor)
        get_user_by_id(uid in number, rs out sys_refcursor)
        get_all_accounts(uname in varchar2, provpw in varchar2, rs out sys_refcursor)
        get_accounts_by_userid(uname in varchar2, provpw in varchar2, uid in number)
        get_all_transactions(uname in varchar2, provpw in varchar2, uid in number)
        get_transactions_by_userid(uname in varchar2, provpw in varchar2, uid in number)
        
        insert_user(uname in varchar2, provpw in varchar2)
        insert_account(uid in number)
        insert_transactiondw(uid in number, transaction_type in number, account1 in number, amount in number)
        insert_transactiont(uid in number, transaction_type in number, account1 in number, account2 in number, amount in number)
        
        
        
    */
    /
    create or replace procedure insert_user(uname in varchar2, provpw in varchar2)
    is
    begin
        insert into person (userid,username,pw) values (0, uname, provpw);
    end;
    /
    create or replace procedure insert_account(uid in number)
    is
    begin
        insert into bankaccount (accountid, owner, balance) values (0, uid, 0.00);
    end;
    /
    create or replace procedure disable_account(accountid2 in number)
    is
    begin
        update bankaccount set bankaccount.activated = 0 where bankaccount.accountid = accountid2;
    end;
    /
    create or replace procedure insert_transactiondw(uid in number, transaction_type in number, account1 in number, amount in number)
    is
    begin
        if (transaction_type = 1 or transaction_type = 2) then
            insert into crushadmin.transaction (transactionid, person, account1, action, amount) values (0, uid, account1, transaction_type, amount);
        end if;
    end;
    /
    create or replace procedure insert_transactiont(uid in number, transaction_type in number, account1 in number, account2 in number, amount in number)
    is
    begin
        if (transaction_type = 3) then
            insert into crushadmin.transaction (transactionid, person, account1, account2, action, amount) values (0, uid, account1, account2, transaction_type, amount);
        end if;
    end;
    /
    create or replace procedure verify_login(uname in varchar2, provpw in varchar2, accepted out number)
    is
    doesitexist number;
    begin
        select count(*) into doesitexist from person where uname = username and provpw = pw and activated = 1;
        if (doesitexist > 0) then
            accepted := 1;
        else
            accepted := 0;
        end if;
    end;
    /
    create or replace procedure verify_super(uname in varchar2, provpw in varchar2, super out number)
    is
    doesitexist number;
    begin  
        select count(*) into doesitexist from person where uname = username and provpw = pw and super = 1;
        if (doesitexist > 0) then
            super := 1;
        else
            super := 0;
        end if;
    end;
    /   
    create or replace procedure get_users(uname in varchar2, provpw in varchar2, rs out sys_refcursor)
    is
        accepted number(1);
        super number(1);
    begin
        verify_login(uname, provpw, accepted);
        verify_super(uname, provpw, super);
        if (accepted > 0) then
            if (super > 0) then
                open rs for select * from person;
            else
                open rs for select * from person where uname = username and provpw = pw;
            end if;
        end if;
    end;
    /
    create or replace procedure get_user_by_id(uname in varchar2, provpw in varchar2, uid in number, rs out sys_refcursor)
    is
        accepted number(1);
        super number(1);
    begin
        verify_login(uname, provpw, accepted);
        verify_super(uname, provpw, super);
        if (accepted > 0) then
            if (super > 0) then
                open rs for select * from person where uid = userid;
            end if;
        end if;
    end;
    /
    create or replace procedure get_all_accounts(uname in varchar2, provpw in varchar2, rs out sys_refcursor)
    is
        accepted number(1);
        super number(1);
    begin
        verify_login(uname, provpw, accepted);
        verify_super(uname, provpw, super);
        if (accepted > 0) then
            if (super > 0) then
                open rs for select * from bankaccount;
            else
                open rs for select * from bankaccount where activated = 1 and bankaccount.owner = (select person.userid from person where uname = person.username);
            end if;
        end if;
    end;
    /
    create or replace procedure get_accounts_by_userid(uname in varchar2, provpw in varchar2, uid in number, rs out sys_refcursor)
    is
        accepted number(1);
        super number(1);
    begin
        verify_login(uname, provpw, accepted);
        verify_super(uname, provpw, super);
        if (accepted > 0) then
            if (super > 0) then
                open rs for select * from bankaccount where uid = bankaccount.owner;
            end if;
        end if;
    end;
    /
    create or replace procedure get_all_transactions(uname in varchar2, provpw in varchar2, uid in number, rs out sys_refcursor)
    is
        accepted number(1);
        super number(1);
    begin
        verify_login(uname, provpw, accepted);
        verify_super(uname, provpw, super);
        if (accepted > 0) then
            if (super > 0) then
                open rs for select * from transaction order by transactiontime;
            else
                open rs for select * from transaction where uid = transaction.person order by transactiontime;
            end if;
        end if;
    end;
    /
    create or replace procedure get_transactions_by_userid(uname in varchar2, provpw in varchar2, uid in number, rs out sys_refcursor)
    is
        accepted number(1);
        super number(1);
    begin
        verify_login(uname, provpw, accepted);
        verify_super(uname, provpw, super);
        if (accepted > 0) then
            if (super > 0) then
                open rs for select * from transaction where uid = transaction.person;
            end if;
        end if;
    end;
    /
    commit;