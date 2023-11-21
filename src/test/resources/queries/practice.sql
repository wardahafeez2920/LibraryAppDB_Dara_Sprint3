SELECT B.name,BC.name
FROM books B
         INNER JOIN book_categories BC
                    ON B.book_category_id=BC.id;

-- US01 -1 - AA


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
where name = 'Naruto';

select name, isbn, year, author, description from books where name = 'Percy Jackson: The Sea of Monsters';
