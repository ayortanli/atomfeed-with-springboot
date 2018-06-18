insert into author(id, name, surname, country, last_update_time) values (AUTHOR_SEQ.nextval, 'William', 'Shakespeare', 'England', {ts '2018-06-01 00:00:00.00'});
insert into author(id, name, surname, country, last_update_time) values (AUTHOR_SEQ.nextval, 'Lev', 'Tolstoy', 'Russia', {ts '2018-06-01 00:00:00.00'});
insert into author(id, name, surname, country, last_update_time) values (AUTHOR_SEQ.nextval, 'Orhan', 'Kemal', 'Turkey', {ts '2018-06-01 00:00:00.00'});


insert into book_author(id, name, surname, country) values (1, 'William', 'Shakespeare', 'England');
insert into book_author(id, name, surname, country) values (2, 'Lev', 'Tolstoy', 'Russia');
insert into book_author(id, name, surname, country) values (3, 'Orhan', 'Kemal', 'Turkey');

insert into book(id, name, publish_year, author_id) values (BOOK_SEQ.nextval, 'Othello', 1603, 1);
insert into book(id, name, publish_year, author_id) values (BOOK_SEQ.nextval, 'Hamlet', 1600, 1);
insert into book(id, name, publish_year, author_id) values (BOOK_SEQ.nextval, 'War and Peace', 1869, 2);
insert into book(id, name, publish_year, author_id) values (BOOK_SEQ.nextval, 'Avare Yıllar', 1950, 3);
