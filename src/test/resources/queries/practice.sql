SELECT B.name,BC.name
FROM books B
         INNER JOIN book_categories BC
                    ON B.book_category_id=BC.id;

-- US01 -1 - AA
select count(id) from users;
select count(distinct id) from users;
select * from users;

-- US02 -1 -WH
select count(*) from book_borrow
where returned_date is null ;

-- US05 -1
select bc.name, count(*)
from book_borrow bb
         inner join books b on bb.book_id = b.id
         inner join book_categories bc on b.book_category_id = bc.id
group by name
order by 2 desc;

-- US06 -1 -SS
select * from books
where name in('Naruto', 'Percy Jackson: The Sea of Monsters');

select * from books
where name like ('Naruto%');

select name, isbn, year, author, description from books where name = 'Percy Jackson: The Sea of Monsters';


select description from books
where id = 21924;

--US06 -1 - KV
select full_name, b.name,bb.borrowed_data from users u
inner join book_borrow on u.id = bb.user.id
inner join books b on bb.book.id = b.id
where full_name = 'Test Student 1' and name= 'Head First Java'
order by 3 desc;
