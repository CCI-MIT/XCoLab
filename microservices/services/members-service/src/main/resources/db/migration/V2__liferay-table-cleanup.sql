ALTER TABLE Role_ DROP companyId;
ALTER TABLE Role_ DROP classNameId;
ALTER TABLE Role_ DROP classPK;
ALTER TABLE Role_ DROP type_;
ALTER TABLE Role_ DROP subtype;
ALTER TABLE Role_ DROP uuid_;
ALTER TABLE Role_ DROP userId;
ALTER TABLE Role_ DROP userName;
ALTER TABLE Role_ DROP modifiedDate;

-- Delete unused roles
delete from Role_ where roleId in (10120, 10123 ,10124 ,10125, 10126, 10127, 10128, 26021, 1015916,
    1015917,1015918,1411593);
