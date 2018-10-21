insert into ers_users values (1000000, 'joeUser', 'pass', 'Joseph', 'Gonzalez', 'joseph@email.com', 100);
insert into ers_users values (1000001, 'joeGonz', 'pass', 'Joel', 'Gomez', 'josephG@test.com', 100);
insert into ers_users values (1000002, 'mrJoe', 'pass', 'mister', 'joe', 'mrjoseph@gmail.com', 101);

commit;

INSERT INTO ers_reimbursements (
    r_id,
    r_amount,
    r_description,
    r_submitted,
    u_id_author,
    u_id_resolver,
    rt_id,
    rs_id
) VALUES (
    2000,
    1000,
    'travel stuff',
    CURRENT_TIMESTAMP,
    1000000,
    1000002,
    100,
    100
);
INSERT INTO ers_reimbursements (
    r_id,
    r_amount,
    r_description,
    r_submitted,
    u_id_author,
    u_id_resolver,
    rt_id,
    rs_id
) VALUES (
    2001,
    10.23,
    'travel stuff',
    CURRENT_TIMESTAMP,
    1000000,
    1000002,
    100,
    100
);
INSERT INTO ers_reimbursements (
    r_id,
    r_amount,
    r_description,
    r_submitted,
    u_id_author,
    u_id_resolver,
    rt_id,
    rs_id
) VALUES (
    2002,
    20.20,
    'bought stuff',
    CURRENT_TIMESTAMP,
    1000000,
    1000002,
    101,
    101
);
INSERT INTO ers_reimbursements (
    r_id,
    r_amount,
    r_description,
    r_submitted,
    u_id_author,
    u_id_resolver,
    rt_id,
    rs_id
) VALUES (
    2003,
    10.23,
    'ate stuff for lunch',
    CURRENT_TIMESTAMP,
    1000000,
    1000002,
    102,
    102
);
commit;
