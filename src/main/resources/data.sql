insert into author(id, name, surname, country) values (AUTHOR_SEQ.nextval, 'William', 'Shakespeare', 'England');
insert into author(id, name, surname, country) values (AUTHOR_SEQ.nextval, 'Lev', 'Tolstoy', 'Russia');
insert into author(id, name, surname, country) values (AUTHOR_SEQ.nextval, 'Orhan', 'Kemal', 'Turkey');


insert into book_author(id, name, surname, country) values (BOOK_AUTHOR_SEQ.nextval, 'William', 'Shakespeare', 'England');
insert into book_author(id, name, surname, country) values (BOOK_AUTHOR_SEQ.nextval, 'Lev', 'Tolstoy', 'Russia');
insert into book_author(id, name, surname, country) values (BOOK_AUTHOR_SEQ.nextval, 'Orhan', 'Kemal', 'Turkey');

insert into book(id, name, publish_year, author_id) values (BOOK_SEQ.nextval, 'Othello', 1603, 1);
insert into book(id, name, publish_year, author_id) values (BOOK_SEQ.nextval, 'Hamlet', 1600, 1);
insert into book(id, name, publish_year, author_id) values (BOOK_SEQ.nextval, 'War and Peace', 1869, 2);
insert into book(id, name, publish_year, author_id) values (BOOK_SEQ.nextval, 'Avare Yıllar', 1950, 3);
