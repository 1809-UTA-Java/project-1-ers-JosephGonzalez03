--erase all tables
drop table ers_users;
drop table ers_user_roles;

--create tables for reimbursement system
create table ers_user_roles (
    ur_id number(*,0) primary key,
    ur_role varchar2(40 byte)
);

create table ers_users (
    u_id number(*,0) primary key,
    u_username varchar2(40 byte) unique,
    u_password varchar2(40 byte),
    u_firstname varchar2(30 byte),
    u_lastname varchar2(30 byte),
    u_email varchar2(100 byte) unique,
    ur_id number(*,0) references ers_user_roles(ur_id)
);

create table ers_reimbursement_status (
    rs_id number(*,0) primary key,
    rs_status varchar2(30 byte)
);

create table ers_reimbursement_type (
    rt_id number(*,0) primary key,
    rt_type varchar2(30 byte)
);

create table ers_reimbursements (
    r_id number(*,0) primary key,
    r_amount number(22,2),
    r_description varchar2(100 byte),
    r_receipt blob,
    r_submitted timestamp,
    r_resolved timestamp,
    u_id_author number(*,0) references ers_users(u_id),
    u_id_resolver number(*,0) references ers_users(u_id),
    rt_type number(*,0) references ers_reimbursement_type(rt_id),
    rt_status number(*,0) references ers_reimbursement_status(rs_id)
);